package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerRolePerm;

public interface SellerRolePermBaseMapper {
    int insert(SellerRolePerm record);

    int insertSelective(SellerRolePerm record);
}