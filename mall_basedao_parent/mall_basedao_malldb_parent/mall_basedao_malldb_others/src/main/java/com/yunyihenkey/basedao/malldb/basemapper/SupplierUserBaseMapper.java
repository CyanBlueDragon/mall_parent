package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SupplierUser;

public interface SupplierUserBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SupplierUser record);

    int insertSelective(SupplierUser record);

    SupplierUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SupplierUser record);

    int updateByPrimaryKey(SupplierUser record);
}