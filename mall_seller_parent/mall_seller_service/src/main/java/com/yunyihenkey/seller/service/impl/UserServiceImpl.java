package com.yunyihenkey.seller.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yunyihenkey.common.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunyihenkey.auth.service.enums.Increment;
import com.yunyihenkey.auth.service.util.PasswordUtil;
import com.yunyihenkey.basedao.malldb.basemapper.SellerRegistrationCodeBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerShopBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerUserBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerRegistrationCode;
import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.basedao.malldb.basevo.SellerUser;
import com.yunyihenkey.common.constant.MessageServer;
import com.yunyihenkey.common.constant.SMSTemplateEnum;
import com.yunyihenkey.common.idworker.IdWorker;
import com.yunyihenkey.common.utils.JacksonUtils;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.vo.base.SMSResult;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerRegistrationCodeMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerShopMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerUserMapper;
import com.yunyihenkey.seller.dao.malldb.vo.result.user.DistributorTypeResult;
import com.yunyihenkey.seller.service.UserService;

/**
 * @date 2018/5/3 10:45
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private RedisUtil redisUtil;

	@Autowired(required = false)
	private AuthSellerUserMapper userMapper;

	@Resource
	private SellerUserBaseMapper sellerUserBaseMapper;

	@Autowired
	private IdWorker idWorker;

	@Resource
	private SellerShopBaseMapper sellerShopBaseMapper;

	@Resource
	private AuthSellerRegistrationCodeMapper authSellerRegistrationCodeMapper;

	@Resource
	private SellerRegistrationCodeBaseMapper sellerRegistrationCodeBaseMapper;

	@Resource
	private AuthSellerShopMapper authSellerShopMapper;

	@Resource
	private MessageServer messageServer;

	// 分销商等级 0:A ;1:B ;2:C
	// res;//0:验证成功 1:未获取验证码或者验证码失效 2:验证码错误 3:推广编码错误 4:A级分销商注册码错误
	@Override // 有推广编码调用
	public DistributorTypeResult signUp(String phoneNumber, Integer type, String code, String extensionCoding) {
		String mark = UUID.randomUUID().toString().replace("-", "");
		DistributorTypeResult distributorTypeResult = new DistributorTypeResult();
		String key = type + phoneNumber;
		String oldCode = (String) redisUtil.get(key);
		// String extensionCode = (String) redisUtil.get(phoneNumber);

		distributorTypeResult.setKey(mark);
		distributorTypeResult.setPhoneNumber(phoneNumber);
		// 短信验证码存在
		if (oldCode != null) {
			// 短信验证码正确
			if (oldCode.equals(code)) {
				// 根据推广编码查询对应的店铺
				SellerShop sellerShop = sellerShopBaseMapper
						.selectByPrimaryKey(Long.parseLong(extensionCoding.toLowerCase(), Character.MAX_RADIX));
				// 店铺不存在 表示推广码错误
				if (sellerShop == null) {
					distributorTypeResult.setRes(3);
					return distributorTypeResult;
				}
				// 验证成功 返回0
				distributorTypeResult.setRes(0);
				// 判断推广码所属店铺等级
				if (sellerShop.getSellerGrade() == 0) {
					distributorTypeResult.setType(1);
				} else if (sellerShop.getSellerGrade() == 1) {
					distributorTypeResult.setType(2);
				}
				distributorTypeResult.setParentShopId(sellerShop.getParentShopId());
				String s = JacksonUtils.writeValueAsString(distributorTypeResult);
				redisUtil.set(RedisConstant.CACHE_SIGNUP_MESSAGE + mark, s, 60 * 30);
				redisUtil.expire(key, 0);
				return distributorTypeResult;
				// 绑定上级分销商逻辑
			}
			distributorTypeResult.setRes(2);
			return distributorTypeResult;
		}
		distributorTypeResult.setRes(1);
		return distributorTypeResult;
	}

	// 没有推广编码调用
	@Override
	public DistributorTypeResult signUpV1(String phoneNumber, Integer type, String code) {
		String mark = UUID.randomUUID().toString().replace("-", "");
		String messageKey = getMessageType(type);
		DistributorTypeResult distributorTypeResult = new DistributorTypeResult();
		// String key = type + phoneNumber;
		// String oldCode = (String) redisUtil.get(key);
		// String extensionCode = (String) redisUtil.get(phoneNumber);
		distributorTypeResult.setKey(mark);
		distributorTypeResult.setPhoneNumber(phoneNumber);
		int i = checkoutCode(phoneNumber, type, code);
		if (i == 1) {
			distributorTypeResult.setType(1);// 分销商等级 0:A ;1:B ;2:C
			distributorTypeResult.setRes(0);// res;//0:验证成功 1:未获取验证码或者验证码失效 2:验证码错误 3:推广编码错误 4:A级分销商注册码错误
			String s = JacksonUtils.writeValueAsString(distributorTypeResult);
			redisUtil.set(RedisConstant.CACHE_SIGNUP_MESSAGE + mark, s, 60 * 30);
			redisUtil.expire(messageKey + phoneNumber, 0);
			return distributorTypeResult;
		}
		if (i == 2) {
			distributorTypeResult.setRes(2);
			return distributorTypeResult;
		}
		distributorTypeResult.setRes(1);
		return distributorTypeResult;
	}

	// 注册编码调用
	@Override
	public DistributorTypeResult signUpV2(String phoneNumber, Integer type, String code, String extensionCoding) {
		String mark = UUID.randomUUID().toString().replace("-", "");
		DistributorTypeResult distributorTypeResult = new DistributorTypeResult();
		String messageKey = getMessageType(type);
		String key = messageKey + phoneNumber;
		String oldCode = (String) redisUtil.get(key);
		// String extensionCode = (String) redisUtil.get(phoneNumber);
		distributorTypeResult.setKey(mark);
		distributorTypeResult.setPhoneNumber(phoneNumber);
		if (oldCode != null) {
			if (oldCode.equals(code)) {
				SellerRegistrationCode sellerRegistrationCode = sellerRegistrationCodeBaseMapper
						.selectByPrimaryKey(extensionCoding.toLowerCase());
				if (sellerRegistrationCode != null && sellerRegistrationCode.getApply() == 0) {
					distributorTypeResult.setType(0);// 分销商等级 0:A ;1:B ;2:C
					distributorTypeResult.setRes(0);// res;//0:验证成功 1:未获取验证码或者验证码失效 2:验证码错误 3:推广编码错误 4:A级分销商注册码错误
					String s = JacksonUtils.writeValueAsString(distributorTypeResult);
					redisUtil.set(RedisConstant.CACHE_SIGNUP_MESSAGE + mark, s, 60 * 30);
					redisUtil.expire(key, 0);
					sellerRegistrationCode.setApply(1);
					sellerRegistrationCodeBaseMapper.updateByPrimaryKeySelective(sellerRegistrationCode);
					return distributorTypeResult;
				}
				distributorTypeResult.setRes(4);
				return distributorTypeResult;
			}
			distributorTypeResult.setRes(2);
			return distributorTypeResult;
		}
		distributorTypeResult.setRes(1);
		return distributorTypeResult;
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param phoneNumber
	 * @param smsTemplateEnum
	 * @return
	 */
	@Override
	public SMSResult verificationCode(String phoneNumber, SMSTemplateEnum smsTemplateEnum) {
		String messageKey = getMessageType(smsTemplateEnum.getValue());
		boolean b = redisUtil.hasKey(messageKey + phoneNumber);
		SMSResult smsResult = new SMSResult();
		if (b) {
			smsResult.setResult(true);
			smsResult.setCode((String) redisUtil.get(messageKey + phoneNumber));
			return smsResult;
		}
		smsResult = messageServer.sendMessage(phoneNumber, smsTemplateEnum);
		if (smsResult.getCode() != null) {
			redisUtil.set(messageKey + phoneNumber, smsResult.getCode(), 60 * 15);
		}
		return smsResult;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public boolean saveUser(String password, String key, String cityCode,String provinceCode ,HttpServletRequest request) {
		String o = (String) redisUtil.get(RedisConstant.CACHE_SIGNUP_MESSAGE + key);
		ObjectMapper mapper = new ObjectMapper();
		DistributorTypeResult distributorTypeResult = null;
		try {
			distributorTypeResult = mapper.readValue(o, DistributorTypeResult.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (distributorTypeResult != null) {
			long userId = idWorker.nextId();
			SellerShop shop = new SellerShop();
			shop.setSellerGrade(distributorTypeResult.getType());
			shop.setParentShopId(distributorTypeResult.getParentShopId());
			shop.setUserId(userId);
			authSellerShopMapper.insertSelective(shop);
			SellerShop shop1 = new SellerShop();
			long shopId = shop.getId();
			int port = request.getServerPort();
			shop1.setId(shopId);
			if (port == 80) {
				shop1.setUrl("http://" + request.getServerName() + "/" + shopId);
			} else {
				shop1.setUrl("http://" + request.getServerName() + ":" + port + "/" + shopId);
			}
			shop1.setCreateTime(new Timestamp(System.currentTimeMillis()));
			sellerShopBaseMapper.updateByPrimaryKeySelective(shop1);
			SellerUser user = new SellerUser();
			String phoneNumber = distributorTypeResult.getPhoneNumber();
			String encryptionPassword = PasswordUtil.enCode(phoneNumber, password);
			user.setId(userId);
			user.setUserName(phoneNumber);
			user.setPassword(encryptionPassword);
			user.setCityCode(cityCode);
			user.setProvinceCode(provinceCode);
			user.setMobile(phoneNumber);
			user.setShopId(shopId);
			user.setSellerGrade(distributorTypeResult.getType());
			// user.setSellerGrade(distributorTypeResult.getType());
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			redisUtil.expire(key, 0);
			Integer countNewMembers = (Integer) redisUtil.get(Increment.CountNewMembers.getText());
			if (countNewMembers == null) {
				countNewMembers = 0;
			}
			redisUtil.set(Increment.CountNewMembers.getText(), countNewMembers + 1);
			sellerUserBaseMapper.insertSelective(user);
			return true;
		}
		return false;

	}

	@Override
	public int verificationPhone(String phoneNumber) {
		return userMapper.verificationPhone(phoneNumber);
	}

	@Override
	public int forgetPassword(String userName, String newPassword) {
		SellerUser user = userMapper.selectByUserName(userName);
		user.setId(user.getId());
		user.setPassword(PasswordUtil.enCode(userName, newPassword));
		user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		sellerUserBaseMapper.updateByPrimaryKeySelective(user);
		return 0;
	}

	@Override
	public void addRegistrationCode(List set) {
		authSellerRegistrationCodeMapper.insertBatch(set);
	}

	@Override
	public int checkoutCode(String phoneNumber, Integer type, String code) {
		String messageType = getMessageType(type);
		String key = messageType + phoneNumber;
		String oldCode = (String) redisUtil.get(key);
		if (oldCode == null) {
			return 0;
		}
		if (oldCode.equals(code)) {
			return 1;
		}
		return 2;
	}

	private String getMessageType(Integer type){
		switch (type){
			case 1:
				return RedisConstant.SIGN_UP_VERICODE;
			case 2:
				return RedisConstant.FORGET_PASSWORD;
		}
		return null;
	}

}
