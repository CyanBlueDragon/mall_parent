package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;

public interface SupplierGoodsInfoBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SupplierGoodsInfo record);

    int insertSelective(SupplierGoodsInfo record);

    SupplierGoodsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SupplierGoodsInfo record);

    int updateByPrimaryKeyWithBLOBs(SupplierGoodsInfo record);

    int updateByPrimaryKey(SupplierGoodsInfo record);
}