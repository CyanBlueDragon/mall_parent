package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderProductInfo;
import com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderProductInfo.PostageTypeEnum;
import com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderProductInfo.SendStatusEnum;
import com.yunyihenkey.seller.dao.malldb.exportVo.OrderProductExportVo;
import com.yunyihenkey.seller.dao.malldb.mapper.ShoppingmallOrderProductInfoMapper;
import com.yunyihenkey.seller.service.ShoppingmallOrderProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author SunQ
 * @Date 2018/5/8 0008 14:43
 */
@Service
public class ShoppingmallOrderProductInfoInfoServiceImpl implements ShoppingmallOrderProductInfoService {

    @Autowired
    private ShoppingmallOrderProductInfoMapper shoppingmallOrderProductInfoMapper;

    @Override
    public List<Map<String, Object>> selectAllByPage(String mallId, String supplierId, String productName, String receiverName) {
        return shoppingmallOrderProductInfoMapper.selectAll(mallId, supplierId, productName, receiverName);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return shoppingmallOrderProductInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ShoppingmallOrderProductInfo record) {
        return shoppingmallOrderProductInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(ShoppingmallOrderProductInfo shoppingmallOrderProductInfo) {
        return shoppingmallOrderProductInfoMapper.insertSelective(shoppingmallOrderProductInfo);
    }

    @Override
    public ShoppingmallOrderProductInfo selectByPrimaryKey(Long id) {
        return shoppingmallOrderProductInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ShoppingmallOrderProductInfo record) {
        return shoppingmallOrderProductInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ShoppingmallOrderProductInfo record) {
        return shoppingmallOrderProductInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<OrderProductExportVo> selectExportVo(String mallId, String supplierId, String productName, String receiverName) {
        List<OrderProductExportVo> list = shoppingmallOrderProductInfoMapper.selectExportVo(mallId, supplierId, productName, receiverName);
        for(OrderProductExportVo vo : list){
            vo.setSendStatus(SendStatusEnum.getByValue(Integer.parseInt(vo.getSendStatus())).getText());
            vo.setPostageType(PostageTypeEnum.getByValue(Integer.parseInt(vo.getPostageType())).getText());
        }
        return list;
    }
}
