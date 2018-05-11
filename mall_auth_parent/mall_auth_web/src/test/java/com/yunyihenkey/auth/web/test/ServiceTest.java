package com.yunyihenkey.auth.web.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunyihenkey.Application;
import com.yunyihenkey.auth.service.AuthJwtService;
import com.yunyihenkey.auth.service.enums.LoginSourceEnum;
import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServiceTest {

	@Autowired(required = false)
	AuthJwtService authJwtService;
	
	@Autowired(required = false)
	JwtUtils jwtUtils;

	@Test
	public void testLogOut() throws InterruptedException {

		String token = jwtUtils.cretaJwt("",SystemCodeEnum.SELLER,LoginSourceEnum.Web);

		System.out.println("token=" + token);
		System.out.println("logOutResult=" + authJwtService.loginout(token));

	}
}
