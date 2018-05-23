package com.yunyihenkey.supplier.dao.malldb.vo.param.GoodsInfoController;

import com.yunyihenkey.common.vo.page.PageParam;

/**
 * @ClassName SupplierGoodsInfoParam
 * @Description 某一分类或者全部商品的分页展示参数实体类
 * @Author LuTong
 * @Date 2018/5/12 17:32
 * @Version 1.0
 */
public class SupplierGoodsInfoParam extends PageParam {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
