
package com.yunyihenkey.seller.service;

import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetShopInfoResult;

/**
 * @author : 007
 * @date : 2018/5/8 16:49
 */
public interface SellerShopService extends BaseService<SellerShop, Long> {

	/**
	 * 修改支付方式
	 *
	 * @param id
	 * @param payMethod
	 * @param isEnable
	 * @throws Exception
	 */
	void updatePay(Long id, Integer payMethod, Integer isEnable) throws Exception;

	/**
	 * 
	 * @desc 员工是否设置了支付密码
	 * @auth wulm
	 * @date 2018年5月12日 下午2:50:13
	 * @param shopId
	 * @return
	 */
	boolean isSetPayPwd(Long shopId);

	/**
	 * 修改店铺通用设置
	 *
	 * @param id
	 * @param unpaidTime
	 * @param confirmOrderTime
	 * @param refundTime
	 * @param sellOutTime
	 * @param returnOrderTime
	 * @throws Exception
	 */
	void updateCommonSet(Long id, Long unpaidTime, Integer confirmOrderTime, Integer refundTime, Integer sellOutTime,
			Integer returnOrderTime) throws Exception;

	GetShopInfoResult getShopInfoForAccount(Long shopId);

}