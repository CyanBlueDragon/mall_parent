package com.yunyihenkey.seller.web.config.datasource.dbconfig.malldb;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.yunyihenkey.common.utils.LogUtils;

/**
 * 
 * @desc 支持多数据源配置，复制一份代码修改所有malldb即可<br>
 *       以该数据源为主数据源。只能设置@Primary到一份数据源作为主数据源
 * @author josnow
 */
@Configuration
@MapperScan(basePackages = { "com.yunyihenkey.basedao.malldb.basemapper",
		"com.yunyihenkey.seller.dao.malldb.mapper" }, sqlSessionTemplateRef = "malldbSqlSessionTemplate")
public class MybatisConfigmalldb {

	@Bean(name = "malldbSqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("malldbDataSource") DataSource dataSource)
			throws Exception {
		Logger logger = LogUtils.getLogger();
		logger.info(
				"testsadf!!!!!!testsadf!!!!!!testsadf!!!!!!testsadf!!!!!!testsadf!!!!!!testsadf!!!!!!testsadf!!!!!!testsadf!!!!!!testsadf!!!!!!");

		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(setMapperLocations());
		return bean.getObject();
	}

	private Resource[] setMapperLocations() throws IOException {
		Resource[] Resource = new PathMatchingResourcePatternResolver()
				.getResources("classpath*:mybatis/*/malldb/*.xml");
		// Resource[] Resource = { new
		// ClassPathResource("/mybatis/basemapper/malldb/TestDbMapper.xml"),
		// new ClassPathResource("/mybatis/basemapper/malldb/TestDb2Mapper.xml") };
		return Resource;
	}

	@Bean(name = "malldbSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("malldbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
