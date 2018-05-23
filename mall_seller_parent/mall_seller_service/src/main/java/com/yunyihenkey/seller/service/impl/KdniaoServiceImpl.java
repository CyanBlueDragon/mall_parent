//package com.yunyihenkey.seller.service.impl;
//
//import com.yunyihenkey.common.utils.JacksonUtils;
//import com.yunyihenkey.common.utils.LogUtils;
//import com.yunyihenkey.common.utils.kdniao.KdniaoUtil;
//import com.yunyihenkey.common.utils.kdniao.param.OrderTracesParam;
//import com.yunyihenkey.common.utils.kdniao.result.OrderTracesResult;
//import com.yunyihenkey.seller.service.KdniaoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author SunQ
// * @Date 2018/5/18 16:18
// */
//@Service
//public class KdniaoServiceImpl implements KdniaoService {
//
//    @Autowired
//    private KdniaoUtil kdniaoUtil;
//
//    @Override
//    public OrderTracesResult orderTraces(OrderTracesParam orderTracesParam) {
//        try {
//            String requestData= "{'OrderCode':'" + orderTracesParam.getOrderCode() + "','ShipperCode':'" + orderTracesParam.getShipperCode() + "','LogisticCode':'" + orderTracesParam.getLogisticCode() + "'}";
//            Map<String, String> params = new HashMap<String, String>();
//            params.put("RequestData", kdniaoUtil.urlEncoder(requestData, "UTF-8"));
//            params.put("EBusinessID", kdniaoUtil.getBusinessID());
//            params.put("RequestType", "1002");
//            String dataSign = kdniaoUtil.encrypt(requestData, kdniaoUtil.getAppKey(), "UTF-8");
//            params.put("DataSign", kdniaoUtil.urlEncoder(dataSign, "UTF-8"));
//            params.put("DataType", "2");
//            String result = kdniaoUtil.sendPost(kdniaoUtil.getOrderTracesUrl(), params);
//            result = result.replace("ShipperCode", "shipperCode")
//                    .replace("LogisticCode", "logisticCode")
//                    .replace("EBusinessID", "eBusinessID")
//                    .replace("State", "state")
//                    .replace("Success", "success")
//                    .replace("Traces", "traces")
//                    .replaceAll("AcceptStation", "acceptStation")
//                    .replaceAll("AcceptTime", "acceptTime")
//                    .replaceAll("Remark", "remark");
//            OrderTracesResult orderTracesResult = JacksonUtils.readValue(result, OrderTracesResult.class);
//            return orderTracesResult;
//        } catch (Exception e) {
//            LogUtils.getLogger().error(e.getMessage(), e);
//        }
//        return null;
//    }
//}
