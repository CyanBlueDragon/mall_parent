package com.yunyihenkey.auth.service.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.yunyihenkey.common.utils.JacksonUtils;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;

/**
 * @author : 007
 * @date : 2018/5/4 11:38
 */
public class HttpResponseUtils {

	public static void responseJson(HttpServletResponse response, ResultInfo<Object> resultInfo) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String resultJson = JacksonUtils.writeValueAsString(resultInfo);
		PrintWriter out = response.getWriter();
		out.append(resultJson);
		out.flush();
		out.close();
	}

}
