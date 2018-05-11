package com.yunyihenkey.seller.dao.malldb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunyihenkey.basedao.malldb.basevo.SellerUserToken;

public interface AuthSellerUserTokenMapper {

	List<SellerUserToken> getByUserId(@Param("userId") String userId);

}