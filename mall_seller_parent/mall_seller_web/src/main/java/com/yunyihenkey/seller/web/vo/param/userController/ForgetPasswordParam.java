package com.yunyihenkey.seller.web.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotEmpty;

/**
 * Created by 007 on 2018/5/10.
 */
public class ForgetPasswordParam extends BaseVo {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
