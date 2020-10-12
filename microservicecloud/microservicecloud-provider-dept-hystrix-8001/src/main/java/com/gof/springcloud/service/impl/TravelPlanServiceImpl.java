package com.gof.springcloud.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gof.springcloud.config.Topics;
import com.gof.springcloud.model.TravelPlanEvent;
import com.gof.springcloud.model.TravelPlanModel;
import com.gof.springcloud.repository.TravelPlanRepository;
import com.gof.springcloud.service.TravelPlanService;

import io.github.majusko.pulsar.producer.PulsarTemplate;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TravelPlanServiceImpl implements TravelPlanService {
	@Autowired
	private TravelPlanRepository travelPlanRepository;
	@Autowired
	private PulsarTemplate<String> eventJsonProducer;

	@Override
	@SneakyThrows
	public TravelPlanModel addPlan(TravelPlanModel travelPlanModel){
		travelPlanModel = travelPlanRepository.save(travelPlanModel);
		// set event entity
		TravelPlanEvent event = new TravelPlanEvent();
		event.setModel(travelPlanModel);
		event.setOptTime(new Date());
		event.setOptType("SAVE");
		//try {
			// transfer into json
			ObjectMapper objectMapper = new ObjectMapper();
			String eventJsonStr = objectMapper.writeValueAsString(event);
			eventJsonProducer.send(Topics.PLAN_EVENT_JSON, eventJsonStr);
			log.info("pulsar produce plan json: {}", eventJsonStr);
		//} catch (JsonProcessingException e) {
		//	log.error("pulsar produce plan fail:{} JsonProcessingException - reason:{}", event.toString(), e);
	    //    throw new IllegalArgumentException("Divider must not be 0");

		//} catch (PulsarClientException e) {
		//	log.error("pulsar produce plan fail:{} PulsarClientException - reason:{}", event.toString(), e);
		//}
		return travelPlanModel;
	}

	@Override
	public List<TravelPlanModel> getByName(String name) {
		return travelPlanRepository.findByName(name);
	}

	@Override
	public TravelPlanModel getById(String id) {
		return travelPlanRepository.findById(id).get();
	}
}
