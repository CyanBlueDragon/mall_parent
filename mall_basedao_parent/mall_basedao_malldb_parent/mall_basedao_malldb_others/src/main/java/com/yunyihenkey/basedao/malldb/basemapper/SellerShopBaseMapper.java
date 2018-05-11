package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShop;

public interface SellerShopBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerShop record);

    int insertSelective(SellerShop record);

    SellerShop selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerShop record);

    int updateByPrimaryKey(SellerShop record);
}