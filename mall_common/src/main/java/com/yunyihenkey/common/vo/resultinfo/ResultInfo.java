package com.yunyihenkey.common.vo.resultinfo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResultInfo<E> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 系统编码 */
	private Integer systemCode;

	/** 状态码 */
	private Integer statusCode;

	/** 返回提示信息 */
	private String msg;

	/** 返回数据 */
	private E data;

	public ResultInfo(SystemCodeEnum systemCodeEnum, CodeEnum codeEnum) {
		this.systemCode = systemCodeEnum.getValue();
		this.statusCode = codeEnum.getValue();
		this.msg = codeEnum.getText();
	}

	public ResultInfo(SystemCodeEnum systemCodeEnum, CodeEnum codeEnum, String msg) {
		this.systemCode = systemCodeEnum.getValue();
		this.statusCode = codeEnum.getValue();
		this.msg = msg;
	}

	public ResultInfo(SystemCodeEnum systemCodeEnum, CodeEnum codeEnum, E data) {
		this.systemCode = systemCodeEnum.getValue();
		this.statusCode = codeEnum.getValue();
		this.msg = codeEnum.getText();
		this.data = data;
	}

	public ResultInfo(SystemCodeEnum systemCodeEnum, CodeEnum codeEnum, String msg, E data) {
		this.systemCode = systemCodeEnum.getValue();
		this.statusCode = codeEnum.getValue();
		this.msg = msg;
		this.data = data;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Integer getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(Integer systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * 
	 * @desc 是否失败状态
	 * @return true：失败； false：成功
	 */
	public static boolean isFailed(ResultInfo resultInfo) {
		return resultInfo != null && resultInfo.getStatusCode() > 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("msg", this.msg).append("statusCode", this.statusCode)
				.append("systemCode", this.systemCode).append("data", this.data).toString();
	}
}
