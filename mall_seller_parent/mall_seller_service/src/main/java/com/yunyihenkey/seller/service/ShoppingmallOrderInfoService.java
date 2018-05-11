package com.yunyihenkey.seller.service;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderInfo;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.seller.dao.malldb.vo.ShoppingmallOrderInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author SunQ
 * @Date 2018/5/7 0007 17:21
 */
public interface ShoppingmallOrderInfoService extends BaseService<ShoppingmallOrderInfo, Long> {

    /**
     * 查询订单列表
     * @author SunQ
     * @Date 15:51 2018/5/11 0011
     * @Param [mallId, orderCode, memberAccount, orderStatus]
     * @return java.util.List<com.yunyihenkey.seller.dao.malldb.vo.ShoppingmallOrderInfoVo>
     */
    List<ShoppingmallOrderInfoVo> selectAllByPage(String mallId, String orderCode, String memberAccount, int[] orderStatus);

    /**
     * 通过订单编号获取订单对象
     * @author SunQ
     * @Date 9:32 2018/5/8 0008
     * @Param [orderCode]
     * @return com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderInfoVo
     */
    ShoppingmallOrderInfoVo selectByOrderCode(@Param("orderCode") String orderCode);

    /**
     * 通过主键ID获取订单对象
     * @author SunQ
     * @Date 18:06 2018/5/7 0007
     * @Param [id]
     * @return com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderInfoVo
     */
    ShoppingmallOrderInfo selectByPrimaryKey(Long id);
}
