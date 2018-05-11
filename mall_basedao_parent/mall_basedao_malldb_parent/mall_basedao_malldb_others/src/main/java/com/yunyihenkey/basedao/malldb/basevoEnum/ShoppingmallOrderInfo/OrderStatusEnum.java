package com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderInfo;


public enum OrderStatusEnum {
	
	/**待支付*/
	WAITPAY(0,"待支付"),
	/**待发货*/
	WAITSEND(1,"待发货"),
	/**已取消*/
	CANCLEED(2,"已取消"),
	/**待收货*/
	WAITRECEIVED(3,"待收货"),
	/**已收货(待评价)*/
	RECEIVED(4,"已收货(待评价)"),
	/**售后/退款*/
	AFTERSALE(5,"售后/退款"),
	/**已完成*/
	FINISH(6,"已完成"),
	;
	private int value;
	private String text;
	
	private OrderStatusEnum(int value, String text){
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
	
	public static OrderStatusEnum getByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for (OrderStatusEnum code : values()) {
			if (code.getValue()== value) {
				return code;
			}
		}
		return null;
	}
}