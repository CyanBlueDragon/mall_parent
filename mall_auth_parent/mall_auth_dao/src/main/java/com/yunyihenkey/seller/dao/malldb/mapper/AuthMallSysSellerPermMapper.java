package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AuthMallSysSellerPermMapper {

	List<SellerPerm> getPermByUserId(@Param("userId")Long userId);
	
	
}