package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysSellerRole;

public interface MallSysSellerRoleBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysSellerRole record);

    int insertSelective(MallSysSellerRole record);

    MallSysSellerRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysSellerRole record);

    int updateByPrimaryKey(MallSysSellerRole record);
}