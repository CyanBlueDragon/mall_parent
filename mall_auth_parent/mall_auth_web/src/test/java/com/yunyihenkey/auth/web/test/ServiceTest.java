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
import com.yunyihenkey.auth.service.vo.authjwt.GetTokenParam;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServiceTest {

	@Autowired
	AuthJwtService authJwtService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Test
	public void testLogOut() throws InterruptedException {
		
		GetTokenParam getTokenParam = new GetTokenParam();
		getTokenParam.setUserName("张三");
		getTokenParam.setPassword("temp");
		getTokenParam.setSystemCodeEnum(SystemCodeEnum.SELLER);
		getTokenParam.setLoginSourceEnum(LoginSourceEnum.Web);
		String token = jwtUtils.cretaJwt(getTokenParam);
		
		
		
		System.out.println("token=" + token);
		System.out.println("logOutResult=" + authJwtService.loginout(token));
		
		
	}

}
