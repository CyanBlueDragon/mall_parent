package com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderProductInfo;


public enum RefundSignEnum {
	
	/**未退款*/
	UNREFUND(0,"未退款"),
	/**已退款*/
	REFUNDED(1,"已退款"),
	;
	private int value;
	private String text;
	
	private RefundSignEnum(int value, String text){
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
	
	public static RefundSignEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (RefundSignEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}