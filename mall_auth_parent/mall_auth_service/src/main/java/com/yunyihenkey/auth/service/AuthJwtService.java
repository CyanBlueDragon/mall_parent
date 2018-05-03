package com.yunyihenkey.auth.service;

import com.yunyihenkey.auth.service.vo.authjwt.GetTokenParam;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * 
 * @desc 授权、认证jwt
 * @author wulm
 * @date 2018年4月28日 下午12:09:42
 * @version 1.0.0
 */
public interface AuthJwtService {

	/**
	 * 
	 * @desc 授权token
	 * @param getTokenParam
	 * @return
	 */
	ResultInfo<String> getToken(GetTokenParam getTokenParam);

	/**
	 * 
	 * @desc 认证token,
	 * @param jwtStr
	 * @return
	 */
	ResultInfo<Jws<Claims>> validateToken(String jwtStr);

	/**
	 * 
	 * @desc 刷新token
	 * @return
	 */
	ResultInfo<String> refreshToken(String jwtStr);

	/**
	 * 
	 * @desc 注销、登出token
	 * @return
	 */
	ResultInfo loginout(String jwtStr);

	/**
	 * 
	 * @desc 修改密码，注销、登出当前用户授权的所有token
	 * @param userName
	 * @param systemCodeEnum
	 * @return
	 */
	ResultInfo LoginoutAllUpdPwd(String userName, SystemCodeEnum systemCodeEnum);
}
