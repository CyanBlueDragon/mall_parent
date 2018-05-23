package com.yunyihenkey.seller.dao.malldb.vo.param.GoodsCategoryController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @desc:
 * @author: zhouh
 * @date: 2018年5月21日 上午10:01:57
 */
public class UpdCategoryParam {

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
    @NotNull(message = "排序不能为空！")
    private Integer sortOrder;

    @NotNull(message = "id不存在！")
    @Min(value = 1, message = "id不存在！")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
