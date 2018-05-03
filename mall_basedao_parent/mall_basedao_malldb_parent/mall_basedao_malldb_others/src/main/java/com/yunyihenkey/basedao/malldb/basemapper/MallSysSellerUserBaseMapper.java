package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.MallSysSellerUser;
import org.springframework.stereotype.Repository;

@Repository
public interface MallSysSellerUserBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallSysSellerUser record);

    int insertSelective(MallSysSellerUser record);

    MallSysSellerUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSysSellerUser record);

    int updateByPrimaryKey(MallSysSellerUser record);

    MallSysSellerUser selectNameByUser(String userName);
}