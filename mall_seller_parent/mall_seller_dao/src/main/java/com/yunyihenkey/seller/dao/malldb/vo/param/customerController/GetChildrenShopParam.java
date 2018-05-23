package com.yunyihenkey.seller.dao.malldb.vo.param.customerController;

import com.yunyihenkey.common.vo.page.PageParam;

/**
 * @author LiarYang
 * @date 2018/5/17 18:07
 * @desc
 */
public class GetChildrenShopParam extends PageParam {
    private Long id;

    private String ShopName;

    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
