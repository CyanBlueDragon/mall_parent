package com.yunyihenkey.auth.service.vo.authjwt;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.yunyihenkey.auth.service.enums.LoginSourceEnum;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;

public class GetTokenParam {

	/** 用户名 **/
	@NotEmpty
	private String userName;

	/** 密码 **/
	@NotEmpty
	private String password;

	/** 系统来源 **/
	@NotNull
	private SystemCodeEnum systemCodeEnum;

	/** 登录来源 **/
	@NotNull
	private LoginSourceEnum loginSourceEnum;

	public GetTokenParam() {
		super();
	}

	public GetTokenParam(String userName, String password, SystemCodeEnum systemCodeEnum,
			LoginSourceEnum loginSourceEnum) {
		super();
		this.userName = userName;
		this.password = password;
		this.systemCodeEnum = systemCodeEnum;
		this.loginSourceEnum = loginSourceEnum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SystemCodeEnum getSystemCodeEnum() {
		return systemCodeEnum;
	}

	public void setSystemCodeEnum(SystemCodeEnum systemCodeEnum) {
		this.systemCodeEnum = systemCodeEnum;
	}

	public LoginSourceEnum getLoginSourceEnum() {
		return loginSourceEnum;
	}

	public void setLoginSourceEnum(LoginSourceEnum loginSourceEnum) {
		this.loginSourceEnum = loginSourceEnum;
	}

	public static void main(String[] args) {
		GetTokenParam getTokenParam = new GetTokenParam();
		getTokenParam.setUserName("sssss");

		System.out.println(ValidatorUtils.validatorFactory.getValidator().validate(getTokenParam, Default.class));
	}

}
