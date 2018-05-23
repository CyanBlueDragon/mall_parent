package com.yunyihenkey.seller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.basedao.malldb.basemapper.SellerShopMoneyBusinessBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopMoneyBusiness;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerShopMoneyBusinessMapper;
import com.yunyihenkey.seller.service.SellerShopMoneyBusinessService;

@Service
public class SellerShopMoneyBusinessServiceImpl implements SellerShopMoneyBusinessService {
    @Autowired
    private SellerShopMoneyBusinessBaseMapper sellerShopMoneyBusinessBaseMapper;
    @Autowired
    private SellerShopMoneyBusinessMapper sellerShopMoneyBusinessMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sellerShopMoneyBusinessBaseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SellerShopMoneyBusiness record) {
        return sellerShopMoneyBusinessBaseMapper.insert(record);
    }

    @Override
    public int insertSelective(SellerShopMoneyBusiness record) {
        return sellerShopMoneyBusinessBaseMapper.insertSelective(record);
    }

    @Override
    public SellerShopMoneyBusiness selectByPrimaryKey(Long id) {
        return sellerShopMoneyBusinessBaseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SellerShopMoneyBusiness record) {
        return sellerShopMoneyBusinessBaseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SellerShopMoneyBusiness record) {
        return sellerShopMoneyBusinessBaseMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SellerShopMoneyBusiness> getByShopId(Long shopId) {
        return sellerShopMoneyBusinessMapper.getByShopId(shopId);
    }

}
