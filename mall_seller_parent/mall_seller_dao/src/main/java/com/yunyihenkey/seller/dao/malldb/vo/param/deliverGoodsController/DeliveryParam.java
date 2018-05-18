package com.yunyihenkey.seller.dao.malldb.vo.param.deliverGoodsController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 发货操作参数
 * @author SunQ
 * @Date 9:59 2018/5/18
 * @Param
 * @return
 */
public class DeliveryParam {

    /** 订单商品ID **/
    @NotBlank(message = "发货订单商品不能为空!")
    private String orderProductId;

    /** 订单号 **/
    @NotBlank(message = "单号不能为空!")
    private String orderCode;

    /** 物流单号 **/
    @NotBlank(message = "物流单号不能为空!")
    private String expressNumber;

    /** 物流公司编号 **/
    @NotBlank(message = "物流公司编号不能为空!")
    private String expressCode;

    /** 物流公司名称 **/
    @NotBlank(message = "物流公司名称不能为空!")
    private String expressCompany;

    public String getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(String orderProductId) {
        this.orderProductId = orderProductId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    @Override
    public String toString() {
        return "DeliveryParam{" +
                "orderProductId='" + orderProductId + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", expressNumber='" + expressNumber + '\'' +
                ", expressCode='" + expressCode + '\'' +
                ", expressCompany='" + expressCompany + '\'' +
                '}';
    }
}
