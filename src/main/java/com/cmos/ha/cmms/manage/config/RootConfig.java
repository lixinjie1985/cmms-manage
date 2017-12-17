package com.cmos.ha.cmms.manage.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cmos.ha.cmms.manage.config.datasource.DataSourceConfigDev;
import com.cmos.ha.cmms.manage.config.datasource.DataSourceConfigProd;
import com.cmos.ha.cmms.manage.config.datasource.DataSourceConfigTest;
import com.cmos.ha.cmms.manage.config.mybatis.MybatisConfig;
import com.cmos.ha.cmms.manage.config.transaction.TransactionManagerConfig;

/**
 * @author lixinjie
 * @since 2017-12-15
 */
@Configuration
@Import({DataSourceConfigDev.class, DataSourceConfigTest.class, DataSourceConfigProd.class, TransactionManagerConfig.class, MybatisConfig.class})
@ComponentScan(basePackages = {"com.cmos.ha.cmms.manage.example.service"}, excludeFilters = @Filter(value = EnableWebMvc.class))
public class RootConfig {

}
