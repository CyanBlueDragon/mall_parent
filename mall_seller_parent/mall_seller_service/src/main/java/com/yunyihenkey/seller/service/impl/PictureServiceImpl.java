package com.yunyihenkey.seller.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.FtpUtil;
import com.yunyihenkey.common.utils.LogUtils;
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

    // ftp地址
    @Value("${FTP.FTP_ADDRESS}")
    private String FTP_ADDRESS;
    // ftp端口
    @Value("${FTP.FTP_PORT}")
    private Integer FTP_PORT;
    // ftp账号
    @Value("${FTP.FTP_USERNAME}")
    private String FTP_USERNAME;
    // ftp密码
    @Value("${FTP.FTP_PASSWORD}")
    private String FTP_PASSWORD;
    // 图片根路径
    @Value("${FTP.FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    // 图片url根地址
    @Value("${FTP.IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;
    // 图片最大大小
    @Value("${FTP.IMAGE_MAX_SIZE}")
    private Integer maxSize;

	@Override
    public Object uploadPic(MultipartFile uploadFile) {
        LogUtils.getLogger().info("上传图片......");

        // 上传后的图片地址
        String result = "";
        try {

            // 验证参数
            if (uploadFile == null) {
                return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "图片不存在！");
            }

            // 文件类型
            String contentType = uploadFile.getContentType();
            // 文件名称
            String oldName = uploadFile.getOriginalFilename();
            if (StringUtils.isBlank(contentType) || StringUtils.isBlank(oldName)
                    || !"image".equals(contentType.split("/")[0])) {
                return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "只能上传图片！");
            }
            if (uploadFile.getSize() > maxSize) {
                return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR,
                        LogUtils.getString("图片大小不能超过", maxSize, "M！"));
            }

            // 生成图片名称
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
            // 图片路径
            String imagePath = DateUtil.format(new Date(), "/yyyy/MM/dd");

            // 上传图片
            boolean isUpload = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
                    imagePath, newName, uploadFile.getInputStream());
            if (!isUpload) {
                return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "上传图片失败！");
            }
            // 图片地址
            result = IMAGE_BASE_URL + imagePath + "/" + newName;
            LogUtils.getLogger().info("上传图片成功,图片地址=", result);

        } catch (Exception e) {
            LogUtils.getLogger().error("上传图片失败！", e);
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "上传图片失败！");
        }

        return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, "上传成功！", result);
	}

}
