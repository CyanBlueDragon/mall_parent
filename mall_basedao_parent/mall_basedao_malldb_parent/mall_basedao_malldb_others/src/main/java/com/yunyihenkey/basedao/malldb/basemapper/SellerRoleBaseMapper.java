package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerRole;

public interface SellerRoleBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerRole record);

    int insertSelective(SellerRole record);

    SellerRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerRole record);

    int updateByPrimaryKey(SellerRole record);
}