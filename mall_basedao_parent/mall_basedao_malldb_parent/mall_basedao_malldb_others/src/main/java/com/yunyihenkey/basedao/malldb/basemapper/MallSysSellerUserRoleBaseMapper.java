package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysSellerUserRole;

public interface MallSysSellerUserRoleBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysSellerUserRole record);

    int insertSelective(MallSysSellerUserRole record);

    MallSysSellerUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysSellerUserRole record);

    int updateByPrimaryKey(MallSysSellerUserRole record);
}