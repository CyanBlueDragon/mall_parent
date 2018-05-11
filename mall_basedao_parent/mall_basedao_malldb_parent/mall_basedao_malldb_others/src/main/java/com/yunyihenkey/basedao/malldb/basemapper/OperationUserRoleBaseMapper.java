package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.OperationUserRole;

public interface OperationUserRoleBaseMapper {
    int insert(OperationUserRole record);

    int insertSelective(OperationUserRole record);
}