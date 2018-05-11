package com.yunyihenkey.seller.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.auth.service.vo.authjwt.seller.AuthSellerUser;
import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopBankInfo;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopBusinessLog;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopTakeCashLog;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.service.SellerShopBankInfoService;
import com.yunyihenkey.seller.service.SellerShopBusinessLogService;
import com.yunyihenkey.seller.service.SellerShopService;
import com.yunyihenkey.seller.service.SellerShopTakeCashLogService;
import com.yunyihenkey.seller.web.vo.param.accountController.AddBankParam;
import com.yunyihenkey.seller.web.vo.param.accountController.GetBusinessLogListParam;
import com.yunyihenkey.seller.web.vo.param.accountController.GetShopInfoParam;
import com.yunyihenkey.seller.web.vo.param.accountController.GetTakeCashLogListParam;

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

	/**
	 * 
	 * @desc 查询店铺交易记录列表
	 * @auth wulm
	 * @date 2018年5月11日 上午11:59:42
	 */
	@PostMapping("getBusinessLogList")
	public Object getBusinessLogList(HttpServletRequest request, GetBusinessLogListParam param) throws Exception {

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

		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, list);
	}

	/**
	 * 
	 * @desc 查询店铺提现记录列表
	 * @auth wulm
	 * @date 2018年5月9日 下午5:00:15
	 */
	@PostMapping("getTakeCashLogList")
	public Object getTakeCashLogList(HttpServletRequest request, GetTakeCashLogListParam param) throws Exception {

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

		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, list);
	}

	/**
	 * 
	 * @desc 获取店铺信息
	 * @auth wulm
	 * @date 2018年5月9日 下午2:44:15
	 */
	@PostMapping("getShopInfo")
	public Object getShopInfo(HttpServletRequest request, @RequestBody GetShopInfoParam param) throws Exception {

		AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);

		SellerShop sellerShop = sellerShopService.selectByPrimaryKey(Long.valueOf(sellerUser.getShopId()));

		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, sellerShop);
	}

	/**
	 * 
	 * @desc 添加银行卡
	 * @auth wulm
	 * @date 2018年5月11日 下午12:04:39
	 */
	@PostMapping("addBank")
	public Object addBank(HttpServletRequest request, @RequestBody AddBankParam param) throws Exception {

		AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
		// sellerUser.get

		// 验证必填项
		String errorInfo = validatorUtils.validateAndGetErrorInfo(param, Default.class);
		if (StringUtils.isNotEmpty(errorInfo)) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo);
		}

		// 检查是否已经添加过相同银行卡
		if (sellerShopBankInfoService.isExistCard(sellerUser.getShopId(), param.getBankCardNumber())) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_EXIST);
		}

		// 添加银行卡
		SellerShopBankInfo sbi = new SellerShopBankInfo();
		sbi.setShopId(Long.valueOf(sellerUser.getShopId()));
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

}
