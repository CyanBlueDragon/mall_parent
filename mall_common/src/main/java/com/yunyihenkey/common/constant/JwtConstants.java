package com.yunyihenkey.common.constant;

public class JwtConstants {

	/** 系统来源 */
	public static final String JWT_SYSTEM_CODE = "systemCode";
	/** 登录来源 */
	public static final String JWT_LOGIN_SOURCE = "loginSource";
	/** jwt用户信息 */
	public static final String JWT_USER_INFO = "user";
	/** 权限key */
	public static final String JWT_USER_PERMISSION = "userPerm";

	/** jwt web网页端失效时间（秒） web端1天后过期； */
	public static final int EXPIRATION_WEB = 86400;
	/** jwt 其他端失效时间（秒） 其他端7天后过期 */
	public static final int EXPIRATION_OTHERS = 604800;
	/** 集群环境各个机器时间差最大范围 (秒) */
	public static final int MAX_DIFFER = 300;

	/** 获取请求头中保存的token key */
	public static final String HEADER_TOKEN = "token";
}
