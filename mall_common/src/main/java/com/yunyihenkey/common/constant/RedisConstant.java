package com.yunyihenkey.common.constant;

import com.yunyihenkey.common.utils.LogUtils;

public class RedisConstant {

	/** redis 分隔符 */
	public static final String REDIS_SPLIT = ":";

	/** jwt的黑名单key，前部分 */
	public static final String REDIS_MALL_JWT_BLACK = "mall:jwt:black";

	/** jwt的黑名单key ，所有部分 */
	public static String getMallJwtBlackKey(String systemcode, String jwtId) {
		// 例如：mall:jwt:black:102:245b6d459dd644cc8344283d5ad9d04f
		return LogUtils.getString(RedisConstant.REDIS_MALL_JWT_BLACK, RedisConstant.REDIS_SPLIT, systemcode,
				RedisConstant.REDIS_SPLIT, jwtId);
	}
	/** 验证码客户端KEY */
	public static final String REDIS_VERTI_CODE = "mall:login:veriCode:";
}
