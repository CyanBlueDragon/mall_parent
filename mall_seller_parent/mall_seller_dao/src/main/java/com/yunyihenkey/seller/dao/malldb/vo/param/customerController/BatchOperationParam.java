package com.yunyihenkey.seller.dao.malldb.vo.param.customerController;

/**
 * @author LiarYang
 * @date 2018/5/21 17:49
 * @desc
 */
public class BatchOperationParam {
    private Long[] ids;
    private Integer operation;


    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}
