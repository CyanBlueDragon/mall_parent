package com.yunyihenkey.seller.dao.malldb.vo.param.accountController;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.yunyihenkey.common.utils.JacksonUtils;
import com.yunyihenkey.common.vo.base.BaseVo;

public class AddBankCardParam extends BaseVo {
	/** 持卡人姓名 */
	@NotEmpty(message = "持卡人姓名不能为空")
	private String bankUserName;

	/** 开户银行 */
	@NotEmpty(message = "开户银行不能为空")
	private String bankName;

	/** 开户银行地址(某支行) */
	@NotEmpty(message = "开户银行地址不能为空")
	private String bankAddress;

	/** 银行账号 */
	@NotEmpty(message = "银行账号不能为空")
	@Length(min = 14, max = 21, message = "银行卡号最少{min}位,最多{max}位")
	private String bankCardNumber;

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	public static void main(String[] args) {
		System.out.println(JacksonUtils.writeValueAsString(new AddBankCardParam()));
	}

}
