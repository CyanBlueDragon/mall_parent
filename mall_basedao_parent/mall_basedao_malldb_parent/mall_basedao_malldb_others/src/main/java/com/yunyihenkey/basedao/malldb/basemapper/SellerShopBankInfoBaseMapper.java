package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopBankInfo;

public interface SellerShopBankInfoBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerShopBankInfo record);

    int insertSelective(SellerShopBankInfo record);

    SellerShopBankInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerShopBankInfo record);

    int updateByPrimaryKey(SellerShopBankInfo record);
}