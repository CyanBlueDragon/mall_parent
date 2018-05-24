package com.yunyihenkey.seller.dao.malldb.vo.param.goodsController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * @desc:
 * @author: zhouh
 * @date: 2018年5月22日 下午3:46:04
 *
 */
public class UpdGoodsParam {

	/** 分销商商品id */
	@NotNull(message = "商品id不能为空！")
	@Min(value = 1, message = "商品id不存在！")
	private Long id;

	/** 商品标题 */
	@NotBlank(message = "商品标题不能为空！")
	private String goodsTitle;

	/** 商品图片 */
	@NotBlank(message = "商品图片不能为空！")
	private String picUrl;

	/** 商品详情 */
	@NotBlank(message = "商品详情不能为空！")
	private String description;

	/** 分类id */
	@NotNull(message = "分类不能为空！")
	@Min(value = 1, message = "分类不存在！")
	private Long categoryId;
	
	/** 商品售价（单位分） */
	@NotNull(message = "商品售价不能为空！")
	@Min(value = 0, message = "商品售价必须大于零!")
	private Long price;

	/** 运费模版 */
	private String deliveryTemplateName;

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDescription() {
		return description;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public Long getPrice() {
		return price;
	}

	public String getDeliveryTemplateName() {
		return deliveryTemplateName;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public void setDeliveryTemplateName(String deliveryTemplateName) {
		this.deliveryTemplateName = deliveryTemplateName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
