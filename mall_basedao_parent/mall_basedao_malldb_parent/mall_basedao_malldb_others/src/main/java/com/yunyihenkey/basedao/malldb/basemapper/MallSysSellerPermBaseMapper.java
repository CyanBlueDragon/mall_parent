package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysSellerPerm;

public interface MallSysSellerPermBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysSellerPerm record);

    int insertSelective(MallSysSellerPerm record);

    MallSysSellerPerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysSellerPerm record);

    int updateByPrimaryKey(MallSysSellerPerm record);
}