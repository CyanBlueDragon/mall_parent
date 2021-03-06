package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.seller.dao.malldb.vo.param.decorationShopController.GetShopTemplateResult;
import org.apache.ibatis.annotations.Param;

import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetShopInfoResult;

public interface SellerShopMapper {

	boolean isSetPayPwd(@Param("shopId") Long shopId);

	GetShopInfoResult getShopInfoForAccount(@Param("shopId") Long shopId);

	GetShopTemplateResult getShopTemplate(@Param("shopId") Long shopId);


}