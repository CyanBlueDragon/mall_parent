package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.OperationPerm;

public interface OperationPermBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OperationPerm record);

    int insertSelective(OperationPerm record);

    OperationPerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OperationPerm record);

    int updateByPrimaryKey(OperationPerm record);
}