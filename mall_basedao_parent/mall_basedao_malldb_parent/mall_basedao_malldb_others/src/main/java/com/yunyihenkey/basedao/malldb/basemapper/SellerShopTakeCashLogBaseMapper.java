package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopTakeCashLog;

public interface SellerShopTakeCashLogBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerShopTakeCashLog record);

    int insertSelective(SellerShopTakeCashLog record);

    SellerShopTakeCashLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerShopTakeCashLog record);

    int updateByPrimaryKey(SellerShopTakeCashLog record);
}