package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.auth.service.enums.PermissionTypeEnum;
import com.yunyihenkey.auth.service.util.TreeUtil;
import com.yunyihenkey.auth.service.vo.authjwt.seller.PermissionTree;
import com.yunyihenkey.basedao.malldb.basemapper.SellerPermBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerPermMapper;
import com.yunyihenkey.seller.service.SellerPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/15 11:16
 */
@Service
public class SellerPermissionServiceImpl implements SellerPermissionService {
    @Autowired
    private SellerPermBaseMapper sellerPermBaseMapper;
    @Autowired
    private SellerPermMapper sellerPermMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sellerPermBaseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SellerPerm record) {
        return sellerPermBaseMapper.insert(record);
    }

    @Override
    public int insertSelective(SellerPerm record) {
        return sellerPermBaseMapper.insertSelective(record);
    }

    @Override
    public SellerPerm selectByPrimaryKey(Long id) {
        return sellerPermBaseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SellerPerm record) {
        return sellerPermBaseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SellerPerm record) {
        return sellerPermBaseMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PermissionTree> queryTreeList() {
        List<SellerPerm> sellerPermsList = sellerPermMapper.queryList();
        List<PermissionTree> permissionTreeList = new ArrayList<>();
        for (SellerPerm sellerPerm : sellerPermsList) {
            PermissionTree permissionTree = new PermissionTree();
            permissionTree.setId(sellerPerm.getId());
            permissionTree.setPid(sellerPerm.getPid());
            permissionTree.setCreateTime(sellerPerm.getCreateTime());
            permissionTree.setDescription(sellerPerm.getDescription());
            permissionTree.setIcon(sellerPerm.getIcon());
            permissionTree.setPermissionCode(sellerPerm.getPermissionCode());
            permissionTree.setPermissionName(sellerPerm.getPermissionName());
            permissionTree.setUrl(sellerPerm.getUrl());
            permissionTree.setType(sellerPerm.getType());
            permissionTreeList.add(permissionTree);
        }
        return TreeUtil.bulid(permissionTreeList, -1L);
    }
}
