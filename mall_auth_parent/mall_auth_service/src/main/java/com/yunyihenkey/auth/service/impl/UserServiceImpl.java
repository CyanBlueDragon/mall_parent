package com.yunyihenkey.auth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunyihenkey.auth.service.UserService;
import com.yunyihenkey.basedao.malldb.basemapper.SellerRegistrationCodeBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerShopBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerUserBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerRegistrationCode;
import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.basedao.malldb.basevo.SellerUser;
import com.yunyihenkey.common.constant.MessageServerFactory;
import com.yunyihenkey.common.idworker.IdWorker;
import com.yunyihenkey.common.utils.JacksonUtils;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.vo.base.SMSResult;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerRegistrationCodeMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerShopMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerUserMapper;
import com.yunyihenkey.seller.dao.malldb.vo.user.DistributorType;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

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

	//分销商等级 0:A ;1:B ;2:C
	//res;//0:验证成功 1:未获取验证码或者验证码失效 2:验证码错误 3:推广编码错误 4:A级分销商注册码错误
	@Override //有推广编码调用
	public DistributorType signUp(String phoneNumber, Integer type, String code, String extensionCoding) {
		String mark = UUID.randomUUID().toString().replace("-", "");
		DistributorType distributorType = new DistributorType();
		String key = type + phoneNumber;
		String oldCode = (String) redisUtil.get(key);
		//String extensionCode = (String) redisUtil.get(phoneNumber);

		distributorType.setKey(mark);
		distributorType.setPhoneNumber(phoneNumber);
		//短信验证码存在
		if (oldCode != null) {
			//短信验证码正确
			if (oldCode.equals(code)) {
				//根据推广编码查询对应的店铺
				SellerShop sellerShop = sellerShopBaseMapper.selectByPrimaryKey(Long.parseLong(extensionCoding, Character.MAX_RADIX));
				//店铺不存在 表示推广码错误
				if (sellerShop == null) {
					distributorType.setRes(3);
					return distributorType;
				}
				//验证成功 返回0
				distributorType.setRes(0);
				//判断推广码所属店铺等级
				if (sellerShop.getSellerGrade() == 0) {
					distributorType.setType(1);
				}else if(sellerShop.getSellerGrade() == 1){
					distributorType.setType(2);
				}
				distributorType.setParentShopId(sellerShop.getParentShopId());
				String s = JacksonUtils.writeValueAsString(distributorType);
				redisUtil.set(mark, s, 60 * 30);
				redisUtil.expire(key,0);
				return distributorType;
				//绑定上级分销商逻辑
			}
			distributorType.setRes(2);
			return distributorType;
		}
		distributorType.setRes(1);
		return distributorType;
	}

	//没有推广编码调用
	@Override
	public DistributorType signUpV1(String phoneNumber, Integer type, String code) {
		String mark = UUID.randomUUID().toString().replace("-", "");
		DistributorType distributorType = new DistributorType();
		String key = type + phoneNumber;
		String oldCode = (String) redisUtil.get(key);
		//String extensionCode = (String) redisUtil.get(phoneNumber);
		distributorType.setKey(mark);
		distributorType.setPhoneNumber(phoneNumber);
		if (oldCode != null) {
			if (oldCode.equals(code)) {
				distributorType.setType(1);//分销商等级 0:A ;1:B ;2:C
				distributorType.setRes(0);//res;//0:验证成功 1:未获取验证码或者验证码失效 2:验证码错误 3:推广编码错误 4:A级分销商注册码错误
				String s = JacksonUtils.writeValueAsString(distributorType);
				redisUtil.set(mark, s, 60 * 30);
				redisUtil.expire(key,0);
				return distributorType;
			}
			distributorType.setRes(2);
			return distributorType;
		}
		distributorType.setRes(1);
		return distributorType;
	}

	//注册编码调用
	@Override
	public DistributorType signUpV2(String phoneNumber, Integer type, String code, String extensionCoding) {
		String mark = UUID.randomUUID().toString().replace("-", "");
		DistributorType distributorType = new DistributorType();
		String key = type + phoneNumber;
		String oldCode = (String) redisUtil.get(key);
		//String extensionCode = (String) redisUtil.get(phoneNumber);
		distributorType.setKey(mark);
		distributorType.setPhoneNumber(phoneNumber);
		if (oldCode != null) {
			if (oldCode.equals(code)) {
				SellerRegistrationCode sellerRegistrationCode = sellerRegistrationCodeBaseMapper.selectByPrimaryKey(extensionCoding);
				if (sellerRegistrationCode != null && sellerRegistrationCode.getApply() == 0){
					distributorType.setType(0);//分销商等级 0:A ;1:B ;2:C
					distributorType.setRes(0);//res;//0:验证成功 1:未获取验证码或者验证码失效 2:验证码错误 3:推广编码错误 4:A级分销商注册码错误
					String s = JacksonUtils.writeValueAsString(distributorType);
					redisUtil.set(mark, s, 60 * 30);
					redisUtil.expire(key,0);
					sellerRegistrationCode.setApply(1);
					sellerRegistrationCodeBaseMapper.updateByPrimaryKeySelective(sellerRegistrationCode);
					return distributorType;
				}
				distributorType.setRes(4);
				return distributorType;
			}
			distributorType.setRes(2);
			return distributorType;
		}
		distributorType.setRes(1);
		return distributorType;
	}

	@Override
	public SMSResult verificationCode(String phoneNumber, Integer type) {
		SMSResult smsResult = MessageServerFactory.buildMessageServer().sendMessage(phoneNumber, type);
		if (smsResult.getCode() != null ){
			redisUtil.set(type + phoneNumber,smsResult.getCode(),60*15);
		}
		return smsResult;
	}



	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public boolean saveUser(String password, String key, String cityCode,HttpServletRequest request) {
		String o = (String)redisUtil.get(key);
		ObjectMapper mapper = new ObjectMapper();
		DistributorType distributorType = null;
		try {
			distributorType = mapper.readValue(o, DistributorType.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (distributorType != null) {
			long userId = idWorker.nextId();
			SellerShop shop =  new SellerShop();
			shop.setSellerGrade(distributorType.getType());
			shop.setParentShopId(distributorType.getParentShopId());

			shop.setUserId(userId);
			authSellerShopMapper.insertSelective(shop);
			SellerShop shop1 = new SellerShop();
			long shopId = shop.getId();
			int port  = request.getServerPort();
			shop1.setId(shopId);
			if (port == 80) {
				shop1.setUrl("http://"+request.getServerName() + "/" + shopId);
			}else {
				shop1.setUrl("http://"+request.getServerName()+":"+port + "/" + shopId);
			}
			shop1.setCreateTime(new Timestamp(System.currentTimeMillis()));
			sellerShopBaseMapper.updateByPrimaryKeySelective(shop1);
			SellerUser user = new SellerUser();
			String phoneNumber = distributorType.getPhoneNumber() ;
			String encryptionPassword = DigestUtils.md5Hex(phoneNumber + password);

			user.setId(userId);
			user.setUserName(phoneNumber);
			user.setPassword(encryptionPassword);
			user.setCityCode(cityCode);
			user.setMobile(phoneNumber);
			user.setShopId(shopId);
			user.setSellerGrade(distributorType.getType());
			//user.setSellerGrade(distributorType.getType());
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			redisUtil.expire(key, 0);
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
		user.setPassword(DigestUtils.md5Hex(userName + newPassword));

		user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		sellerUserBaseMapper.updateByPrimaryKeySelective(user);


		return 0;
	}


	@Override
	public void addRegistrationCode(List set) {
		authSellerRegistrationCodeMapper.insertBatch(set);
	}


}
