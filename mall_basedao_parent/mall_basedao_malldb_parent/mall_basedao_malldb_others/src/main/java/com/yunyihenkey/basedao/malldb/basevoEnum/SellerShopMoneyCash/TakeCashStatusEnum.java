package com.yunyihenkey.basedao.malldb.basevoEnum.SellerShopMoneyCash;


public enum TakeCashStatusEnum {

    /**
     * 申请提现
     */
    CREATED(0, "申请提现"),
    /**
     * 处理中
     */
    PROCESSING(2, "处理中"),
    /**
     * 提现失败
     */
    FAIL(4, "提现失败"),
    /**
     * 提现成功
     */
    SUCCESS(6, "提现成功"),;
    private int value;
    private String text;

    private TakeCashStatusEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }

    public static TakeCashStatusEnum getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (TakeCashStatusEnum code : values()) {
            if (code.getValue() == value) {
                return code;
            }
        }
        return null;
    }
}