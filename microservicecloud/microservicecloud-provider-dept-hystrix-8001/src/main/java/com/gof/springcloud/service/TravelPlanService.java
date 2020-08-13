package com.gof.springcloud.service;

import java.util.List;

import com.gof.springcloud.entities.Dept;
import com.gof.springcloud.model.TravelPlanModel;
import com.gof.springcloud.repository.TravelPlanRepository;

public interface TravelPlanService
{
	//public boolean add(Dept dept);

	//public Dept get(Long id);

	//public List<Dept> list();

	public void addPlan(TravelPlanModel travelPlanModel);

	public List<TravelPlanModel> getAll();
}
