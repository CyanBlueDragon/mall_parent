package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopRenovation;

public interface SellerShopRenovationBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerShopRenovation record);

    int insertSelective(SellerShopRenovation record);

    SellerShopRenovation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerShopRenovation record);

    int updateByPrimaryKey(SellerShopRenovation record);
}