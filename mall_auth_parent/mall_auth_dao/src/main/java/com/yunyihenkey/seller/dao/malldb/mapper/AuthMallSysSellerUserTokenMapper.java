package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerUserToken;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMallSysSellerUserTokenMapper {

	List<SellerUserToken> getByUserId(@Param("userId") String userId);

}