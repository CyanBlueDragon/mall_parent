package com.yunyihenkey.seller.dao.malldb.vo.param.shopSetController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author : 007
 * @date : 2018/5/8 12:12
 */
public class ShopUpdateParam extends BaseVo {
    @NotEmpty
    private String name;

    /**
     * 店铺状态#0,营业|OPEN; 1,打烊|CLOSE',
     */
    @NotNull
    private Integer status;
    @NotEmpty
    private String logoUrl;

    /**
     * 店铺简介
     */
    @NotEmpty
    private String depict;
    /**
     * 运营人
     */
    @NotEmpty
    private String operator;

    /**
     * 店铺邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    @NotEmpty
    private String phone;

    private String txQq;

    private String txWx;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTxQq() {
        return txQq;
    }

    public void setTxQq(String txQq) {
        this.txQq = txQq;
    }

    public String getTxWx() {
        return txWx;
    }

    public void setTxWx(String txWx) {
        this.txWx = txWx;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }
}
