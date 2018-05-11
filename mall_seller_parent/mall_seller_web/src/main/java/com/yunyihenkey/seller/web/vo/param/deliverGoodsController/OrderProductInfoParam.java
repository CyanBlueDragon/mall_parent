package com.yunyihenkey.seller.web.vo.param.deliverGoodsController;

import com.yunyihenkey.common.vo.page.PageParam;

/**
 * @Author SunQ
 * @Date 2018/5/8 0008 14:27
 */
public class OrderProductInfoParam extends PageParam {

    /** 商户ID(商城) **/
    private String mallId;

    /** 商户ID(供应商) **/
    private String supplierId;

    /* 商品名称 */
    private String productName;

    /* 收货人姓名 */
    private String receiverName;

    public OrderProductInfoParam() {
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public OrderProductInfoParam(String mallId, String supplierId, String productName, String receiverName) {
        this.mallId = mallId;
        this.supplierId = supplierId;
        this.productName = productName;
        this.receiverName = receiverName;
    }
}
