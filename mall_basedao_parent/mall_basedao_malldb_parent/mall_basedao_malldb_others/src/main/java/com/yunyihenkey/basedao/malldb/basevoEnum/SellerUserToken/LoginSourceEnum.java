package com.yunyihenkey.basedao.malldb.basevoEnum.SellerUserToken;


public enum LoginSourceEnum {
	
	/**Android*/
	Android(0,"Android"),
	/**IOS*/
	IOS(1,"IOS"),
	/**PCClient*/
	PCClient(2,"PCClient"),
	/**Web*/
	Web(3,"Web"),
	;
	private int value;
	private String text;
	
	private LoginSourceEnum(int value, String text){
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
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}