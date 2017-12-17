package com.cmos.ha.cmms.manage.example.service;

import java.util.List;

import com.cmos.ha.cmms.manage.example.bean.Example;

/**
 * @author lixinjie
 * @since 2017-12-16
 */
public interface IExampleService {

int insertExample(Example example);
	
	List<Example> selectExamples(int pageNum, int pageSize);
	
}
