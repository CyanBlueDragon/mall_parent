package com.yunyihenkey.basedao.malldb.basevoEnum.SellerShop;


public enum AuditStatusEnum {
	
	/**待审核*/
	PENDING(0,"待审核"),
	/**审核通过*/
	PASSED(1,"审核通过"),
	/**审核不通过*/
	NOT_THROUGH(2,"审核不通过"),
	;
	private int value;
	private String text;
	
	private AuditStatusEnum(int value, String text){
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
	
	public static AuditStatusEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (AuditStatusEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}