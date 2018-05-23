package com.yunyihenkey.seller.service;

import com.yunyihenkey.basedao.malldb.basevo.SellerRole;
import com.yunyihenkey.basedao.malldb.basevo.SellerRolePerm;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.QueryRoleListResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.RoleDeleteParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.SaveRoleParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.UpdateRoleParam;

import java.util.List;

/**
 * @author : 007
 * @date : 2018/5/4 16:36
 */
public interface SellerRoleService extends BaseService<SellerRole, Long> {
    /**
     * 查询角色列表
     *
     * @param name
     * @return
     */
    List<QueryRoleListResult> queryRoleList(String name);

    /**
     * 保存角色
     *
     * @param saveRoleParam
     * @return
     */
    ResultInfo<String> save(SaveRoleParam saveRoleParam) throws Exception;

    /**
     * 查询角色列表
     *
     * @param sellerRole 角色实体
     * @return
     */
    List<SellerRole> queryRoleList(SellerRole sellerRole);

    /**
     * @param name 角色 名
     * @return
     */
    SellerRole query(String name);

    /**
     * 模糊查询名称
     *
     * @param name 角色名称
     * @return
     */
    SellerRole queryLikeName(String name);

    /**
     * 批量增加角色权限关系表
     *
     * @param sellerRolePermList
     */
    void saveBatchRolePermission(List<SellerRolePerm> sellerRolePermList) throws Exception;

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    ResultInfo<Object> delete(Long roleId) throws Exception;

    /**
     * 批量修改角色权限关系表
     *
     * @param updateRoleParam
     * @return
     * @throws Exception
     */
    ResultInfo<Object> updateBatch(UpdateRoleParam updateRoleParam) throws Exception;

    /**
     * 查询角色与权限
     *
     * @param id
     * @return
     */
    ResultInfo query(Long id);
}
