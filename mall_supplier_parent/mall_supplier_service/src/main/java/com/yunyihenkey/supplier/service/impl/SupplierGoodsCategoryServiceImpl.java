package com.yunyihenkey.supplier.service.impl;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.supplier.dao.malldb.mapper.SupplierGoodsCategoryMapper;
import com.yunyihenkey.supplier.dao.malldb.mapper.SupplierGoodsInfoMapper;
import com.yunyihenkey.supplier.service.SupplierGoodsCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @ClassName SupplierGoodsCategoryServiceImpl
 * @Description 商品分类详情
 * @Author LuTong
 * @Date 2018/5/11 14:54
 * @Version 1.0
 */
@Service
public class SupplierGoodsCategoryServiceImpl implements SupplierGoodsCategoryService {

    @Autowired(required = false)
    private SupplierGoodsCategoryMapper supplierGoodsCategoryMapper;

    @Autowired(required = false)
    private SupplierGoodsInfoMapper supplierGoodsInfoMapper;

    @Autowired
    private RedisUtil redisUtil;

    public List<SupplierGoodsCategory> getList(String name) {
        List<SupplierGoodsCategory> list = supplierGoodsCategoryMapper.selectByName(name);
        for (SupplierGoodsCategory supplierGoodsCategory : list) {
            int count = supplierGoodsInfoMapper.getCount(supplierGoodsCategory.getId());
            supplierGoodsCategory.setCount((long) count);
        }
        return list;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<SupplierGoodsCategory> selectByName(String name) {
        LogUtils.getLogger().info("查询商品分类信息.....");
        if (StringUtils.isBlank(name)) {
            try {
                Object json = redisUtil.get("CATEGORY_KEY");
                if (json != null) {
                    return (List<SupplierGoodsCategory>) json;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<SupplierGoodsCategory> list = getList(name);
            try {
                if (list.size() > 0) {
                    redisUtil.set("CATEGORY_KEY", list);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        } else {
            List<SupplierGoodsCategory> list = getList(name);
            return list;
        }
    }


    @Override
    public int insertSelective(SupplierGoodsCategory record) {
        LogUtils.getLogger().info("新增商品分类.....");
        int selective = supplierGoodsCategoryMapper.insertSelective(record);
        try {
            redisUtil.del("CATEGORY_KEY");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selective;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public int deleteByPrimaryKey(Long id) {
        LogUtils.getLogger().info("删除商品分类.....");
        supplierGoodsInfoMapper.updateCategory(id);
        int i = supplierGoodsCategoryMapper.deleteByPrimaryKey(id);
        try {
            redisUtil.del("CATEGORY_KEY");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int updateByPrimaryKeySelective(SupplierGoodsCategory record) {
        LogUtils.getLogger().info("修改商品分类.....");
        int i = supplierGoodsCategoryMapper.updateByPrimaryKeySelective(record);
        try {
            redisUtil.del("CATEGORY_KEY");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
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
    public int updateByPrimaryKey(SupplierGoodsCategory record) {
        return 0;
    }


}
