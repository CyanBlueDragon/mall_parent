package com.yunyihenkey.basedao.malldb.basevoEnum.SellerRegistrationCode;


public enum ApplyEnum {
	
	/**停用*/
	DISABLE(0,"停用"),
	/**启用*/
	ENABLE(1,"启用"),
	;
	private int value;
	private String text;
	
	private ApplyEnum(int value, String text){
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
	
	public static ApplyEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (ApplyEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}