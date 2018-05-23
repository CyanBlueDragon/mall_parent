package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerShopMoney implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 金额(分)，盈利为正数，扣款为负数
     */
    private Long money;

    /**
     * 金额类型#0,店铺盈利|SHOP_SELL;1,B级抽成|B_TAKE;2,C级抽成|C_TAKE;3,商品退货退款|REFUND_GOODS;4,商品退货退回抽成|REFUND_TAKE;5,冻结金额|FREEZE_MONEY;6,冻结金额退还|UNFREEZE_MONEY;7,提现扣款|TAKE_CASH;8,提现扣款手续费|TAKE_CASH_CHARGE;9,提现失败退回|TAKE_CASH_BACK;10,提现失败退回手续费|TAKE_CASH_CHARGE_BACK;
     */
    private Integer moneyType;

    /**
     * 关联id
     */
    private Long relationId;

    /**
     * 签名
     */
    private String sign;

    /**
     * 创建时间
     */
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

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Integer getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(Integer moneyType) {
        this.moneyType = moneyType;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
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
        sb.append(", money=").append(money);
        sb.append(", moneyType=").append(moneyType);
        sb.append(", relationId=").append(relationId);
        sb.append(", sign=").append(sign);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}