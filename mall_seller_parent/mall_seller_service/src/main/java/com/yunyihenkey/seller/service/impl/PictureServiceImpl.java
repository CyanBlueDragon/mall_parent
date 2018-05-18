package com.yunyihenkey.seller.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.FtpUtil;
import com.yunyihenkey.common.utils.ImageUtil;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.service.PictureService;

/**
 * 
 * @desc: 图片上传实现类
 * @author: zhouh
 * @date: 2018年5月17日 下午6:14:43
 *
 */
@Service
public class PictureServiceImpl implements PictureService {

	private String FTP_ADDRESS = "192.168.1.204";
	private Integer FTP_PORT = 21;
	private String FTP_USERNAME = "ftpuser";
	private String FTP_PASSWORD = "ftpuser";
	private String FTP_BASE_PATH = "/home/ftpuser/images";
	private String IMAGE_BASE_URL = "http://192.168.1.204/images";

	@Override
	public Object uploadPicture(String type, String data) {

		// 获取图片base64
		String imgStr = data.split(",")[1];

		// 获取图片输入流
		InputStream generateImage = ImageUtil.generateImage(imgStr);
		if (generateImage == null) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR);
		}

		// 获取图片后缀
		String fileType = type.split("/")[1];
		String fileName = UUID.randomUUID().toString() + "." + fileType;
		// 图片路径
		String imagePath = DateUtil.format(new Date(), "/yyyy/MM/dd");

		// 上传图片
		boolean uploadFile = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
				imagePath, fileName, generateImage);
		if (!uploadFile) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR);
		}

		// 上传后的图片地址
		String picUrl = IMAGE_BASE_URL + imagePath + "/" + fileName;
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("url", picUrl);

		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, result);
	}

}
