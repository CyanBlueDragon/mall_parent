package com.yunyihenkey.seller.dao.malldb.vo.param.shopSetController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2018/5/10.
 */
public class UpdatePayParam extends BaseVo {

    /** 支付方式 0 微信  1 支付宝 2 银联 */
    @NotNull
    private Integer payMethod;
    /** 是否开启#0,停用|DISABLE;1,启用|ENABLE */
    @NotNull
    private Integer isEnable;



    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }
}
