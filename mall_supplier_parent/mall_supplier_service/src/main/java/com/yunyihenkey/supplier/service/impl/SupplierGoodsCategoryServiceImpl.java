package com.yunyihenkey.supplier.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.supplier.dao.malldb.mapper.SupplierGoodsCategoryMapper;
import com.yunyihenkey.supplier.dao.malldb.mapper.SupplierGoodsInfoMapper;
import com.yunyihenkey.supplier.service.SupplierGoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierGoodsCategoryServiceImpl implements SupplierGoodsCategoryService {

    @Autowired(required = false)
    private SupplierGoodsCategoryMapper supplierGoodsCategoryMapper;

    @Autowired(required = false)
    private SupplierGoodsInfoMapper supplierGoodsInfoMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<SupplierGoodsCategory> selectAll() {
        try {
            Object json = redisUtil.get("CATEGORY_KEY");
            if (json != null) {
                return (List<SupplierGoodsCategory>) json;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SupplierGoodsCategory> list = supplierGoodsCategoryMapper.selectAll();
        for (SupplierGoodsCategory supplierGoodsCategory : list) {
            int count = supplierGoodsInfoMapper.getCount(supplierGoodsCategory.getId());
            supplierGoodsCategory.setCount((long) count);
        }
        try {
            redisUtil.set("CATEGORY_KEY", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<SupplierGoodsCategory> selectByName(String name) {
        List<SupplierGoodsCategory> list = supplierGoodsCategoryMapper.selectByName(name);
        for (SupplierGoodsCategory supplierGoodsCategory : list) {
            int count = supplierGoodsInfoMapper.getCount(supplierGoodsCategory.getId());
            supplierGoodsCategory.setCount((long) count);
        }
        return list;
    }

    @Override
    public int insertSelective(SupplierGoodsCategory record) {
        int selective = supplierGoodsCategoryMapper.insertSelective(record);
        try {
            redisUtil.del("CATEGORY_KEY");
        }catch (Exception e){
            e.printStackTrace();
        }
        return selective;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(SupplierGoodsCategory record) {
        return 0;
    }

    @Override
    public SupplierGoodsCategory selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SupplierGoodsCategory record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SupplierGoodsCategory record) {
        return 0;
    }


}
