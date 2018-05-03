package com.yunyihenkey.seller.dao.malldb.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author LiarYang
 * @date 2018/5/3 17:07
 */
@Repository
public interface UserMapper {
    int verificationPhone(@Param("phoneNumber")String phoneNumber);
}
