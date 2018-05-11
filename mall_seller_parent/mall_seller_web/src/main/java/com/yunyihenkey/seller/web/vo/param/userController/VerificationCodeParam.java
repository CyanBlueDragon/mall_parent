package com.yunyihenkey.seller.web.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;

/**
 * @author LiarYang
 * @date 2018/5/7 18:16
 */
public class VerificationCodeParam extends BaseVo {
    private String phoneNumber;
    private String code;
    private String extensionCoding;

    public VerificationCodeParam() {
        super();
    }

    public VerificationCodeParam(String phoneNumber, String code, String extensionCoding) {
        super();
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.extensionCoding = extensionCoding;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExtensionCoding() {
        return extensionCoding;
    }

    public void setExtensionCoding(String extensionCoding) {
        this.extensionCoding = extensionCoding;
    }
}
