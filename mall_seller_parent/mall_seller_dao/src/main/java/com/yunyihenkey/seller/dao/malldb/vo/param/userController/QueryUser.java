package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotNull;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/18 16:36
 */
public class QueryUser extends BaseVo {
    @NotNull(message = "用户ID不能为空")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
