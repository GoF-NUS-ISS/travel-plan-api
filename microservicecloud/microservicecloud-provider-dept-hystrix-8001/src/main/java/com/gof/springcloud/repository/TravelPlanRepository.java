package com.gof.springcloud.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gof.springcloud.model.TravelPlanModel;

@Repository
public interface TravelPlanRepository extends MongoRepository<TravelPlanModel, String> {

	@Query("{'name':?0}")
	List<TravelPlanModel> findByName(String name);

}
