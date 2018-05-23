package com.yunyihenkey.basedao.malldb.basevoEnum.SellerUserBak;


public enum SellerGradeEnum {

    /**
     * A级分销商
     */
    A(0, "A级分销商"),
    /**
     * B级分销商
     */
    B(1, "B级分销商"),
    /**
     * C级分销商
     */
    C(2, "C级分销商"),;
    private int value;
    private String text;

    private SellerGradeEnum(int value, String text) {
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

    public static SellerGradeEnum getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (SellerGradeEnum code : values()) {
            if (code.getValue() == value) {
                return code;
            }
        }
        return null;
    }
}