package com.yunyihenkey.seller.dao.malldb.vo.param.GoodsCategoryController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @desc:
 * @author: zhouh
 * @date: 2018年5月21日 上午9:53:31
 */
public class AddCategoryParam {

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空！")
    private String name;

    /**
     * 排序
     */
    @Min(value = 1, message = "排序不能小于1！")
    @Max(value = 100, message = "排序不能大于100！")
    private Integer sortOrder;

    public String getName() {
        return name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}
