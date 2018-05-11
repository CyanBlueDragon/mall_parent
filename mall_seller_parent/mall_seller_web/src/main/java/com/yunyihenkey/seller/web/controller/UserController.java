package com.yunyihenkey.seller.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyihenkey.auth.service.AuthJwtService;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.seller.service.SellerRoleService;
import com.yunyihenkey.seller.service.SellerUserService;
import com.yunyihenkey.seller.web.util.VerificationCodeUtils;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {
	@Autowired
	private SellerUserService userService;
	@Autowired
	private SellerRoleService sellerRoleService;
	@Autowired
	private AuthJwtService authJwtService;
	@Autowired
	private VerificationCodeUtils verificationCodeUtils;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private ValidatorUtils validatorUtils;

	@RequestMapping("/getUser/{id}")
	public String getUser(@PathVariable("id") Integer id) {

		return "";
	}

	@RequestMapping("/test")
	public String test() {
		return "测试成功!!!";
	}

}
