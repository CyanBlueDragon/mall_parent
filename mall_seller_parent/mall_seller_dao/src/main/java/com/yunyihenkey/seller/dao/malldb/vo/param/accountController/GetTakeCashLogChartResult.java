package com.yunyihenkey.seller.dao.malldb.vo.param.accountController;

import com.yunyihenkey.common.vo.base.BaseVo;

public class GetTakeCashLogChartResult extends BaseVo {

    private String time;
    private Long money;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

}
