package com.yunyihenkey.seller.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.auth.service.util.PasswordUtil;
import com.yunyihenkey.auth.service.vo.authjwt.seller.AuthSellerUser;
import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopBankInfo;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopBusinessLog;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopTakeCashLog;
import com.yunyihenkey.common.constant.SMSTemplateEnum;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.base.SMSResult;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.AddBankCardParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetBankListResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetBusinessLogListParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetShopInfoResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetTakeCashLogListParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetVerifyCodeSetPayPwdParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.IsSetPayPwdResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.SetPayPwdParam;
import com.yunyihenkey.seller.service.SellerShopBankInfoService;
import com.yunyihenkey.seller.service.SellerShopBusinessLogService;
import com.yunyihenkey.seller.service.SellerShopService;
import com.yunyihenkey.seller.service.SellerShopTakeCashLogService;
import com.yunyihenkey.seller.service.UserService;

/**
 * 
 * @desc 账户模块
 * @author wulm
 * @date 2018年5月8日 下午4:14:10
 * @version 1.0.0
 */
@RestController
@RequestMapping("account")
public class AccountController extends BaseController {

	@Autowired
	private ValidatorUtils validatorUtils;
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private SellerShopService sellerShopService;
	@Autowired
	private SellerShopTakeCashLogService sellerShopTakeCashLogService;
	@Autowired
	private SellerShopBusinessLogService sellerShopBusinessLogService;
	@Autowired
	private SellerShopBankInfoService sellerShopBankInfoService;
	@Autowired
	private UserService userService;

