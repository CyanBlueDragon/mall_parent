package com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderProductInfo;


public enum SendStatusEnum {
	
	/**未发货*/
	UNSEND(0,"未发货"),
	/**配送中*/
	DISTRIBUTION(1,"配送中"),
	/**已签收*/
	RECEIVED(2,"已签收"),
	/**退款/售后*/
	AFTERSALE(3,"退款/售后"),
	;
	private int value;
	private String text;
	
	private SendStatusEnum(int value, String text){
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
	
	public static SendStatusEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (SendStatusEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}