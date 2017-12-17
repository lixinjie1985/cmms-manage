package com.cmos.ha.cmms.manage.example.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.cmos.ha.cmms.manage.example.bean.Example;
import com.cmos.ha.cmms.manage.example.service.IExampleService;
import com.cmos.ha.cmms.manage.result.RestResult;
import com.cmos.ha.cmms.manage.utils.BindingResultUtils;
import com.cmos.ha.cmms.manage.utils.ErrorCode;
import com.github.pagehelper.PageInfo;

/**
 * @author lixinjie
 * @since 2017-12-16
 */
@RequestMapping(path = "/example")
@RestController
public class ExampleController {

	@Autowired
	private IExampleService exampleService;
	
	@PostMapping("/save")
	public RestResult saveExample(@Valid @RequestBody Example example, BindingResult bindingResult) {
		BindingResultUtils.checkBindingResult(bindingResult);
		exampleService.insertExample(example);
		return new RestResult(ErrorCode.Success.getCode(), ErrorCode.Success.getDesc());
	}
	
	@GetMapping("/page/{pageNum:\\d+}/{pageSize:\\d+}")
	public RestResult listExample(@PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize) {
		List<Example> examples = exampleService.selectExamples(pageNum, pageSize);
		PageInfo<Example> pageInfo = new PageInfo<>(examples);
		return new RestResult(ErrorCode.Success.getCode(), ErrorCode.Success.getDesc(), pageInfo);
	}
	
	@PostMapping("/upload")
	public RestResult uploadFile(@RequestPart("username") String name, @RequestPart("file") Part file) {
		Map<String, Object> map = new HashMap<>();
		map.put("username", name);
		map.put("name", file.getName());
		map.put("size", file.getSize());
		map.put("contentType", file.getContentType());
		return new RestResult(ErrorCode.Success.getCode(), ErrorCode.Success.getDesc(), map);
	}
	
	@GetMapping("/download")
	public ResponseEntity<byte[]> downloadFile() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setContentDispositionFormData("attachment", new String("上海陆鹰工作量统计.xlsx".getBytes("UTF-8"), "ISO-8859-1"));
		FileInputStream fis = new FileInputStream("F:\\2345下载\\上海陆鹰工作量统计.xlsx");
		byte[] buff = new byte[fis.available()];
		fis.read(buff, 0, buff.length);
		fis.close();
		return new ResponseEntity<byte[]>(buff, headers, HttpStatus.OK);
	}
	
}
