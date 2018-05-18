package com.yunyihenkey.basedao.malldb.basemapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerSurveyStatistics;

public interface SellerSurveyStatisticsBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerSurveyStatistics record);

    int insertSelective(SellerSurveyStatistics record);

    SellerSurveyStatistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerSurveyStatistics record);

    int updateByPrimaryKey(SellerSurveyStatistics record);
}