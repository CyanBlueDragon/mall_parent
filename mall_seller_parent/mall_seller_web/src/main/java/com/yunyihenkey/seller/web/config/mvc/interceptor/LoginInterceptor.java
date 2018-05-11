package com.yunyihenkey.seller.web.config.mvc.interceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yunyihenkey.auth.service.AuthJwtService;
import com.yunyihenkey.auth.service.util.HttpResponseUtils;
import com.yunyihenkey.common.constant.JwtConstants;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;

/**
 * @author : 007
 * @date : 2018/5/4 10:00
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private AuthJwtService authJwtService;

	private @Value("${server.servlet.context-path}") String contextPath;
	private @Value("${seller.mvc.anonymousUrl}") String anonymousUrl;

	private static String[] anonymousUrls;

	@PostConstruct
	private void init() {
		// 首位追加/，有/则跳过
		if (contextPath.indexOf("/") != 0) {
			contextPath = "/" + contextPath;
		}
		// 结尾去除/，无/则跳过
		if (contextPath.lastIndexOf("/") == contextPath.length() - 1) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}

		anonymousUrls = anonymousUrl.split(";");
		if (anonymousUrls != null || anonymousUrls.length != 0) {

			for (int i = 0; i < anonymousUrls.length; i++) {
				String str = anonymousUrls[i];
				// 白名单url首位追加/，有/则跳过
				if (str.indexOf("/") != 0) {
					str = "/" + str;
				}

				// 白名单url结尾追加/，有/则跳过
				if (str.lastIndexOf("/") != str.length() - 1) {
					str = str + "/";
				}
				anonymousUrls[i] = contextPath + str;
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String reqUrl = request.getRequestURI();
		// url结尾追加/，有/则跳过
		if (reqUrl.lastIndexOf("/") != reqUrl.length() - 1) {
			reqUrl += "/";
		}

		// LogUtils.getLogger().info("当前请求url：：：" + reqUrl);

		boolean isInterceptUrl = true;
		for (String str : anonymousUrls) {
			if ((reqUrl).indexOf(str) == 0) {
				isInterceptUrl = false;
				break;
			}
		}
		// 对白名单url，放行
		if (!isInterceptUrl) {
			return true;
		}

		ResultInfo<Object> resultInfo = authJwtService.validateToken(request, SystemCodeEnum.SELLER);
		if (ResultInfo.isFailed(resultInfo)) {
			HttpResponseUtils.responseJson(response, resultInfo);
			return false;
		}
		return true;
	}
}
