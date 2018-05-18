package com.yunyihenkey.supplier.web.config.mvc.interceptor;

import com.yunyihenkey.common.constant.MallConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : 007
 * @date : 2018/5/4 10:00
 */
@Component
public class AllInterceptor implements HandlerInterceptor {

	public @Value("${spring.profiles.active}") String active;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!MallConstants.ACTIVE_PRD.equals(active)) {// 生产环境不允许跨域
			response.setHeader("Access-Control-Allow-Origin", "*");//设置允许跨域
			response.setHeader("Access-Control-Allow-Headers", "*");
//			response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
