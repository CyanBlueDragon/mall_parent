package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerShopMoneyBusiness implements Serializable {
    /** 主键 */
    private Long id;

    /** 店铺id */
    private Long shopId;

    /** 交易金额(分)，盈利为正数，扣款为负数 */
    private Long businessMoney;

    /** 营收金额(分)，盈利为正数，扣款为负数 */
    private Long revenueMoney;

    /** 交易类型#0,店铺盈利|SHOP_SELL;1,B级抽成|B_TAKE;2,C级抽成|C_TAKE;3,商品退货退款|REFUND_GOODS;4,商品退货退回抽成|REFUND_TAKE;5,冻结金额|FREEZE_MONEY;6,冻结金额退还|UNFREEZE_MONEY; */
    private Integer businessType;

    /** 备注 */
    private String remarks;

    private Date createTime;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        sb.append(", remarks=").append(remarks);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}