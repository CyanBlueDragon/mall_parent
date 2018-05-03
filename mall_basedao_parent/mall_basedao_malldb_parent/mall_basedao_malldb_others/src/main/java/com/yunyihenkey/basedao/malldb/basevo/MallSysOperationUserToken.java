package com.yunyihenkey.basedao.malldb.basevo;

public class MallSysOperationUserToken {
    private Long userId;

    /** tokenID */
    private String tokenId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId == null ? null : tokenId.trim();
    }
}