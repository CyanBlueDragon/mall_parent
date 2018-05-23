package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basemapper.ShoppingmallOrderInfoBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderInfo;
import com.yunyihenkey.seller.dao.malldb.vo.param.orderController.OrderInfoResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author SunQ
 * @Date 2018/5/7 0007 17:07
 */
@Repository
public interface ShoppingmallOrderInfoMapper extends ShoppingmallOrderInfoBaseMapper {

    /**
     * 查询订单列表
     * @author SunQ
     * @Date 15:52 2018/5/11 0011
     * @Param [mallId, orderCode, memberAccount, orderStatus]
     * @return java.util.List<com.yunyihenkey.seller.dao.malldb.vo.param.order.OrderInfoResult>
     */
    List<OrderInfoResult> selectAll(@Param("mallId") String mallId, @Param("orderCode") String orderCode, @Param("memberAccount") String memberAccount, @Param("orderStatus") int[] orderStatus);

    /**
     * 通过订单编号获取订单对象(返回订单信息+订单商品集合)
     * @author SunQ
     * @Date 9:32 2018/5/8 0008
     * @Param [orderCode]
     * @return com.yunyihenkey.seller.dao.malldb.vo.param.order.OrderInfoResult
     */
    OrderInfoResult selectByOrderCode(@Param("orderCode") String orderCode);

    /**
     * 通过订单编号获取订单对象(只返回订单信息)
     * @author SunQ
     * @Date 10:50 2018/5/18
     * @Param [orderCode]
     * @return com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderInfo
     */
    ShoppingmallOrderInfo selectByOrderCodeSingle(@Param("orderCode") String orderCode);
}
