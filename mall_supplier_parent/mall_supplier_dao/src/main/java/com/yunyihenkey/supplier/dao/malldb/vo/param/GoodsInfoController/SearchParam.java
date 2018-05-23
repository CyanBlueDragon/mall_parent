package com.yunyihenkey.supplier.dao.malldb.vo.param.GoodsInfoController;

import com.yunyihenkey.common.vo.page.PageParam;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName SearchParam
 * @Description 使用solr全文检索的参数传递类
 * @Author LuTong
 * @Date 2018/5/16 9:18
 * @Version 1.0
 */
public class SearchParam extends PageParam {

    @NotEmpty(message = "务必输入查询字段")
    private String queryString;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
