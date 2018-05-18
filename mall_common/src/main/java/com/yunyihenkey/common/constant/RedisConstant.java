package com.yunyihenkey.common.constant;

import com.yunyihenkey.common.utils.LogUtils;

public class RedisConstant {

    /**
     * redis 分隔符
     */
    public static final String REDIS_SPLIT = ":";

    /**
     * jwt的黑名单key，前部分
     */
    public static final String REDIS_MALL_JWT_BLACK = "mall:jwt:black";

	/** 验证码客户端KEY */
	public static final String REDIS_VERTI_CODE = "mall:login:veriCode:";

	/** 验证码客户端KEY */
	public static final String REDIS_CODE_SET_PAY_PWD = "mall:login:veriCode:";

	/**
	 * 注册短信验证码Key
	 */
	public static  final String SIGN_UP_VERICODE = "mall:signUp:veriCode:";
	/**
	 * 忘记密码短息验证码KEY
	 */
	public static  final String FORGET_PASSWORD = "mall:resetPassword:veriCode:";

	/**
	 * 注册信息缓存
	 */
	public static  final String CACHE_SIGNUP_MESSAGE	 = "mall:signUpMessage:";
    /**
     * 员工注册验证码
     */
    public static final String REDIS_REGISTER_STAFF_CODE = "mall:register:staff:code:";

	/**
	 * 昨日生产信息缓存
	 */
	public static String getKeyByShopId(Long shopId){
		return RedisConstant.YESTERDAY_PRODUCTION + shopId;
	}
	private static  final String YESTERDAY_PRODUCTION = "mall:yesterday:production:";

	/** jwt的黑名单key ，所有部分 */
	public static String getMallJwtBlackKey(String systemcode, String jwtId) {
		// 例如：mall:jwt:black:102:245b6d459dd644cc8344283d5ad9d04f
		return LogUtils.getString(RedisConstant.REDIS_MALL_JWT_BLACK, RedisConstant.REDIS_SPLIT, systemcode,
				RedisConstant.REDIS_SPLIT, jwtId);
	}



}
