package com.yunyihenkey.seller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.basedao.malldb.basemapper.SellerShopBankInfoBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopBankInfo;
import com.yunyihenkey.basedao.malldb.basevoEnum.OperationUser.EnableFlagEnum;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerShopBankInfoMapper;
import com.yunyihenkey.seller.service.SellerShopBankInfoService;

@Service
public class SellerShopBankInfoServiceImpl implements SellerShopBankInfoService {
	@Autowired
	private SellerShopBankInfoBaseMapper sellerShopBankInfoBaseMapper;
	@Autowired
	private SellerShopBankInfoMapper sellerShopBankInfoMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sellerShopBankInfoBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SellerShopBankInfo record) {
		return sellerShopBankInfoBaseMapper.insert(record);
	}

	@Override
	public int insertSelective(SellerShopBankInfo record) {
		return sellerShopBankInfoBaseMapper.insertSelective(record);
	}

	@Override
	public SellerShopBankInfo selectByPrimaryKey(Long id) {
		return sellerShopBankInfoBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SellerShopBankInfo record) {
		return sellerShopBankInfoBaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SellerShopBankInfo record) {
		return sellerShopBankInfoBaseMapper.updateByPrimaryKey(record);
	}

	@Override
	public boolean isExistCard(String shopId, String bankCardNumber) {
		return sellerShopBankInfoMapper.isExistCard(shopId, bankCardNumber);
	}

}
