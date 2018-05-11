package com.yunyihenkey.seller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.basedao.malldb.basemapper.SellerRoleBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerRole;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerRoleMapper;
import com.yunyihenkey.seller.service.SellerRoleService;

/**
 * @author : 007
 * @date : 2018/5/4 16:51
 */
@Service
public class SellerRoleServiceImpl implements SellerRoleService {
	@Autowired
	private SellerRoleBaseMapper sellerRoleBaseMapper;

	@Autowired
	private SellerRoleMapper sellerRoleMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sellerRoleBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SellerRole record) {
		return sellerRoleBaseMapper.insert(record);
	}

	@Override
	public int insertSelective(SellerRole record) {
		return sellerRoleBaseMapper.insertSelective(record);
	}

	@Override
	public SellerRole selectByPrimaryKey(Long id) {
		return sellerRoleBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SellerRole record) {
		return sellerRoleBaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SellerRole record) {
		return sellerRoleBaseMapper.updateByPrimaryKey(record);
	}

}
