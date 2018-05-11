package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerUser;
import org.apache.ibatis.annotations.Param;

public interface AuthMallSysSellerUserMapper {

	int verificationPhone(@Param("phoneNumber") String phoneNumber);

	SellerUser selectByUserName(@Param("userName") String userName);

	boolean isPasswordTrue(@Param("userName") String userName, @Param("password") String password);

	Long getIdByUserName(@Param("userName") String userName);
}