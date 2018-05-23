package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;
import com.yunyihenkey.basedao.malldb.basevo.SellerRolePerm;

import java.util.List;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/18 14:13
 */
public interface SellerRolePermMapper {

    void deleteByRoleId(Long role);

    List<SellerRolePerm> queryList(SellerRolePerm sellerRolePerm);

    List<SellerPerm> queryRolePermList(SellerRolePerm sellerRolePerm);
}
