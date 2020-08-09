package com.gof.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gof.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
	@Override
	public DeptClientService create(Throwable throwable) {
		return new DeptClientService() {
			@Override
			public Dept get(long id) {
				Dept result = new Dept();
				result.setDname("Provider shutdown, this is a limited service");
				result.setDb_source("No this database in MySQL");
				result.setDeptno(id);
				return result;
			}

			@Override
			public List<Dept> list() {
				return null;
			}

			@Override
			public boolean add(Dept dept) {
				return false;
			}
		};
	}
}
