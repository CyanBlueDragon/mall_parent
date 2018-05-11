package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerUser;

public interface SellerUserBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerUser record);

    int insertSelective(SellerUser record);

    SellerUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerUser record);

    int updateByPrimaryKey(SellerUser record);
}