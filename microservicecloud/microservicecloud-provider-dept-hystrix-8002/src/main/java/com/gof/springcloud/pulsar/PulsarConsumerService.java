package com.gof.springcloud.pulsar;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gof.springcloud.config.Topics;
import com.gof.springcloud.elasticsearch.IElasticService;
import com.gof.springcloud.model.TravelPlanEvent;

import io.github.majusko.pulsar.annotation.PulsarConsumer;

@Service
public class PulsarConsumerService {
	@Autowired
	private IElasticService elasticService;

	private Logger log = LoggerFactory.getLogger(PulsarConsumerService.class);

	public AtomicBoolean received = new AtomicBoolean(false);


	@PulsarConsumer(topic = Topics.PLAN_EVENT_JSON, clazz = String.class)
	public void planJsonConsume(String message) {
		log.info("Pulsar planConsume receive message:{}", message);
		received.set(true);
		log.info(message);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			TravelPlanEvent event = objectMapper.readValue(message, TravelPlanEvent.class);
			if (event.getOptType().equals("SAVE")) {
				log.info(event.getModel().toString());
				elasticService.save(event.getModel());
			} else {
				elasticService.removeById(event.getModel().getId());
			}
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException:{}", e);
		}

	}

}
