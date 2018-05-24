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
public class UpdateRoleParam extends BaseVo {
    @NotNull(message = "角色id不能为空")
    private Long id;
    @NotEmpty(message = "角色名称不能为空")
    private String name;
    @NotNull(message = "分配权限不能为空")
    private List<PermissionVO> permissionVOList = new ArrayList<>();

    private Long shopId;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
