package com.yunyihenkey.seller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.basedao.malldb.basemapper.SellerShopTakeCashLogBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopTakeCashLog;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerShopTakeCashLogMapper;
import com.yunyihenkey.seller.service.SellerShopTakeCashLogService;

@Service
public class SellerShopTakeCashLogServiceImpl implements SellerShopTakeCashLogService {
	@Autowired
	private SellerShopTakeCashLogBaseMapper sellerShopTakeCashLogBaseMapper;
	@Autowired
	private SellerShopTakeCashLogMapper sellerShopTakeCashLogMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sellerShopTakeCashLogBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SellerShopTakeCashLog record) {
		return sellerShopTakeCashLogBaseMapper.insert(record);
	}

	@Override
	public int insertSelective(SellerShopTakeCashLog record) {
		return sellerShopTakeCashLogBaseMapper.insertSelective(record);
	}

	@Override
	public SellerShopTakeCashLog selectByPrimaryKey(Long id) {
		return sellerShopTakeCashLogBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SellerShopTakeCashLog record) {
		return sellerShopTakeCashLogBaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SellerShopTakeCashLog record) {
		return sellerShopTakeCashLogBaseMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SellerShopTakeCashLog> getByShopId(Long shopId) {
		return sellerShopTakeCashLogMapper.getByShopId(shopId);
	}

}
