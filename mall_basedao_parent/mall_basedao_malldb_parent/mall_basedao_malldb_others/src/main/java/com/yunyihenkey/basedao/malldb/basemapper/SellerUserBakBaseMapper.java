package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerUserBak;

public interface SellerUserBakBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerUserBak record);

    int insertSelective(SellerUserBak record);

    SellerUserBak selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerUserBak record);

    int updateByPrimaryKey(SellerUserBak record);
}