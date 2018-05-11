package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.OperationRole;

public interface OperationRoleBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OperationRole record);

    int insertSelective(OperationRole record);

    OperationRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OperationRole record);

    int updateByPrimaryKey(OperationRole record);
}