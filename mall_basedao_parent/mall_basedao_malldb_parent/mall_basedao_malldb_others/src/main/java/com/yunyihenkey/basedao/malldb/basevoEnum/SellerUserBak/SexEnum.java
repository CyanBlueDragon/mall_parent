package com.yunyihenkey.basedao.malldb.basevoEnum.SellerUserBak;


public enum SexEnum {

    /**
     * 男
     */
    MAN(0, "男"),
    /**
     * 女
     */
    WOMAN(1, "女"),;
    private int value;
    private String text;

    private SexEnum(int value, String text) {
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

    public static SexEnum getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (SexEnum code : values()) {
            if (code.getValue() == value) {
                return code;
            }
        }
        return null;
    }
}