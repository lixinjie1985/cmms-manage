package com.cmos.ha.cmms.manage.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author lixinjie
 * @since 2017-12-15
 */
@Configuration
@Import({DataSourceConfig.class, TransactionManagerConfig.class, MybatisConfig.class})
@ComponentScan(basePackages = {"org.eop.spring.mvc.mybatis.annotation.service"}, excludeFilters = @Filter(value = EnableWebMvc.class))
@EnableTransactionManagement
public class RootConfig {

}
