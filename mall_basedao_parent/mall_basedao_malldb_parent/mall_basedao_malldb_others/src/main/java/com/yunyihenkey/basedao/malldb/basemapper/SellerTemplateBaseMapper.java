package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerTemplate;

public interface SellerTemplateBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerTemplate record);

    int insertSelective(SellerTemplate record);

    SellerTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerTemplate record);

    int updateByPrimaryKey(SellerTemplate record);
}