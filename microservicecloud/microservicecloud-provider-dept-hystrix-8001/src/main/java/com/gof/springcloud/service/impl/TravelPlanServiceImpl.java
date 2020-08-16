package com.gof.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gof.springcloud.model.TravelPlanModel;
import com.gof.springcloud.repository.TravelPlanRepository;
import com.gof.springcloud.service.TravelPlanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TravelPlanServiceImpl implements TravelPlanService
{
	@Autowired
	private TravelPlanRepository travelPlanRepository;

	@Override
	public TravelPlanModel addPlan(TravelPlanModel travelPlanModel) {
		return travelPlanRepository.save(travelPlanModel);
	}

	@Override
	public List<TravelPlanModel> getAll() {
		return travelPlanRepository.findAll();
	}

	@Override
	public List<TravelPlanModel> getByName(String name) {
		return travelPlanRepository.findByName(name);
	}
}
