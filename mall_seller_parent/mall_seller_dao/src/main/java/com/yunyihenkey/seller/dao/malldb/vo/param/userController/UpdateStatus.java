package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotNull;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/17 18:31
 */
public class UpdateStatus extends BaseVo {
    @NotNull(message = "用户ID不能为空")
    private Long id;
    /**
     * '状态#0,使用中|ENABLE;1,屏蔽|DISABLE '
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
