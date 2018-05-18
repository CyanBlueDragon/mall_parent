package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotEmpty;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/16 12:33
 */
public class GetCode extends BaseVo {
    @NotEmpty
    private  String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
