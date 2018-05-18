package com.yunyihenkey.basedao.malldb.basevoEnum.SellerShopMembers;


public enum StateEnum {
	
	/**正常*/
	NORMAL(0,"正常"),
	/**黑名单*/
	BLACKLIST( 1,"黑名单"),
	;
	private int value;
	private String text;
	
	private StateEnum(int value, String text){
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
	
	public static StateEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (StateEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}