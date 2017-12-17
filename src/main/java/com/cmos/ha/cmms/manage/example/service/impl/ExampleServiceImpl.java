package com.cmos.ha.cmms.manage.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmos.ha.cmms.manage.example.bean.Example;
import com.cmos.ha.cmms.manage.example.mapper.ExampleMapper;
import com.cmos.ha.cmms.manage.example.service.IExampleService;

/**
 * @author lixinjie
 * @since 2017-12-16
 */
@Service
public class ExampleServiceImpl implements IExampleService {

	@Autowired
	private ExampleMapper exampleMapper;
	
	@Transactional
	@Override
	public int insertExample(Example example) {
		return exampleMapper.insertExample(example);
	}

	@Override
	public List<Example> selectExamples(int pageNum, int pageSize) {
		return exampleMapper.selectExamples(pageNum, pageSize);
	}

}
