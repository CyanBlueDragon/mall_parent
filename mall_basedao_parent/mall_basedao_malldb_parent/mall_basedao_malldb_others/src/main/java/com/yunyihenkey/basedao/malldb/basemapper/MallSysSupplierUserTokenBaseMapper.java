package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysSupplierUserToken;

public interface MallSysSupplierUserTokenBaseMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(MallSysSupplierUserToken record);

    int insertSelective(MallSysSupplierUserToken record);

    MallSysSupplierUserToken selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(MallSysSupplierUserToken record);

    int updateByPrimaryKey(MallSysSupplierUserToken record);
}