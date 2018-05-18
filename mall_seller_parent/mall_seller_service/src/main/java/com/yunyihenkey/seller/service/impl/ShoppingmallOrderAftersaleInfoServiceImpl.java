package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderAftersaleInfo;
import com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderAftersaleInfo.AftersaleStatusEnum;
import com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderProductInfo.SendStatusEnum;
import com.yunyihenkey.seller.dao.malldb.exportVo.OrderAftersaleExportVo;
import com.yunyihenkey.seller.dao.malldb.mapper.ShoppingmallOrderAftersaleInfoMapper;
import com.yunyihenkey.seller.service.ShoppingmallOrderAftersaleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author SunQ
 * @Date 2018/5/9 0009 15:02
 */
@Service
public class ShoppingmallOrderAftersaleInfoServiceImpl implements ShoppingmallOrderAftersaleInfoService {

    @Autowired
    private ShoppingmallOrderAftersaleInfoMapper shoppingmallOrderAftersaleInfoMapper;

    @Override
    public List<ShoppingmallOrderAftersaleInfo> selectAllByPage(String mallId, String orderCode, String memberAccount, String[] aftersaleStatus) {
        return shoppingmallOrderAftersaleInfoMapper.selectAll(mallId, orderCode, memberAccount, aftersaleStatus);
    }

    @Override
    public List<OrderAftersaleExportVo> selectExportVo(String mallId, String orderCode, String memberAccount, String[] aftersaleStatus) {
        List<OrderAftersaleExportVo> list = shoppingmallOrderAftersaleInfoMapper.selectExportVo(mallId, orderCode, memberAccount, aftersaleStatus);
        for(OrderAftersaleExportVo vo : list){
            vo.setAftersaleStatus(SendStatusEnum.getByValue(Integer.parseInt(vo.getAftersaleStatus())).getText());
        }
        return list;
    }

    @Override
    public boolean adopt(Long id) {
        ShoppingmallOrderAftersaleInfo info = shoppingmallOrderAftersaleInfoMapper.selectByPrimaryKey(id);
        if(null!=info){
            info.setAftersaleStatus(AftersaleStatusEnum.REFUNDING.getValue());
            shoppingmallOrderAftersaleInfoMapper.updateByPrimaryKeySelective(info);
        }
        return true;
    }

    @Override
    public boolean unadopt(Long id) {
        ShoppingmallOrderAftersaleInfo info = shoppingmallOrderAftersaleInfoMapper.selectByPrimaryKey(id);
        if(null!=info){
            info.setAftersaleStatus(AftersaleStatusEnum.NOPASS.getValue());
            shoppingmallOrderAftersaleInfoMapper.updateByPrimaryKeySelective(info);
        }
        return true;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return shoppingmallOrderAftersaleInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ShoppingmallOrderAftersaleInfo record) {
        return shoppingmallOrderAftersaleInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(ShoppingmallOrderAftersaleInfo record) {
        return shoppingmallOrderAftersaleInfoMapper.insertSelective(record);
    }

    @Override
    public ShoppingmallOrderAftersaleInfo selectByPrimaryKey(Long id) {
        return shoppingmallOrderAftersaleInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ShoppingmallOrderAftersaleInfo record) {
        return shoppingmallOrderAftersaleInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ShoppingmallOrderAftersaleInfo record) {
        return shoppingmallOrderAftersaleInfoMapper.updateByPrimaryKey(record);
    }
}
