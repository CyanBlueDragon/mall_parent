package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysShoppingmallUserToken;

public interface MallSysShoppingmallUserTokenBaseMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(MallSysShoppingmallUserToken record);

    int insertSelective(MallSysShoppingmallUserToken record);

    MallSysShoppingmallUserToken selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(MallSysShoppingmallUserToken record);

    int updateByPrimaryKey(MallSysShoppingmallUserToken record);
}