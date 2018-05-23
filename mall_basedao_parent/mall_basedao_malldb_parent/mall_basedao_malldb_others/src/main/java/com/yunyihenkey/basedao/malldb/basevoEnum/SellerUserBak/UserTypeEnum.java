package com.yunyihenkey.basedao.malldb.basevoEnum.SellerUserBak;


public enum UserTypeEnum {

    /**
     * 分销商
     */
    SELLER(0, "分销商"),
    /**
     * 员工
     */
    STAFF(2, "员工"),;
    private int value;
    private String text;

    private UserTypeEnum(int value, String text) {
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

    public static UserTypeEnum getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (UserTypeEnum code : values()) {
            if (code.getValue() == value) {
                return code;
            }
        }
        return null;
    }
}