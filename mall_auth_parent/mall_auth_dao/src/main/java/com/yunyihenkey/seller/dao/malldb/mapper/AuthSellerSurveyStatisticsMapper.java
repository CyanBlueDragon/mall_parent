package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerSurveyStatistics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiarYang
 * @date 2018/5/16 17:59
 * @desc
 */
@Repository
public interface AuthSellerSurveyStatisticsMapper {
    SellerSurveyStatistics selectByShopId(@Param("yesterdayDate")String yesterdayDate,@Param("shopId")Long shopId);

    List<Long> getAllShopId();

    void insertBatch(@Param("list")List list);

}
