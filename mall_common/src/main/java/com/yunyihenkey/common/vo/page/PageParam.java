package com.yunyihenkey.common.vo.page;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.yunyihenkey.common.vo.base.BaseVo;

public class PageParam extends BaseVo {

	/** 当前页 */
	@NotNull
	@Min(value = 1, message = "当前页错误")
	private Integer pageNum;

	/** 每页的数量 */
	@NotNull
	@Min(value = 10, message = "每页的数量最小为{value}")
	@Max(value = 100, message = "每页的数量最大为{value}")
	private Integer pageSize;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
