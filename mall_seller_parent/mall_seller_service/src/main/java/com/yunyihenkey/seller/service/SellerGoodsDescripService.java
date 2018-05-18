package com.yunyihenkey.seller.service;

import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsDescrip;
import com.yunyihenkey.common.vo.base.BaseService;

/**
 * 
 * @desc:分销商商品描述service 
 * @author: zhouh
 * @date: 2018年5月10日 上午9:23:37 
 *
 */
public interface SellerGoodsDescripService extends BaseService<SellerGoodsDescrip, Long>{
	
	/**
	 * 
	 * @Desc: 修改一条数据
	 * @param record
	 * @return int
	 */
    int updateByPrimaryKeyWithBLOBs(SellerGoodsDescrip record);

}