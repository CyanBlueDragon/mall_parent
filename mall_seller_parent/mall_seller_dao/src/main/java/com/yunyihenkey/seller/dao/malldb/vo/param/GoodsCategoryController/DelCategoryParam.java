package com.yunyihenkey.seller.dao.malldb.vo.param.GoodsCategoryController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @desc:
 * @author: zhouh
 * @date: 2018年5月21日 上午10:02:00
 */
public class DelCategoryParam {

    @NotNull(message = "id不存在！")
    @Min(value = 1, message = "id不存在！")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
