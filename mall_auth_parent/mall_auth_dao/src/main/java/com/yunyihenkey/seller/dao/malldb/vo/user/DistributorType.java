package com.yunyihenkey.seller.dao.malldb.vo.user;

import java.io.Serializable;

/**
 * 缓存用户信息对象
 * @author LiarYang
 * @date 2018/5/7 15:16
 */
public class DistributorType implements Serializable {
    //手机号码
    private String phoneNumber;

    //分销商等级 0:A ;1:B ;2:C
    private int type;

    private String key;

    private int res;//0:验证成功 1:未获取验证码或者验证码失效 2:验证码错误 3:推广编码错误 4:A级分销商注册码错误

    private long parentShopId;

    public long getParentShopId() {
        return parentShopId;
    }

    public void setParentShopId(long parentShopId) {
        this.parentShopId = parentShopId;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
