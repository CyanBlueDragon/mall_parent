//package com.yunyihenkey.common.utils.excel;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//
////必须继承HttpServlet类
//public class WebHelper {
//	/**
//	 * 获得网站目录的绝对路径
//	 *
//	 * @param request
//	 * @return
//	 */
//	public static String getRealPath(HttpServletRequest request) {
//		// String path = request.getServletContext().getRealPath("/");
//		String path = request.getSession().getServletContext().getRealPath("/");
//		return (StringUtils.isEmpty(path)) ? "/" : path;
//	}
//
//	/**
//	 * 返回URl的域名
//	 *
//	 * @param req
//	 * @return
//	 */
//	public static String getDomain(HttpServletRequest request) {
//		return request.getServerName();
//	}
//
//	/**
//	 * 返回URl的域名
//	 *
//	 * @param req
//	 * @return
//	 */
//	public static String getDomain(String url) {
//		if (StringUtils.isNotEmpty(url) && (url.startsWith("https://") || url.startsWith("http://"))) {
//			String domain = url.substring(url.indexOf("://") + 3);
//			int pos = domain.indexOf("/");
//			if (pos == -1) {
//				pos = domain.indexOf("?");
//			}
//			if (pos > -1) {
//				domain = domain.substring(0, pos);
//			}
//			return domain;
//		} else {
//			return "";
//		}
//	}
//
//	/**
//	 * 返回URl的域名
//	 *
//	 * @param req
//	 * @return
//	 */
//	public static String getDomainNew(String curl) {
//		URL url = null;
//		String q = "";
//		try {
//			url = new URL(curl);
//			q = url.getHost();
//		} catch (MalformedURLException e) {
//		}
//		return q;
//	}
//
//	/**
//	 * 获取user-agent
//	 *
//	 * @param req 请求对象
//	 * @return
//	 */
//	public static String getUserAgent(HttpServletRequest req) {
//		return req.getHeader("User-Agent");
//	}
//
//	public static String getHttpsUrl(HttpServletRequest req, boolean ssl) {
//		String url = req.getRequestURL().toString();
//		return getHttpsUrl(url, ssl);
//	}
//
//	public static String getHttpsUrl(String url, boolean ssl) {
//		if (StringUtils.isNotEmpty(url) && url.startsWith("http")) {
//			int pos = url.indexOf("://");
//			String protocol = url.substring(0, pos);
//			String au = url.substring(pos);
//			if (ssl) {
//				protocol = "https";
//			} else {
//				protocol = "http";
//			}
//			return protocol + au;
//		} else {
//			return url;
//		}
//	}
//}
