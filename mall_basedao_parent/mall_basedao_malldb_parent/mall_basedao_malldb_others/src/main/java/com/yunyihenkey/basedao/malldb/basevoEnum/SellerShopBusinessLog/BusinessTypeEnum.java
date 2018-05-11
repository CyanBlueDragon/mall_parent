package com.yunyihenkey.basedao.malldb.basevoEnum.SellerShopBusinessLog;


public enum BusinessTypeEnum {
	
	/**店铺盈利*/
	SHOP_SELL(0,"店铺盈利"),
	/**B级抽成*/
	B_TAKE(1,"B级抽成"),
	/**C级抽成*/
	C_TAKE(2,"C级抽成"),
	;
	private int value;
	private String text;
	
	private BusinessTypeEnum(int value, String text){
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
	
	public static BusinessTypeEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (BusinessTypeEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}