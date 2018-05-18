package com.yunyihenkey.seller.service;

import com.yunyihenkey.auth.service.vo.authjwt.seller.PermissionTree;
import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;
import com.yunyihenkey.basedao.malldb.basevo.SellerRole;
import com.yunyihenkey.basedao.malldb.basevo.SellerRolePerm;
import com.yunyihenkey.common.vo.base.BaseService;

import java.util.List;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/15 11:11
 */
public interface SellerPermissionService extends BaseService<SellerPerm, Long> {
    /**
     * 权限树列表
     * @return
     */
    List<PermissionTree> queryTreeList();


}
