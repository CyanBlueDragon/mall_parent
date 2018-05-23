package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.seller.dao.malldb.vo.result.user.ChildrenIdResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @author LiarYang
 * @date 2018/5/9 17:10
 */
@Repository
public interface AuthSellerShopMapper {
    int insertSelective(SellerShop record);

    List<ChildrenIdResult> selectParentIdByShopId(Map map);

    List<ChildrenIdResult> getCByShopId(Map map);


    List<Long> getAllShopId();
}
