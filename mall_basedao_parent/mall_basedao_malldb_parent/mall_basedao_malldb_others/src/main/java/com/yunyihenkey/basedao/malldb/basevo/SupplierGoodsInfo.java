package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SupplierGoodsInfo implements Serializable {
    /** 主键 */
    private Long id;

    /** 关联商品分类表 */
    private Long categoryId;

    /** 所属供货商-供货商表ID */
    private Long supplierId;

    /** 商品编码 */
    private String goodsCode;

    /** 商品名称 */
    private String goodsName;

    /** 商品类型#1,实物|material_object;2,虚拟|fictitious */
    private Integer catId;

    /** 库存数量 */
    private Integer stock;

    /** 卖点 */
    private String sellPoint;

    /** 运费模版 */
    private String deliveryTemplateName;

    /** 商品状态#0,仓库中|warehouse;1,上架中|selling;2,已售罄|sold;3,待审核|PENDING;4,审核通过|PASSED;5,审核不通过|NOT_THROUGH */
    private Integer status;

    /** 供货价（单位分） */
    private Long supplyPrice;

    /** 建议最小价格（单位分） */
    private Long minRetailPrice;

    /** 建议最大价格（单位分） */
    private Long maxRetailPrice;

    /** 销量 */
    private Long saleAmount;

    /** 页面点击次数 */
    private Long pvValue;

    /** 发布人 */
    private String createUser;

    /** 发布时间 */
    private Date createDate;

    /** 最后修改时间 */
    private Date updateDate;

    /** 默认图片 */
    private String picUrl;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
    }

    public String getDeliveryTemplateName() {
        return deliveryTemplateName;
    }

    public void setDeliveryTemplateName(String deliveryTemplateName) {
        this.deliveryTemplateName = deliveryTemplateName == null ? null : deliveryTemplateName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(Long supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public Long getMinRetailPrice() {
        return minRetailPrice;
    }

    public void setMinRetailPrice(Long minRetailPrice) {
        this.minRetailPrice = minRetailPrice;
    }

    public Long getMaxRetailPrice() {
        return maxRetailPrice;
    }

    public void setMaxRetailPrice(Long maxRetailPrice) {
        this.maxRetailPrice = maxRetailPrice;
    }

    public Long getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Long saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Long getPvValue() {
        return pvValue;
    }

    public void setPvValue(Long pvValue) {
        this.pvValue = pvValue;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", catId=").append(catId);
        sb.append(", stock=").append(stock);
        sb.append(", sellPoint=").append(sellPoint);
        sb.append(", deliveryTemplateName=").append(deliveryTemplateName);
        sb.append(", status=").append(status);
        sb.append(", supplyPrice=").append(supplyPrice);
        sb.append(", minRetailPrice=").append(minRetailPrice);
        sb.append(", maxRetailPrice=").append(maxRetailPrice);
        sb.append(", saleAmount=").append(saleAmount);
        sb.append(", pvValue=").append(pvValue);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", picUrl=").append(picUrl);
        sb.append("]");
        return sb.toString();
    }
}