package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerGoodsInfo implements Serializable {
    /** 主键 */
    private Long id;

    /** 店铺编号 */
    private Long shopId;

    /** 分类id */
    private Long categoryId;

    /** 供应商商品id */
    private Long goodsId;

    /** 商品编码 */
    private String goodsCode;

    /** 商品名称 */
    private String goodsName;

    /** 库存 */
    private Integer stock;

    /** 卖点 */
    private String sellPoint;

    /** 运费模版 */
    private String deliveryTemplateName;

    /** 供货价（单位分） */
    private Long supplyPrice;

    /** 商品单价（单位分） */
    private Long price;

    /** 建议最小价格（单位分） */
    private Long minRetailPrice;

    /** 建议最大价格（单位分） */
    private Long maxRetailPrice;

    /** 商品状态#0,仓库中|warehouse;1,上架中|selling;2,已售罄|sold */
    private Integer status;

    /** 创建人 */
    private String createUser;

    /** 创建时间 */
    private Date createDate;

    /** 修改人 */
    private String updateUser;

    /** 修改时间 */
    private Date updateDate;

    /** 销量 */
    private Long saleAmount;

    /** 点击量 */
    private Long pvValue;

    /** 默认图片 */
    private String picUrl;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public Long getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(Long supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
        sb.append(", shopId=").append(shopId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", stock=").append(stock);
        sb.append(", sellPoint=").append(sellPoint);
        sb.append(", deliveryTemplateName=").append(deliveryTemplateName);
        sb.append(", supplyPrice=").append(supplyPrice);
        sb.append(", price=").append(price);
        sb.append(", minRetailPrice=").append(minRetailPrice);
        sb.append(", maxRetailPrice=").append(maxRetailPrice);
        sb.append(", status=").append(status);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", saleAmount=").append(saleAmount);
        sb.append(", pvValue=").append(pvValue);
        sb.append(", picUrl=").append(picUrl);
        sb.append("]");
        return sb.toString();
    }
}