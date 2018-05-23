package com.yunyihenkey.seller.dao.malldb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopMoneyBusiness;

public interface SellerShopMoneyBusinessMapper {

    List<SellerShopMoneyBusiness> getByShopId(@Param("shopId") Long shopId);

}