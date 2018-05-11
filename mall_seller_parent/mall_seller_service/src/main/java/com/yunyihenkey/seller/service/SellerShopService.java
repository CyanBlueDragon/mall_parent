
package com.yunyihenkey.seller.service;

import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.common.vo.base.BaseService;

/**
 * @author : 007
 * @date : 2018/5/8 16:49
 */
public interface SellerShopService extends BaseService<SellerShop, Long> {

    /**
     * 修改支付方式
     * @param id
     * @param payMethod
     * @param isEnable
     * @throws Exception
     */
    void updatePay(Long id,Integer payMethod,Integer isEnable)throws Exception;

}