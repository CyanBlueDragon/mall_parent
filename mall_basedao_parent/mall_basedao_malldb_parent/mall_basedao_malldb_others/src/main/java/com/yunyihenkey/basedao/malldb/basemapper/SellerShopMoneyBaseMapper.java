package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopMoney;

public interface SellerShopMoneyBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerShopMoney record);

    int insertSelective(SellerShopMoney record);

    SellerShopMoney selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerShopMoney record);

    int updateByPrimaryKey(SellerShopMoney record);
}