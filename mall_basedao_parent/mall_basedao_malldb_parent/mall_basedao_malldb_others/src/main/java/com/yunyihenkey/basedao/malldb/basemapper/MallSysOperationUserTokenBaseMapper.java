package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysOperationUserToken;

public interface MallSysOperationUserTokenBaseMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(MallSysOperationUserToken record);

    int insertSelective(MallSysOperationUserToken record);

    MallSysOperationUserToken selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(MallSysOperationUserToken record);

    int updateByPrimaryKey(MallSysOperationUserToken record);
}