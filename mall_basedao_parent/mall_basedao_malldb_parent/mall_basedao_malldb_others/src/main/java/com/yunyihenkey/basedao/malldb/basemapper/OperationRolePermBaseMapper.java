package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.OperationRolePerm;

public interface OperationRolePermBaseMapper {
    int insert(OperationRolePerm record);

    int insertSelective(OperationRolePerm record);
}