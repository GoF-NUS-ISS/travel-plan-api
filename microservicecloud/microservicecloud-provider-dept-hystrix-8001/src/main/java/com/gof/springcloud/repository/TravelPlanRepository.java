package com.gof.springcloud.repository;

import com.gof.springcloud.model.TravelPlanModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TravelPlanRepository extends MongoRepository<TravelPlanModel, String> {
}
