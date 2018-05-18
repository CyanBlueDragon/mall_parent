package com.yunyihenkey.seller.dao.malldb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunyihenkey.basedao.malldb.basemapper.ShoppingmallOrderAftersaleInfoBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderAftersaleInfo;
import com.yunyihenkey.seller.dao.malldb.exportVo.OrderAftersaleExportVo;

/**
 * @Author SunQ
 * @Date 2018/5/9 0009 14:32
 */
@Repository
public interface ShoppingmallOrderAftersaleInfoMapper extends ShoppingmallOrderAftersaleInfoBaseMapper {

    /**
     * 列表查询
     * @author SunQ
     * @Date 16:05 2018/5/11 0011
     * @Param [mallId, orderCode, memberAccount, aftersaleStatus]
     * @return java.util.List<ShoppingmallOrderAftersaleInfo>
     */
    List<ShoppingmallOrderAftersaleInfo> selectAll(@Param("mallId") String mallId, @Param("orderCode") String orderCode, @Param("memberAccount") String memberAccount, @Param("aftersaleStatus") String[] aftersaleStatus);

    /**
     * 获取需要导出的对象集合
     * @author SunQ
     * @Date 16:02 2018/5/11 0011
     * @Param [mallId, orderCode, memberAccount, aftersaleStatus]
     * @return java.util.List<com.yunyihenkey.seller.dao.malldb.exportVo.OrderAftersaleExportVo>
     */
    List<OrderAftersaleExportVo> selectExportVo(@Param("mallId") String mallId, @Param("orderCode") String orderCode, @Param("memberAccount") String memberAccount, @Param("aftersaleStatus") String[] aftersaleStatus);
}
