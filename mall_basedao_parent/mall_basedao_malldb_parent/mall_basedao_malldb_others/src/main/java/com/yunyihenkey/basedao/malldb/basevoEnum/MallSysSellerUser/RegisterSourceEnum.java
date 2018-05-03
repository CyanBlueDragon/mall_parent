package com.yunyihenkey.basedao.malldb.basevoEnum.MallSysSellerUser;


public enum RegisterSourceEnum {
	
	Android(0,"Android"),
	IOS(1,"IOS"),
	Web(2,"Web"),
	;
	private int value;
	private String text;
	
	private RegisterSourceEnum(int value, String text){
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
	
	public static RegisterSourceEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (RegisterSourceEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}