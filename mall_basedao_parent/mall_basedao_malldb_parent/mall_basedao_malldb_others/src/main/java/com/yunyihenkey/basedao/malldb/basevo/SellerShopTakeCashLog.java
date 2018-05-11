package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerShopTakeCashLog implements Serializable {
    /** 主键 */
    private Long id;

    /** 店铺id */
    private Long shopId;

    /** 提现金额(分) */
    private Long takeCashMoney;

    /** 手续费(分) */
    private Long serviceChargeMoney;

    /** 提现状态#0,申请提现|CREATED;2,处理中|PROCESSING;4,提现失败|FAIL;6,提现成功|SUCCESS; */
    private Integer takeCashStatus;

    /** 持卡人姓名 */
    private String bankUserName;

    /** 开户银行 */
    private String bankName;

    /** 开户银行地址(某支行) */
    private String bankAddress;

    /** 银行账号 */
    private String bankCardNumber;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getTakeCashMoney() {
        return takeCashMoney;
    }

    public void setTakeCashMoney(Long takeCashMoney) {
        this.takeCashMoney = takeCashMoney;
    }

    public Long getServiceChargeMoney() {
        return serviceChargeMoney;
    }

    public void setServiceChargeMoney(Long serviceChargeMoney) {
        this.serviceChargeMoney = serviceChargeMoney;
    }

    public Integer getTakeCashStatus() {
        return takeCashStatus;
    }

    public void setTakeCashStatus(Integer takeCashStatus) {
        this.takeCashStatus = takeCashStatus;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName == null ? null : bankUserName.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress == null ? null : bankAddress.trim();
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber == null ? null : bankCardNumber.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", takeCashMoney=").append(takeCashMoney);
        sb.append(", serviceChargeMoney=").append(serviceChargeMoney);
        sb.append(", takeCashStatus=").append(takeCashStatus);
        sb.append(", bankUserName=").append(bankUserName);
        sb.append(", bankName=").append(bankName);
        sb.append(", bankAddress=").append(bankAddress);
        sb.append(", bankCardNumber=").append(bankCardNumber);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}