package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import com.yunyihenkey.auth.service.vo.authjwt.seller.PermissionTree;
import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;
import com.yunyihenkey.basedao.malldb.basevo.SellerRole;
import com.yunyihenkey.common.vo.base.BaseVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/18 17:15
 */
public class QueryUserRoleResult extends BaseVo {
    private Long id; //角色ID
    private String name; //角色名称
    private List<PermissionTree> permissionTreeList = new ArrayList<>(); //权限集合

    public List<PermissionTree> getPermissionTreeList() {
        return permissionTreeList;
    }

    public void setPermissionTreeList(List<PermissionTree> permissionTreeList) {
        this.permissionTreeList = permissionTreeList;
    }

    public QueryUserRoleResult(Long id, String name, List<PermissionTree> permissionTreeList) {
        this.id = id;
        this.name = name;
        this.permissionTreeList = permissionTreeList;
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


}
