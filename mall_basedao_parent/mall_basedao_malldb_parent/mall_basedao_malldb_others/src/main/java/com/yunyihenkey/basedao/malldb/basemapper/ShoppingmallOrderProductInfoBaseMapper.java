package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderProductInfo;

public interface ShoppingmallOrderProductInfoBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingmallOrderProductInfo record);

    int insertSelective(ShoppingmallOrderProductInfo record);

    ShoppingmallOrderProductInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingmallOrderProductInfo record);

    int updateByPrimaryKey(ShoppingmallOrderProductInfo record);
}