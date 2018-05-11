package com.yunyihenkey.seller.dao.malldb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopTakeCashLog;

public interface SellerShopTakeCashLogMapper {

	List<SellerShopTakeCashLog> getByShopId(@Param("shopId") Long shopId);

}