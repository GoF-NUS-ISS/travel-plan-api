package com.gof.springcloud.service.impl;

import java.util.List;

import com.gof.springcloud.model.TravelPlanModel;
import com.gof.springcloud.repository.TravelPlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gof.springcloud.entities.Dept;
import com.gof.springcloud.dao.TravelPlanDao;
import com.gof.springcloud.service.TravelPlanService;

@Slf4j
@Service
public class TravelPlanServiceImpl implements TravelPlanService
{
	private final TravelPlanRepository travelPlanRepository;

	public TravelPlanServiceImpl(TravelPlanRepository travelPlanRepository){
		this.travelPlanRepository = travelPlanRepository;
	}
	/*@Autowired
	private TravelPlanDao dao;

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
	}*/

	@Override
	public void addPlan(TravelPlanModel travelPlanModel) {
		log.info("model:{}", travelPlanModel);
		travelPlanRepository.save(travelPlanModel);
	}

	@Override
	public List<TravelPlanModel> getAll() {
		return travelPlanRepository.findAll();
	}
}
