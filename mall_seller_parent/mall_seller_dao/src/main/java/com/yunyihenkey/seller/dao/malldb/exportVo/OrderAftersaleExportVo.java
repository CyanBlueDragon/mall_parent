package com.yunyihenkey.seller.dao.malldb.exportVo;

import com.yunyihenkey.common.annotation.Export;

import java.util.Date;

/**
 * @Author SunQ
 * @Date 2018/5/10 0010 10:09
 */
public class OrderAftersaleExportVo {

    @Export(index = 0, title = "订单编号")
    /** 订单编号 */
    private Long orderCode;

    @Export(index = 1, title = "退款商品")
    /** 退款商品 */
    private String productName;

    @Export(index = 2, title = "退款金额")
    /** 退款金额 */
    private Long refundAmount;

    @Export(index = 3, title = "用户账户")
    /** 用户账户 */
    private String memberAccount;

    @Export(index = 4, title = "用户昵称")
    /** 用户昵称 */
    private String memberName;

    @Export(index = 5, title = "申请时间")
    /** 申请时间 */
    private Date createTime;

    @Export(index = 6, title = "状态")
    /** 状态#0,申请退款|REFUND;1,退款中|REFUNDING;2,退款完成|REFUNDED */
    private String aftersaleStatus;

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
        this.productName = productName;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAftersaleStatus() {
        return aftersaleStatus;
    }

    public void setAftersaleStatus(String aftersaleStatus) {
        this.aftersaleStatus = aftersaleStatus;
    }
}
