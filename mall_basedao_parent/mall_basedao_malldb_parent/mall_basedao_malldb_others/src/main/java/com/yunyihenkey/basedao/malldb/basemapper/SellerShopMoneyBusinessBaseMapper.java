package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopMoneyBusiness;

public interface SellerShopMoneyBusinessBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerShopMoneyBusiness record);

    int insertSelective(SellerShopMoneyBusiness record);

    SellerShopMoneyBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerShopMoneyBusiness record);

    int updateByPrimaryKey(SellerShopMoneyBusiness record);
}