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

    /**
     * 是否默认分类,1->是,0->否
     */
    private Integer isDefault;

	/** 排序 */
	private Integer sortOrder;

    /**
     * 创建时间
     */
    private Date createTime;

	/** 商品数量 */
	private Integer goodsNum;

	public Long getId() {
		return id;
	}

    public String getName() {
        return name;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

	public Integer getSortOrder() {
        return sortOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Integer getGoodsNum() {
        return goodsNum;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public void setName(String name) {
        this.name = name;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

}
