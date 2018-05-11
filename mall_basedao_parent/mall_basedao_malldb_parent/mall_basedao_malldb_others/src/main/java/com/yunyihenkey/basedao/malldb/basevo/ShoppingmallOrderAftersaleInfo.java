package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class ShoppingmallOrderAftersaleInfo implements Serializable {
    /** 主键 */
    private Long id;

    /** 商城ID */
    private Long mallId;

    /** 供货商ID */
    private Long supplierId;

    /** 退款单号 */
    private Long aftersaleCode;

    /** 订单号 */
    private Long orderCode;

    /** 商品名称 */
    private String productName;

    /** 退款商品数量 */
    private Integer productCount;

    /** 退款金额 */
    private Long refundAmount;

    /** 退款原因 */
    private String aftersaleReason;

    /** 会员账号 */
    private String memberAccount;

    /** 会员名称 */
    private String memberName;

    /** 售后状态#0,申请退款|REFUND;1,退款中|REFUNDING;2,退款完成|REFUNDED;3,审核不通过|NOPASS */
    private Integer aftersaleStatus;

    /** 退货物流单号 */
    private String expressCode;

    /** 申请时间 */
    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMallId() {
        return mallId;
    }

    public void setMallId(Long mallId) {
        this.mallId = mallId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getAftersaleCode() {
        return aftersaleCode;
    }

    public void setAftersaleCode(Long aftersaleCode) {
        this.aftersaleCode = aftersaleCode;
    }

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getAftersaleReason() {
        return aftersaleReason;
    }

    public void setAftersaleReason(String aftersaleReason) {
        this.aftersaleReason = aftersaleReason == null ? null : aftersaleReason.trim();
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount == null ? null : memberAccount.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public Integer getAftersaleStatus() {
        return aftersaleStatus;
    }

    public void setAftersaleStatus(Integer aftersaleStatus) {
        this.aftersaleStatus = aftersaleStatus;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode == null ? null : expressCode.trim();
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
        sb.append(", mallId=").append(mallId);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", aftersaleCode=").append(aftersaleCode);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", productName=").append(productName);
        sb.append(", productCount=").append(productCount);
        sb.append(", refundAmount=").append(refundAmount);
        sb.append(", aftersaleReason=").append(aftersaleReason);
        sb.append(", memberAccount=").append(memberAccount);
        sb.append(", memberName=").append(memberName);
        sb.append(", aftersaleStatus=").append(aftersaleStatus);
        sb.append(", expressCode=").append(expressCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}