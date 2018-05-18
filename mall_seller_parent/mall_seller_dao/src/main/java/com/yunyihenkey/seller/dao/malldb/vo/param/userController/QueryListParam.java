package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import com.yunyihenkey.common.vo.page.PageParam;

import javax.validation.constraints.NotNull;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/16 11:21
 */
public class QueryListParam extends PageParam {
    private String userName;
    @NotNull
    private Long roleId; //角色ID

    private Long shopId;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
