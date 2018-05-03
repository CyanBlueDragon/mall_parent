package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysSupplierUser;

public interface MallSysSupplierUserBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysSupplierUser record);

    int insertSelective(MallSysSupplierUser record);

    MallSysSupplierUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysSupplierUser record);

    int updateByPrimaryKey(MallSysSupplierUser record);
}