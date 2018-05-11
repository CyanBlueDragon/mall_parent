package com.yunyihenkey.basedao.malldb.basevoEnum.OperationUser;


public enum EnableFlagEnum {
	
	/**停用*/
	Disable(0,"停用"),
	/**启用*/
	Enable(1,"启用"),
	;
	private int value;
	private String text;
	
	private EnableFlagEnum(int value, String text){
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
	
	public static EnableFlagEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (EnableFlagEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}