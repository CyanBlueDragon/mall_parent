package com.yunyihenkey.seller.service;

import java.util.List;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopTakeCashLog;
import com.yunyihenkey.common.vo.base.BaseService;

/**
 * 
 * @desc 店铺表提现记录表service
 * @author wulm
 * @date 2018年5月9日 下午5:07:33
 * @version 1.0.0
 */
public interface SellerShopTakeCashLogService extends BaseService<SellerShopTakeCashLog, Long> {

	List<SellerShopTakeCashLog> getByShopId(Long shopId);

}
