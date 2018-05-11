package com.yunyihenkey.seller.web.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotEmpty;

/**
 * @author LiarYang
 * @date 2018/5/8 12:02
 */
public class SignParam extends BaseVo {
    @NotEmpty
    private String key ;
    @NotEmpty
    private String password;
    @NotEmpty
    private String cityCode;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
