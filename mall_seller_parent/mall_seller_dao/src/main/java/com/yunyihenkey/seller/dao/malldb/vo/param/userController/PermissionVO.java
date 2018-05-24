package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/16 18:07
 */
public class PermissionVO implements Serializable{
    private static final long serialVersionUID = 1L;
    @NotEmpty(message = "ID不能为空")
    private Long id;

    public PermissionVO() {
    }

    public PermissionVO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
