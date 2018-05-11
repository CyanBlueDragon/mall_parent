package com.yunyihenkey.seller.dao.malldb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;

public interface AuthSellerPermMapper {

	List<SellerPerm> getPermByUserId(@Param("userId") Long userId);

}