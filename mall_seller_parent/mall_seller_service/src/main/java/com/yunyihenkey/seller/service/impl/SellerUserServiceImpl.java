package com.yunyihenkey.seller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.basedao.malldb.basemapper.SellerUserBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerUser;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerUserMapper;
import com.yunyihenkey.seller.service.SellerUserService;

/**
 * @author : 007
 * @date : 2018/5/4 15:55
 */
@Service
public class SellerUserServiceImpl implements SellerUserService {
	@Autowired
	private SellerUserBaseMapper sellerUserBaseMapper;
	@Autowired
	private SellerUserMapper sellerUserMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sellerUserBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SellerUser record) {
		return sellerUserBaseMapper.insert(record);
	}

	@Override
	public int insertSelective(SellerUser record) {
		return sellerUserBaseMapper.insertSelective(record);
	}

	@Override
	public SellerUser selectByPrimaryKey(Long id) {
		return sellerUserBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SellerUser record) {
		return sellerUserBaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SellerUser record) {
		return sellerUserBaseMapper.updateByPrimaryKey(record);
	}
}
