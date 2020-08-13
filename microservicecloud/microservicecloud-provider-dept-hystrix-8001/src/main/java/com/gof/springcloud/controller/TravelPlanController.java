package com.gof.springcloud.controller;

import com.gof.springcloud.model.TravelPlanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gof.springcloud.entities.Dept;
import com.gof.springcloud.service.TravelPlanService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.List;

@RestController
public class TravelPlanController
{
	@Autowired
	private final TravelPlanService service = null;

	/*@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	// once find failure of invoking and cast of exception,
	// will call specific method within fallbackMethod automatically,
	// which is labeled by HystrixCommand
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id)
	{
		System.out.println("111");
		Dept dept = this.service.get(id);
		if (null == dept) {
			throw new RuntimeException("ID-" + id + ":there is no responding result");
		}
		return dept;
	}

	public Dept processHystrix_Get(@PathVariable("id") Long id)
	{
		System.out.println("222");
		Dept result = new Dept();
		result.setDname("Provider shutdown, there is a limited service");
		result.setDeptno(id);
		result.setDb_source("no this database in MySQL");
		return result;
	}*/

	@PostMapping("/travelPlan")
	public ResponseEntity addPlan(){
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/getPlans")
	public List<TravelPlanModel> getAll(){
		return service.getAll();
	}

}