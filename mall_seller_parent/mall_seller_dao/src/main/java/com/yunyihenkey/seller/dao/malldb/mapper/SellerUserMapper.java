package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerUser;

public interface SellerUserMapper {
	/**
	 * 查询用户
	 * 
	 * @param SellerUser
	 * @return
	 */
	SellerUser selectUser(SellerUser SellerUser);

}