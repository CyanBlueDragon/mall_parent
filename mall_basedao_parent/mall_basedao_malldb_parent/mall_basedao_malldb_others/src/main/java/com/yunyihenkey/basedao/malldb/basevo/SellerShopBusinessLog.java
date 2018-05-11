package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerShopBusinessLog implements Serializable {
    /** 主键 */
    private Long id;

    /** 店铺id */
    private Long shopId;

    /** 交易金额(分) */
    private Long businessMoney;

    /** 营收金额(分) */
    private Long revenueMoney;

    /** 交易类型#0,店铺盈利|SHOP_SELL;1,B级抽成|B_TAKE;2,C级抽成|C_TAKE; */
    private Integer businessType;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getBusinessMoney() {
        return businessMoney;
    }

    public void setBusinessMoney(Long businessMoney) {
        this.businessMoney = businessMoney;
    }

    public Long getRevenueMoney() {
        return revenueMoney;
    }

    public void setRevenueMoney(Long revenueMoney) {
        this.revenueMoney = revenueMoney;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
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
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", businessMoney=").append(businessMoney);
        sb.append(", revenueMoney=").append(revenueMoney);
        sb.append(", businessType=").append(businessType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}