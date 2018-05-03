package com.yunyihenkey.auth.service.impl;

import java.util.Date;

import javax.validation.groups.Default;

import com.yunyihenkey.basedao.malldb.basemapper.MallSysSellerUserBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.MallSysShoppingmallUserBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.MallSysSellerUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.auth.service.AuthJwtService;
import com.yunyihenkey.auth.service.constant.RedisConstant;
import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.auth.service.vo.authjwt.GetTokenParam;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Service
public class AuthJwtServiceImpl implements AuthJwtService {

	// @Autowired
	// private TestDbMapper testDbMapper;
    @Autowired
    private MallSysSellerUserBaseMapper mallSysSellerUserBaseMapper;

	@Autowired
	private ValidatorUtils validatorUtils;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public ResultInfo<String> getToken(GetTokenParam getTokenParam) {
		// 参数校验
		String errorInfo = validatorUtils.validateAndGetErrorInfo(getTokenParam, Default.class);
		if (StringUtils.isNotEmpty(errorInfo)) {
			return new ResultInfo<String>(SystemCodeEnum.AUTH, CodeEnum.ERROR_PARAM, errorInfo, null);
		}

		// 验证账号密码
        String userName = getTokenParam.getUserName();
        String password = getTokenParam.getPassword();
        SystemCodeEnum systemCodeEnum = getTokenParam.getSystemCodeEnum();
        //查询平台的用户
        MallSysSellerUser mallSysSellerUser = null;
        switch (getTokenParam.getSystemCodeEnum()) {
            case SELLER: //分销平台
                mallSysSellerUser = mallSysSellerUserBaseMapper.selectNameByUser(userName);
                if(mallSysSellerUser==null){
                    return new ResultInfo<String>(SystemCodeEnum.AUTH,CodeEnum.ERROR,"手机号不存在",null);
                }
                String userPassword= mallSysSellerUser.getPassword();
                //密码校验   规则：手机号+密码
                String md5Hex=  DigestUtils.md5Hex(userPassword+userName);
                if(!StringUtils.equals(md5Hex,userPassword)){
                    return new ResultInfo<String>(SystemCodeEnum.AUTH,CodeEnum.ERROR,"密码不正确",null);
                }
        }

		// 创建jwt
		String jwtStr = jwtUtils.cretaJwt(getTokenParam);

		return new ResultInfo<String>(SystemCodeEnum.AUTH, CodeEnum.SUCCESS, CodeEnum.SUCCESS.getText(), jwtStr);
	}

	@Override
	public ResultInfo<Jws<Claims>> validateToken(String jwtStr) {
		if (StringUtils.isEmpty(jwtStr)) {
			return new ResultInfo<Jws<Claims>>(SystemCodeEnum.AUTH, CodeEnum.ERROR_PARAM, "jwt串不能为空");
		}
		Jws<Claims> j;
		try {
			j = jwtUtils.parseJwtStr(jwtStr);
			// OK, we can trust this JWT

		} catch (Exception e) {
			// don't trust the JWT!
			return new ResultInfo<Jws<Claims>>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH, "认证失败");
		}

		try {
			// 验证该jwt是否在redis黑名单
			String jwtId = j.getBody().getId();
			// .........................................

			boolean f = true;
			if (f) {
				// 认证成功
				return new ResultInfo<Jws<Claims>>(SystemCodeEnum.AUTH, CodeEnum.SUCCESS, j);
			} else {
				// 命中黑名单,已被注销
				return new ResultInfo<Jws<Claims>>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH, "账号已被注销");
			}

		} catch (Exception e) {
			// don't trust the JWT!
			return new ResultInfo<Jws<Claims>>(SystemCodeEnum.AUTH, CodeEnum.ERROR_SERVER);
		}

	}

	@Override
	public ResultInfo<String> refreshToken(String jwtStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultInfo<Object> loginout(String jwtStr) {

		// 验证token是否有效、是否过期
		Claims claims = jwtUtils.parseJwtStr(jwtStr).getBody();
		if (claims == null) {
			return new ResultInfo<Object>(SystemCodeEnum.AUTH, CodeEnum.ERROR_NOT_AUTH);
		}

		// 获取jwtId
		String jwtId = claims.getId();
		// 获取失效日期
		Date expiration = claims.getExpiration();
		// 根据日期获取失效时间，单位（秒）
		int diffSeconds = DateUtil.getDiffSeconds(new Date(), expiration);

		try {
			// 加入黑名单
			redisUtil.set(RedisConstant.REDIS_JWT_BLACK_LIST_KEY, jwtId, diffSeconds);
		} catch (Exception e) {
			LogUtils.getLogger().error("退出登录时jwtId加入黑名单失败,jwtId=" + jwtId + ";错误信息：" + e);
		}

		return new ResultInfo<Object>(SystemCodeEnum.AUTH, CodeEnum.SUCCESS);
	}

	@Override
	public ResultInfo<Object> LoginoutAllUpdPwd(String userName, SystemCodeEnum systemCodeEnum) {
		// TODO Auto-generated method stub
		return null;
	}

}
