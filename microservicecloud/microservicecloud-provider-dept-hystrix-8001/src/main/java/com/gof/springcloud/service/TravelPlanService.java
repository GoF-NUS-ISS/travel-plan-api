package com.gof.springcloud.service;

import java.util.List;

import com.gof.springcloud.model.TravelPlanModel;

public interface TravelPlanService {

	public TravelPlanModel addPlan(TravelPlanModel travelPlanModel);

	public List<TravelPlanModel> getAll();

	public List<TravelPlanModel> getByName(String name);
}
