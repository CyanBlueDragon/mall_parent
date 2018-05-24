package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerUserToken implements Serializable {
    private Long userId;

    /** tokenID */
    private String tokenId;

    /** 登录来源#ANDROID,安卓端|ANDROID;IOS,IOS端|IOS;PC,电脑端|PC;WEB,网页端|WEB */
    private String loginSource;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId == null ? null : tokenId.trim();
    }

    public String getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(String loginSource) {
        this.loginSource = loginSource == null ? null : loginSource.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", tokenId=").append(tokenId);
        sb.append(", loginSource=").append(loginSource);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}