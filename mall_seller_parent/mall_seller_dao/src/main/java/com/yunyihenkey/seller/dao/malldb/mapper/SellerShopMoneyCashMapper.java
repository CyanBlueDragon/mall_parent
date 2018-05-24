package com.yunyihenkey.seller.dao.malldb.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopMoneyCash;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetMoneyBusinessChartResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetTakeCashLogChartResult;

public interface SellerShopMoneyCashMapper {

    List<SellerShopMoneyCash> getByShopId(@Param("shopId") Long shopId);

    List<GetTakeCashLogChartResult> getTakeCashLogChart(@Param("shopId") Long shopId,
                                                        @Param("startDate") Date startDate);

	List<GetMoneyBusinessChartResult> getMoneyBusinessChart(@Param("shopId") Long shopId,
			@Param("startDate") Date startDate, @Param("mysqlDateFormet") String mysqlDateFormet);

}