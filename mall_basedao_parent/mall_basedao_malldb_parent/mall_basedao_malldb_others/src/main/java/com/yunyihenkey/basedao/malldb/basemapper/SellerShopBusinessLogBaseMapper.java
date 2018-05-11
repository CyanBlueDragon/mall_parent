package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopBusinessLog;

public interface SellerShopBusinessLogBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerShopBusinessLog record);

    int insertSelective(SellerShopBusinessLog record);

    SellerShopBusinessLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerShopBusinessLog record);

    int updateByPrimaryKey(SellerShopBusinessLog record);
}