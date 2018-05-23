package com.yunyihenkey.seller.service;

import java.util.List;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopMoneyBusiness;
import com.yunyihenkey.common.vo.base.BaseService;

/**
 * @author wulm
 * @version 1.0.0
 * @desc 店铺表交易记录表service
 * @date 2018年5月9日 下午5:07:33
 */
public interface SellerShopMoneyBusinessService extends BaseService<SellerShopMoneyBusiness, Long> {

    List<SellerShopMoneyBusiness> getByShopId(Long shopId);
}
