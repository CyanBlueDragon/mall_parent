package com.yunyihenkey.seller.dao.malldb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author : 007
 * @date : 2018/5/3 18:59
 */
public interface AuthSellerRoleMapper {

	List<String> getRoleNameByUserId(@Param("userId") Long userId);

}
