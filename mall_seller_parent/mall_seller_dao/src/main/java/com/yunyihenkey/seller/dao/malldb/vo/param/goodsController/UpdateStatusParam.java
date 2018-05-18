package com.yunyihenkey.seller.dao.malldb.vo.param.goodsController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateStatusParam {

	/** 主键id */
	@NotNull(message = "不能为空！")
	@Size(min = 1, message = "不能为空！")
	private Long[] ids;

	/** 商品状态 */
	@NotNull(message = "不能为空！")
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
