package com.gof.springcloud.service;

import java.util.List;

import com.gof.springcloud.model.TravelPlanModel;

public interface TravelPlanService {

	public TravelPlanModel addPlan(TravelPlanModel travelPlanModel);

	public TravelPlanModel getById(String id);

	public List<TravelPlanModel> getByName(String name);

}
