package com.yunyihenkey.seller.web.vo.param.userController;

import java.io.Serializable;

/**
 * @author : 007
 * @date : 2018/5/6 16:53
 */
public class VerificationCodeResult implements Serializable {
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVriCode() {
        return vriCode;
    }

    public void setVriCode(String vriCode) {
        this.vriCode = vriCode;
    }

    private String vriCode;



}
