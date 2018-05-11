package com.yunyihenkey.seller.web.test.jwt;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunyihenkey.Application;
import com.yunyihenkey.auth.service.enums.LoginSourceEnum;
import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.auth.service.vo.authjwt.seller.AuthSellerUser;
import com.yunyihenkey.common.constant.JwtConstants;
import com.yunyihenkey.common.utils.BeanMapUtils;
import com.yunyihenkey.common.utils.JacksonUtils;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

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

		System.out.println("privatekey！！！！" + jwtUtils.getPrivateKey());
		System.out.println("publickey！！！！" + jwtUtils.getPublicKey());

		String compactJws = jwtUtils.cretaJwt("13265602329", SystemCodeEnum.SELLER, LoginSourceEnum.Web);
		System.out.println("length::::" + compactJws.length() + "jwtstr::::::::::::::" + compactJws);

		try {
			Jws<Claims> j = Jwts.parser().setSigningKey(jwtUtils.getPrivateKey()).parseClaimsJws(compactJws);

			// OK, we can trust this JWT

			System.out.println(j.getSignature());
			System.out.println(j.getHeader().toString());
			System.out.println(j.getBody().get(JwtConstants.JWT_USER_INFO).getClass());

			Map<String, Object> map = (Map<String, Object>) j.getBody().get(JwtConstants.JWT_USER_INFO);

			AuthSellerUser authSellerUser = new AuthSellerUser();
			System.out.println(authSellerUser);
			authSellerUser = BeanMapUtils.mapToBean(map, authSellerUser);
			System.out.println(authSellerUser);
			System.out.println(JacksonUtils.writeValueAsString(authSellerUser));

			System.out.println("认证通过......................");

			AuthSellerUser sellerUser = jwtUtils.getSellerUser(compactJws);

			System.out.println(sellerUser);

		} catch (ExpiredJwtException e) {
			// don't trust the JWT!
			System.out.println("令牌已失效!!");
		} catch (SignatureException e) {
			// don't trust the JWT!
			System.out.println("验签失败!!");
		} catch (Exception e) {
			// don't trust the JWT!
			System.out.println("认证失败" + e.getMessage());
			e.printStackTrace();
		}
	}

}
