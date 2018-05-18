package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class ShoppingmallOrderProductInfo implements Serializable {
    /** 主键 */
    private Long id;

    /** 商城ID */
    private Long mallId;

    /** 供货商ID */
    private Long supplierId;

    /** 订单号 */
    private Long orderCode;

    /** 商品ID */
    private Long productId;

    /** 商品名称 */
    private String productName;

    /** 商品单价 */
    private Long productPrice;

    /** 购买数量 */
    private Integer productCount;

    /** 邮费金额 */
    private Long postageAmount;

    /** 邮费类型#0,包邮|FREESHIPPING;1,按件计费|BYNUMBER;2,按重量计费|BYWEIGHT */
    private Integer postageType;

    /** 发货状态#0,未发货|UNSEND;1,配送中|DISTRIBUTION;2,已签收|RECEIVED;3,退款/售后|AFTERSALE */
    private Integer sendStatus;

    /** 通知发货标记#0,未通知|NOTNOTICE;1,已通知|NOTICEED */
    private Integer noticeSign;

    /** 快递单号 */
    private String expressNumber;

    /** 快递公司编码 */
    private String expressCode;

    /** 快递公司名称 */
    private String expressCompany;

    /** 退款标记#0,未退款|UNREFUND;1,已退款|REFUNDED */
    private Integer refundSign;

    /** 发货时间 */
    private Date sendTime;

    /** 收货时间 */
    private Date receivedTime;

    /** 退款时间 */
    private Date refundTime;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 是否删除#0,否|No;1,是|Yes */
    private Integer isDelete;

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

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Long getPostageAmount() {
        return postageAmount;
    }

    public void setPostageAmount(Long postageAmount) {
        this.postageAmount = postageAmount;
    }

    public Integer getPostageType() {
        return postageType;
    }

    public void setPostageType(Integer postageType) {
        this.postageType = postageType;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getNoticeSign() {
        return noticeSign;
    }

    public void setNoticeSign(Integer noticeSign) {
        this.noticeSign = noticeSign;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber == null ? null : expressNumber.trim();
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode == null ? null : expressCode.trim();
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany == null ? null : expressCompany.trim();
    }

    public Integer getRefundSign() {
        return refundSign;
    }

    public void setRefundSign(Integer refundSign) {
        this.refundSign = refundSign;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
        sb.append(", orderCode=").append(orderCode);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productCount=").append(productCount);
        sb.append(", postageAmount=").append(postageAmount);
        sb.append(", postageType=").append(postageType);
        sb.append(", sendStatus=").append(sendStatus);
        sb.append(", noticeSign=").append(noticeSign);
        sb.append(", expressNumber=").append(expressNumber);
        sb.append(", expressCode=").append(expressCode);
        sb.append(", expressCompany=").append(expressCompany);
        sb.append(", refundSign=").append(refundSign);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", receivedTime=").append(receivedTime);
        sb.append(", refundTime=").append(refundTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}