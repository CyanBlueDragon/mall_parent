package com.yunyihenkey.seller.web.config.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yunyihenkey.common.utils.JacksonUtils;
import com.yunyihenkey.common.utils.XmlUtils;
import com.yunyihenkey.seller.web.config.mvc.interceptor.AllInterceptor;
import com.yunyihenkey.seller.web.config.mvc.interceptor.LoginInterceptor;

/**
 * @author : 007
 * @date : 2018/5/4 10:03 注册mvc
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;
	@Autowired
	private AllInterceptor allInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**");// 添加拦击器，拦截全部
		registry.addInterceptor(allInterceptor).addPathPatterns("/**");// 添加拦击器，拦截全部
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		if (converters != null && !converters.isEmpty()) {
			for (HttpMessageConverter<?> httpMessageConverter : converters) {
				if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
					MappingJackson2HttpMessageConverter Jackson2 = (MappingJackson2HttpMessageConverter) httpMessageConverter;
					Jackson2.setObjectMapper(JacksonUtils.objectMapper);
				}
				if (httpMessageConverter instanceof MappingJackson2XmlHttpMessageConverter) {
					MappingJackson2XmlHttpMessageConverter Jackson2Xml = (MappingJackson2XmlHttpMessageConverter) httpMessageConverter;
					Jackson2Xml.setObjectMapper(XmlUtils.xmlMapper);
				}

			}
		}
		WebMvcConfigurer.super.configureMessageConverters(converters);
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
