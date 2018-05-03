package com.yunyihenkey.auth.service.vo.response;

import java.io.Serializable;

/**
 * @author : 007
 * @date : 2018/5/3 17:02
 */
public class UserPermissionVo implements Serializable {
    private Long id;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String password;

    /**权限url*/
    private String url;

    /**权限代码*/
    private String permissionCode;

    /**角色*/
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
