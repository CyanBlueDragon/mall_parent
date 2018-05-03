package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysSellerUserToken;

public interface MallSysSellerUserTokenBaseMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(MallSysSellerUserToken record);

    int insertSelective(MallSysSellerUserToken record);

    MallSysSellerUserToken selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(MallSysSellerUserToken record);

    int updateByPrimaryKey(MallSysSellerUserToken record);
}