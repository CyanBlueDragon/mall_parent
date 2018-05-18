package com.yunyihenkey.auth.service.enums;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author LiarYang
 * @date 2018/5/10 10:33
 * 统计昨日数据
 * 存缓存中 统一存贮头 通过shopId在RedisConstant中获取 拼接上对应的字符串
 */
public enum Increment {


    /**昨日新增会员*/
    CountNewMembers(0,"c587a7d3aae940a08b88fb0c4294554e"),
    /**昨日订单数*/
    CountOrderYesterday(1,"300d8e37767b4049a8540b2a66f8e182"),
    /**昨日订单金额*/
    OrderAmountOfYesterday(2,"052d5943a6144d30b4589d944c6d4bfa"),
    /**退款/售后*/
    CountRefundAfterSale(3,"dc14a7d158c540db8c1322449747f4bc"),
    /**昨日收益*/
    YesterdaysEarnings(4,"0491092eb44f4918b1bb0830297fa14c"),
    ;
    private int value;
    private String text;

    private Increment(int value, String text){
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

    public static Increment getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (Increment code : values()) {
            if (code.getValue()== value) {
                return code;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Calendar cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   0);
        String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
        System.out.println(yesterday);

        /*Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        date = calendar.getTime();
        return date;*/
    }
}
