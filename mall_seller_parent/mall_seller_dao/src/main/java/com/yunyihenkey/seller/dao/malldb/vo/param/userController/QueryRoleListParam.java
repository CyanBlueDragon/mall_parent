package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;
import com.yunyihenkey.common.vo.page.PageParam;

import javax.validation.constraints.NotEmpty;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/12 18:08
 */
public class QueryRoleListParam extends PageParam {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
