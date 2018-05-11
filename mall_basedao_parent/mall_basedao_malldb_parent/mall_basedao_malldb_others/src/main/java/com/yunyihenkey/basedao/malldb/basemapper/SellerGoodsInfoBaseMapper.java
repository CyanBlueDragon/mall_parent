package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsInfo;

public interface SellerGoodsInfoBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerGoodsInfo record);

    int insertSelective(SellerGoodsInfo record);

    SellerGoodsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerGoodsInfo record);

    int updateByPrimaryKeyWithBLOBs(SellerGoodsInfo record);

    int updateByPrimaryKey(SellerGoodsInfo record);
}