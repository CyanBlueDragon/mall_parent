package com.yunyihenkey.seller.service;

import java.util.List;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopBusinessLog;
import com.yunyihenkey.common.vo.base.BaseService;

/**
 * 
 * @desc 店铺表交易记录表service
 * @author wulm
 * @date 2018年5月9日 下午5:07:33
 * @version 1.0.0
 */
public interface SellerShopBusinessLogService extends BaseService<SellerShopBusinessLog, Long> {

	List<SellerShopBusinessLog> getByShopId(Long shopId);

}
