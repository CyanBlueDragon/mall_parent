package com.yunyihenkey.seller.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.service.PictureService;

/**
 * 
 * @desc: 上传图片contro
 * @author: zhouh
 * @date: 2018年5月18日 上午10:26:05
 *
 */
@RestController
@RequestMapping("/pic")
public class PictureController {

	@Autowired
	private PictureService pictureService;

	@PostMapping("/upload")
	public Object uploadPic(String type, String data) {

		if (StringUtils.isBlank(type)) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "图片类型不能为空！");
		}

		if (StringUtils.isBlank(data)) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "图片不存在！");
		}

		return pictureService.uploadPicture(type, data);
	}

}
