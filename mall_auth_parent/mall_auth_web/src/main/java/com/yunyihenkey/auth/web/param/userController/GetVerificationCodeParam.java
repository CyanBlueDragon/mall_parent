package com.yunyihenkey.auth.web.param.userController;

import javax.validation.constraints.NotEmpty;

/**
 * @author LiarYang
 * @date 2018/5/8 11:58
 */
public class GetVerificationCodeParam {
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private Integer type;

    public GetVerificationCodeParam() {
        super();
    }

    public GetVerificationCodeParam(String phoneNumber, Integer type) {
        super();
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
