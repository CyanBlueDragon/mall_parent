package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysOperationRolePerm;

public interface MallSysOperationRolePermBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysOperationRolePerm record);

    int insertSelective(MallSysOperationRolePerm record);

    MallSysOperationRolePerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysOperationRolePerm record);

    int updateByPrimaryKey(MallSysOperationRolePerm record);
}