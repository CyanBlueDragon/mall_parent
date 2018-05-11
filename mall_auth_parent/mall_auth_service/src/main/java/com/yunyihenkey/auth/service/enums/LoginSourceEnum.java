package com.yunyihenkey.auth.service.enums;

/**
 * 
 * @desc 登录来源
 * @author wulm
 * @date 2018年4月28日 下午2:41:33
 * @version 1.0.0
 */
public enum LoginSourceEnum {

	/**  */
	Android(0, "Android"),
	/**  */
	IOS(1, "IOS"),
	/**  */
	PCClient(2, "PCClient"),
	/**  */
	Web(3, "Web"),

	;

	private int value;
	private String text;

	private LoginSourceEnum(int value, String text) {
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

	public static LoginSourceEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (LoginSourceEnum code : values()) {
			if (code.getValue() == value) {
				return code;
			}
		}
		return null;
	}
}
