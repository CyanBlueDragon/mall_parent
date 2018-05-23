package com.yunyihenkey.auth.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yunyihenkey.auth.service.AuthJwtService;
import com.yunyihenkey.auth.service.enums.RequestSourceEnum;
import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.auth.service.util.PasswordUtil;
import com.yunyihenkey.auth.service.vo.authjwt.seller.AuthSellerUser;
import com.yunyihenkey.basedao.malldb.basevo.SellerUserToken;
import com.yunyihenkey.basedao.malldb.basevoEnum.SellerUser.SellerGradeEnum;
import com.yunyihenkey.basedao.malldb.basevoEnum.SellerUser.UserTypeEnum;
import com.yunyihenkey.common.constant.JwtConstants;
import com.yunyihenkey.common.constant.MallConstants;
import com.yunyihenkey.common.constant.RedisConstant;
import com.yunyihenkey.common.utils.BeanMapUtils;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerUserMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerUserTokenMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Service
public class AuthJwtServiceImpl implements AuthJwtService {

	@Autowired(required = false)
	private AuthSellerUserTokenMapper authSellerUserTokenMapper;
	@Autowired(required = false)
	private AuthSellerUserMapper authSellerUserMapper;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public ResultInfo<String> createToken(String userName, String password, SystemCodeEnum systemCodeEnum,
                                          RequestSourceEnum loginSourceEnum) {

		// 验证账号密码
		switch (systemCodeEnum) {
		case SELLER: // 分销平台

			// 验证账号密码 md5(手机号+密码)
			if (!authSellerUserMapper.isPasswordTrue(userName, PasswordUtil.enCode(userName, password))) {
				return new ResultInfo<String>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH, "账号或密码错误", null);
			}
			break;
		default:
			throw new IllegalArgumentException("其他平台正在开发");
			// break;
		}

		// 创建jwt
		String jwtStr = jwtUtils.cretaJwt(userName, systemCodeEnum, loginSourceEnum);

