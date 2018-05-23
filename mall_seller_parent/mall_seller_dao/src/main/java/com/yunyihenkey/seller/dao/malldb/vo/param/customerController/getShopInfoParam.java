package com.yunyihenkey.seller.dao.malldb.vo.param.customerController;

import javax.validation.constraints.NotEmpty;

/**
 * @author LiarYang
 * @date 2018/5/21 17:01
 * @desc
 */
public class getShopInfoParam {
    @NotEmpty
    private Long shopId;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
