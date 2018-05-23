package com.yunyihenkey.seller.dao.malldb.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author LiarYang
 * @date 2018/5/18 11:40
 * @desc
 */
@Repository
public interface AuthSellerShopMembersMapper {

    List selectByShopId(@Param("shopId") Long shopId);


    List getMembers(Map map);

    void updateBatch(@Param("list") List item);
}
