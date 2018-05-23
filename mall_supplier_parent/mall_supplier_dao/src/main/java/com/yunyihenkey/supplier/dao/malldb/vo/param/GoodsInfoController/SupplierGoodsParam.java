package com.yunyihenkey.supplier.dao.malldb.vo.param.GoodsInfoController;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsDescrip;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.common.vo.base.BaseVo;

/**
 * @ClassName SupplierGoodsParam
 * @Description 返回前端的商品详情信息
 * @Author LuTong
 * @Date 2018/5/14 11:34
 * @Version 1.0
 */
public class SupplierGoodsParam extends BaseVo {

    private SupplierGoodsInfo supplierGoodsInfo;
    private SupplierGoodsDescrip supplierGoodsDescrip;

    public SupplierGoodsInfo getSupplierGoodsInfo() {
        return supplierGoodsInfo;
    }

    public void setSupplierGoodsInfo(SupplierGoodsInfo supplierGoodsInfo) {
        this.supplierGoodsInfo = supplierGoodsInfo;
    }

    public SupplierGoodsDescrip getSupplierGoodsDescrip() {
        return supplierGoodsDescrip;
    }

    public void setSupplierGoodsDescrip(SupplierGoodsDescrip supplierGoodsDescrip) {
        this.supplierGoodsDescrip = supplierGoodsDescrip;
    }
}
