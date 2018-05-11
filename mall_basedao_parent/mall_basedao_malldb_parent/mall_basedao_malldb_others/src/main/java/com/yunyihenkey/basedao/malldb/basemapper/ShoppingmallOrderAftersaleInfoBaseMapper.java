package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderAftersaleInfo;

public interface ShoppingmallOrderAftersaleInfoBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingmallOrderAftersaleInfo record);

    int insertSelective(ShoppingmallOrderAftersaleInfo record);

    ShoppingmallOrderAftersaleInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingmallOrderAftersaleInfo record);

    int updateByPrimaryKey(ShoppingmallOrderAftersaleInfo record);
}