package com.yunyihenkey.supplier.dao.malldb.vo.param.Review;

import com.yunyihenkey.common.vo.base.BaseVo;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @ClassName GoodsQualifiedParam
 * @Description 对商品批量审核
 * @Author LuTong
 * @Date 2018/5/22 16:58
 * @Version 1.0
 */
public class GoodsQualifiedParam extends BaseVo {

    @NotNull(message = "商品id不可为空")
    private String ids;

    @NotNull(message = "请输入要修改的状态编码")
    @Min(value = 0)
    @Max(value = 6)
    private Integer status;

    public String getId() {
        return ids;
    }

    public void setId(String ids) {
        this.ids = ids;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
