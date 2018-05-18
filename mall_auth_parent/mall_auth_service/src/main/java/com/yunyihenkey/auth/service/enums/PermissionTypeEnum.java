package com.yunyihenkey.auth.service.enums;

/**
 * @author HeXing
 * @desc 权限类型
 * @date 2018/5/15 9:40
 */
public enum PermissionTypeEnum {
    TYPEBUTTON(0,"按钮"),
    TYPEMENU(1,"菜单");

    PermissionTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    private Integer value;
    private String name;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static PermissionTypeEnum getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (PermissionTypeEnum code : values()) {
            if (code.getValue() == value) {
                return code;
            }
        }
        return null;
    }
}
