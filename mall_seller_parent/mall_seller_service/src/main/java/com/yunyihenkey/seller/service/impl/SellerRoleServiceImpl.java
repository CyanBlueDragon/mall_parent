package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.auth.service.vo.authjwt.seller.PermissionTree;
import com.yunyihenkey.basedao.malldb.basemapper.SellerPermBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerRoleBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;
import com.yunyihenkey.basedao.malldb.basevo.SellerRole;
import com.yunyihenkey.basedao.malldb.basevo.SellerRolePerm;
import com.yunyihenkey.basedao.malldb.basevo.SellerUserRole;
import com.yunyihenkey.common.idworker.SnowflakeIdWorker;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerRoleMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerUserRoleMapper;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.*;
import com.yunyihenkey.seller.service.SellerPermissionService;
import com.yunyihenkey.seller.service.SellerRolePermService;
import com.yunyihenkey.seller.service.SellerRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 007
 * @date : 2018/5/4 16:51
 */
@Service
public class SellerRoleServiceImpl implements SellerRoleService {
    @Autowired
    private SellerRoleBaseMapper sellerRoleBaseMapper;

    @Autowired
    private SellerRoleMapper sellerRoleMapper;
    @Autowired
    private SellerPermBaseMapper sellerPermBaseMapper;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private SellerUserRoleMapper sellerUserRoleMapper;
    @Autowired
    private SellerRolePermService sellerRolePermService;
    @Autowired
    private SellerPermissionService sellerPermissionService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sellerRoleBaseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SellerRole record) {
        return sellerRoleBaseMapper.insert(record);
    }

    @Override
    public int insertSelective(SellerRole record) {
        return sellerRoleBaseMapper.insertSelective(record);
    }

    @Override
    public SellerRole selectByPrimaryKey(Long id) {
        return sellerRoleBaseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SellerRole record) {
        return sellerRoleBaseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SellerRole record) {
        return sellerRoleBaseMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<QueryRoleListResult> queryRoleList(String name) {
        return sellerRoleMapper.queryRoleList(name);
    }

    @Transactional
    @Override
    public ResultInfo<String> save(SaveRoleParam saveRoleParam) throws Exception {
        String name = saveRoleParam.getName();
        List<PermissionVO> permissionVOList = saveRoleParam.getPermissionVOList();
        //检查角色名是否存在
        if (query(name) != null) {
            LogUtils.getLogger().info("角色名已存在" + name);
            return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "角色名已存在", null);
        }
        //检查权限ID是否存在 不存在的删除
        List<SellerRolePerm> sellerRolePermList = new ArrayList<>();
        Long id = snowflakeIdWorker.nextId();
        for (int i = 0; i < permissionVOList.size(); i++) {
            SellerPerm sellerPerm = sellerPermBaseMapper.selectByPrimaryKey(permissionVOList.get(i).getId());
            if (sellerPerm == null) {
                permissionVOList.remove(i);
            }
            SellerRolePerm sellerRolePerm = new SellerRolePerm();
            sellerRolePerm.setPermissionId(sellerPerm.getId());
            sellerRolePerm.setRoleId(id);
            sellerRolePermList.add(sellerRolePerm);
        }
        //保存角色表
        SellerRole sellerRole = new SellerRole();
        sellerRole.setId(id);
        sellerRole.setName(name);
        sellerRole.setDescription(name);
        sellerRole.setCreateTime(DateUtil.getCurrentDate());
        sellerRoleBaseMapper.insertSelective(sellerRole);
        //保存角色与权限关系表
        saveBatchRolePermission(sellerRolePermList);
        return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);

    }

    @Override
    public List<SellerRole> queryRoleList(SellerRole sellerRole) {
        return sellerRoleMapper.queryAllRoleList(sellerRole);
    }

    @Override
    public SellerRole query(String name) {
        SellerRole sellerRole = new SellerRole();
        sellerRole.setName(name);
        List<SellerRole> sellerRoleList = sellerRoleMapper.queryAllRoleList(sellerRole);
        if (sellerRoleList != null && !sellerRoleList.isEmpty()) {
            return sellerRoleList.get(0);
        }
        return null;

    }

    @Override
    public void saveBatchRolePermission(List<SellerRolePerm> sellerRolePermList) throws Exception {
        sellerRoleMapper.saveBatchRolePermission(sellerRolePermList);
    }

    @Override
    public SellerRole queryLikeName(String name) {
        SellerRole sellerRole = new SellerRole();
        sellerRole.setName(name);
        List<SellerRole> sellerRoleList = sellerRoleMapper.queryRoleList(sellerRole);
        if (sellerRoleList != null && sellerRoleList.isEmpty()) {
            return sellerRoleList.get(0);
        }
        return null;
    }

    @Transactional
    @Override
    public ResultInfo<Object> delete(Long roleId) throws Exception {
        SellerUserRole sellerUserRole = new SellerUserRole();
        sellerUserRole.setRoleId(roleId);
        List<SellerUserRole> sellerUserRoleList = sellerUserRoleMapper.queryList(sellerUserRole);
        if (sellerUserRoleList != null && !sellerUserRoleList.isEmpty()) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "角色下还有用户不能删除");
        }
        //删除权限与角色关系
        sellerRolePermService.deleteByRoleId(roleId);
        //删除角色
        deleteByPrimaryKey(roleId);
        return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, "删除成功");
    }

    @Transactional
    @Override
    public ResultInfo<Object> updateBatch(UpdateRoleParam updateRoleParam) throws Exception {
        Long roleId = updateRoleParam.getId();
        List<PermissionVO> permissionVOList = updateRoleParam.getPermissionVOList();
        SellerRole sellerRole = selectByPrimaryKey(roleId);
        if (sellerRole == null) {
            LogUtils.getLogger().info("角色ID不存在");
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "角色ID不存在");
        }
        //检查是否存在的用户名
        SellerRole sellerRoleName = query(updateRoleParam.getName());
        if (sellerRoleName != null && sellerRoleName.getId().longValue() != sellerRole.getId().longValue()) {
            LogUtils.getLogger().info("角色名不存在");
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "角色名不存在");
        }
        //修改用户名
        SellerRole sellerRoleUp = new SellerRole();
        sellerRoleUp.setId(roleId);
        sellerRoleUp.setName(updateRoleParam.getName());
        sellerRoleUp.setUpdateTime(DateUtil.getCurrentDate());
        sellerRoleUp.setDescription(updateRoleParam.getName());
        updateByPrimaryKey(sellerRoleUp);
        //批量删除 批量增加
        sellerRolePermService.deleteByRoleId(roleId);
        List<SellerRolePerm> sellerRolePermList = new ArrayList<>();
        for (PermissionVO permissionVO : permissionVOList) {
            SellerRolePerm sellerRolePerm = new SellerRolePerm();
            sellerRolePerm.setRoleId(roleId);
            sellerRolePerm.setPermissionId(permissionVO.getId());
            sellerRolePermList.add(sellerRolePerm);
        }
        saveBatchRolePermission(sellerRolePermList);
        return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, "修改成功");
    }

    @Override
    public ResultInfo query(Long id) {
        SellerRole sellerRole = selectByPrimaryKey(id);
        if (sellerRole == null) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "角色ID不存在");
        }
        SellerRolePerm sellerRolePerm = new SellerRolePerm();
        sellerRolePerm.setRoleId(id);
        List<SellerPerm> sellerPermList = sellerRolePermService.queryRolePermList(sellerRolePerm);
        List<PermissionTree> permissionTreeList = sellerPermissionService.buildPermTree(sellerPermList);
        QueryUserRoleResult queryUserRoleResult = new QueryUserRoleResult(sellerRole.getId(), sellerRole.getName(), permissionTreeList);
        return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, queryUserRoleResult);
    }
}
