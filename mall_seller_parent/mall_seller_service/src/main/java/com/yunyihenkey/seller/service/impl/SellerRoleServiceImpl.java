package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.basedao.malldb.basemapper.SellerPermBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;
import com.yunyihenkey.basedao.malldb.basevo.SellerRolePerm;
import com.yunyihenkey.common.idworker.SnowflakeIdWorker;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.PermissionVO;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.QueryRoleListResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.SaveRoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.basedao.malldb.basemapper.SellerRoleBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerRole;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerRoleMapper;
import com.yunyihenkey.seller.service.SellerRoleService;
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
    public ResultInfo<String> save(SaveRoleParam saveRoleParam) {
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
    public void saveBatchRolePermission(List<SellerRolePerm> sellerRolePermList) {
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
}
