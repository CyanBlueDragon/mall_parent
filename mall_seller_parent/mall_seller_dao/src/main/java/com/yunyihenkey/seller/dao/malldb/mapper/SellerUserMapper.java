package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerUser;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.QueryListParam;

import java.util.List;

public interface SellerUserMapper {

	List<SellerUser> queryUserList(SellerUser SellerUser);

	List<SellerUser> queryUserRoleList(QueryListParam queryListParam);




}