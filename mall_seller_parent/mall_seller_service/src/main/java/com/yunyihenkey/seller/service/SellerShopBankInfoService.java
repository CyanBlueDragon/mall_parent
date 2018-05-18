package com.yunyihenkey.seller.service;

import java.util.List;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopBankInfo;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetBankListResult;

/**
 * 
 * @desc 商户银行卡信息表service
 * @author wulm
 * @date 2018年5月9日 下午5:07:33
 * @version 1.0.0
 */
public interface SellerShopBankInfoService extends BaseService<SellerShopBankInfo, Long> {

	boolean isExistCard(Long shopId, String bankCardNumber);

	List<GetBankListResult> getBankCardList(Long shopId);

}
