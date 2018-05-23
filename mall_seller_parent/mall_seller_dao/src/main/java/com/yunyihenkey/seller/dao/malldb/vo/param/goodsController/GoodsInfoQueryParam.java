package com.yunyihenkey.seller.dao.malldb.vo.param.goodsController;

import javax.validation.constraints.Min;

import com.yunyihenkey.common.vo.page.PageParam;

/**
 * @desc:
 * @author: zhouh
 * @date: 2018年5月22日 下午3:46:21
 */
public class GoodsInfoQueryParam extends PageParam {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商品标题
     */
    private String goodsTitle;

    /**
     * 分类id
     */
    @Min(value = 1, message = "分类id不存在!")
    private Long categoryId;

    /**
     * 商品状态
     */
    private Integer status;

    public Long getCategoryId() {
        return categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

}
