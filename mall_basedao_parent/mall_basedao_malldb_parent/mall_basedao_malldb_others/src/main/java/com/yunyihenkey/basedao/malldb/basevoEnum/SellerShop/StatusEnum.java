package com.yunyihenkey.basedao.malldb.basevoEnum.SellerShop;


public enum StatusEnum {
	
	/**营业*/
	OPEN(0,"营业"),
	/**打烊*/
	CLOSE( 1,"打烊"),
	;
	private int value;
	private String text;
	
	private StatusEnum(int value, String text){
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
	
	public static StatusEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (StatusEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}