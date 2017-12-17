package com.cmos.ha.cmms.manage.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmos.ha.cmms.manage.example.bean.Example;

/**
 * @author lixinjie
 * @since 2017-12-16
 */
public interface ExampleMapper {

	int insertExample(Example example);
	
	List<Example> selectExamples(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
}
