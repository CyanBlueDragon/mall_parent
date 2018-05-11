package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallUserAddress;

public interface ShoppingmallUserAddressBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingmallUserAddress record);

    int insertSelective(ShoppingmallUserAddress record);

    ShoppingmallUserAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingmallUserAddress record);

    int updateByPrimaryKey(ShoppingmallUserAddress record);
}