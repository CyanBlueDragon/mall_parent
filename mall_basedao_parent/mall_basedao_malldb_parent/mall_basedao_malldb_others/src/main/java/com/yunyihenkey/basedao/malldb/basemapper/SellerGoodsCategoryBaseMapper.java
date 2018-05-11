package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsCategory;

public interface SellerGoodsCategoryBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerGoodsCategory record);

    int insertSelective(SellerGoodsCategory record);

    SellerGoodsCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerGoodsCategory record);

    int updateByPrimaryKey(SellerGoodsCategory record);
}