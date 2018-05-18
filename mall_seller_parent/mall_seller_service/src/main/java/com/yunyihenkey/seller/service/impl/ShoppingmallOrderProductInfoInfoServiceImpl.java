package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderInfo;
import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderProductInfo;
import com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderInfo.OrderStatusEnum;
import com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderProductInfo.NoticeSignEnum;
import com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderProductInfo.PostageTypeEnum;
import com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderProductInfo.SendStatusEnum;
import com.yunyihenkey.seller.dao.malldb.exportVo.OrderProductExportVo;
import com.yunyihenkey.seller.dao.malldb.mapper.ShoppingmallOrderInfoMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.ShoppingmallOrderProductInfoMapper;
import com.yunyihenkey.seller.dao.malldb.vo.param.deliverGoodsController.DeliveryParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.orderController.ShoppingmallOrderInfoResult;
import com.yunyihenkey.seller.service.ShoppingmallOrderProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private ShoppingmallOrderInfoMapper shoppingmallOrderInfoMapper;

    @Override
    public List<ShoppingmallOrderProductInfo> selectAllByPage(String mallId, String supplierId, String productName, String receiverName) {
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

    @Override
    public boolean noticeSend(Long id) {
        ShoppingmallOrderProductInfo info = shoppingmallOrderProductInfoMapper.selectByPrimaryKey(id);
        // 非空判断&&未通知判断
        if(null!=info && NoticeSignEnum.NOTICEED.getValue()!=info.getNoticeSign()){
            info.setNoticeSign(NoticeSignEnum.NOTICEED.getValue());
            shoppingmallOrderProductInfoMapper.updateByPrimaryKeySelective(info);
        }
        return true;
    }

    @Override
    public boolean delivery(DeliveryParam deliveryParam) {
        Date current = new Date(System.currentTimeMillis());
        // 获取订单商品对象
        ShoppingmallOrderProductInfo info = shoppingmallOrderProductInfoMapper.selectByPrimaryKey(Long.parseLong(deliveryParam.getOrderProductId()));
        // 获取订单对象
        ShoppingmallOrderInfo orderInfo = shoppingmallOrderInfoMapper.selectByOrderCodeSingle(deliveryParam.getOrderCode());
        // 非空判断&&未发货判断
        if(null!=info && null!=orderInfo && SendStatusEnum.UNSEND.getValue()==info.getSendStatus()){
            info.setExpressNumber(deliveryParam.getExpressNumber());
            info.setExpressCode(deliveryParam.getExpressCode());
            info.setExpressCompany(deliveryParam.getExpressCompany());
            info.setSendStatus(SendStatusEnum.DISTRIBUTION.getValue());
            info.setSendTime(current);
            info.setUpdateTime(current);
            // 获取未发货的订单商品数量
            Integer unsendCount = shoppingmallOrderProductInfoMapper.countUnsendByOrderCode(Long.parseLong(deliveryParam.getOrderCode()));
            // 当未发货数量为'1'时，则说明未发货的商品就是当前商品
            if(1 == unsendCount){
                orderInfo.setOrderStatus(OrderStatusEnum.WAITRECEIVED.getValue());
                orderInfo.setUpdateTime(current);
            }
            shoppingmallOrderProductInfoMapper.updateByPrimaryKeySelective(info);
            shoppingmallOrderInfoMapper.updateByPrimaryKeySelective(orderInfo);
            return true;
        }
        return false;
    }
}
