package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerSurveyStatistics implements Serializable {
    private Long id;

    /** 当天日期 */
    private String dateOfTheDay;

    /** 新增会员数量 */
    private Integer countNewMembers;

    /** 当天订单数 */
    private Integer countOrderToday;

    /** 当天订单金额 */
    private Long orderAmountOfToday;

    /** 当天退款/售后数量 */
    private Integer countRefundAfterSale;

    /** 当天收益 */
    private Long todaysEarnings;

    /** 店铺Id */
    private Long shopId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateOfTheDay() {
        return dateOfTheDay;
    }

    public void setDateOfTheDay(String dateOfTheDay) {
        this.dateOfTheDay = dateOfTheDay == null ? null : dateOfTheDay.trim();
    }

    public Integer getCountNewMembers() {
        return countNewMembers;
    }

    public void setCountNewMembers(Integer countNewMembers) {
        this.countNewMembers = countNewMembers;
    }

    public Integer getCountOrderToday() {
        return countOrderToday;
    }

    public void setCountOrderToday(Integer countOrderToday) {
        this.countOrderToday = countOrderToday;
    }

    public Long getOrderAmountOfToday() {
        return orderAmountOfToday;
    }

    public void setOrderAmountOfToday(Long orderAmountOfToday) {
        this.orderAmountOfToday = orderAmountOfToday;
    }

    public Integer getCountRefundAfterSale() {
        return countRefundAfterSale;
    }

    public void setCountRefundAfterSale(Integer countRefundAfterSale) {
        this.countRefundAfterSale = countRefundAfterSale;
    }

    public Long getTodaysEarnings() {
        return todaysEarnings;
    }

    public void setTodaysEarnings(Long todaysEarnings) {
        this.todaysEarnings = todaysEarnings;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dateOfTheDay=").append(dateOfTheDay);
        sb.append(", countNewMembers=").append(countNewMembers);
        sb.append(", countOrderToday=").append(countOrderToday);
        sb.append(", orderAmountOfToday=").append(orderAmountOfToday);
        sb.append(", countRefundAfterSale=").append(countRefundAfterSale);
        sb.append(", todaysEarnings=").append(todaysEarnings);
        sb.append(", shopId=").append(shopId);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}