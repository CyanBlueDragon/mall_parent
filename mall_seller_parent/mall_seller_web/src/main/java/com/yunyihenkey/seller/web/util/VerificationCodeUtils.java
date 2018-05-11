package com.yunyihenkey.seller.web.util;

import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.seller.web.config.verificationcode.CaptchaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author : 007
 * @date : 2018/5/6 15:41
 * 验证码工具类
 */
@Component
public class VerificationCodeUtils {
    @Autowired
    private CaptchaConfig captchaConfig;

    /**
     * 生成BASE64编码之后的验证码
     *
     * @return
     */
    public String getGenerteImageBase64Code(String text) {
        BufferedImage bufferedImage= createImage(text);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (IOException e) {
            LogUtils.getLogger().error("生成验证码异常",e);
        }
        BASE64Encoder base64Encoder =new BASE64Encoder();
       return  "data:image/jpg;base64,"+base64Encoder.encodeBuffer(outputStream.toByteArray());

    }

    /**
     * 获取验证码文字
     *
     * @return
     */
    public String createText() {
        return captchaConfig.getKaptchaBean().createText();
    }

    /**
     * 生成图片
     * @return
     */
    public BufferedImage createImage(String text) {
        return captchaConfig.getKaptchaBean().createImage(text);

    }

}
