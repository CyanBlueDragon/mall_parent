package com.yunyihenkey.supplier.service.impl;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.supplier.dao.malldb.mapper.SupplierGoodsInfoMapper;
import com.yunyihenkey.supplier.service.SupplierGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName SupplierGoodsInfoServiceImpl
 * @Description TODO
 * @Author LuTong
 * @Date 2018/5/11 14:54
 * @Version 1.0
 */
public class SupplierGoodsInfoServiceImpl implements SupplierGoodsInfoService {

    @Autowired
    private SupplierGoodsInfoMapper supplierGoodsInfoMapper;

    @Override
    public int getCount(Long id) {
        return supplierGoodsInfoMapper.getCount(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(SupplierGoodsInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(SupplierGoodsInfo record) {
        return 0;
    }

    @Override
    public SupplierGoodsInfo selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SupplierGoodsInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SupplierGoodsInfo record) {
        return 0;
    }
}
