package com.yunyihenkey.seller.web.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyihenkey.auth.service.AuthJwtService;
import com.yunyihenkey.auth.service.UserService;
import com.yunyihenkey.auth.service.enums.LoginSourceEnum;
import com.yunyihenkey.common.constant.RedisConstant;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.base.SMSResult;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.user.DistributorType;
import com.yunyihenkey.seller.web.util.VerificationCodeUtils;
import com.yunyihenkey.seller.web.vo.param.userController.ForgetPasswordParam;
import com.yunyihenkey.seller.web.vo.param.userController.GetVerificationCodeParam;
import com.yunyihenkey.seller.web.vo.param.userController.LoginParam;
import com.yunyihenkey.seller.web.vo.param.userController.SignParam;
import com.yunyihenkey.seller.web.vo.param.userController.VerificationCodeParam;
import com.yunyihenkey.seller.web.vo.param.userController.VerificationCodeResult;

/**
 * 
 * @desc 匿名接口，不需要登录就可以访问的接口
 * @author wulm
 * @date 2018年5月10日 上午10:10:10
 * @version 1.0.0
 */

@RestController
@RequestMapping("anon")
public class AnonController extends BaseController {

	@Autowired
	private AuthJwtService authJwtService;
	@Autowired
	private VerificationCodeUtils verificationCodeUtils;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private ValidatorUtils validatorUtils;
	@Resource
	private UserService userService;

	/**
	 * 分销商平台登录
	 *
	 * @param loginParam
	 * @return
	 */
	@PostMapping("login")
	public ResultInfo<String> login(@RequestBody LoginParam loginParam) throws Exception {

		// 验证必填项
		String errorInfo = validatorUtils.validateAndGetErrorInfo(loginParam, Default.class);
		if (StringUtils.isNotEmpty(errorInfo)) {
			return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo, null);
		}

		// 验证码
		String vCationCode = loginParam.getvCationCode();
		String uid = loginParam.getUid();
		if (!redisUtil.hasKey(RedisConstant.REDIS_VERTI_CODE + DigestUtils.md5Hex(uid + vCationCode))) {
			return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.LOGIN_VERI_CODE_FAIL);
		}

		// 设置生成TOKEN返回客户端
		return authJwtService.createToken(loginParam.getUserName(), loginParam.getPassword(), SystemCodeEnum.SELLER,
				LoginSourceEnum.getByValue(loginParam.getLoginSourceEnum()));
	}

	@GetMapping("veriCode")
	public ResultInfo<VerificationCodeResult> veriCode() throws Exception {
		VerificationCodeResult verificationCodeResult = new VerificationCodeResult();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String text = verificationCodeUtils.createText();
		LogUtils.getLogger().info(LogUtils.getString("登录验证码uid:", uuid, ",登录验证码:", text));
		String veriCode = verificationCodeUtils.getGenerteImageBase64Code(text);
		// 存储再redis中规则是： UUID+验证码 MD5加密
		redisUtil.set(RedisConstant.REDIS_VERTI_CODE + DigestUtils.md5Hex(uuid + text), text, 180); // 3分钟
		verificationCodeResult.setUid(uuid);
		verificationCodeResult.setVriCode(veriCode);
		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, verificationCodeResult);

	}

	/**
	 * 检测验证码和推广编码
	 * 
	 * @param verificationCodeParam
	 * @return
	 */
	@PostMapping("/verificationCode")
	public ResultInfo<DistributorType> verificationCode(@RequestBody VerificationCodeParam verificationCodeParam) {
		String extensionCoding = verificationCodeParam.getExtensionCoding();
		DistributorType distributorType = new DistributorType();
		final Integer type = 1;
		if (extensionCoding == null) {
			distributorType = userService.signUpV1(verificationCodeParam.getPhoneNumber(), type,
					verificationCodeParam.getCode());
		} else if (extensionCoding.length() == 8) {
			distributorType = userService.signUpV2(verificationCodeParam.getPhoneNumber(), type,
					verificationCodeParam.getCode(), extensionCoding);
		} else if (extensionCoding.length() == 6) {
			distributorType = userService.signUp(verificationCodeParam.getPhoneNumber(), type,
					verificationCodeParam.getCode(), extensionCoding);
		}
		if (distributorType.getRes() == 0) {
			return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.SUCCESS, distributorType.getKey());
		}
		if (distributorType.getRes() == 1) {
			return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.ERROR, "未获取验证码或者验证码失效!");
		}
		if (distributorType.getRes() == 2) {
			return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.ERROR, "验证码错误!");
		}
		if (distributorType.getRes() == 3) {
			return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.ERROR, "推广编码错误!");
		}
		return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.ERROR, "A级分销商注册码错误!");
	}

	/**
	 * 获取验证码 getVerificationCodeParam
	 */
	@PostMapping("/getVerificationCode")
	public ResultInfo getVerificationCode(@RequestBody GetVerificationCodeParam getVerificationCodeParam) {
		int i = userService.verificationPhone(getVerificationCodeParam.getPhoneNumber());
		if (i != 0) {
			return new ResultInfo(SystemCodeEnum.AUTH, CodeEnum.ERROR, "该手机号码已经注册!");
		}

		SMSResult smsResult = userService.verificationCode(getVerificationCodeParam.getPhoneNumber(),
				getVerificationCodeParam.getType());

		if (smsResult.getResulltMesseage().equals("OK")) {
			return new ResultInfo(SystemCodeEnum.AUTH, CodeEnum.SUCCESS);
		}
		return new ResultInfo(SystemCodeEnum.AUTH, CodeEnum.ERROR, smsResult.getResulltMesseage());
	}

	/**
	 * 注册
	 * 
	 * @param signParam
	 * @return
	 */
	@PostMapping("/signUp")
	public ResultInfo signUp(@RequestBody SignParam signParam, HttpServletRequest request) {

		boolean flag = userService.saveUser(signParam.getPassword(), signParam.getKey(), signParam.getCityCode(),
				request);
		if (flag) {
			return new ResultInfo(SystemCodeEnum.AUTH, CodeEnum.SUCCESS);
		}
		return new ResultInfo(SystemCodeEnum.AUTH, CodeEnum.ERROR, "注册请求超时!");
	}

	/**
	 * 忘记密码
	 * 
	 * @param forgetPasswordParam
	 * @return
	 */

	@PostMapping("/forgetPassword")
	public ResultInfo forgetPassword(@RequestBody ForgetPasswordParam forgetPasswordParam) {
		int i = userService.verificationPhone(forgetPasswordParam.getUserName());
		if (i == 0) {
			return new ResultInfo(SystemCodeEnum.AUTH, CodeEnum.ERROR, "该手机号码还没有注册!");
		}
		authJwtService.LoginoutAllUpdPwd(forgetPasswordParam.getUserName(), SystemCodeEnum.AUTH);
		userService.forgetPassword(forgetPasswordParam.getUserName(), forgetPasswordParam.getPassword());

		return new ResultInfo(SystemCodeEnum.AUTH, CodeEnum.SUCCESS);
	}

}
