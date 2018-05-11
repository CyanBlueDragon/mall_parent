package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;

public class SellerRegistrationCode implements Serializable {
    /** 注册码 */
    private String registrationCode;

    /** 状态#0,停用|DISABLE;1,启用|ENABLE */
    private Integer apply;

    private static final long serialVersionUID = 1L;

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode == null ? null : registrationCode.trim();
    }

    public Integer getApply() {
        return apply;
    }

    public void setApply(Integer apply) {
        this.apply = apply;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", registrationCode=").append(registrationCode);
        sb.append(", apply=").append(apply);
        sb.append("]");
        return sb.toString();
    }
}