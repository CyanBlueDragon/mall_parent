package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysOperationRole;

public interface MallSysOperationRoleBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysOperationRole record);

    int insertSelective(MallSysOperationRole record);

    MallSysOperationRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysOperationRole record);

    int updateByPrimaryKey(MallSysOperationRole record);
}