package com.yunyihenkey.common.utils;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.digest.DigestUtils;

public class MallUtils {

	/**
	 * 
	 * @desc 设置默认值（如果sourceObject不为空，则返回sourceObject值。否则返回默认值）
	 * @auth josnow
	 * @date 2018年1月29日 上午10:01:02
	 * @param sourceObject
	 * @param defaultValue
	 * @return
	 */
	public static String defaultString(Object sourceObject, String defaultValue) {
		return sourceObject == null ? defaultValue : sourceObject.toString();
	}

	/**
	 * 
	 * @desc 获取通用签名，可用于查看支付订单详情，亦可用于其他签名
	 * @auth josnow
	 * @date 2018年3月1日 下午4:01:04
	 * @param param
	 * @return
	 */
	public static String getSignCommon(String param) {
		return DigestUtils.md5Hex(DigestUtils.sha512(DatatypeConverter.printBase64Binary(param.getBytes()) + param));
	}

}
