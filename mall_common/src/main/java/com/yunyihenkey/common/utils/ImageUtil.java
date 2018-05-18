package com.yunyihenkey.common.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import sun.misc.BASE64Decoder;

/**
 * 
 * @desc: 图片工具类
 * @author: zhouh
 * @date: 2018年5月17日 下午6:25:21
 *
 */
public class ImageUtil {

	/**
	 * 
	 * @desc: 将base64编码字符串转换为图片
	 * @author zhouh
	 * @param imgStr
	 *            base64编码字符串
	 * @return InputStream 返回输入流、失败返回null
	 * @date: 2018年5月17日 下午6:50:53
	 */
	public static InputStream generateImage(String imgStr) {

		// 图像数据为空
		if (imgStr == null) {
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			return new ByteArrayInputStream(b);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 1M=1024k=1048576字节
	 * 
	 * @desc: 获取base64图片大小
	 * @author zhouh
	 * @param image
	 * @return Integer
	 * @date: 2018年5月17日 下午6:56:15
	 */
	public static Integer imageSize(String image) {
		String str = image.substring(22); // 1.需要计算文件流大小，首先把头部的data:image/png;base64,（注意有逗号）去掉。
		Integer equalIndex = str.indexOf("=");// 2.找到等号，把等号也去掉
		if (str.indexOf("=") > 0) {
			str = str.substring(0, equalIndex);
		}
		Integer strLength = str.length();// 3.原来的字符流大小，单位为字节
		Integer size = strLength - (strLength / 8) * 2;// 4.计算后得到的文件流大小，单位为字节
		return size;
	}

}
