package com.yunyihenkey.basedao.malldb.basevoEnum.SupplierUser;


public enum IsDeleteEnum {
	
	/**否*/
	No(0,"否"),
	/**是*/
	Yes(1,"是"),
	;
	private int value;
	private String text;
	
	private IsDeleteEnum(int value, String text){
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
	
	public static IsDeleteEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (IsDeleteEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}