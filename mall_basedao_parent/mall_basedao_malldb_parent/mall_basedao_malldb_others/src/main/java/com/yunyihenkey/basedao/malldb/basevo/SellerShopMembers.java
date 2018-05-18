package com.yunyihenkey.basedao.malldb.basevo;

import java.io.Serializable;
import java.util.Date;

public class SellerShopMembers implements Serializable {
    private Long id;

    /** 店铺Id */
    private Long shopId;

    /** 会员账号 */
    private String membersAccount;

    /** 手机号码 */
    private String phoneNum;

    /** 会员名称 */
    private String membersName;

    /** 余额 */
    private Long balance;

    /** 状态#0,正常|NORMAL; 1,黑名单|BLACKLIST */
    private Integer state;

    private Date createTime;

    /** 是否删除#0,否|No;1,是|Yes */
    private Integer isDelete;

    /** 修改时间 */
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

    public String getMembersAccount() {
        return membersAccount;
    }

    public void setMembersAccount(String membersAccount) {
        this.membersAccount = membersAccount == null ? null : membersAccount.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getMembersName() {
        return membersName;
    }

    public void setMembersName(String membersName) {
        this.membersName = membersName == null ? null : membersName.trim();
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
        sb.append(", membersAccount=").append(membersAccount);
        sb.append(", phoneNum=").append(phoneNum);
        sb.append(", membersName=").append(membersName);
        sb.append(", balance=").append(balance);
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}