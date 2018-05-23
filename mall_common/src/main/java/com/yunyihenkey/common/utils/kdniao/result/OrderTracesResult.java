package com.yunyihenkey.common.utils.kdniao.result;

import java.util.List;

/**
 * 订单物流轨迹对象
 *
 * @author SunQ
 * @Date 14:41 2018/5/18
 * @Param
 * @return
 */
public class OrderTracesResult {

    /**
     * 快递单号
     **/
    private String logisticCode;

    /**
     * 快递公司编码
     **/
    private String shipperCode;

    /**
     * 物流状态
     **/
    private Integer state;

    /**
     * 用户 ID
     **/
    private String eBusinessID;

    /**
     * 成功与否(true/false)
     **/
    private Boolean success;

    /**
     * 轨迹集合
     **/
    private List<TraceInfo> traces;

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String geteBusinessID() {
        return eBusinessID;
    }

    public void seteBusinessID(String eBusinessID) {
        this.eBusinessID = eBusinessID;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<TraceInfo> getTraces() {
        return traces;
    }

    public void setTraces(List<TraceInfo> traces) {
        this.traces = traces;
    }

    @Override
    public String toString() {
        return "OrderTracesResult{" +
                "logisticCode='" + logisticCode + '\'' +
                ", shipperCode='" + shipperCode + '\'' +
                ", state=" + state +
                ", eBusinessID='" + eBusinessID + '\'' +
                ", success=" + success +
                ", traces=" + traces +
                '}';
    }
}
