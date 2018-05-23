package com.yunyihenkey.seller.dao.malldb.vo.param.orderController;

import com.yunyihenkey.common.vo.page.PageParam;

import javax.validation.constraints.NotNull;

/**
 * @Author SunQ
 * @Date 2018/5/4 0004 17:10
 */
public class QueryOrderParam extends PageParam {

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
     * 订单状态
     **/
    private int[] orderStatus;

    public QueryOrderParam() {
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

    public int[] getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int[] orderStatus) {
        this.orderStatus = orderStatus;
    }

    public QueryOrderParam(String mallId, String orderCode, String memberAccount, int[] orderStatus) {
        this.mallId = mallId;
        this.orderCode = orderCode;
        this.memberAccount = memberAccount;
        this.orderStatus = orderStatus;
    }
}