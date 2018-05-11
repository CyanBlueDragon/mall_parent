package com.yunyihenkey.seller.dao.malldb.mapper;

import org.apache.ibatis.annotations.Param;

public interface SellerShopBankInfoMapper {

	boolean isExistCard(@Param("shopId") String shopId, @Param("bankCardNumber") String bankCardNumber);

}