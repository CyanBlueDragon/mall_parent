package com.yunyihenkey.supplier.dao.malldb.vo.param.GoodsInfoController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName SupplierGoodsAddParam
 * @Description TODO 传入商品描述
 * @Author LuTong
 * @Date 2018/5/18 10:08
 * @Version 1.0
 */
public class SupplierGoodsAddParam extends BaseVo {

    private Long id;

    /**
     * 关联商品分类表
     */
    @NotNull(message = "分类id不能为空")
    private Long categoryId;

    /**
     * 所属供货商-供货商表ID
     */
    private Long supplierId;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    @NotEmpty(message = "商品名不能为空")
    private String goodsName;

    /**
     * 商品类型#1,实物|material_object;2,虚拟|fictitious
     */
    @NotNull(message = "是否实物？")
    private Integer catId;

    /**
     * 库存数量
     */
    @NotNull(message = "库存不可为空")
    @Min(value = 0, message = "库存最小为0")
    private Integer stock;

    /**
     * 卖点
     */
    @NotEmpty(message = "请输入商品卖点")
    private String sellPoint;

    /**
     * 运费模版
     */
    @NotEmpty(message = "运费模版内容")
    private String deliveryTemplateName;

    /**
     * 商品状态#0,仓库中|warehouse;1,上架中|selling;2,已售罄|sold;3,已下架|already_down;4,未审核|PENDING;5,审核通过|PASSED;6,审核不通过|NOT_THROUGH
     */
    private Integer status;

    /**
     * 供货价（单位分）
     */
    @NotNull(message = "供货价不能为空")
    @Min(value = 0, message = "供货价至少为0")
    private Long supplyPrice;

    /**
     * 发布人
     */
    private String createUser;

    /**
     * 发布时间
     */
    private Date createTime;


    /**
     * 默认图片
     */
    @NotEmpty(message = "请传入图片")
    private String picUrl;

    /**
     * 商品描述
     */
    @NotEmpty(message = "请传入商品描述")
    private String description;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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
        this.sellPoint = sellPoint;
    }

    public String getDeliveryTemplateName() {
        return deliveryTemplateName;
    }

    public void setDeliveryTemplateName(String deliveryTemplateName) {
        this.deliveryTemplateName = deliveryTemplateName;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

}
