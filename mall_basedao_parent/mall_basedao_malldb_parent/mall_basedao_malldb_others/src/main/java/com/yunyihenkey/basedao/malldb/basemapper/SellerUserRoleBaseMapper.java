package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerUserRole;

public interface SellerUserRoleBaseMapper {
    int insert(SellerUserRole record);

    int insertSelective(SellerUserRole record);
}