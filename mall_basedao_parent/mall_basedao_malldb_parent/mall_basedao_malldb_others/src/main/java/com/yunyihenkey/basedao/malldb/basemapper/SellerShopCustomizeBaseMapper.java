package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopCustomize;

public interface SellerShopCustomizeBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerShopCustomize record);

    int insertSelective(SellerShopCustomize record);

    SellerShopCustomize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerShopCustomize record);

    int updateByPrimaryKey(SellerShopCustomize record);
}