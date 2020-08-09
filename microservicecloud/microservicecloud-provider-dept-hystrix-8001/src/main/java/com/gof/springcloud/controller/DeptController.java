package com.gof.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.entities.Dept;
import com.gof.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController
{
	@Autowired
	private DeptService service = null;

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
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
	}
}