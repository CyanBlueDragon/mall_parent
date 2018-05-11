package com.yunyihenkey.seller.dao.malldb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopBusinessLog;

public interface SellerShopBusinessLogMapper {

	List<SellerShopBusinessLog> getByShopId(@Param("shopId") Long shopId);

}