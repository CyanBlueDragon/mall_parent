package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.auth.service.enums.Increment;
import com.yunyihenkey.basedao.malldb.basemapper.SellerSurveyStatisticsBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerSurveyStatistics;
import com.yunyihenkey.common.constant.RedisConstant;
import com.yunyihenkey.common.idworker.IdWorker;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerShopMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerSurveyStatisticsMapper;
import com.yunyihenkey.seller.service.SurveyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author LiarYang
 * @date 2018/5/11 11:44
 */
@Service
public class SurveyServiceImpl implements SurveyService {
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private IdWorker idWorker;

    @Resource
    private AuthSellerShopMapper authSellerShopMapper;

    @Resource
    private SellerSurveyStatisticsBaseMapper sellerSurveyStatisticsBaseMapper;

    @Resource
    private AuthSellerSurveyStatisticsMapper authSellerSurveyStatisticsMapper;

    @Override
    public SellerSurveyStatistics getYesterdayData( Long shopId) {
       // return sellerSurveyStatisticsBaseMapper.selectByPrimaryKey(getYesterdayDate());
        return authSellerSurveyStatisticsMapper.selectByShopId(getYesterdayDate(),shopId);
    }

    @Override
    public void SaveSurveyInformation() {

        List<Long> allShopId = authSellerShopMapper.getAllShopId();
        if (allShopId.size() != 0) {
            List<SellerSurveyStatistics> list = new ArrayList<>();
            for (Long anAllShopId : allShopId) {
                SellerSurveyStatistics sellerSurveyStatistics = new SellerSurveyStatistics();
                sellerSurveyStatistics.setId(idWorker.nextId());
                sellerSurveyStatistics.setShopId(anAllShopId);
                sellerSurveyStatistics.setDateOfTheDay(getYesterdayDate());
                sellerSurveyStatistics.setCountNewMembers((Integer) redisUtil.get(RedisConstant.getYesterdayCacheKeyByShopId(anAllShopId) + Increment.CountNewMembers.getText()));
                sellerSurveyStatistics.setCountOrderToday((Integer) redisUtil.get(RedisConstant.getYesterdayCacheKeyByShopId(anAllShopId) + Increment.CountOrderYesterday.getText()));
                sellerSurveyStatistics.setCountRefundAfterSale((Integer) redisUtil.get(RedisConstant.getYesterdayCacheKeyByShopId(anAllShopId) + Increment.CountRefundAfterSale.getText()));
                sellerSurveyStatistics.setOrderAmountOfToday((Long) redisUtil.get(RedisConstant.getYesterdayCacheKeyByShopId(anAllShopId) + Increment.OrderAmountOfYesterday.getText()));
                sellerSurveyStatistics.setTodaysEarnings((Long) redisUtil.get(RedisConstant.getYesterdayCacheKeyByShopId(anAllShopId) + Increment.YesterdaysEarnings.getText()));
                list.add(sellerSurveyStatistics);
            }

            authSellerSurveyStatisticsMapper.insertBatch(list);

            for (Long anAllShopId : allShopId) {
                redisUtil.set(RedisConstant.getYesterdayCacheKeyByShopId(anAllShopId) + Increment.CountNewMembers.getText(), 0);
                redisUtil.set(RedisConstant.getYesterdayCacheKeyByShopId(anAllShopId) + Increment.CountOrderYesterday.getText(), 0);
                redisUtil.set(RedisConstant.getYesterdayCacheKeyByShopId(anAllShopId) + Increment.CountRefundAfterSale.getText(), 0);
                redisUtil.set(RedisConstant.getYesterdayCacheKeyByShopId(anAllShopId) + Increment.OrderAmountOfYesterday.getText(), 0);
                redisUtil.set(RedisConstant.getYesterdayCacheKeyByShopId(anAllShopId) + Increment.YesterdaysEarnings.getText(), 0);
            }
        }


    }

    private String getYesterdayDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        return new SimpleDateFormat( "yyyyMMdd ").format(cal.getTime());
    }

}
