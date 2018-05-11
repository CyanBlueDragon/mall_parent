package com.yunyihenkey.seller.web.vo.param.accountController;

import javax.validation.constraints.NotEmpty;

import com.yunyihenkey.common.vo.base.BaseVo;

/**
 * @author : 007
 * @date : 2018/5/4 14:37
 */
public class AddBankParam extends BaseVo {
	/** 持卡人姓名 */
	@NotEmpty
	private String bankUserName;

	/** 开户银行 */
	@NotEmpty
	private String bankName;

	/** 开户银行地址(某支行) */
	@NotEmpty
	private String bankAddress;

	/** 银行账号 */
	@NotEmpty
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

}
