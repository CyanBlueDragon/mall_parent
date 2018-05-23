package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerUserRole;

import java.util.List;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/18 12:26
 */
public interface SellerUserRoleMapper {
    List<SellerUserRole> queryList(SellerUserRole sellerUserRole);
}