		return new ResultInfo<String>(SystemCodeEnum.AUTH, CodeEnum.SUCCESS, CodeEnum.SUCCESS.getText(), jwtStr);
	}

	@Override
	public ResultInfo<Object> validateToken(HttpServletRequest request, SystemCodeEnum systemCodeEnum) {

		String jwtStr = request.getHeader(MallConstants.HEADER_TOKEN);

		if (StringUtils.isEmpty(jwtStr)) {
			return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH);
		}

		Jws<Claims> jws = jwtUtils.parseJwtStr(jwtStr);
		if (jws == null) {
			return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH);
		}

		Claims bodyClaims = jws.getBody();

		String systemCodeStr = bodyClaims.get(JwtConstants.JWT_SYSTEM_CODE).toString();

		if (!systemCodeStr.equals(Integer.toString(systemCodeEnum.getValue()))) {
			// jwt系统编码和所属系统编码不一致
			return new ResultInfo<>(systemCodeEnum, CodeEnum.ERROR_NOT_AUTH, "传入系统编码和所属系统编码不一致");
		}

		if (jwtUtils.isInBlacklist(systemCodeStr, bodyClaims.getId())) {
			// 命中token黑名单
			return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH);
		}

		AuthSellerUser authSellerUser = BeanMapUtils
				.mapToBean((Map<String, Object>) bodyClaims.get(JwtConstants.JWT_USER_INFO), new AuthSellerUser());

		String reqUrl = request.getRequestURI();// 用户检测C级分销商权限和员工权限
		// 验证权限
		switch (systemCodeEnum) {
		case SELLER: // 分销平台

			// 验证权限
			if (!Integer.toString(UserTypeEnum.SELLER.getValue()).equals(authSellerUser.getUserType())) {
				// 分销商不需要验证权限，拥有分销平台所有权限，特殊的url权限要验证，如：不能发展下级，不能查看下级店铺
				Assert.notNull(authSellerUser.getSellerGrade(), "分销商级别不能为空");
				if (Integer.toString(SellerGradeEnum.C.getValue()).equals(authSellerUser.getSellerGrade())) {
					// C分销商权限判断
				}
				break;
			} else {
				// 分销商员工权限判断

				break;
			}

		default:
			throw new IllegalArgumentException("其他平台正在开发");
			// break;
		}
		request.setAttribute(JwtConstants.JWT_USER_INFO, authSellerUser);
		return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.SUCCESS);
	}

	@Override
	public ResultInfo<String> refreshToken(HttpServletRequest request) {

		String jwtStr = request.getHeader(MallConstants.HEADER_TOKEN);

		if (StringUtils.isEmpty(jwtStr)) {
			return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH);
		}

		// 验证token
		Jws<Claims> j = jwtUtils.parseJwtStr(jwtStr);
		if (j == null) {
			return new ResultInfo<String>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH);
		}

		Claims bodyClaims = j.getBody();
		if (bodyClaims.getIssuedAt().getTime() + JwtConstants.REFRESH_TOKEN_TIME >= System.currentTimeMillis()) {
			// 2小时内不可重复续签
			return new ResultInfo<String>(SystemCodeEnum.AUTH, CodeEnum.ERROR, "2小时内不可重复续签", null);
		}

		if (jwtUtils.isInBlacklist(bodyClaims.get(JwtConstants.JWT_SYSTEM_CODE).toString(), bodyClaims.getId())) {
			// 命中token黑名单
			return new ResultInfo<String>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH);
		}

		// 续签jwt，更新签发时间为当前时间，延长失效时间
		String newJwtStr = jwtUtils.refreshToken(bodyClaims);

		return new ResultInfo<String>(SystemCodeEnum.AUTH, CodeEnum.SUCCESS, CodeEnum.SUCCESS.getText(), newJwtStr);
	}

	@Override
	public ResultInfo<Object> loginout(String jwtStr) {
		// 验证token是否有效、是否过期
		Jws<Claims> jws = jwtUtils.parseJwtStr(jwtStr);
		if (jws == null) {
			return new ResultInfo<Object>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH);
		}

		Claims bodyClaims = jws.getBody();

		// 加入黑名单
        if (RequestSourceEnum.WEB.getValue().equals(bodyClaims.get(JwtConstants.JWT_LOGIN_SOURCE).toString())) {
			// web端1天后过期
			redisUtil.set(RedisConstant.getMallJwtBlackKey(bodyClaims.get(JwtConstants.JWT_SYSTEM_CODE).toString(),
					bodyClaims.getId()), "", JwtConstants.EXPIRATION_WEB);
		} else {
			// 其他端7天后过期
			redisUtil.set(RedisConstant.getMallJwtBlackKey(bodyClaims.get(JwtConstants.JWT_SYSTEM_CODE).toString(),
					bodyClaims.getId()), "", JwtConstants.EXPIRATION_OTHERS);
		}

		return new ResultInfo<Object>(SystemCodeEnum.AUTH, CodeEnum.SUCCESS);
	}

	@Override
	public void LoginoutAllUpdPwd(String userId, SystemCodeEnum systemCodeEnum) {
		switch (systemCodeEnum) {
		case SELLER:
			List<SellerUserToken> blackList = authSellerUserTokenMapper.getByUserId(userId);

			if (blackList != null && !blackList.isEmpty()) {
				for (SellerUserToken userToken : blackList) {
                    if (RequestSourceEnum.WEB.getValue() == userToken.getLoginSource()) {
						// web端1天后过期
						redisUtil.set(
								RedisConstant.getMallJwtBlackKey(Integer.toString(SystemCodeEnum.SELLER.getValue()),
										userToken.getTokenId()),
								"", JwtConstants.EXPIRATION_WEB);
					} else {
						// 其他端7天后过期
						redisUtil.set(
								RedisConstant.getMallJwtBlackKey(Integer.toString(SystemCodeEnum.SELLER.getValue()),
										userToken.getTokenId()),
								"", JwtConstants.EXPIRATION_OTHERS);
					}
				}
			}

			break;

		default:
			throw new IllegalArgumentException("其他平台还在开发!!!!!!");
			// break;
		}

	}

	@Override
	public ResultInfo<Object> appointLoginout(String jwtStr, String appointTokenId) {

		// // 验证token是否有效、是否过期
		// Jws<Claims> jws = jwtUtils.parseJwtStr(jwtStr);
		// if (jws == null) {
		// return new ResultInfo<Object>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH);
		// }
		//
		// Claims bodyClaims = jws.getBody();
		//
		// if
		// (jwtUtils.isInBlacklist(bodyClaims.get(JwtConstants.JWT_SYSTEM_CODE).toString(),
		// bodyClaims.getId())) {
		// // 命中token黑名单
		// return new ResultInfo<String>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH);
		// }

		// 把指定的tokenId 加入黑名单
		// if (LoginSourceEnum.Web.getValue() == Integer
		// .parseInt(bodyClaims.get(JwtConstants.JWT_LOGIN_SOURCE).toString())) {
		// // web端1天后过期
		// redisUtil.set(RedisConstant.getMallJwtBlackKey(bodyClaims.get(JwtConstants.JWT_SYSTEM_CODE).toString(),
		// bodyClaims.getId()), "", JwtConstants.JWT_EXPIRATION_WEB);
		// } else {
		// // 其他端7天后过期
		// redisUtil.set(RedisConstant.getMallJwtBlackKey(bodyClaims.get(JwtConstants.JWT_SYSTEM_CODE).toString(),
		// bodyClaims.getId()), "", JwtConstants.JWT_EXPIRATION_OTHERS);
		// }
		//
		// return new ResultInfo<Object>(SystemCodeEnum.AUTH, CodeEnum.SUCCESS);

		return null;
	}

}
