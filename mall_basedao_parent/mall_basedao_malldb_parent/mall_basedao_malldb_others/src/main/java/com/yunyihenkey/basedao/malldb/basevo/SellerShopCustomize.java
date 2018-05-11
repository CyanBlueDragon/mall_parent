package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerShopCustomize implements Serializable {
    private Long id;

    private Long shopId;

    /** 是否使用轮播0 不使用 1使用 */
    private Integer isCrousel;

    /** 是否使用导航 0否 1是 */
    private Integer isNavigation;

    /** 是否使用商品模块 0否 1是 */
    private Integer isProduct;

    private Date createTime;

    private Date updateTime;

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

    public Integer getIsCrousel() {
        return isCrousel;
    }

    public void setIsCrousel(Integer isCrousel) {
        this.isCrousel = isCrousel;
    }

    public Integer getIsNavigation() {
        return isNavigation;
    }

    public void setIsNavigation(Integer isNavigation) {
        this.isNavigation = isNavigation;
    }

    public Integer getIsProduct() {
        return isProduct;
    }

    public void setIsProduct(Integer isProduct) {
        this.isProduct = isProduct;
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
        sb.append(", shopId=").append(shopId);
        sb.append(", isCrousel=").append(isCrousel);
        sb.append(", isNavigation=").append(isNavigation);
        sb.append(", isProduct=").append(isProduct);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}