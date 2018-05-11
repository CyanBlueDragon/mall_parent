package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerSurveyStatistics;

public interface SellerSurveyStatisticsBaseMapper {
    int deleteByPrimaryKey(String dateOfTheDay);

    int insert(SellerSurveyStatistics record);

    int insertSelective(SellerSurveyStatistics record);

    SellerSurveyStatistics selectByPrimaryKey(String dateOfTheDay);

    int updateByPrimaryKeySelective(SellerSurveyStatistics record);

    int updateByPrimaryKey(SellerSurveyStatistics record);
}