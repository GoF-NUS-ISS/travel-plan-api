package com.gof.springcloud.elasticsearch;

import java.util.List;

import com.gof.springcloud.model.TravelPlanModel;
import com.gof.springcloud.model.TravelPlanQueryParam;

public interface IElasticService {

	public boolean createIndex();

	public void save(TravelPlanModel plan);

	public void removeById(String id);

	public void removeAll();

	public List<TravelPlanModel> pageByParam(TravelPlanQueryParam param, int startPage, int pageSize);

	public Long countByParam(TravelPlanQueryParam param);

}
