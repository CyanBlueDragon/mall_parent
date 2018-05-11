package com.yunyihenkey.basedao.malldb.basevoEnum.SellerGoodsInfo;


public enum StatusEnum {
	
	/**仓库中*/
	warehouse(0,"仓库中"),
	/**上架中*/
	selling(1,"上架中"),
	/**已售罄*/
	sold(2,"已售罄"),
	;
	private int value;
	private String text;
	
	private StatusEnum(int value, String text){
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
	
	public static StatusEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (StatusEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}