package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerPerm;

public interface SellerPermBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerPerm record);

    int insertSelective(SellerPerm record);

    SellerPerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerPerm record);

    int updateByPrimaryKey(SellerPerm record);
}