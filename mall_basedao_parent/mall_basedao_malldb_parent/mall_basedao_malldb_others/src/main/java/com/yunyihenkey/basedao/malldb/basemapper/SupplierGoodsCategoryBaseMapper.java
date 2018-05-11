package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory;

public interface SupplierGoodsCategoryBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SupplierGoodsCategory record);

    int insertSelective(SupplierGoodsCategory record);

    SupplierGoodsCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SupplierGoodsCategory record);

    int updateByPrimaryKey(SupplierGoodsCategory record);
}