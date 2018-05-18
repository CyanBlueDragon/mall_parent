package com.yunyihenkey.seller.service;

/**
 * 
 * @desc: 图片上传service
 * @author: zhouh
 * @date: 2018年5月17日 下午6:13:00
 *
 */
public interface PictureService {

	/**
	 * 
	 * @desc: 上传图片
	 * @author zhouh
	 * @param type
	 *            图片类型
	 * @param data
	 *            base64
	 * @return Object
	 * @date: 2018年5月17日 下午6:15:45
	 */
	Object uploadPicture(String type, String data);

}
