package com.yunyihenkey.seller.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopMoneyCash;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetMoneyBusinessChartParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetMoneyBusinessChartResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetTakeCashLogChartParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetTakeCashLogChartResult;

/**
 * 
 * @desc 店铺表提现记录表service
 * @author wulm
 * @date 2018年5月9日 下午5:07:33
 * @version 1.0.0
 */
public interface SellerShopMoneyCashService extends BaseService<SellerShopMoneyCash, Long> {

	List<SellerShopMoneyCash> getByShopId(Long shopId);

	List<GetTakeCashLogChartResult> getTakeCashLogChart(Long shopId, GetTakeCashLogChartParam param)
			throws InstantiationException, IllegalAccessException, ParseException;

	List<GetMoneyBusinessChartResult> getMoneyBusinessChart(Long shopId, GetMoneyBusinessChartParam param)
			throws InstantiationException, IllegalAccessException, ParseException;

}
