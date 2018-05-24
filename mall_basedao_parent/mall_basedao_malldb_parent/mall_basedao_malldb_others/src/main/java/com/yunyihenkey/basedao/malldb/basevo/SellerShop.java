package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerShop implements Serializable {
    /** 主键ID，推广编码使用主键ID转32进制 */
    private Long id;

    /** 会员编号 */
    private Long userId;

    /** 店铺名称 */
    private String name;

    /** 主营 */
    private String majorBusiness;

    /** 支付密码 */
    private String payPassword;

    /** 支付密码绑定的手机（用于下次修改支付密码） */
    private String payPasswordMobile;

    /** 店铺审核状态#0,待审核|PENDING;1,审核通过|PASSED;2,审核不通过|NOT_THROUGH  */
    private Integer auditStatus;

    /** 审核时间 */
    private Date auditTime;

    /** 店铺logo */
    private String logoUrl;

    /** 是否自定义模板 #0,否|NO;1,是|YES */
    private Integer isCustomize;

    /** 店铺编号，备用 */
    private String code;

    /** 店铺状态#0,营业|OPEN; 1,打烊|CLOSE */
    private Integer status;

    /** 运营人 */
    private String operator;

    /** 店铺邮箱 */
    private String email;

    /** 手机号码 */
    private String phone;

    private String txQq;

    private String txWx;

    /** 店铺简介 */
    private String depict;

    /** 店铺链接 */
    private String url;

    /** 分销商级别#0,A级分销商|A;1,B级分销商|B;2,C级分销商|C */
    private Integer sellerGrade;

    /** 上级店铺Id */
    private Long parentShopId;

    /** 微信支付是否开启#0,停用|DISABLE;1,启用|ENABLE */
    private Integer payWx;

    /** 支付宝支付是否开启#0,停用|DISABLE;1,启用|ENABLE */
    private Integer payAli;

    /** 银联支付是否开启#0,停用|DISABLE;1,启用|ENABLE */
    private Integer payUnion;

    /** 未支付倒计时 单位秒 */
    private Long unpaidTime;

    /** 订单确认收货 */
    private Integer confirmOrderTime;

    /** 收货后退款 */
    private Integer refundTime;

    /** 下架时间 */
    private Integer sellOutTime;

    /** 买家申请退换货 */
    private Integer returnOrderTime;

    /** 店铺营业额 */
    private Long shopTurnover;

    /** 总销量 */
    private Integer totalSales;

    /** 退款金额 */
    private Long refundAmount;

    /** 退款总数量 */
    private Integer refundCount;

    /** 店铺商品数量 */
    private Integer storeQuantity;

    /**  1简约风格 2传统电商 3电商模板 */
    private Integer templateType;

    /** 自定义模板Id */
    private Long templateId;

    /** 退换货地址-收货人 */
    private String consignee;

    /** 退换货地址-联系方式 */
    private String returnedContactWay;

    /** 退换货地址-收货地址 */
    private String returnedAddress;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMajorBusiness() {
        return majorBusiness;
    }

    public void setMajorBusiness(String majorBusiness) {
        this.majorBusiness = majorBusiness == null ? null : majorBusiness.trim();
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public String getPayPasswordMobile() {
        return payPasswordMobile;
    }

    public void setPayPasswordMobile(String payPasswordMobile) {
        this.payPasswordMobile = payPasswordMobile == null ? null : payPasswordMobile.trim();
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public Integer getIsCustomize() {
        return isCustomize;
    }

    public void setIsCustomize(Integer isCustomize) {
        this.isCustomize = isCustomize;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getTxQq() {
        return txQq;
    }

    public void setTxQq(String txQq) {
        this.txQq = txQq == null ? null : txQq.trim();
    }

    public String getTxWx() {
        return txWx;
    }

    public void setTxWx(String txWx) {
        this.txWx = txWx == null ? null : txWx.trim();
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict == null ? null : depict.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getSellerGrade() {
        return sellerGrade;
    }

    public void setSellerGrade(Integer sellerGrade) {
        this.sellerGrade = sellerGrade;
    }

    public Long getParentShopId() {
        return parentShopId;
    }

    public void setParentShopId(Long parentShopId) {
        this.parentShopId = parentShopId;
    }

    public Integer getPayWx() {
        return payWx;
    }

    public void setPayWx(Integer payWx) {
        this.payWx = payWx;
    }

    public Integer getPayAli() {
        return payAli;
    }

    public void setPayAli(Integer payAli) {
        this.payAli = payAli;
    }

    public Integer getPayUnion() {
        return payUnion;
    }

    public void setPayUnion(Integer payUnion) {
        this.payUnion = payUnion;
    }

    public Long getUnpaidTime() {
        return unpaidTime;
    }

    public void setUnpaidTime(Long unpaidTime) {
        this.unpaidTime = unpaidTime;
    }

    public Integer getConfirmOrderTime() {
        return confirmOrderTime;
    }

    public void setConfirmOrderTime(Integer confirmOrderTime) {
        this.confirmOrderTime = confirmOrderTime;
    }

    public Integer getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Integer refundTime) {
        this.refundTime = refundTime;
    }

    public Integer getSellOutTime() {
        return sellOutTime;
    }

    public void setSellOutTime(Integer sellOutTime) {
        this.sellOutTime = sellOutTime;
    }

    public Integer getReturnOrderTime() {
        return returnOrderTime;
    }

    public void setReturnOrderTime(Integer returnOrderTime) {
        this.returnOrderTime = returnOrderTime;
    }

    public Long getShopTurnover() {
        return shopTurnover;
    }

    public void setShopTurnover(Long shopTurnover) {
        this.shopTurnover = shopTurnover;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;
    }

    public Integer getStoreQuantity() {
        return storeQuantity;
    }

    public void setStoreQuantity(Integer storeQuantity) {
        this.storeQuantity = storeQuantity;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getReturnedContactWay() {
        return returnedContactWay;
    }

    public void setReturnedContactWay(String returnedContactWay) {
        this.returnedContactWay = returnedContactWay == null ? null : returnedContactWay.trim();
    }

    public String getReturnedAddress() {
        return returnedAddress;
    }

    public void setReturnedAddress(String returnedAddress) {
        this.returnedAddress = returnedAddress == null ? null : returnedAddress.trim();
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
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", majorBusiness=").append(majorBusiness);
        sb.append(", payPassword=").append(payPassword);
        sb.append(", payPasswordMobile=").append(payPasswordMobile);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", logoUrl=").append(logoUrl);
        sb.append(", isCustomize=").append(isCustomize);
        sb.append(", code=").append(code);
        sb.append(", status=").append(status);
        sb.append(", operator=").append(operator);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", txQq=").append(txQq);
        sb.append(", txWx=").append(txWx);
        sb.append(", depict=").append(depict);
        sb.append(", url=").append(url);
        sb.append(", sellerGrade=").append(sellerGrade);
        sb.append(", parentShopId=").append(parentShopId);
        sb.append(", payWx=").append(payWx);
        sb.append(", payAli=").append(payAli);
        sb.append(", payUnion=").append(payUnion);
        sb.append(", unpaidTime=").append(unpaidTime);
        sb.append(", confirmOrderTime=").append(confirmOrderTime);
        sb.append(", refundTime=").append(refundTime);
        sb.append(", sellOutTime=").append(sellOutTime);
        sb.append(", returnOrderTime=").append(returnOrderTime);
        sb.append(", shopTurnover=").append(shopTurnover);
        sb.append(", totalSales=").append(totalSales);
        sb.append(", refundAmount=").append(refundAmount);
        sb.append(", refundCount=").append(refundCount);
        sb.append(", storeQuantity=").append(storeQuantity);
        sb.append(", templateType=").append(templateType);
        sb.append(", templateId=").append(templateId);
        sb.append(", consignee=").append(consignee);
        sb.append(", returnedContactWay=").append(returnedContactWay);
        sb.append(", returnedAddress=").append(returnedAddress);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}