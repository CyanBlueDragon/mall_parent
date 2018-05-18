package com.yunyihenkey.seller.dao.malldb.vo.param.accountController;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.yunyihenkey.common.utils.JacksonUtils;
import com.yunyihenkey.common.vo.base.BaseVo;

public class GetVerifyCodeSetPayPwdParam extends BaseVo {

	/** 支付密码绑定的手机号 */
	@NotEmpty(message = "手机号不能为空")
	@Length(min = 11, max = 11, message = "手机号码长度最少{min}位")
	private String payPasswordMobile;

	public String getPayPasswordMobile() {
		return payPasswordMobile;
	}

	public void setPayPasswordMobile(String payPasswordMobile) {
		this.payPasswordMobile = payPasswordMobile;
	}

	public static void main(String[] args) {
		System.out.println(JacksonUtils.writeValueAsString(new GetVerifyCodeSetPayPwdParam()));
	}

}
