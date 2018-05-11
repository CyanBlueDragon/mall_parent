package com.yunyihenkey.seller.service;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderAftersaleInfo;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.seller.dao.malldb.exportVo.OrderAftersaleExportVo;

import java.util.List;
import java.util.Map;

/**
 * @Author SunQ
 * @Date 2018/5/9 0009 15:00
 */
public interface ShoppingmallOrderAftersaleInfoService extends BaseService<ShoppingmallOrderAftersaleInfo, Long> {

    /**
     * 列表查询
     * @author SunQ
     * @Date 16:04 2018/5/11 0011
     * @Param [mallId, orderCode, memberAccount, aftersaleStatus]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String, Object>> selectAllByPage(String mallId, String orderCode, String memberAccount, String[] aftersaleStatus);

    /**
     * 获取需要导出的对象集合
     * @author SunQ
     * @Date 16:04 2018/5/11 0011
     * @Param [mallId, orderCode, memberAccount, aftersaleStatus]
     * @return java.util.List<com.yunyihenkey.seller.dao.malldb.exportVo.OrderAftersaleExportVo>
     */
    List<OrderAftersaleExportVo> selectExportVo(String mallId, String orderCode, String memberAccount, String[] aftersaleStatus);

    /**
     * (确认退款)审核通过
     * @author SunQ
     * @Date 12:30 2018/5/11 0011
     * @Param [id]
     * @return boolean
     */
    boolean adopt(Long id);

    /**
     * 审核不通过
     * @author SunQ
     * @Date 12:30 2018/5/11 0011
     * @Param [id]
     * @return boolean
     */
    boolean unadopt(Long id);
}
