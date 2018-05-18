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
	public static final long EXPIRATION_WEB = 86400;
	/** jwt 其他端失效时间（秒） 其他端7天后过期 */
	public static final long EXPIRATION_OTHERS = 604800;
	/** jwt 其他端失效时间（秒） 其他端7天后过期 */
	public static final long REFRESH_TOKEN_TIME = 7200000;
	/** 集群环境各个机器时间差最大范围 (秒) 防止集群环境各个机器时间不同步 */
	public static final long MAX_DIFFER = 300;

}
