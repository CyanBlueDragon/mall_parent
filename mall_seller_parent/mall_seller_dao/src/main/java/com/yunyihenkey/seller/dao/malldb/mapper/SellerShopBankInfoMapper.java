package com.yunyihenkey.seller.dao.malldb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetBankListResult;

public interface SellerShopBankInfoMapper {

	boolean isExistCard(@Param("shopId") Long shopId, @Param("bankCardNumber") String bankCardNumber);

	List<GetBankListResult> getBankCardList(@Param("shopId")Long shopId);

}