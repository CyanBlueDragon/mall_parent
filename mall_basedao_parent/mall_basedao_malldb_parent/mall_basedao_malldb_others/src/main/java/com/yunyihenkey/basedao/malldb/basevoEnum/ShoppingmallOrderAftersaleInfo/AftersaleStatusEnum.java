package com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderAftersaleInfo;


public enum AftersaleStatusEnum {
	
	/**申请退款*/
	REFUND(0,"申请退款"),
	/**退款中*/
	REFUNDING(1,"退款中"),
	/**退款完成*/
	REFUNDED(2,"退款完成"),
	/**审核不通过*/
	NOPASS(3,"审核不通过"),
	;
	private int value;
	private String text;
	
	private AftersaleStatusEnum(int value, String text){
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
	
	public static AftersaleStatusEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (AftersaleStatusEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}