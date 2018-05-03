package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysOperationPerm;

public interface MallSysOperationPermBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysOperationPerm record);

    int insertSelective(MallSysOperationPerm record);

    MallSysOperationPerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysOperationPerm record);

    int updateByPrimaryKey(MallSysOperationPerm record);
}