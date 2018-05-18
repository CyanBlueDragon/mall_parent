package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import org.springframework.stereotype.Repository;



/**
 * @author LiarYang
 * @date 2018/5/9 17:10
 */
@Repository
public interface AuthSellerShopMapper {
    int insertSelective(SellerShop record);


}
