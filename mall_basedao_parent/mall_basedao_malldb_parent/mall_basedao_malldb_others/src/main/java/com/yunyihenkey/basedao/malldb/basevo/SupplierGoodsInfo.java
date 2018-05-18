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

    /** 商品状态#0,仓库中|warehouse;1,上架中|selling;2,已售罄|sold;3,已下架|already_down;4,未审核|PENDING;5,审核通过|PASSED;6,审核不通过|NOT_THROUGH */
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

    private String minProfit;

    private String maxProfit;

    /** 发布人 */
    private String createUser;

    private String updateUser;

    /** 发布时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date updateTime;

    /** 是否删除#0,否|No;1,是|Yes */
    private Integer isDelete;

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

    public String getMinProfit() {
        return minProfit;
    }

    public void setMinProfit(String minProfit) {
        this.minProfit = minProfit == null ? null : minProfit.trim();
    }

    public String getMaxProfit() {
        return maxProfit;
    }

    public void setMaxProfit(String maxProfit) {
        this.maxProfit = maxProfit == null ? null : maxProfit.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
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
        sb.append(", minProfit=").append(minProfit);
        sb.append(", maxProfit=").append(maxProfit);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", picUrl=").append(picUrl);
        sb.append("]");
        return sb.toString();
    }
}