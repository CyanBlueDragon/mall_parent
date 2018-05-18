package com.yunyihenkey.seller.dao.malldb.vo.param.accountController;

import com.yunyihenkey.common.vo.base.BaseVo;

public class GetBankListResult extends BaseVo {

	/** 持卡人姓名 */
	private String bankUserName;

	/** 开户银行 */
	private String bankName;

	/** 银行账号 */
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

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

}
