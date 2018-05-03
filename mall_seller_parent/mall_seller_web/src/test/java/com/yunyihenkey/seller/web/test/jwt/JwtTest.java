package com.yunyihenkey.seller.web.test.jwt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunyihenkey.Application;
import com.yunyihenkey.auth.service.enums.LoginSourceEnum;
import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.auth.service.vo.authjwt.GetTokenParam;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
// @ActiveProfiles("uat")
public class JwtTest {

	/**
	 * 
	 */
	@Autowired
	private JwtUtils jwtUtils;

	@Test
	public void test() {
		String jwtStr = jwtUtils
				.cretaJwt(new GetTokenParam("a592622272", "aaa", SystemCodeEnum.SELLER, LoginSourceEnum.Android));
		System.out.println("jwtStr:::" + jwtStr);
		System.out.println("jwtStr:::length:::" + jwtStr.length());

		Jws<Claims> j = jwtUtils.parseJwtStr(jwtStr);

		System.out.println(j.getBody().toString());

	}

}
