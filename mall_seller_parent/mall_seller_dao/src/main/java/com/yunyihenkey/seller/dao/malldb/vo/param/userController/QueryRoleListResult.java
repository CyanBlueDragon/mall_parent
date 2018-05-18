package com.yunyihenkey.seller.dao.malldb.vo.param.userController;

import com.yunyihenkey.common.vo.base.BaseVo;

import java.util.Date;

/**
 * @author HeXing
 * @desc 权限列表返回参数
 * @date 2018/5/12 17:32
 */
public class QueryRoleListResult extends BaseVo {
    private Long id;
    private String name;
    private Integer numb;
    private Date createTime;

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

    public Integer getNumb() {
        return numb;
    }

    public void setNumb(Integer numb) {
        this.numb = numb;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
