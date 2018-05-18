package com.yunyihenkey.seller.dao.malldb.vo.param.goodsController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddGoodsParam {

	/** 供应商商品id */
	@NotNull(message = "供应商商品id不能为空！")
	@Min(value = 1, message = "供应商商品id不存在！")
	private Long goodsId;

	/** 商品编号 */
	@NotNull(message = "商品编号不能为空！")
	@Min(value = 1, message = "商品编号不存在！")
	private Long goodsCode;

	/** 商品标题 */
	@NotBlank(message = "商品标题不能为空！")
	private String goodsTitle;

	/** 供货价（单位分） */
	@NotNull(message = "供货价不能为空！")
	@Min(value = 0, message = "供货价必须大于零!")
	private Long supplyPrice;

	/** 默认图片 */
	@NotBlank(message = "图片不能为空！")
	private String picUrl;

	/** 商品件数 */
	@NotNull(message = "商品件数不能为空！")
	@Min(value = 0, message = "商品件数必须大于零!")
	private Integer stock;

	/** 商品售价（单位分） */
	@NotNull(message = "商品售价不能为空！")
	@Min(value = 0, message = "商品售价必须大于零!")
	private Long price;

	/** 分类id */
	@NotNull(message = "分类不能为空！")
	@Min(value = 1, message = "分类不存在！")
	private Long categoryId;

	/** 运费模版 */
	private String deliveryTemplateName;

	/** 商品状态#0,仓库中|warehouse;1,上架中|selling;2,已售罄|sold;3,已下架|already_down */
	@NotNull(message = "商品状态不能为空！")
	private Integer status;
	
	/** 商品描述 */
	@NotBlank(message = "商品描述不能为空！")
	private String description;

	public Long getCategoryId() {
		return categoryId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public Integer getStock() {
		return stock;
	}

	public String getDeliveryTemplateName() {
		return deliveryTemplateName;
	}

	public Long getSupplyPrice() {
		return supplyPrice;
	}

	public Long getPrice() {
		return price;
	}

	public Integer getStatus() {
		return status;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public void setDeliveryTemplateName(String deliveryTemplateName) {
		this.deliveryTemplateName = deliveryTemplateName;
	}

	public void setSupplyPrice(Long supplyPrice) {
		this.supplyPrice = supplyPrice;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(Long goodsCode) {
		this.goodsCode = goodsCode;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
