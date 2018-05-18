package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShopMembers;

public interface SellerShopMembersBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerShopMembers record);

    int insertSelective(SellerShopMembers record);

    SellerShopMembers selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerShopMembers record);

    int updateByPrimaryKey(SellerShopMembers record);
}