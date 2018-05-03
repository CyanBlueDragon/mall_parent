package com.yunyihenkey.seller.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyihenkey.common.web.BaseController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@RequestMapping("/getUser/{id}")
	public String getUser(@PathVariable("id") Integer id) {

		return "";
	}

	@RequestMapping("/test")
	public String test() {
		return "测试成功!!!";
	}

}
