package com.yunyihenkey.seller.dao.malldb.vo.param.GoodsCategoryController;

import javax.validation.constraints.NotBlank;

import com.yunyihenkey.common.vo.page.PageParam;

/**
 * @desc:
 * @author: zhouh
 * @date: 2018年5月21日 上午9:53:35
 */
public class QueryCategoryParam extends PageParam {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空！")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
