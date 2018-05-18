package com.yunyihenkey.seller.dao.malldb.vo.param.accountController;

import com.yunyihenkey.common.vo.base.BaseVo;

public class GetShopInfoResult extends BaseVo {

	/** 主键ID，推广编码使用主键ID转32进制 */
	private Long id;
	/** 店铺名称 */
	private String name;
	/** 店铺logo */
	private String logoUrl;
	/** 分销商级别#0,A级分销商|A;1,B级分销商|B;2,C级分销商|C */
	private Integer sellerGrade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public Integer getSellerGrade() {
		return sellerGrade;
	}

	public void setSellerGrade(Integer sellerGrade) {
		this.sellerGrade = sellerGrade;
	}

}
