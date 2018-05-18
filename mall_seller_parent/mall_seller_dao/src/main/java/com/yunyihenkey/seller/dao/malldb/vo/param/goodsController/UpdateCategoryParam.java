package com.yunyihenkey.seller.dao.malldb.vo.param.goodsController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateCategoryParam {

	/** 主键 */
	@NotNull(message = "不能为空！")
	@Size(min = 1, message = "不能为空！")
	private Long[] ids;

	/** 分类id */
	@NotNull(message = "不能为空！")
	@Min(value = 1, message = "不存在!")
	private Long categoryId;

	public Long[] getIds() {
		return ids;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
