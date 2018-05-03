package com.yunyihenkey.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author : 周浩
 * @date : 2018/4/16 不用在每个类中使用 LogManager.getLogger(xxx.class) 的方式了 直接使用
 *       LogManager.getLogger(),或者用LogUtils.getLogger 使用以上方法,或自动获取当前调用者的类名
 */
public class LogUtils {

	/**
	 * 返回一个调用这的名称的Logger
	 * 
	 * @return The Logger for the calling class.
	 * @throws UnsupportedOperationException
	 *             if the calling class cannot be determined.
	 */
	public static Logger getLogger() {
		return LogManager.getLogger();
	}

	public static String getString(Object... infos) {
		StringBuilder sb = new StringBuilder();
		if (infos != null && infos.length > 0) {
			for (Object in : infos) {
				sb.append(in);
			}
		}
		return sb.toString();
	}
}
