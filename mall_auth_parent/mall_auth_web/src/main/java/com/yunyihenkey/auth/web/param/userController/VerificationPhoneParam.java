package com.yunyihenkey.auth.web.param.userController;

import javax.validation.constraints.NotEmpty;

/**
 * @author LiarYang
 * @date 2018/5/8 12:08
 */
public class VerificationPhoneParam {
    @NotEmpty
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
