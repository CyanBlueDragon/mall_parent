package com.yunyihenkey.operation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyihenkey.common.idworker.IdWorker;
import com.yunyihenkey.common.web.BaseController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IdWorker idWorker;

	@RequestMapping("/getUser/{id}")
	public String getUser(@PathVariable("id") Integer id) {

		return "";
	}

	@RequestMapping("/test")
	public String test() {

		System.out.println(idWorker);

		for (int i = 0; i < 100; i++) {
			System.out.println(idWorker.nextId());

		}

		return "测试成功!!!";
	}

}
