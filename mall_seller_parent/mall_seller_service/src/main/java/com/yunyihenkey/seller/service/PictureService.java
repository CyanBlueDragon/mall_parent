package com.yunyihenkey.seller.service;

import org.springframework.web.multipart.MultipartFile;

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
     * @param uploadFile
     *            图片流
	 * @return Object
     * @date: 2018年5月18日 下午5:21:42
	 */
    Object uploadPic(MultipartFile uploadFile);

}
