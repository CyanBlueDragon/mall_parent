
package com.yunyihenkey.seller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.basedao.malldb.basemapper.SellerShopBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerShopMapper;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetShopInfoResult;
import com.yunyihenkey.seller.service.SellerShopService;

@Service
public class SellerShopServiceImpl implements SellerShopService {
	@Autowired
	private SellerShopBaseMapper sellerShopBaseMapper;
	@Autowired
	private SellerShopMapper sellerShopMapper;

	@Override
	public void updatePay(Long id, Integer payMethod, Integer isEnable) throws Exception {
		SellerShop sellerShop = new SellerShop();
		switch (payMethod) {
		case 0: // 支付宝
			sellerShop.setPayAli(isEnable);
			break;
		case 1: // 微信
			sellerShop.setPayWx(isEnable);
			break;
		case 2: // 银联
			sellerShop.setPayUnion(isEnable);
			break;
		}
		sellerShop.setId(id);
		sellerShop.setCreateTime(DateUtil.getCurrentDate());
		updateByPrimaryKeySelective(sellerShop);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sellerShopBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SellerShop record) {
		return sellerShopBaseMapper.insert(record);
	}

	@Override
	public int insertSelective(SellerShop record) {
		return sellerShopBaseMapper.insertSelective(record);
	}

	@Override
	public SellerShop selectByPrimaryKey(Long id) {
		return sellerShopBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SellerShop record) {
		return sellerShopBaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SellerShop record) {
		return sellerShopBaseMapper.updateByPrimaryKey(record);
	}

	@Override
	public boolean isSetPayPwd(Long shopId) {
		return sellerShopMapper.isSetPayPwd(shopId);
	}

	@Override
	public void updateCommonSet(Long id, Long unpaidTime, Integer confirmOrderTime, Integer refundTime,
			Integer sellOutTime, Integer returnOrderTime) throws Exception {
		SellerShop sellerShop = new SellerShop();
		sellerShop.setId(id);
		sellerShop.setUnpaidTime(unpaidTime);
		sellerShop.setConfirmOrderTime(confirmOrderTime);
		sellerShop.setRefundTime(refundTime);
		sellerShop.setSellOutTime(sellOutTime);
		sellerShop.setReturnOrderTime(returnOrderTime);
		sellerShop.setCreateTime(DateUtil.getCurrentDate());
		updateByPrimaryKeySelective(sellerShop);
	}

	@Override
	public GetShopInfoResult getShopInfoForAccount(Long shopId) {
		return sellerShopMapper.getShopInfoForAccount(shopId);
	}

}
