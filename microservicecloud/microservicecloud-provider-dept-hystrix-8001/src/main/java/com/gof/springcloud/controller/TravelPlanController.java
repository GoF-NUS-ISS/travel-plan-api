package com.gof.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	@Autowired
	private TravelPlanService service;

	@PostMapping("/travelPlan")
	@ApiOperation(value = "Add a travelPlan", notes = "Add a travelPlan")
	public AjaxResponse addPlan(@RequestBody TravelPlanModel travelPlan){
		return AjaxResponse.success(service.addPlan(travelPlan));
	}

	@GetMapping("/travelPlan/{name}")
	@ApiOperation(value = "Get travelPlan by name", notes = "Get travelPlan by name")
	@Cacheable(value = "travelPlanByName", key = "#name", unless = "#result == null || #result.size() == 0")
	public List<TravelPlanModel> getByName(@RequestParam("name") String name){
		return service.getByName(name);
	}

}