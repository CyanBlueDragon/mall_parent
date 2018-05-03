package com.yunyihenkey.auth.web.config.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory).build();
		return redisCacheManager;
	}

	/**
	 * @Description: 防止redis入库序列化乱码的问题
	 * @return 返回类型
	 * @date 2018/4/27 10:54
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		// key序列化
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		// value序列化
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Object.class)); 
		redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer(Object.class)); 
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

}
