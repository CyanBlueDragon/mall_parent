package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author LiarYang
 * @date 2018/5/8 12:02
 */
public class SignParam extends BaseVo {
    @NotEmpty
    private String key ;

    @NotEmpty(message = "支付密码不能为空")
    @Pattern(regexp = "^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$", message = "支付密码长度为6-20位,由数字和字母组成,至少包含数字和字母")
    private String password;

    private String provinceCode;

    private String cityCode;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

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
