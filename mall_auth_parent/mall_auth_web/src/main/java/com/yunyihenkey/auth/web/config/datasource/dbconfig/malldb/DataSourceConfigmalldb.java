package com.yunyihenkey.auth.web.config.datasource.dbconfig.malldb;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @desc 支持多数据源配置，复制一份代码修改所有malldb即可<br>
 *       以该数据源为主数据源。只能设置@Primary到一份数据源作为主数据源
 * @author josnow
 */
@Configuration
public class DataSourceConfigmalldb {

	@Bean(name = "malldbDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.malldb")
	@Primary
	public DataSource testDataSource() {
		// return DataSourceBuilder.create().build();
		return new DruidDataSource();
	}

	@Bean(name = "malldbTransactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager(@Qualifier("malldbDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
