package com.yunyihenkey.seller.service;

import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;
import com.yunyihenkey.basedao.malldb.basevo.SellerRolePerm;

import java.util.List;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/18 14:25
 */
public interface SellerRolePermService {

    void deleteByRoleId(Long role) throws Exception;

    List<SellerRolePerm> queryList(SellerRolePerm sellerRolePerm);

    /**
     * 查询角色的权限
     *
     * @param sellerRolePerm
     * @return
     */
    List<SellerPerm> queryRolePermList(SellerRolePerm sellerRolePerm);


}
