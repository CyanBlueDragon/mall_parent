package com.yunyihenkey.seller.dao.malldb.vo.param.goodsController;

import java.util.Date;

/**
 * 
 * @desc:分销商平台商品分类vo
 * @author: zhouh
 * @date: 2018年5月14日 下午2:18:10
 *
 */
public class SellerGoodsCategoryInfoResult {

	/** 主键 */
	private Long id;

	/** 分类名称 */
	private String name;

	/** 排序 */
	private Integer sortOrder;

	/** 创建时间 */
	private Date createDate;

	/** 商品数量 */
	private Integer goodsNum;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

}
