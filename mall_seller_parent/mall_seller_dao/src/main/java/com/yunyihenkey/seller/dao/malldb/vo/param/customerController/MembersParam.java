package com.yunyihenkey.seller.dao.malldb.vo.param.customerController;

import com.yunyihenkey.common.vo.page.PageParam;

/**
 * @author LiarYang
 * @date 2018/5/18 11:53
 * @desc
 */
public class MembersParam extends PageParam {
    /**
     * 会员名称
     */
    private String membersName;
    /**
     * 会员账号
     */
    private String membersAccount;

    /**
     * 状态#0,正常|NORMAL; 1,黑名单|BLACKLIST
     */
    private Integer state;

    public String getMembersName() {
        return membersName;
    }

    public void setMembersName(String membersName) {
        this.membersName = membersName;
    }

    public String getMembersAccount() {
        return membersAccount;
    }

    public void setMembersAccount(String membersAccount) {
        this.membersAccount = membersAccount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
