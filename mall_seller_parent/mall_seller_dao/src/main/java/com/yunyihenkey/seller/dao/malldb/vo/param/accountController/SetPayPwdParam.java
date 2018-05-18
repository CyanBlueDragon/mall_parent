package com.yunyihenkey.seller.dao.malldb.vo.param.accountController;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;

import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseVo;

public class SetPayPwdParam extends BaseVo {

	/** 支付密码绑定的手机号 */
	@NotEmpty(message = "手机号码不能为空")
	@Length(min = 11, max = 11, message = "手机号码长度最少{min}位")
	private String payPasswordMobile;

	/** 验证码 */
	@NotEmpty(message = "验证码不能为空")
	private String verifyCode;

	/** 支付密码 */
	@NotEmpty(message = "支付密码不能为空")
	@Pattern(regexp = "^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$", message = "支付密码长度为6-20位,由数字和字母组成,至少包含数字和字母")
	private String payPassword;

	public String getPayPasswordMobile() {
		return payPasswordMobile;
	}

	public void setPayPasswordMobile(String payPasswordMobile) {
		this.payPasswordMobile = payPasswordMobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

//	public static void main(String[] args) {
//		SetPayPwdParam setPayPwdParam = new SetPayPwdParam();
//		setPayPwdParam.setPayPasswordMobile("13265602326");
//		setPayPwdParam.setVerifyCode("5564");
//		setPayPwdParam.setPayPassword("123456789");
//		for (ConstraintViolation<SetPayPwdParam> p : ValidatorUtils.validatorFactory.getValidator()
//				.validate(setPayPwdParam, Default.class)) {
//			System.out.println(p.getMessage());
//		}
//	}

}
