package com.yunyihenkey.seller.service;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderProductInfo;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.seller.dao.malldb.exportVo.OrderProductExportVo;

import java.util.List;
import java.util.Map;

/**
 * @Author SunQ
 * @Date 2018/5/8 0008 14:42
 */
public interface ShoppingmallOrderProductInfoService extends BaseService<ShoppingmallOrderProductInfo, Long> {

    /**
     * 列表查询
     * @author SunQ
     * @Date 15:57 2018/5/11 0011
     * @Param [mallId, supplierId, productName, receiverName]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String, Object>> selectAllByPage(String mallId, String supplierId, String productName, String receiverName);

    /**
     * 新增
     * @author SunQ
     * @Date 15:16 2018/5/8 0008
     * @Param [shoppingmallOrderProductInfo]
     * @return int
     */
    int insertSelective(ShoppingmallOrderProductInfo shoppingmallOrderProductInfo);

    /**
     * 通过主键获得对象
     * @author SunQ
     * @Date 10:07 2018/5/9 0009
     * @Param [id]
     * @return com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderProductInfo
     */
    ShoppingmallOrderProductInfo selectByPrimaryKey(Long id);

    /**
     * 获取导出Excel对象集合
     * @author SunQ
     * @Date 15:59 2018/5/11 0011
     * @Param [mallId, supplierId, productName, receiverName]
     * @return java.util.List<com.yunyihenkey.seller.dao.malldb.exportVo.OrderProductExportVo>
     */
    List<OrderProductExportVo> selectExportVo(String mallId, String supplierId, String productName, String receiverName);
}
