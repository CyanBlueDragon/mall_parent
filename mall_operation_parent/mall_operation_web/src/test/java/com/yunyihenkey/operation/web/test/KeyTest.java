package com.yunyihenkey.operation.web.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunyihenkey.Application;
import com.yunyihenkey.auth.service.util.JwtUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
// @ActiveProfiles("uat")
public class KeyTest {

	/**
	 * 
	 */
	@Autowired
	private JwtUtils jwtUtils;

	@Test
	public void test() {
System.out.println("privatekey！！！！"+jwtUtils.getPrivateKey());
System.out.println("publickey！！！！"+jwtUtils.getPublicKey());
	System.out.println(jwtUtils.cretaJwt(null));
	}

}
