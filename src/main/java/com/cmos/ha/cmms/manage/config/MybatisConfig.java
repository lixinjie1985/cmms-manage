package com.cmos.ha.cmms.manage.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author lixinjie
 * @since 2017-12-15
 */
@Configuration
@MapperScan(basePackages = "org.eop.spring.mvc.mybatis.annotation.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig {

	@Autowired
	private DruidDataSource dataSource;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactory.setConfigLocation(resolver.getResource("mybatis/mybatis-config.xml"));
		sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/**/*.xml"));
		return sqlSessionFactory.getObject();
	}
}
