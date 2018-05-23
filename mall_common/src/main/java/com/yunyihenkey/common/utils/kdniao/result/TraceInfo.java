package com.yunyihenkey.common.utils.kdniao.result;

import java.util.Date;

/**
 * 轨迹对象
 *
 * @author SunQ
 * @Date 14:41 2018/5/18
 * @Param
 * @return
 */
public class TraceInfo {

    /**
     * 轨迹发生时间
     **/
    private String acceptTime;

    /**
     * 轨迹描述
     **/
    private String acceptStation;

    /**
     * 备注
     **/
    private String remark;

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getAcceptStation() {
        return acceptStation;
    }

    public void setAcceptStation(String acceptStation) {
        this.acceptStation = acceptStation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TraceInfo{" +
                "acceptTime='" + acceptTime + '\'' +
                ", acceptStation='" + acceptStation + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
