package com.yunyihenkey.supplier.web.vo.param.categoryController;

import com.yunyihenkey.common.vo.base.BaseVo;

/**
 * @ClassName SupplierGoodsCategoryParam
 * @Description TODO
 * @Author LuTong
 * @Date 2018/5/11 16:25
 * @Version 1.0
 */
public class SupplierGoodsCategoryParam extends BaseVo {

    private String name;

    private Integer sort_order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }
}
