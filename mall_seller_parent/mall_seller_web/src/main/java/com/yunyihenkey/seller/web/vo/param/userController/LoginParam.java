package com.yunyihenkey.seller.web.vo.param.userController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.yunyihenkey.common.vo.base.BaseVo;

/**
 * @author : 007
 * @date : 2018/5/4 14:37
 */
public class LoginParam extends BaseVo {
	@NotEmpty
	private String userName;

	@NotEmpty
	private String password;

	/** 验证码 */
	@NotEmpty
	private String vCationCode;

	/** 0 Android 1IOS 2PCClient 3Web */
	@NotNull
	private Integer loginSourceEnum;

	/** 验证码绑定的uuid */
	@NotEmpty
	private String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getLoginSourceEnum() {
		return loginSourceEnum;
	}

	public void setLoginSourceEnum(Integer loginSourceEnum) {
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

	public String getvCationCode() {
		return vCationCode;
	}

	public void setvCationCode(String vCationCode) {
		this.vCationCode = vCationCode;
	}

}
