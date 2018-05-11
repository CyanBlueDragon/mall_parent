package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsDescrip;

public interface SellerGoodsDescripBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerGoodsDescrip record);

    int insertSelective(SellerGoodsDescrip record);

    SellerGoodsDescrip selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerGoodsDescrip record);

    int updateByPrimaryKeyWithBLOBs(SellerGoodsDescrip record);

    int updateByPrimaryKey(SellerGoodsDescrip record);
}