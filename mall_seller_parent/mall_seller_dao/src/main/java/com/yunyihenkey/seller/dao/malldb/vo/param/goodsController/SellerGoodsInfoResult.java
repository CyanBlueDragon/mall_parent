package com.yunyihenkey.seller.dao.malldb.vo.param.goodsController;

/**
 * 
 * @desc: 分销商平台商品详情页面 商品数据
 * @author: zhouh
 * @date: 2018年5月11日 下午2:56:33
 *
 */
public class SellerGoodsInfoResult {

	/** 主键 */
	private Long id;

    /**
     * 供应商商品id
     */
    private Long goodsId;

	/** 商品标题 */
	private String goodsTitle;

	/** 商品图片 */
	private String picUrl;

	/** 商品单价（单位分） */
	private Long price;

	/** 供货价（单位分） */
	private Long supplyPrice;

	/** 库存 */
	private Integer stock;

	/** 分类id */
	private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 创建时间
     */
    private String createTime;

	/** 商品状态#0,仓库中|warehouse;1,上架中|selling;3,已售罄|sold */
	private Integer status;

	/** 商品添加次数 */
	private Integer addCount;

	public Long getId() {
		return id;
	}

	public String getGoodsTitle() {
        return goodsTitle;
    }

    public String getPicUrl() {
        return picUrl;
	}

	public Long getPrice() {
        return price;
    }

    public Long getSupplyPrice() {
        return supplyPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCreateTime() {
        return createTime;
	}

	public Integer getStatus() {
		return status;
	}

    public Integer getAddCount() {
        return addCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setSupplyPrice(Long supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setAddCount(Integer addCount) {
        this.addCount = addCount;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

}
