package com.yunyihenkey.auth.web.controller;

import com.yunyihenkey.auth.service.UserService;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private
	RedisUtil redisUtil	;

	@RequestMapping("/getUser/{id}")
	public String getUser(@PathVariable("id") Integer id) {

		return "";
	}

	@RequestMapping("/test")
	public String test() {


		return "测试成功!!!";
	}

	/**
	 * 检测验证码
	 * @param phoneNumber
	 * @param type
	 * @return
	 */
	@GetMapping("/verificationCode")
	public ResultInfo<Map<String, String>> verificationCode(@RequestParam("phoneNumber")String phoneNumber,
												  @RequestParam("type")Integer type,
												  @RequestParam("code")Integer code,
												  @RequestParam("extensionCoding")String extensionCoding){
		Map<String, String > map = new HashMap<>();
		if (phoneNumber == null || type == null) {
            return new ResultInfo<>(SystemCodeEnum.AUTH,CodeEnum.ERROR_PARAM,"请求参数有误!");
        }
		String resultKey = userService.signUp(phoneNumber, type, code ,extensionCoding);
		if (resultKey != null) {
			map.put("result",resultKey);
			return new ResultInfo<>(SystemCodeEnum.AUTH,CodeEnum.SUCCESS,map);
		}
		return new ResultInfo<>(SystemCodeEnum.AUTH,CodeEnum.ERROR,"验证失败!");
	}

	/**
	 * 获取验证码
	 * @param phoneNumber
	 * @param type
	 * @return
	 */
	@GetMapping("/getVerificationCode")
	public ResultInfo getVerificationCode(@RequestParam("phoneNumber")String phoneNumber,
										  @RequestParam("type")Integer type){
		if (phoneNumber == null) {
			return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.ERROR_PARAM,"请求参数有误!");
		}

		String message = userService.verificationCode(phoneNumber, type);

		if (message.equals("OK")) {
			return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.SUCCESS);
		}
		return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.ERROR,message);
	}


	/**
	 * 注册
	 * @param password
	 * @param resultKey
	 * @param cityCode
	 * @return
	 */
	@GetMapping("/signUp")
	public ResultInfo signUp(@RequestParam("password")String password,
							 @RequestParam("resultKey")String resultKey,
							 @RequestParam("cityCode")String cityCode){
		userService.saveUser(password,resultKey,cityCode);
		return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.SUCCESS);
	}


	/**
	 * 验证手机号码是否已经注册
	 * @param phoneNumber
	 * @return
	 */
	@GetMapping("/verificationPhone")
	public ResultInfo verificationPhone(@RequestParam("phoneNumber")String phoneNumber){
		int i = userService.verificationPhone(phoneNumber);
		if (i == 0) {
			return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.SUCCESS);
		}
		return new ResultInfo(SystemCodeEnum.AUTH,CodeEnum.ERROR,"该手机号码已经注册!");
	}




}
