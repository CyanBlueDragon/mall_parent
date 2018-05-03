package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysShoppingmallUser;

public interface MallSysShoppingmallUserBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysShoppingmallUser record);

    int insertSelective(MallSysShoppingmallUser record);

    MallSysShoppingmallUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysShoppingmallUser record);

    int updateByPrimaryKey(MallSysShoppingmallUser record);
}