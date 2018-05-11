package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopBankInfoLog;

public interface SellerShopBankInfoLogBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerShopBankInfoLog record);

    int insertSelective(SellerShopBankInfoLog record);

    SellerShopBankInfoLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerShopBankInfoLog record);

    int updateByPrimaryKey(SellerShopBankInfoLog record);
}