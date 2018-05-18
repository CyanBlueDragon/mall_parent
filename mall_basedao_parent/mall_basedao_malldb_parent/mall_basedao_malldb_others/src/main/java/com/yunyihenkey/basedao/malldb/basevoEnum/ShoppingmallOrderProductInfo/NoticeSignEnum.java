package com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderProductInfo;


public enum NoticeSignEnum {
	
	/**未通知*/
	NOTNOTICE(0,"未通知"),
	/**已通知*/
	NOTICEED(1,"已通知"),
	;
	private int value;
	private String text;
	
	private NoticeSignEnum(int value, String text){
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
	
	public static NoticeSignEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (NoticeSignEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}