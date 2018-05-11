package com.yunyihenkey.seller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.basedao.malldb.basemapper.SellerShopBusinessLogBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopBusinessLog;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerShopBusinessLogMapper;
import com.yunyihenkey.seller.service.SellerShopBusinessLogService;

@Service
public class SellerShopBusinessLogServiceImpl implements SellerShopBusinessLogService {
	@Autowired
	private SellerShopBusinessLogBaseMapper sellerShopBusinessLogBaseMapper;
	@Autowired
	private SellerShopBusinessLogMapper sellerShopBusinessLogMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sellerShopBusinessLogBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SellerShopBusinessLog record) {
		return sellerShopBusinessLogBaseMapper.insert(record);
	}

	@Override
	public int insertSelective(SellerShopBusinessLog record) {
		return sellerShopBusinessLogBaseMapper.insertSelective(record);
	}

	@Override
	public SellerShopBusinessLog selectByPrimaryKey(Long id) {
		return sellerShopBusinessLogBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SellerShopBusinessLog record) {
		return sellerShopBusinessLogBaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SellerShopBusinessLog record) {
		return sellerShopBusinessLogBaseMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SellerShopBusinessLog> getByShopId(Long shopId) {
		return sellerShopBusinessLogMapper.getByShopId(shopId);
	}

}
