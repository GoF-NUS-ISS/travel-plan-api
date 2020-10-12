package com.gof.springcloud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.entities.interaction.AjaxResponse;
import com.gof.springcloud.model.TravelPlanModel;
import com.gof.springcloud.service.TravelPlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Travel Plan API")
public class TravelPlanController
{
	private Logger log = LoggerFactory.getLogger(TravelPlanController.class);

	@Autowired
	private TravelPlanService service;

	@PostMapping("/travelPlan")
	@ApiOperation(value = "Add a travelPlan", notes = "Add a travelPlan")
	public AjaxResponse addPlan(@RequestBody TravelPlanModel travelPlan){
		return AjaxResponse.success(service.addPlan(travelPlan));
	}

	@GetMapping("/travelPlan/id/{id}")
	@ApiOperation(value = "Get travelPlan by id", notes = "Get travelPlan by id")
	public TravelPlanModel getById(@PathVariable String id){
		return service.getById(id);
	}

	@GetMapping("/travelPlan/name/{name}")
	@ApiOperation(value = "Get travelPlan by name", notes = "Get travelPlan by name")
	//@ApiImplicitParam(paramType = "name", required = true, defaultValue = "tim")
	@Cacheable(value = "travelPlanByName", key = "#name", unless = "#result == null || #result.size() == 0")
	public List<TravelPlanModel> getByName(@PathVariable String name){
		return service.getByName(name);
	}


}