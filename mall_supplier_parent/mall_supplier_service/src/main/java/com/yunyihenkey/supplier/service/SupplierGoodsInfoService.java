package com.yunyihenkey.supplier.service;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.supplier.dao.malldb.vo.param.Controller.SupplierGoodsAddParam;

import java.util.List;

public interface SupplierGoodsInfoService extends BaseService<SupplierGoodsInfo,Long> {

    int getCount(Long id);

    List<SupplierGoodsInfo> selectAll(Long id);

    List selectByGoodsId(Long id);

    Long supplierInsertGoods(SupplierGoodsAddParam goodsAddParam);
}
