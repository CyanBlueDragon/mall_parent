package com.yunyihenkey.seller.dao.malldb.vo.param.goodsController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 
 * @desc:
 * @author: zhouh
 * @date: 2018年5月22日 下午3:46:13
 *
 */
public class AddGoodsParam {
	
	/** 供应商商品id */
	@NotNull(message = "供应商商品id不能为空！")
	@Min(value = 1, message = "供应商商品id不存在！")
	private Long goodsId;

	/** 商品售价（单位分） */
	@NotNull(message = "商品售价不能为空！")
	@Min(value = 0, message = "商品售价必须大于零!")
	private Long price;

	/** 分类id */
	@NotNull(message = "分类不能为空！")
	@Min(value = 1, message = "分类不存在！")
	private Long categoryId;

	/** 商品件数 */
	@NotNull(message = "商品件数不能为空！")
	@Min(value = 1, message = "商品件数必须大于零!")
	private Integer stock;

	/** 运费模版 */
	private String deliveryTemplateName;

	/** 商品状态#0,仓库中|warehouse;1,上架中|selling;2,已售罄|sold;3,已下架|already_down */
	@NotNull(message = "商品状态不能为空！")
	private Integer status;

	public Long getGoodsId() {
		return goodsId;
	}

	public Long getPrice() {
		return price;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public Integer getStock() {
		return stock;
	}

	public String getDeliveryTemplateName() {
		return deliveryTemplateName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public void setDeliveryTemplateName(String deliveryTemplateName) {
		this.deliveryTemplateName = deliveryTemplateName;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
