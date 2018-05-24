package com.yunyihenkey.auth.service.enums;

/**
 * 
 * @desc 请求来源
 * @author wulm
 * @date 2018年4月28日 下午2:41:33
 * @version 1.0.0
 */
public enum RequestSourceEnum {

	/**  */
	ANDROID("ANDROID", "安卓端"),
	/**  */
	IOS("IOS", "IOS端"),
	/**  */
	PC("PC", "电脑端"),
	/**  */
	WEB("WEB", "网页端"),

	;

	private String value;
	private String text;

	private RequestSourceEnum(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static RequestSourceEnum getByValue(String value) {
		if (value == null) {
			return null;
		}
		for (RequestSourceEnum code : values()) {
			if (code.getValue().equals(value)) {
				return code;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(getByValue("WEB"));
	}
}
