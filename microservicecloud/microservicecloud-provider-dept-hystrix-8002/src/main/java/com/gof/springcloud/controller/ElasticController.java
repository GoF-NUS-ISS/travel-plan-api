package com.gof.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.elasticsearch.IElasticService;
import com.gof.springcloud.model.TravelPlanModel;
import com.gof.springcloud.model.TravelPlanQueryParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("ElasticSearch API")
@RestController
@RequestMapping("/elastic")
public class ElasticController {

	@Autowired
	private IElasticService elasticService;

	@GetMapping("/createIndex")
	@ApiOperation(value = "createIndex")
	public boolean createIndex() {
		return elasticService.createIndex();
	}

	@GetMapping("/removeAllPlan")
	@ApiOperation(value = "remove all data")
	public void removeAllPlan() {
		elasticService.removeAll();
	}

	@GetMapping("/removePlanById")
	@ApiOperation(value = "remove data by id")
	public void removePlanById(String id) {
		elasticService.removeById(id);
	}

	@PostMapping("/pageByParam")
	@ApiOperation(value = "query by param in page")
	public List<TravelPlanModel> pageByParam(@RequestBody TravelPlanQueryParam param, int startPage, int pageSize) {
		return elasticService.pageByParam(param, startPage, pageSize);
	}

	@PostMapping("/countByParam")
	@ApiOperation(value = "query num by param")
	public Long countByParam(@RequestBody TravelPlanQueryParam param) {
		return elasticService.countByParam(param);
	}

}
