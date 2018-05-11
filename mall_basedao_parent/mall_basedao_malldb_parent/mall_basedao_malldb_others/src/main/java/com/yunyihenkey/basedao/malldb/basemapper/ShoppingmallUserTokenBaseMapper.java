package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallUserToken;

public interface ShoppingmallUserTokenBaseMapper {
    int insert(ShoppingmallUserToken record);

    int insertSelective(ShoppingmallUserToken record);
}