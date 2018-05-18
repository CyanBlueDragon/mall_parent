package com.yunyihenkey.seller.service;

import com.yunyihenkey.basedao.malldb.basevo.SellerSurveyStatistics;

/**
 * @author LiarYang
 * @date 2018/5/11 11:43
 */
public interface SurveyService {

    SellerSurveyStatistics getYesterdayData(Long shopId);

    /**
     * 每日凌晨0:00执行添加昨日数据
     */
    void SaveSurveyInformation();
}
