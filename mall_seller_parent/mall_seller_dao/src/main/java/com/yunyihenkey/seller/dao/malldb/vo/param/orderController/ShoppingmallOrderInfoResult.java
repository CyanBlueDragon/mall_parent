package com.yunyihenkey.seller.dao.malldb.vo.param.orderController;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderInfo;
import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderProductInfo;

import java.util.List;

/**
 * @Author SunQ
 * @Date 2018/5/10 0010 9:22
 */
public class ShoppingmallOrderInfoResult extends ShoppingmallOrderInfo {

    /** 订单下的商品集合 */
    private List<ShoppingmallOrderProductInfo> orderProducts;

    public List<ShoppingmallOrderProductInfo> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<ShoppingmallOrderProductInfo> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
