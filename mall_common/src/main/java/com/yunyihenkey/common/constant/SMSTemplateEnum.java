package com.yunyihenkey.common.constant;

/**
 * @author LiarYang
 * @date 2018/4/28 11:39
 */
public enum SMSTemplateEnum {

    //信息变更
    InformationChange(0,"SMS_80290253"),
    //修改密码
    ModifyThePassword(1,"SMS_80290254"),
    //用户注册
    UserRegistration(2,"SMS_80290255"),
    //登录异常
    LoginException(3,"SMS_80290256"),
    //登录确认
    LoginConfirmation(4,"SMS_80290257"),
    //身份验证
    Authentication(5,"SMS_80290259"),

    ;


    private int value;
    private String text;

    SMSTemplateEnum(int value, String text) {
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
    public static SMSTemplateEnum getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (SMSTemplateEnum code : values()) {
            if (code.getValue() == value) {
                return code;
            }
        }
        return null;

        
    }
    public static void main(String[] args) {
        System.out.println(getByValue(3));
    }

}
