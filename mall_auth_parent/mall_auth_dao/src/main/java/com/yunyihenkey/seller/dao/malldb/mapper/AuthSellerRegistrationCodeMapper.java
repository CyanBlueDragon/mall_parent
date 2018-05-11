package com.yunyihenkey.seller.dao.malldb.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LiarYang
 * @date 2018/5/9 11:26
 */
public interface AuthSellerRegistrationCodeMapper {
    void insertBatch(@Param("list")List code);
}
