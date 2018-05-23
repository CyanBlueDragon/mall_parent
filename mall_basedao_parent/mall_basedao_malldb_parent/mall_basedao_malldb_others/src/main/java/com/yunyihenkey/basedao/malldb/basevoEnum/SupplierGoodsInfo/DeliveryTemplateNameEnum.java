package com.yunyihenkey.basedao.malldb.basevoEnum.SupplierGoodsInfo;


public enum DeliveryTemplateNameEnum {

    /**
     * 包邮
     */
    Shipping(0, "包邮"),
    /**
     * 免邮
     */
    Freepost(1, "免邮"),
    /**
     * 按件计费
     */
    Piece_Billing(2, "按件计费"),
    /**
     * 按斤计费
     */
    Kilos_Billing(3, "按斤计费"),;
    private int value;
    private String text;

    private DeliveryTemplateNameEnum(int value, String text) {
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

    public static DeliveryTemplateNameEnum getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (DeliveryTemplateNameEnum code : values()) {
            if (code.getValue() == value) {
                return code;
            }
        }
        return null;
    }
}