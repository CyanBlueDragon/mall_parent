package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderInfo;
import com.yunyihenkey.seller.dao.malldb.mapper.ShoppingmallOrderInfoMapper;
import com.yunyihenkey.seller.dao.malldb.vo.param.orderController.ShoppingmallOrderInfoResult;
import com.yunyihenkey.seller.service.ShoppingmallOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author SunQ
 * @Date 2018/5/7 0007 17:22
 */
@Service
public class ShoppingmallOrderInfoServiceImpl implements ShoppingmallOrderInfoService {

    @Autowired
    private ShoppingmallOrderInfoMapper shoppingmallOrderInfoMapper;

    @Override
    public List<ShoppingmallOrderInfoResult> selectAllByPage(String mallId, String orderCode, String memberAccount, int[] orderStatus) {
        return shoppingmallOrderInfoMapper.selectAll(mallId, orderCode, memberAccount, orderStatus);
    }

    @Override
    public ShoppingmallOrderInfoResult selectByOrderCode(String orderCode) {
        return shoppingmallOrderInfoMapper.selectByOrderCode(orderCode);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return shoppingmallOrderInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ShoppingmallOrderInfo record) {
        return shoppingmallOrderInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(ShoppingmallOrderInfo record) {
        return shoppingmallOrderInfoMapper.insertSelective(record);
    }

    @Override
    public ShoppingmallOrderInfo selectByPrimaryKey(Long id) {
        return shoppingmallOrderInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ShoppingmallOrderInfo record) {
        return shoppingmallOrderInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ShoppingmallOrderInfo record) {
        return shoppingmallOrderInfoMapper.updateByPrimaryKey(record);
    }
}
