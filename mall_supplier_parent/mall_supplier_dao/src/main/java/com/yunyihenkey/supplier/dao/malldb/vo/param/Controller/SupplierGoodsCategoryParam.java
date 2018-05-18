package com.yunyihenkey.supplier.dao.malldb.vo.param.Controller;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName SupplierGoodsCategoryParam
 * @Description 传入id则修改，无id则为新增
 * @Author LuTong
 * @Date 2018/5/11 16:25
 * @Version 1.0
 */
public class SupplierGoodsCategoryParam extends BaseVo {

    private Long id;

    @NotEmpty(message = "分类名称不能为空")
    private String name;

    @NotNull(message = "排序权值不能为空")
    private Integer sortOrder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
