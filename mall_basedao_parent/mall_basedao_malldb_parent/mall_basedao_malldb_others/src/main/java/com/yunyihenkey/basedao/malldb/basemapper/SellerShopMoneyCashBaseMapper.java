package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopMoneyCash;

public interface SellerShopMoneyCashBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerShopMoneyCash record);

    int insertSelective(SellerShopMoneyCash record);

    SellerShopMoneyCash selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerShopMoneyCash record);

    int updateByPrimaryKey(SellerShopMoneyCash record);
}