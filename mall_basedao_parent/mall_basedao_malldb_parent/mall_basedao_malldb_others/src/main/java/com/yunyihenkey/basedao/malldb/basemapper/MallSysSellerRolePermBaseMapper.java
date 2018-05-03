package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysSellerRolePerm;

public interface MallSysSellerRolePermBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysSellerRolePerm record);

    int insertSelective(MallSysSellerRolePerm record);

    MallSysSellerRolePerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysSellerRolePerm record);

    int updateByPrimaryKey(MallSysSellerRolePerm record);
}