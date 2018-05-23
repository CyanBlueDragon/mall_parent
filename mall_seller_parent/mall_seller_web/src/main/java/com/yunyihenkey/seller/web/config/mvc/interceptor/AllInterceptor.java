package com.yunyihenkey.seller.web.config.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yunyihenkey.auth.service.enums.RequestSourceEnum;
import com.yunyihenkey.auth.service.util.HttpResponseUtils;
import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.common.constant.MallConstants;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;

/**
 * @author : 007
 * @date : 2018/5/4 10:00
 */
@Component
public class AllInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtils jwtUtils;
    public @Value("${spring.profiles.active}")
    String active;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!MallConstants.ACTIVE_PRD.equals(active)) {// 生产环境不允许跨域
            response.setHeader("Access-Control-Allow-Origin", "*");// 设置允许跨域
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        }
        if (jwtUtils.isHeaderReqSourceBlank(request)) {
            HttpResponseUtils.responseJson(response, new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM,
                    "header中没用" + MallConstants.HEADER_REQ_SOURCE));
            return false;
        }
        RequestSourceEnum reqSourceEnum = jwtUtils.getHeaderReqSource(request);
        if (reqSourceEnum == null) {
            HttpResponseUtils.responseJson(response, new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM,
                    "header中错误的" + MallConstants.HEADER_REQ_SOURCE));
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
