package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsDescrip;

public interface SupplierGoodsDescripBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SupplierGoodsDescrip record);

    int insertSelective(SupplierGoodsDescrip record);

    SupplierGoodsDescrip selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SupplierGoodsDescrip record);

    int updateByPrimaryKeyWithBLOBs(SupplierGoodsDescrip record);

    int updateByPrimaryKey(SupplierGoodsDescrip record);
}