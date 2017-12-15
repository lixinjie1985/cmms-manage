package com.cmos.ha.cmms.manage.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author lixinjie
 * @since 2017-12-15
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.eop.spring.mvc.mybatis.annotation.web.controller", "org.eop.spring.mvc.mybatis.annotation.web.exception.handler"})
public class WebConfig extends WebMvcConfigurerAdapter {

}
