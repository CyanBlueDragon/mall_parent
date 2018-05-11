package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerRegistrationCode;

public interface SellerRegistrationCodeBaseMapper {
    int deleteByPrimaryKey(String registrationCode);

    int insert(SellerRegistrationCode record);

    int insertSelective(SellerRegistrationCode record);

    SellerRegistrationCode selectByPrimaryKey(String registrationCode);

    int updateByPrimaryKeySelective(SellerRegistrationCode record);

    int updateByPrimaryKey(SellerRegistrationCode record);
}