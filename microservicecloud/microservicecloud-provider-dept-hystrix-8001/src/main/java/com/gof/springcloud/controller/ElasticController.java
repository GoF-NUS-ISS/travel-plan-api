package com.gof.springcloud.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.elasticsearch.DocBean;
import com.gof.springcloud.elasticsearch.IElasticService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("ElasticSearch API")
@RestController
@RequestMapping("/elastic")
public class ElasticController {

	@Autowired
	private IElasticService elasticService;

	@GetMapping("/init")
	@ApiOperation(value = "init data in elasticsearch")
	public void init() {
		List<DocBean> list = new ArrayList<>();
		list.add(new DocBean(1L, "XX0193", "XX8064", "For microservicecloud-provider-dept-hystrix-8001 project", 1));
		list.add(new DocBean(2L, "XX0210", "XX7475", "Create 3 MySQL databases on your local machine", 2));
		list.add(new DocBean(3L, "XX0257", "XX8097", "Description for different modules", 3));
		elasticService.saveAll(list);
	}

	@GetMapping("/all")
	@ApiOperation(value = "query all data from elasticsearch")
	public Iterator<DocBean> all() {
		return elasticService.findAll();
	}

	@GetMapping("/findByFirstCode")
	@ApiOperation(value = "query by FirstCode")
	public Page<DocBean> findByFirstCode(String firstCode) {
		return elasticService.findByFirstCode(firstCode);
	}

	@GetMapping("/findByContent")
	@ApiOperation(value = "query by content")
	public Page<DocBean> findByContent(String content) {
		return elasticService.findByContent(content);
	}

	@GetMapping("/findByKey")
	@ApiOperation(value = "query by key")
	public Optional<DocBean> findByKey(Long key) {
		return elasticService.query(key);
	}
}
