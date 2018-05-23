package com.yunyihenkey.common.utils.kdniao.param;

import javax.validation.constraints.NotBlank;

/**
 * @Author SunQ
 * @Date 2018/5/18 16:13
 */
public class OrderTracesParam {

    /**
     * 物流公司编码
     **/
    @NotBlank(message = "物流公司编码不能为空")
    private String shipperCode;

    /**
     * 物流单号
     **/
    @NotBlank(message = "物流单号不能为空")
    private String logisticCode;

    /**
     * 物流订单号
     **/
    private String orderCode;

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public OrderTracesParam() {
    }

    public OrderTracesParam(@NotBlank(message = "物流公司编码不能为空") String shipperCode, @NotBlank(message = "物流单号不能为空") String logisticCode) {
        this.shipperCode = shipperCode;
        this.logisticCode = logisticCode;
    }

    public OrderTracesParam(@NotBlank(message = "物流公司编码不能为空") String shipperCode, @NotBlank(message = "物流单号不能为空") String logisticCode, String orderCode) {
        this.shipperCode = shipperCode;
        this.logisticCode = logisticCode;
        this.orderCode = orderCode;
    }
}
