package com.yunyihenkey.supplier.dao.malldb.vo.exportVo;

import com.yunyihenkey.common.vo.base.BaseVo;

import java.util.Date;

/**
 * @ClassName SupplierGoodsExportParam
 * @Description
 * @Author LuTong
 * @Date 2018/5/23 9:38
 * @Version 1.0
 */

public class SupplierGoodsExportParam extends BaseVo {

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String picUrl;

    /**
     * 商品供货价
     */
    private Long price;

    /**
     * 商品分销价
     */
    private Long supplyPrice;

    /**
     * 商品实时库存
     */
    private Integer stock;

    /**
     * 商品总库存
     */
    private Integer totalStock;

    /**
     * 商品分类名称
     */
    private String categoryName;

    /**
     * 供货商名称
     */
    private String supplyName;

    /**
     * 提交人
     */
    private String submitUser;

    /**
     * 商品状态#0,仓库中|warehouse;1,上架中|selling;2,已售罄|sold;3,已下架|already_down;4,未审核|PENDING;5,审核通过|PASSED;6,审核不通过|NOT_THROUGH
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(Long supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Integer totalStock) {
        this.totalStock = totalStock;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getSubmitUser() {
        return submitUser;
    }

    public void setSubmitUser(String submitUser) {
        this.submitUser = submitUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SupplierGoodsExportParam{");
        sb.append("id=").append(id);
        sb.append(", goodsCode='").append(goodsCode).append('\'');
        sb.append(", picUrl='").append(picUrl).append('\'');
        sb.append(", price=").append(price);
        sb.append(", supplyPrice=").append(supplyPrice);
        sb.append(", stock=").append(stock);
        sb.append(", totalStock=").append(totalStock);
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append(", supplyName='").append(supplyName).append('\'');
        sb.append(", submitUser='").append(submitUser).append('\'');
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
