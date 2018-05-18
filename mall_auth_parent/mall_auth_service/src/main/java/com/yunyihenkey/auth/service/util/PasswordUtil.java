package com.yunyihenkey.auth.service.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author YangLiu
 * @desc
 * @date 2018/5/12 11:53
 */
public class PasswordUtil {
	public static String enCode(String userName, String password) {
		return DigestUtils.md5Hex(userName + password);
	}

	/**
	 * 
	 * @desc 加密店铺支付密码
	 * @auth wulm
	 * @date 2018年5月14日 下午5:13:38
	 * @param shopId
	 *            店铺id
	 * @param payPassword
	 *            店铺支付密码
	 * @return 加密后的密码（用户存放到数据库）
	 */
	public static String encodePayPassword(String shopId, String payPassword) {
		return DigestUtils.md5Hex(shopId + payPassword);
	}
}
