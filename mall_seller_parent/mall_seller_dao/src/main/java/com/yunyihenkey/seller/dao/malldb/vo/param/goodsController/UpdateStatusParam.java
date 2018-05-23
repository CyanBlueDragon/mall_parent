package com.yunyihenkey.seller.dao.malldb.vo.param.goodsController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @desc:
 * @author: zhouh
 * @date: 2018年5月22日 下午3:46:35
 */
public class UpdateStatusParam {

    /**
     * 主键id
     */
    @NotNull(message = "id不能为空！")
    @Size(min = 1, message = "id不能为空！")
    private Long[] ids;

    /**
     * 商品状态
     */
    @NotNull(message = "商品状态不能为空！")
    @Min(value = 0, message = "商品状态不存在!")
    private Integer status;

    public Long[] getIds() {
        return ids;
    }

    public Integer getStatus() {
        return status;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
