package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SupplierUserToken;

public interface SupplierUserTokenBaseMapper {
    int insert(SupplierUserToken record);

    int insertSelective(SupplierUserToken record);
}