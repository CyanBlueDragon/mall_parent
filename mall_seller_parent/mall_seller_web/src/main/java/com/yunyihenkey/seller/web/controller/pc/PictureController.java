package com.yunyihenkey.seller.web.controller.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yunyihenkey.seller.service.PictureService;

/**
 * @desc: 上传图片contro
 * @author: zhouh
 * @date: 2018年5月18日 上午10:26:05
 */
@RestController
@RequestMapping("/pic")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    /**
     * @param uploadFile
     * @return Object
     * @desc: 图片上传
     * @author zhouh
     * @date: 2018年5月21日 上午11:42:27
     */
    @PostMapping("/upload")
    public Object uploadPic(MultipartFile file) {
        return pictureService.uploadPic(file);
    }

}
