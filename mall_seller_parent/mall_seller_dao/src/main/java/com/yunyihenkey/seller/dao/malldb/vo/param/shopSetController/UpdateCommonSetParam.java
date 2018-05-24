package com.yunyihenkey.seller.dao.malldb.vo.param.shopSetController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotNull;

/**
 * @author HeXing
 * @desc 通用设置
 * @date 2018/5/12 10:11
 */
public class UpdateCommonSetParam extends BaseVo {
    @NotNull
    private Long unpaidTime;
    @NotNull
    private Integer confirmOrderTime;
    @NotNull
    private Integer refundTime;
    @NotNull
    private Integer sellOutTime;
    @NotNull
    private Integer returnOrderTime;

    private String consignee;
    private String returnedContactWay;
    private String returnedAddress;

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getReturnedContactWay() {
        return returnedContactWay;
    }

    public void setReturnedContactWay(String returnedContactWay) {
        this.returnedContactWay = returnedContactWay;
    }

    public String getReturnedAddress() {
        return returnedAddress;
    }

    public void setReturnedAddress(String returnedAddress) {
        this.returnedAddress = returnedAddress;
    }

    public Long getUnpaidTime() {
        return unpaidTime;
    }

    public void setUnpaidTime(Long unpaidTime) {
        this.unpaidTime = unpaidTime;
    }

    public Integer getConfirmOrderTime() {
        return confirmOrderTime;
    }

    public void setConfirmOrderTime(Integer confirmOrderTime) {
        this.confirmOrderTime = confirmOrderTime;
    }

    public Integer getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Integer refundTime) {
        this.refundTime = refundTime;
    }

    public Integer getSellOutTime() {
        return sellOutTime;
    }

    public void setSellOutTime(Integer sellOutTime) {
        this.sellOutTime = sellOutTime;
    }

    public Integer getReturnOrderTime() {
        return returnOrderTime;
    }

    public void setReturnOrderTime(Integer returnOrderTime) {
        this.returnOrderTime = returnOrderTime;
    }
}