	/**
	 * 
	 * @desc 查询店铺交易记录列表
	 * @auth wulm
	 * @date 2018年5月11日 上午11:59:42
	 */
	@PostMapping("getBusinessLogList")
	public Object getBusinessLogList(HttpServletRequest request, @RequestBody GetBusinessLogListParam param)
			throws Exception {

		// 验证必填项
		String errorInfo = validatorUtils.validateAndGetErrorInfo(param, Default.class);
		if (StringUtils.isNotEmpty(errorInfo)) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo);
		}

		// 获取当前登录用户信息
		AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);

		// 查询分页
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<SellerShopBusinessLog> list = sellerShopBusinessLogService
				.getByShopId(Long.valueOf(sellerUser.getShopId()));

		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, new PageInfo<>(list));
	}

	/**
	 * 
	 * @desc 查询店铺提现记录列表
	 * @auth wulm
	 * @date 2018年5月9日 下午5:00:15
	 */
	@PostMapping("getTakeCashLogList")
	public Object getTakeCashLogList(HttpServletRequest request, @RequestBody GetTakeCashLogListParam param)
			throws Exception {

		// 验证必填项
		String errorInfo = validatorUtils.validateAndGetErrorInfo(param, Default.class);
		if (StringUtils.isNotEmpty(errorInfo)) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo);
		}

		// 获取当前登录用户信息
		AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);

		// 查询分页
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<SellerShopTakeCashLog> list = sellerShopTakeCashLogService
				.getByShopId(Long.valueOf(sellerUser.getShopId()));

		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, new PageInfo<>(list));
	}

	/**
	 * 
	 * @desc 获取店铺信息
	 * @auth wulm
	 * @date 2018年5月9日 下午2:44:15
	 */
	@GetMapping("getShopInfo")
	public Object getShopInfo(HttpServletRequest request) throws Exception {

		AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);

		GetShopInfoResult getShopInfoResult = sellerShopService
				.getShopInfoForAccount(Long.valueOf(sellerUser.getShopId()));

		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, getShopInfoResult);
	}

	/**
	 * 
	 * @desc 获取银行卡号列表
	 * @auth wulm
	 * @date 2018年5月17日 下午2:08:37
	 */
	@GetMapping("getBankCardList")
	public Object getBankCardList(HttpServletRequest request) throws Exception {

		AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);

		Long shopId = Long.valueOf(sellerUser.getShopId());

		List<GetBankListResult> sellerShopBankInfos = sellerShopBankInfoService.getBankCardList(shopId);

		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, sellerShopBankInfos);
	}

	/**
	 * 
	 * @desc 添加银行卡
	 * @auth wulm
	 * @date 2018年5月11日 下午12:04:39
	 */
	@PostMapping("addBankCard")
	public Object addBankCard(HttpServletRequest request, @RequestBody AddBankCardParam param) throws Exception {

		// 验证必填项
		String errorInfo = validatorUtils.validateAndGetErrorInfo(param, Default.class);
		if (StringUtils.isNotEmpty(errorInfo)) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo);
		}
		AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);

		Long shopId = Long.valueOf(sellerUser.getShopId());

		// 检查是否已经添加过相同银行卡
		if (sellerShopBankInfoService.isExistCard(shopId, param.getBankCardNumber())) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_EXIST);
		}

		// 添加银行卡
		SellerShopBankInfo sbi = new SellerShopBankInfo();
		sbi.setShopId(shopId);
		sbi.setBankUserName(param.getBankUserName());
		sbi.setBankName(param.getBankName());
		sbi.setBankAddress(param.getBankAddress());
		sbi.setBankCardNumber(param.getBankCardNumber());
		Date curDate = new Date();
		sbi.setCreateTime(curDate);
		sbi.setUpdateTime(curDate);

		sellerShopBankInfoService.insert(sbi);

		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	/**
	 * 
	 * @desc 查询店铺是否设置了支付密码
	 * @auth wulm
	 * @date 2018年5月11日 下午7:04:34
	 */
	@GetMapping("isSetPayPwd")
	public Object isSetPayPwd(HttpServletRequest request) throws Exception {

		AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);

		boolean b = sellerShopService.isSetPayPwd(Long.valueOf(sellerUser.getShopId()));

		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, new IsSetPayPwdResult(b));
	}

	/**
	 * 
	 * @desc 首次设置支付密码时获取短信验证码
	 * @auth wulm
	 * @date 2018年5月12日 下午3:32:44
	 */
	@PostMapping("getVerifyCodeSetPayPwd")
	public Object getVerifyCodeSetPayPwd(HttpServletRequest request, @RequestBody GetVerifyCodeSetPayPwdParam param)
			throws Exception {

		// 验证必填项
		String errorInfo = validatorUtils.validateAndGetErrorInfo(param, Default.class);
		if (StringUtils.isNotEmpty(errorInfo)) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo);
		}

		AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);

		// 员工是否设置了支付密码
		if (sellerShopService.isSetPayPwd(Long.valueOf(sellerUser.getShopId()))) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "已经设置过支付密码，请勿重复操作");
		}

		// 发送设置支付密码的短信验证码
		SMSResult smsResult = userService.verificationCode(param.getPayPasswordMobile(),
				SMSTemplateEnum.SET_PAY_PWD_FIRST);

		if (smsResult.isResult()) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
		} else {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR);
		}

	}

	/**
	 * 
	 * @desc 首次设置支付密码
	 * @auth wulm
	 * @date 2018年5月12日 下午3:03:26
	 */
	@PostMapping("setPayPwd")
	public Object setPayPwd(HttpServletRequest request, @RequestBody SetPayPwdParam param) throws Exception {

		// 验证必填项
		String errorInfo = validatorUtils.validateAndGetErrorInfo(param, Default.class);
		if (StringUtils.isNotEmpty(errorInfo)) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo);
		}

		AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);

		// 员工是否设置了支付密码
		Long shopId = Long.valueOf(sellerUser.getShopId());
		if (sellerShopService.isSetPayPwd(shopId)) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "已经设置过支付密码，请勿重复操作");
		}

		int i = userService.checkoutCode(param.getPayPasswordMobile(), SMSTemplateEnum.SET_PAY_PWD_FIRST.getValue(),
				param.getVerifyCode());
		switch (i) {
		case 0:
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "验证码失效,请重新获取!");
		case 2:
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "验证码错误!");
		case 1:
			// 进行设置支付密码
			SellerShop sellerShop = new SellerShop();
			sellerShop.setId(shopId);
			sellerShop.setPayPassword(PasswordUtil.encodePayPassword(sellerUser.getShopId(), param.getPayPassword()));
			sellerShop.setPayPasswordMobile(param.getPayPasswordMobile());

			sellerShopService.updateByPrimaryKeySelective(sellerShop);

			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
		default:
			throw new IllegalArgumentException("系统异常");
		}

	}

}
