package com.yunyihenkey.seller.web.controller.pc;

import java.util.HashMap;
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
import com.yunyihenkey.auth.service.enums.RequestSourceEnum;
import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.common.constant.MallConstants;
import com.yunyihenkey.common.constant.RedisConstant;
import com.yunyihenkey.common.constant.SMSTemplateEnum;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.base.SMSResult;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.ForgetPasswordParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.GetVerificationCodeParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.LoginParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.SignParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.VerificationCodeParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.VerificationCodeResult;
import com.yunyihenkey.seller.dao.malldb.vo.result.user.DistributorTypeResult;
import com.yunyihenkey.seller.service.UserService;
import com.yunyihenkey.seller.web.util.VerificationCodeUtils;

/**
 * 
 * @desc 匿名接口，不需要登录就可以访问的接口
 * @author wulm
 * @date 2018年5月10日 上午10:10:10
 * @version 1.0.0
 */

@RestController
@RequestMapping("/anon/")
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
	@Autowired
	private JwtUtils jwtUtils;

	/**
	 * 分销商平台登录
	 *
	 * @param loginParam
	 * @return
	 */
	@PostMapping("login")
	public Object login(@RequestBody LoginParam loginParam, HttpServletRequest request) throws Exception {

		// 验证必填项
		String errorInfo = validatorUtils.validateAndGetErrorInfo(loginParam, Default.class);
		if (StringUtils.isNotEmpty(errorInfo)) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo);
		}
		RequestSourceEnum reqSourceEnum =jwtUtils.getHeaderReqSource(request);
		// 验证码
		String vCationCode = loginParam.getvCationCode();
		String uid = loginParam.getUid();
		String keyR=RedisConstant.REDIS_VERTI_CODE + DigestUtils.md5Hex(uid + vCationCode);
		if (!redisUtil.hasKey(keyR)) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.LOGIN_VERI_CODE_FAIL);
		}
		redisUtil.del(keyR);
		// 设置生成TOKEN返回客户端
		return authJwtService.createToken(loginParam.getUserName(), loginParam.getPassword(), SystemCodeEnum.SELLER,
				reqSourceEnum);

	}

	@GetMapping("veriCode")
	public ResultInfo<VerificationCodeResult> veriCode() throws Exception {
		VerificationCodeResult verificationCodeResult = new VerificationCodeResult();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String text = verificationCodeUtils.createText();
		LogUtils.getLogger().debug(LogUtils.getString("登录验证码uid:", uuid, ",登录验证码:", text));
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
	@PostMapping("verificationCode")
	public ResultInfo<HashMap<String, String>> verificationCode(
			@RequestBody VerificationCodeParam verificationCodeParam) {
		String extensionCoding = verificationCodeParam.getExtensionCoding();
		DistributorTypeResult distributorTypeResult = new DistributorTypeResult();
		final Integer type = SMSTemplateEnum.UserRegistration.getValue();
		if (extensionCoding == null || extensionCoding.equals("")) {
			distributorTypeResult = userService.signUpV1(verificationCodeParam.getPhoneNumber(), type,
					verificationCodeParam.getCode());
		} else if (extensionCoding.length() == 8) {
			distributorTypeResult = userService.signUpV2(verificationCodeParam.getPhoneNumber(), type,
					verificationCodeParam.getCode(), extensionCoding);
		} else if (extensionCoding.length() == 6) {
			distributorTypeResult = userService.signUp(verificationCodeParam.getPhoneNumber(), type,
					verificationCodeParam.getCode(), extensionCoding);
		}
		if (distributorTypeResult.getRes() == 0) {
			HashMap<String, String> map = new HashMap<>();
			map.put("key", distributorTypeResult.getKey());
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, "操作成功", map);
		}
		if (distributorTypeResult.getRes() == 1) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "未获取验证码或者验证码失效!");
		}
		if (distributorTypeResult.getRes() == 2) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "验证码错误!");
		}
		if (distributorTypeResult.getRes() == 3) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "推广编码错误!");
		}
		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "A级分销商注册码错误!");
	}

	/**
	 * 获取验证码 getVerificationCodeParam
	 */
	@PostMapping("getVerificationCode")
	public ResultInfo getVerificationCode(@RequestBody GetVerificationCodeParam getVerificationCodeParam) {
		int i = userService.verificationPhone(getVerificationCodeParam.getPhoneNumber());
		if (i != 0) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该手机号码已经注册!");
		}

		SMSResult smsResult = userService.verificationCode(getVerificationCodeParam.getPhoneNumber(),
				SMSTemplateEnum.UserRegistration);

		if (smsResult.isResult()) {
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
		}
		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR);
	}

	/**
	 * 注册
	 * 
	 * @param signParam
	 * @return
	 */
	@PostMapping("signUp")
	public ResultInfo signUp(@RequestBody SignParam signParam, HttpServletRequest request) {

		boolean flag = userService.saveUser(signParam.getPassword(), signParam.getKey(), signParam.getCityCode(),
				signParam.getProvinceCode(), request);
		if (flag) {
			return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
		}
		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "注册请求超时!");
	}

	/**
	 * 修改密码
	 * 
	 * @param forgetPasswordParam
	 * @return
	 */
	@PostMapping("forgetPassword")
	public ResultInfo forgetPassword(@RequestBody ForgetPasswordParam forgetPasswordParam) {
		int i = userService.verificationPhone(forgetPasswordParam.getUserName());
		if (i == 0) {
			return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该手机号码还没有注册!");
		}
		boolean flag = (boolean) redisUtil.get(RedisConstant.FORGET_PASSWORD + forgetPasswordParam.getKey());
		if( !flag ){
			return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.ERROR, "请求参数错误!");
		}
		authJwtService.LoginoutAllUpdPwd(forgetPasswordParam.getUserName(), SystemCodeEnum.SELLER);
		userService.forgetPassword(forgetPasswordParam.getUserName(), forgetPasswordParam.getPassword());

		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	/**
	 * 忘记密码 发送短信验证码
	 * 
	 * @param getVerificationCodeParam
	 * @return
	 */
	@PostMapping("getVerificationCodeForPassword")
	public ResultInfo getVerificationCodeForPassword(@RequestBody GetVerificationCodeParam getVerificationCodeParam) {
		int i = userService.verificationPhone(getVerificationCodeParam.getPhoneNumber());
		if (i == 0) {
			return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该手机号码未注册!");
		}
		SMSResult smsResult = userService.verificationCode(getVerificationCodeParam.getPhoneNumber(),
				SMSTemplateEnum.ModifyThePassword);

		if (smsResult.isResult()) {

			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
		}
		return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR);
	}

	/**
	 * 验证忘记密码短信验证码
	 * 
	 * @param verificationCodeParam
	 * @return
	 */
	@PostMapping("verificationCodeForPassword")
	public ResultInfo verificationCodeForPassword(@RequestBody VerificationCodeParam verificationCodeParam) {
		int i = userService.checkoutCode(verificationCodeParam.getPhoneNumber(),
				SMSTemplateEnum.ModifyThePassword.getValue(), verificationCodeParam.getCode());
		switch (i) {
		case 0:
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "验证码失效,请重新获取!");
		case 1:
			String key = UUID.randomUUID().toString().replace("-","");
			String data = RedisConstant.FORGET_PASSWORD + key;
			redisUtil.set(data,true,60*30);
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, "操作成功!",key);
		case 2:
			return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "验证码错误!");
		}
		return null;
	}

	/**
	 * 续签 刷新token
	 * @param request
	 * @return
	 */
	@GetMapping("refreshToken")
	public ResultInfo refreshToken(HttpServletRequest request){
		if(ResultInfo.isFailed(authJwtService.refreshToken(request))){
			return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.ERROR, "刷新token失败，请重试");
		}
		return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

}
