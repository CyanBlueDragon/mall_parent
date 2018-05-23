package com.yunyihenkey.seller.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopMoneyCash;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetTakeCashLogChartResult;

/**
 * @author wulm
 * @version 1.0.0
 * @desc 店铺表提现记录表service
 * @date 2018年5月9日 下午5:07:33
 */
public interface SellerShopMoneyCashService extends BaseService<SellerShopMoneyCash, Long> {

    List<SellerShopMoneyCash> getByShopId(Long shopId);

    List<GetTakeCashLogChartResult> getTakeCashLogChart(Long shopId, Date startDate) throws InstantiationException, IllegalAccessException, ParseException;

}
