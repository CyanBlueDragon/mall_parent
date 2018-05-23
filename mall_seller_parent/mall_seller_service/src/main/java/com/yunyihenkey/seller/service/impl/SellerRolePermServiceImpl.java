package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;
import com.yunyihenkey.basedao.malldb.basevo.SellerRolePerm;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerRolePermMapper;
import com.yunyihenkey.seller.service.SellerRolePermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/18 14:28
 */
@Service
public class SellerRolePermServiceImpl implements SellerRolePermService {
    @Autowired
    private SellerRolePermMapper sellerRolePermMapper;

    @Override
    public void deleteByRoleId(Long role) throws Exception {
        if (role == null) {
            throw new NullPointerException("角色ID不能为空");
        }
        sellerRolePermMapper.deleteByRoleId(role);
    }

    @Override
    public List<SellerRolePerm> queryList(SellerRolePerm sellerRolePerm) {
        return sellerRolePermMapper.queryList(sellerRolePerm);
    }

    @Override
    public List<SellerPerm> queryRolePermList(SellerRolePerm sellerRolePerm) {
        return sellerRolePermMapper.queryRolePermList(sellerRolePerm);
    }
}
