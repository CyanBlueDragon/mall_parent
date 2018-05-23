package com.yunyihenkey.basedao.malldb.basevoEnum.SellerShopMoney;


public enum MoneyTypeEnum {

    /**
     * 店铺盈利
     */
    SHOP_SELL(0, "店铺盈利"),
    /**
     * B级抽成
     */
    B_TAKE(1, "B级抽成"),
    /**
     * C级抽成
     */
    C_TAKE(2, "C级抽成"),
    /**
     * 商品退货退款
     */
    REFUND_GOODS(3, "商品退货退款"),
    /**
     * 商品退货退回抽成
     */
    REFUND_TAKE(4, "商品退货退回抽成"),
    /**
     * 冻结金额
     */
    FREEZE_MONEY(5, "冻结金额"),
    /**
     * 冻结金额退还
     */
    UNFREEZE_MONEY(6, "冻结金额退还"),
    /**
     * 提现扣款
     */
    TAKE_CASH(7, "提现扣款"),
    /**
     * 提现扣款手续费
     */
    TAKE_CASH_CHARGE(8, "提现扣款手续费"),
    /**
     * 提现失败退回
     */
    TAKE_CASH_BACK(9, "提现失败退回"),
    /**
     * 提现失败退回手续费
     */
    TAKE_CASH_CHARGE_BACK(10, "提现失败退回手续费"),;
    private int value;
    private String text;

    private MoneyTypeEnum(int value, String text) {
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

    public static MoneyTypeEnum getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (MoneyTypeEnum code : values()) {
            if (code.getValue() == value) {
                return code;
            }
        }
        return null;
    }
}