package com.yunyihenkey.basedao.malldb.basevoEnum.SellerShop;


public enum IsCustomizeEnum {
	
	/**否*/
	NO(0,"否"),
	/**是*/
	YES(1,"是"),
	;
	private int value;
	private String text;
	
	private IsCustomizeEnum(int value, String text){
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
	
	public static IsCustomizeEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (IsCustomizeEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}