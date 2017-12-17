package com.cmos.ha.cmms.manage.config.datasource;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author lixinjie
 * @since 2017-12-15
 */
@Profile("dev")
@Configuration
@PropertySource("classpath:db/dev/db.properties")
public class DataSourceConfigDev {
	
	@Value("${jdbc.driver}")
	private String driver;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Value("${initialSize}")
	private int initialSize;
	
	@Value("${maxActive}")
	private int maxActive;
	
	@Value("${minIdle}")
	private int minIdle;
	
	@Value("${maxWait}")
	private int maxWait;
	
	@Value("${validationQuery}")
	private String validationQuery;
	
	@Value("${validationQueryTimeout}")
	private int validationQueryTimeout;
	
	@Value("${testOnBorrow}")
	private boolean testOnBorrow;
	
	@Value("${testOnReturn}")
	private boolean testOnReturn;
	
	@Value("${testWhileIdle}")
	private boolean testWhileIdle;
	
	@Value("${poolPreparedStatements}")
	private boolean poolPreparedStatements;
	
	@Value("${maxPoolPreparedStatementPerConnectionSize}")
	private int maxPoolPreparedStatementPerConnectionSize;
	
	@Value("${keepAlive}")
	private boolean keepAlive;
	
	@Value("${timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;
	
	@Value("${minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;
	
	@Value("${filters}")
	private String filters;
	
	@Value("${connectionProperties}")
	private String connectionProperties;
	
	@Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource dataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxActive(maxActive);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxWait(maxWait);
		dataSource.setValidationQuery(validationQuery);
		dataSource.setValidationQueryTimeout(validationQueryTimeout);
		dataSource.setTestOnBorrow(testOnBorrow);
		dataSource.setTestOnReturn(testOnReturn);
		dataSource.setTestWhileIdle(testWhileIdle);
		dataSource.setPoolPreparedStatements(poolPreparedStatements);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		dataSource.setKeepAlive(keepAlive);
		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		dataSource.setFilters(filters);
		dataSource.setConnectionProperties(connectionProperties);
		return dataSource;
	}
	
}
