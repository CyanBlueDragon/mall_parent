package com.yunyihenkey.basedao.malldb.basevoEnum.SellerUser;


public enum StatusEnum {
	
	/**使用中*/
	ENABLE(0,"使用中"),
	/**屏蔽*/
	DISABLE(1,"屏蔽"),
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