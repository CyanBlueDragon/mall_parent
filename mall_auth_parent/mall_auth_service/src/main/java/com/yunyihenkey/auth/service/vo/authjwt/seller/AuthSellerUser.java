package com.yunyihenkey.auth.service.vo.authjwt.seller;

public class AuthSellerUser {
	/** 用户id */
	private String id;

	/** 店铺id */
	private String shopId;

	/** 用户名 */
	private String userName;

	/** 用户类型#0,分销商|SELLER;2,员工|STAFF */
	private String userType;

	/** 父ID 用于员工用户 */
	private String parentUserId;

	/** 用户类型#0,A级分销商|A;1,B级分销商|B;2,C级分销商|C */
	private String sellerGrade;

	public AuthSellerUser() {
		super();
	}

	public AuthSellerUser(String id, String shopId, String userName, String userType, String parentUserId,
			String sellerGrade) {
		super();
		this.id = id;
		this.shopId = shopId;
		this.userName = userName;
		this.userType = userType;
		this.parentUserId = parentUserId;
		this.sellerGrade = sellerGrade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(String parentUserId) {
		this.parentUserId = parentUserId;
	}

	public String getSellerGrade() {
		return sellerGrade;
	}

	public void setSellerGrade(String sellerGrade) {
		this.sellerGrade = sellerGrade;
	}

}