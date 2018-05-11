package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basemapper.ShoppingmallOrderProductInfoBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderProductInfo;
import com.yunyihenkey.seller.dao.malldb.exportVo.OrderProductExportVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author SunQ
 * @Date 2018/5/8 0008 14:15
 */

@Repository
public interface ShoppingmallOrderProductInfoMapper extends ShoppingmallOrderProductInfoBaseMapper {

    /**
     * 列表查询
     * @author SunQ
     * @Date 15:59 2018/5/11 0011
     * @Param [mallId, supplierId, productName, receiverName]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String, Object>> selectAll(@Param("mallId") String mallId, @Param("supplierId") String supplierId, @Param("productName") String productName, @Param("receiverName") String receiverName);

    /**
     * 获取订单下的商品集合
     * @author SunQ
     * @Date 10:22 2018/5/9 0009
     * @Param [orderCode]
     * @return java.util.List<com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderProductInfo>
     */
    List<ShoppingmallOrderProductInfo> selectByOrderCode(@Param("orderCode") Long orderCode);

    /**
     * 获取需要导出的对象集合
     * @author SunQ
     * @Date 15:54 2018/5/11 0011
     * @Param [mallId, supplierId, productName, receiverName]
     * @return java.util.List<com.yunyihenkey.seller.dao.malldb.exportVo.OrderProductExportVo>
     */
    List<OrderProductExportVo> selectExportVo(@Param("mallId") String mallId, @Param("supplierId") String supplierId, @Param("productName") String productName, @Param("receiverName") String receiverName);
}
