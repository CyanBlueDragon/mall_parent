package com.yunyihenkey.seller.dao.malldb.vo.result.user;

import java.io.Serializable;

/**
 * 缓存用户信息对象
 * @author LiarYang
 * @date 2018/5/7 15:16
 */
public class DistributorTypeResult implements Serializable {
    //手机号码
    private String phoneNumber;

    //分销商等级 0:A ;1:B ;2:C
    private Integer type;

    private String key;

    private Integer res;//0:验证成功 1:未获取验证码或者验证码失效 2:验证码错误 3:推广编码错误 4:A级分销商注册码错误

    private Long parentShopId;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }

    public Long getParentShopId() {
        return parentShopId;
    }

    public void setParentShopId(Long parentShopId) {
        this.parentShopId = parentShopId;
    }
}
