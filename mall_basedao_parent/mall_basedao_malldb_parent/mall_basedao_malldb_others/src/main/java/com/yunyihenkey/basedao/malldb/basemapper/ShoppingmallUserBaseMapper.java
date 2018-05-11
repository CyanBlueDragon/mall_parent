package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallUser;

public interface ShoppingmallUserBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingmallUser record);

    int insertSelective(ShoppingmallUser record);

    ShoppingmallUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingmallUser record);

    int updateByPrimaryKey(ShoppingmallUser record);
}