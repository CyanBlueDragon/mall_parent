package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotNull;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/18 12:18
 */
public class RoleDeleteParam extends BaseVo {
    @NotNull(message = "角色ID不能为空")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
