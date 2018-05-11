package com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderProductInfo;


public enum PostageTypeEnum {
	
	/**包邮*/
	FREESHIPPING(0,"包邮"),
	/**按件计费*/
	BYNUMBER(1,"按件计费"),
	/**按重量计费*/
	BYWEIGHT(2,"按重量计费"),
	;
	private int value;
	private String text;
	
	private PostageTypeEnum(int value, String text){
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
	
	public static PostageTypeEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (PostageTypeEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}