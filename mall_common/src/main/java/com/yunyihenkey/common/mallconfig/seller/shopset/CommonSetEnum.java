package com.yunyihenkey.common.mallconfig.seller.shopset;

/**
 * @author HeXing
 * @desc 店铺通用设置初始值
 * @date 2018/5/11 17:24
 */
public enum CommonSetEnum {
    /**
     * 未支付倒计时 默认一天
     */
    UNPAIDTIME_TIME(86400L, "秒"),
    /**
     * 订单确认收货
     */
    CONFIRM_ORDER_TIME(7L, "天"),
    /**
     * 收货后退款 0代表可允许退换货,大于0标识时间
     */
    REFUND_TIME(0L, "天"),
    /**
     * 下架时间
     */
    SELLER_OUT_TIME(1L, "天"),
    /**
     * 买家申请退换货
     */
    RETURN_ORDER_TIME(7L, "天");


    private Long value;
    private String text;


    private CommonSetEnum(Long value, String text) {
        this.value = value;
        this.text = text;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }

    public static CommonSetEnum getByValue(Long value) {
        if (value == null) {
            return null;
        }
        for (CommonSetEnum code : values()) {
            if (code.getValue() == value) {
                return code;
            }
        }
        return null;
    }


}
