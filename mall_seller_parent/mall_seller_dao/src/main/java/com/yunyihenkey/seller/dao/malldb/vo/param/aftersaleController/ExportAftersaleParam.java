package com.yunyihenkey.seller.dao.malldb.vo.param.aftersaleController;

/**
 * @Author SunQ
 * @Date 2018/5/9 0009 14:42
 */
public class ExportAftersaleParam {

    /**
     * 商户ID(商城)
     **/
    private String mallId;

    /**
     * 订单号
     **/
    private String orderCode;

    /**
     * 会员账号
     **/
    private String memberAccount;

    /**
     * 售后状态
     **/
    private String[] aftersaleStatus;

    public ExportAftersaleParam() {
        super();
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String[] getAftersaleStatus() {
        return aftersaleStatus;
    }

    public void setAftersaleStatus(String[] aftersaleStatus) {
        this.aftersaleStatus = aftersaleStatus;
    }

    public ExportAftersaleParam(String mallId, String orderCode, String memberAccount, String[] aftersaleStatus) {
        this.mallId = mallId;
        this.orderCode = orderCode;
        this.memberAccount = memberAccount;
        this.aftersaleStatus = aftersaleStatus;
    }
}
