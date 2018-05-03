package com.yunyihenkey.common.vo.resultinfo;

/**
 * 
 * @desc 系统编码
 * @author josnow
 * @date 2018年1月18日 下午4:57:10
 * @version 1.0.0
 */
public enum SystemCodeEnum {

	/** 供应商后台 */
	SUPPLIER(100, "supplier"),
	/** 运营管理后台 */
	OPERATION(101, "operation"),
	/** 分销平台 */
	SELLER(102, "seller"),
	/** 商城平台 */
	SHOPPINGMALL(103, "shoppingmall"),
	/** 授权系统 */
	AUTH(104, "auth"),;

	private int value;
	private String text;

	private SystemCodeEnum(int value, String text) {
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

}
