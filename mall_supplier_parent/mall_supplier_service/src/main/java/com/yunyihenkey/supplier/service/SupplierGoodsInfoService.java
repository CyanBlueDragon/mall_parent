package com.yunyihenkey.supplier.service;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.common.vo.base.BaseService;

public interface SupplierGoodsInfoService extends BaseService<SupplierGoodsInfo,Long> {

    int getCount(Long id);
}
