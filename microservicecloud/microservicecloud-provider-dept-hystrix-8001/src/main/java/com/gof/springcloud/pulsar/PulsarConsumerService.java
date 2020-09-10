package com.gof.springcloud.pulsar;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gof.springcloud.config.Topics;
import com.gof.springcloud.model.TravelPlanModel;

import io.github.majusko.pulsar.annotation.PulsarConsumer;

@Service
public class PulsarConsumerService {
	private Logger log = LoggerFactory.getLogger(PulsarConsumerService.class);

	public AtomicBoolean received = new AtomicBoolean(false);

	@PulsarConsumer(topic = Topics.STR_SAMPLE, clazz = String.class)
	public void strConsume(String message) {
		log.info("Pulsar strConsume receive message:{}", message);
		received.set(true);
	}

	@PulsarConsumer(topic = Topics.PLAN_SAMPLE, clazz = TravelPlanModel.class)
	public void planConsume(TravelPlanModel message) {
		log.info("Pulsar planConsume receive message:{}", message.toString());
		received.set(true);
	}
}
