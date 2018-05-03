package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysOperationUser;

public interface MallSysOperationUserBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysOperationUser record);

    int insertSelective(MallSysOperationUser record);

    MallSysOperationUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysOperationUser record);

    int updateByPrimaryKey(MallSysOperationUser record);
}