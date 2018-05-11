package com.yunyihenkey.basedao.malldb.basevoEnum.SupplierGoodsInfo;


public enum CatIdEnum {
	
	/**实物*/
	material_object(1,"实物"),
	/**虚拟*/
	fictitious(2,"虚拟"),
	;
	private int value;
	private String text;
	
	private CatIdEnum(int value, String text){
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
	
	public static CatIdEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (CatIdEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}