package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/16 18:01
 */
public class SaveRoleParam extends BaseVo {
    @NotEmpty(message = "角色名称")
    private String name;
    @NotNull(message = "分配权限")
    private List<PermissionVO> permissionVOList =new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionVO> getPermissionVOList() {
        return permissionVOList;
    }

    public void setPermissionVOList(List<PermissionVO> permissionVOList) {
        this.permissionVOList = permissionVOList;
    }
}
