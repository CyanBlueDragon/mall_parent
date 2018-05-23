package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/18 16:18
 */
public class UpdateUserParam extends BaseVo {
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "使用人不能为空")
    private String nickName;
    @NotNull(message = "用户ID不能为空")
    private Long id;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
