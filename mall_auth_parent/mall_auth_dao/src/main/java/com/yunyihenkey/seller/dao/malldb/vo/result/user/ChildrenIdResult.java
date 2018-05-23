package com.yunyihenkey.seller.dao.malldb.vo.result.user;

import java.util.Date;

/**
 * @author LiarYang
 * @date 2018/5/17 12:04
 * @desc
 */
public class ChildrenIdResult {

    private Long id;


    /**
     * 店铺名称
     */
    private String name;

    /**
     * 店铺状态#0,营业|OPEN; 1,打烊|CLOSE
     */
    private Integer status;

    /**
     * 分销商级别#0,A级分销商|A;1,B级分销商|B;2,C级分销商|C
     */
    private Integer sellerGrade;

    /**
     * 店铺营业额
     */
    private Long shopTurnover;

    /**
     * 总销量
     */
    private Integer totalSales;

    /**
     * 退款金额
     */
    private Long refundAmount;

    /**
     * 退款总数量
     */
    private Integer refundCount;

    /**
     * 店铺商品数量
     */
    private Integer storeQuantity;

    /**
     * 店铺会员数量
     */
    private Integer membersCount;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSellerGrade() {
        return sellerGrade;
    }

    public void setSellerGrade(Integer sellerGrade) {
        this.sellerGrade = sellerGrade;
    }

    public Long getShopTurnover() {
        return shopTurnover;
    }

    public void setShopTurnover(Long shopTurnover) {
        this.shopTurnover = shopTurnover;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;
    }

    public Integer getStoreQuantity() {
        return storeQuantity;
    }

    public void setStoreQuantity(Integer storeQuantity) {
        this.storeQuantity = storeQuantity;
    }

    public Integer getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Integer membersCount) {
        this.membersCount = membersCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
