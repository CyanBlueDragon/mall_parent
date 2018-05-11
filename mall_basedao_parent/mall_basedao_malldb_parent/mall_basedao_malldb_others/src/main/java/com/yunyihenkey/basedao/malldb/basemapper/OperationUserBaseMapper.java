package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.OperationUser;

public interface OperationUserBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OperationUser record);

    int insertSelective(OperationUser record);

    OperationUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OperationUser record);

    int updateByPrimaryKey(OperationUser record);
}