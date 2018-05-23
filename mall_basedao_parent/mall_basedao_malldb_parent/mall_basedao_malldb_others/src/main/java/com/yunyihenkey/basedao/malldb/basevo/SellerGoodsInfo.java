package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerGoodsInfo implements Serializable {
    /** 主键 */
    private Long id;

    /** 店铺id */
    private Long shopId;

    /** 分类id */
    private Long categoryId;

    /** 供应商商品id */
    private Long goodsId;

    /** 商品编号 */
    private Long goodsCode;

    /** 库存 */
    private Integer stock;

    /** 商品标题 */
    private String goodsTitle;

    /** 运费模版 */
    private String deliveryTemplateName;

    /** 供货价（单位分） */
    private Long supplyPrice;

    /** 商品单价（单位分） */
    private Long price;

    /** 商品状态#0,仓库中|warehouse;1,上架中|selling;2,已售罄|sold;3,已下架|already_down */
    private Integer status;

    /** 创建人 */
    private String createUser;

    /** 创建时间 */
    private Date createTime;

    /** 修改人 */
    private String updateUser;

    /** 修改时间 */
    private Date updateTime;

    /** 销量 */
    private Long saleAmount;

    /** 点击量 */
    private Long pvValue;

    /** 商品添加次数 */
    private Integer addCount;

    /** 是否删除#0,否|No;1,是|Yes */
    private Integer isDelete;

    /**
     * 商品图片
     */
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

    public Long getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(Long goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle == null ? null : goodsTitle.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getAddCount() {
        return addCount;
    }

    public void setAddCount(Integer addCount) {
        this.addCount = addCount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
        sb.append(", stock=").append(stock);
        sb.append(", goodsTitle=").append(goodsTitle);
        sb.append(", deliveryTemplateName=").append(deliveryTemplateName);
        sb.append(", supplyPrice=").append(supplyPrice);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", saleAmount=").append(saleAmount);
        sb.append(", pvValue=").append(pvValue);
        sb.append(", addCount=").append(addCount);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", picUrl=").append(picUrl);
        sb.append("]");
        return sb.toString();
    }
}