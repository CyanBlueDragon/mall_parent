package com.yunyihenkey.seller.dao.malldb.vo.param.accountController;

import com.yunyihenkey.common.vo.base.BaseVo;

public class IsSetPayPwdResult extends BaseVo {

	/** 是否设置了支付密码 */
	private Boolean IsSetPayPwd;

	public IsSetPayPwdResult() {
		super();
	}

	public IsSetPayPwdResult(Boolean isSetPayPwd) {
		super();
		IsSetPayPwd = isSetPayPwd;
	}

	public Boolean getIsSetPayPwd() {
		return IsSetPayPwd;
	}

	public void setIsSetPayPwd(Boolean isSetPayPwd) {
		IsSetPayPwd = isSetPayPwd;
	}

}
