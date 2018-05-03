package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysOperationUserRole;

public interface MallSysOperationUserRoleBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysOperationUserRole record);

    int insertSelective(MallSysOperationUserRole record);

    MallSysOperationUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysOperationUserRole record);

    int updateByPrimaryKey(MallSysOperationUserRole record);
}