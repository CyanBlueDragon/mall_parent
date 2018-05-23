package com.yunyihenkey.seller.service;

import com.yunyihenkey.common.utils.kdniao.param.OrderTracesParam;
import com.yunyihenkey.common.utils.kdniao.result.OrderTracesResult;

/**
 * @Author SunQ
 * @Date 2018/5/18 16:11
 */
public interface KdniaoService {

    /**
     * 物流轨迹查询
     *
     * @return com.yunyihenkey.common.utils.kdniao.result.OrderTracesResult
     * @author SunQ
     * @Date 16:35 2018/5/18
     * @Param [orderTracesParam]
     */
    OrderTracesResult orderTraces(OrderTracesParam orderTracesParam);
}
