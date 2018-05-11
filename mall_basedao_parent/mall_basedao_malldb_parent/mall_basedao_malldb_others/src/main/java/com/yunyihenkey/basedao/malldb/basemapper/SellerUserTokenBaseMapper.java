package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerUserToken;

public interface SellerUserTokenBaseMapper {
    int insert(SellerUserToken record);

    int insertSelective(SellerUserToken record);
}