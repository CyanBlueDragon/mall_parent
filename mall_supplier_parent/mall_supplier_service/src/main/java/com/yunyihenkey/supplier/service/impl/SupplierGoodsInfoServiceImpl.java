package com.yunyihenkey.supplier.service.impl;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.supplier.dao.malldb.mapper.SupplierGoodsDescripMapper;
import com.yunyihenkey.supplier.dao.malldb.mapper.SupplierGoodsInfoMapper;
import com.yunyihenkey.supplier.dao.malldb.vo.param.Controller.SupplierGoodsAddParam;
import com.yunyihenkey.supplier.service.SupplierGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SupplierGoodsInfoServiceImpl
 * @Description 商品详情
 * @Author LuTong
 * @Date 2018/5/11 14:54
 * @Version 1.0
 */

@Service
public class SupplierGoodsInfoServiceImpl implements SupplierGoodsInfoService {

    @Autowired(required = false)
    private SupplierGoodsInfoMapper supplierGoodsInfoMapper;

    @Autowired(required = false)
    private SupplierGoodsDescripMapper supplierGoodsDescripMapper;

    @Override
    public int getCount(Long id) {
        return supplierGoodsInfoMapper.getCount(id);
    }

    @Override
    public List<SupplierGoodsInfo> selectAll(Long id) {
        LogUtils.getLogger().info("分页查询商品信息......");
        return supplierGoodsInfoMapper.selectAll(id);
    }

    @Override
    public List selectByGoodsId(Long id) {
        LogUtils.getLogger().info("根据商品id查询商品详情......");
        List<Object> list = new ArrayList<>();
        list.add(0, supplierGoodsInfoMapper.selectByPrimaryKey(id));
        list.add(1, supplierGoodsDescripMapper.selectByGoodsId(id));
        return list;
    }

    @Override
    public Long supplierInsertGoods(SupplierGoodsAddParam goodsAddParam) {
        LogUtils.getLogger().info("供货商进行商品信息上传......");
        supplierGoodsInfoMapper.supplierInsertGoods(goodsAddParam);
        return goodsAddParam.getId();
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
