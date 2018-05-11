package com.yunyihenkey.seller.dao.malldb.mapper;

import org.apache.ibatis.annotations.Param;

import com.yunyihenkey.basedao.malldb.basevo.SellerUser;

public interface AuthSellerUserMapper {

	int verificationPhone(@Param("phoneNumber") String phoneNumber);

	SellerUser selectByUserName(@Param("userName") String userName);

	boolean isPasswordTrue(@Param("userName") String userName, @Param("password") String password);

	Long getIdByUserName(@Param("userName") String userName);


}