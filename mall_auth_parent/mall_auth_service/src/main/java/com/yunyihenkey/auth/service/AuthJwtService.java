package com.yunyihenkey.auth.service;

import javax.servlet.http.HttpServletRequest;

import com.yunyihenkey.auth.service.enums.ReqSourceEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;

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
	 * @auth wulm
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param systemCodeEnum
	 *            系统来源
	 * @param loginSourceEnum
	 *            登录来源
	 * @return
	 */
	ResultInfo<String> createToken(String userName, String password, SystemCodeEnum systemCodeEnum,
			ReqSourceEnum loginSourceEnum);

	/**
	 * 
	 * @desc 认证token,
	 * @param request TODO
	 * @param authenticationTokenParam
	 * @return
	 */
	ResultInfo<Object> validateToken(HttpServletRequest request, SystemCodeEnum systemCodeEnum);

	/**
	 * 
	 * @desc 刷新token
	 * @return
	 */
	ResultInfo<String> refreshToken(HttpServletRequest request);

	/**
	 * 
	 * @desc 注销、登出token
	 * @return
	 */
	ResultInfo<Object> loginout(String jwtStr);

	/**
	 * 
	 * @desc 修改密码，注销、登出当前用户授权的所有token
	 * @param userName
	 * @param systemCodeEnum
	 * @return
	 */
	void LoginoutAllUpdPwd(String userName, SystemCodeEnum systemCodeEnum);

	/**
	 * 
	 * @desc 指定登出注销自己账户的某个token
	 * @auth wulm
	 * @date 2018年5月4日 下午3:49:20
	 * @return
	 */
	ResultInfo<Object> appointLoginout(String jwtStr, String appointTokenId);
}
