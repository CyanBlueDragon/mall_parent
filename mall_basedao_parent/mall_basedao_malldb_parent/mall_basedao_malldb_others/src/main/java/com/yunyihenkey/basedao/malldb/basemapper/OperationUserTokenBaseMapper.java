package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.OperationUserToken;

public interface OperationUserTokenBaseMapper {
    int insert(OperationUserToken record);

    int insertSelective(OperationUserToken record);
}