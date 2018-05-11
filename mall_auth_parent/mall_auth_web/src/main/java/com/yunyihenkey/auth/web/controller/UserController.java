package com.yunyihenkey.auth.web.controller;

import com.yunyihenkey.auth.service.AuthJwtService;
import com.yunyihenkey.auth.service.UserService;
import com.yunyihenkey.auth.web.param.userController.*;
import com.yunyihenkey.basedao.malldb.basevo.SellerRegistrationCode;
import com.yunyihenkey.common.idworker.RegistrationCodeUtil;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.user.DistributorType;
import com.yunyihenkey.common.vo.base.SMSResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private UserService userService;

	@Resource
	private RedisUtil redisUtil	;

	@Resource
	private AuthJwtService authJwtService;

	@RequestMapping("/getUser/{id}")
	public String getUser(@PathVariable("id") Integer id) {

		return "";
	}


	@RequestMapping("/test1")
	public String test1() {



		return "测试成功";
	}
	@RequestMapping("/test")
	public String test() {
		HashSet<String> set = new HashSet<>();
		List<SellerRegistrationCode> list = new ArrayList<>();

		while (set.size() <= 1000000) {
			String a = RegistrationCodeUtil.getRandomString(8);
			set.add(a);
		}
		System.out.println(set.size());
		for(String value: set){
			SellerRegistrationCode code = new SellerRegistrationCode();
			code.setRegistrationCode(value);
			code.setApply(0);
			list.add(code);
		}
		System.out.println(list.size());
		userService.addRegistrationCode(list);
		return null;
	}

	/**
	 * 检测验证码和推广编码
	 * @param verificationCodeParam
	 * @return
	 */
	@PostMapping("/verificationCode")
	public ResultInfo<DistributorType> verificationCode(@RequestBody VerificationCodeParam verificationCodeParam){
		String extensionCoding = verificationCodeParam.getExtensionCoding();
		DistributorType distributorType = new DistributorType();
		final Integer type = 1;
		if (extensionCoding == null){
			 distributorType = userService.signUpV1(verificationCodeParam.getPhoneNumber(), type, verificationCodeParam.getCode());
		}
		else
			if (extensionCoding.length() == 8){
			 distributorType = userService.signUpV2(verificationCodeParam.getPhoneNumber(), type, verificationCodeParam.getCode(),extensionCoding);
		}else
			if (extensionCoding.length() == 6) {
			distributorType = userService.signUp(verificationCodeParam.getPhoneNumber(), type, verificationCodeParam.getCode(),extensionCoding);
		}
		if (distributorType.getRes() == 0) {
			return new ResultInfo<>(SystemCodeEnum.AUTH,CodeEnum.SUCCESS,distributorType.getKey());
		}
		if (distributorType.getRes() == 1) {
			return new ResultInfo<>(SystemCodeEnum.AUTH,CodeEnum.ERROR,"未获取验证码或者验证码失效!");
		}
		if (distributorType.getRes() == 2) {
			return new ResultInfo<>(SystemCodeEnum.AUTH,CodeEnum.ERROR,"验证码错误!");
		}
		if (distributorType.getRes() == 3) {
			return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.ERROR, "推广编码错误!");
		}
		return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.ERROR, "A级分销商注册码错误!");
	}

	/**
	 * 获取验证码
	 * getVerificationCodeParam
	 */
	@PostMapping("/getVerificationCode")
	public ResultInfo getVerificationCode(@RequestBody GetVerificationCodeParam getVerificationCodeParam){
		int i = userService.verificationPhone(getVerificationCodeParam.getPhoneNumber());
		if (i != 0) {
			return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.ERROR,"该手机号码已经注册!");
		}

		SMSResult smsResult = userService.verificationCode(getVerificationCodeParam.getPhoneNumber(), getVerificationCodeParam.getType());

		if (smsResult.getResulltMesseage().equals("OK")) {
			return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.SUCCESS);
		}
		return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.ERROR,smsResult.getResulltMesseage());
	}


	/**
	 * 注册
	 * @param signParam
	 * @return
	 */
	@PostMapping("/signUp")
	public ResultInfo signUp(@RequestBody SignParam signParam, HttpServletRequest request){


		boolean flag = userService.saveUser(signParam.getPassword(),signParam.getKey(),signParam.getCityCode(),request);
		if (flag) {
			return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.SUCCESS);
		}
		return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.ERROR,"注册请求超时!");
	}


	/**
	 * 验证手机号码是否已经注册
	 * @param param
	 * @return
	 */
	/*@PostMapping("/verificationPhone")
	public ResultInfo verificationPhone(@RequestBody VerificationPhoneParam param){


	}*/

	/**
	 * 忘记密码
	 * @param loginParam
	 * @return
	 */

	@PostMapping("/forgetPassword")
	public ResultInfo forgetPassword(@RequestBody LoginParam loginParam){
		int i = userService.verificationPhone(loginParam.getUserName());
		if (i == 0) {
			return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.ERROR,"该手机号码还没有注册!");
		}
		authJwtService.LoginoutAllUpdPwd(loginParam.getUserName(),SystemCodeEnum.AUTH);
		userService.forgetPassword(loginParam.getUserName(),loginParam.getPassword());

		return  new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.SUCCESS);
	}



}
