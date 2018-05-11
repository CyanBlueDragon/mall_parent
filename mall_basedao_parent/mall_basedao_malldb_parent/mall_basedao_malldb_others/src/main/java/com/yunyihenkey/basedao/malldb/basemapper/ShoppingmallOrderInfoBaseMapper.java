package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderInfo;

public interface ShoppingmallOrderInfoBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShoppingmallOrderInfo record);

    int insertSelective(ShoppingmallOrderInfo record);

    ShoppingmallOrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingmallOrderInfo record);

    int updateByPrimaryKey(ShoppingmallOrderInfo record);
}