package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerRole;
import com.yunyihenkey.basedao.malldb.basevo.SellerRolePerm;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.QueryRoleListResult;

import java.util.List;

/**
 * @author : HEXING
 * @date : 2018/5/3 18:59
 */
public interface SellerRoleMapper {
    /**
     * 查询角色列表
     *
     * @param name
     * @return
     */
    List<QueryRoleListResult> queryRoleList(String name);

    /**
     * 连表查询
     *
     * @param sellerRole
     * @return
     */
    List<SellerRole> queryRoleList(SellerRole sellerRole);

    List<SellerRole> queryAllRoleList(SellerRole sellerRole);

    /**
     * 批量增加角色权限关系表
     * @param sellerRolePermList
     */
    void saveBatchRolePermission(List<SellerRolePerm> sellerRolePermList) ;

}
