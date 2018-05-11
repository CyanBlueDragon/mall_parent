package com.yunyihenkey.seller.dao.malldb.exportVo;

import com.yunyihenkey.common.annotation.Export;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author SunQ
 * @Date 2018/5/9 0009 10:15
 */
public class OrderProductExportVo {

    @Export(index = 0, title = "商品名称")
    /** 商品名称 */
    private String productName;

    @Export(index = 1, title = "商品单价")
    /** 商品单价 */
    private Long productPrice;

    @Export(index = 2, title = "收货人")
    /** 收货人 */
    private String receiverName;

    @Export(index = 3, title = "联系电话")
    /** 联系电话 */
    private String receiverPhone;

    @Export(index = 4, title = "联系地址")
    /** 联系地址 */
    private String receiverAddress;

    @Export(index = 5, title = "状态")
    /** 发货状态#0,未发货|UNSEND;1,配送中|DISTRIBUTION;2,已签收|RECEIVED;3,退款/售后|AFTERSALE */
    private String sendStatus;

    @Export(index = 6, title = "邮费类型")
    /** 邮费类型#0,包邮|FREESHIPPING;1,按件计费|BYNUMBER;2,按重量计费|BYWEIGHT */
    private String postageType;

    @Export(index = 7, title = "时间")
    /** 创建时间 */
    private Date createTime;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getPostageType() {
        return postageType;
    }

    public void setPostageType(String postageType) {
        this.postageType = postageType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
