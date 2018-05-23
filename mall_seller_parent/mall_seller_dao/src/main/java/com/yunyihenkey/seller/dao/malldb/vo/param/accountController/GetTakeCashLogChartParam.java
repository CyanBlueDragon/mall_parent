package com.yunyihenkey.seller.dao.malldb.vo.param.accountController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.yunyihenkey.common.vo.base.BaseVo;

public class GetTakeCashLogChartParam extends BaseVo {

    @NotEmpty(message = "时间不能为空")
    @Pattern(regexp = "^3M|6M|12M$", message = "错误的时间")
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static enum GetTakeCashLogChartEnum {

        /**  */
        MONTH_3("3M", "3个月", 3),
        /**  */
        MONTH_6("6M", "6个月", 6),
        /**  */
        MONTH_12("12M", "12个月", 12),;

        private String value;
        private String text;
        private Integer month;

        private GetTakeCashLogChartEnum(String value, String text, Integer month) {
            this.value = value;
            this.text = text;
            this.month = month;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getMonth() {
            return month;
        }

        public void setMonth(Integer month) {
            this.month = month;
        }

        public static GetTakeCashLogChartEnum getByValue(String value) {
            if (value == null) {
                return null;
            }
            for (GetTakeCashLogChartEnum code : values()) {
                if (code.getValue().equals(value)) {
                    return code;
                }
            }
            return null;
        }

        public static void main(String[] args) {
            System.out.println(getByValue("6M"));
        }
    }
}
