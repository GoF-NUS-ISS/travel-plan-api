package com.gof.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gof.springcloud.entities.Dept;
import com.gof.springcloud.dao.DeptDao;
import com.gof.springcloud.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService
{
	@Autowired
	private DeptDao dao;

	@Override
	public boolean add(Dept dept) {
		return dao.addDept(dept);
	}

	@Override
	public Dept get(Long id) {
		System.out.println(id);
		Dept d1 = dao.findById(id);
		System.out.println(d1.toString());
		return d1;
	}

	@Override
	public List<Dept> list() {
		List<Dept> result = dao.findAll();
		if (null == result || result.size() == 0) {
			System.out.println("null");
		} else {
			System.out.println(result.size());
		}
		return result;
	}

}
