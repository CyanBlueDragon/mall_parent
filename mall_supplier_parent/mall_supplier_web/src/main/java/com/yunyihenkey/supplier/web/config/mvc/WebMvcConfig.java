package com.yunyihenkey.supplier.web.config.mvc;


import com.yunyihenkey.supplier.web.config.mvc.interceptor.AllInterceptor;
//import com.yunyihenkey.supplier.web.config.mvc.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author : 007
 * @date : 2018/5/4 10:03 注册mvc
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//	@Autowired
//	private LoginInterceptor loginInterceptor;
	@Autowired
	private AllInterceptor allInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(loginInterceptor).addPathPatterns("/**");// 添加拦击器，拦截全部
		registry.addInterceptor(allInterceptor).addPathPatterns("/**");// 添加拦击器，拦截全部
	}

	/**
	 * 消息转换
	 *
	 * @param converters
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 默认转换器注册后, 插入自定义的请求响应转换器
		// converters.add( new MappingJackson2HttpMessageConverter(new ObjectMapper()));

	}
}
