package com.yunyihenkey.auth.dao.vo;

import java.io.Serializable;
import java.util.Date;

/**
 *  分销平台的用户表
 */
public class MallSysSellerUser implements Serializable{
    private Long id;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String password;

    /** 邮箱 */
    private String email;

    /** 手机号码 */
    private String mobile;

    /** 性别 */
    private Integer sex;

    /** 头像地址 */
    private String logoUrl;

    /** 生日 */
    private Date birthday;

    /** 用户类型#0,卖家|SELLER;1,分销商|DISTRIBUTOR;2,员工|STAFF */
    private Integer userType;

    /** 父ID 用于员工用户 */
    private Long parentUserId;

    /** 注册来源#0,Android|Android;1,IOS|IOS;2,Web|Web */
    private Integer registerSource;

    /** 注册ip */
    private String registerIp;

    private Date updateTime;

    private Date createTime;

    /** 城市区号 */
    private String cityCode;

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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Long parentUserId) {
        this.parentUserId = parentUserId;
    }

    public Integer getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(Integer registerSource) {
        this.registerSource = registerSource;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp == null ? null : registerIp.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }
}