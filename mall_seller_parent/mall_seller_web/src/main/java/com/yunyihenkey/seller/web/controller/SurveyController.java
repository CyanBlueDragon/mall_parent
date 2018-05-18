package com.yunyihenkey.seller.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.auth.service.vo.authjwt.seller.AuthSellerUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyihenkey.basedao.malldb.basevo.SellerSurveyStatistics;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.service.SurveyService;

/**
 * @author LiarYang
 * @date 2018/5/11 10:11
 * @desc
 */
@RestController
@RequestMapping("/survey")
public class SurveyController {
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private SurveyService surveyService;
    @GetMapping("/getYesterdayData")
    public  ResultInfo getYesterdayData (HttpServletRequest request ){
        AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        Long shopId = Long.valueOf(sellerUser.getShopId());
        SellerSurveyStatistics yesterdayData = surveyService.getYesterdayData(shopId);
        return new ResultInfo<>(SystemCodeEnum.AUTH, CodeEnum.ERROR,yesterdayData);
    }

   /* public static void main(String[] args) {
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
        System.out.println(yesterday.replace("-",""));

    }*/






}
