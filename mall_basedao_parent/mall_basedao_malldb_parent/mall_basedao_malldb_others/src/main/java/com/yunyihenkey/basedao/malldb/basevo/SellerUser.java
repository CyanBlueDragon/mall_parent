package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerUser implements Serializable {
    private Long id;

    /** 店铺id */
    private Long shopId;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String password;

    /** 邮箱 */
    private String email;

    /** 手机号码 */
    private String mobile;

    /** 性别#0,男|MAN;1,女|WOMAN; */
    private Integer sex;

    /** 头像地址 */
    private String logoUrl;

    /** 生日 */
    private Date birthday;

    /** 用户类型#0,分销商|SELLER;2,员工|STAFF */
    private Integer userType;

    /** 父ID 用于员工用户 */
    private Long parentUserId;

    /** 注册来源#0,Android|Android;1,IOS|IOS;2,Web|Web */
    private Integer registerSource;

    /** 注册ip */
    private String registerIp;

    /** 城市区号 */
    private String cityCode;

    /** 用户类型#0,A级分销商|A;1,B级分销商|B;2,C级分销商|C */
    private Integer sellerGrade;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public Integer getSellerGrade() {
        return sellerGrade;
    }

    public void setSellerGrade(Integer sellerGrade) {
        this.sellerGrade = sellerGrade;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", mobile=").append(mobile);
        sb.append(", sex=").append(sex);
        sb.append(", logoUrl=").append(logoUrl);
        sb.append(", birthday=").append(birthday);
        sb.append(", userType=").append(userType);
        sb.append(", parentUserId=").append(parentUserId);
        sb.append(", registerSource=").append(registerSource);
        sb.append(", registerIp=").append(registerIp);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", sellerGrade=").append(sellerGrade);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}