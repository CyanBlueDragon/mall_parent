package com.yunyihenkey.auth.web.param.userController;

import javax.validation.constraints.NotEmpty;

/**
 * @author LiarYang
 * @date 2018/5/4 9:03
 */
public class LoginParam {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;

    public LoginParam() {
        super();
    }

    public LoginParam( String userName,  String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

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
