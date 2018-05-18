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

	/** 创建时间 */
	private String createDate;

	/** 商品状态#0,仓库中|warehouse;1,上架中|selling;3,已售罄|sold */
	private Integer status;

	/** 商品添加次数 */
	private Integer addCount;

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

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getAddCount() {
		return addCount;
	}

	public void setAddCount(Integer addCount) {
		this.addCount = addCount;
	}

}
