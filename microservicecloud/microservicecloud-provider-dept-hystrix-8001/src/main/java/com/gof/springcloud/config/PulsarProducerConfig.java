package com.gof.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gof.springcloud.model.TravelPlanModel;

import io.github.majusko.pulsar.producer.ProducerFactory;

@Configuration
public class PulsarProducerConfig {

	@Bean
	public ProducerFactory producerFactory() {
		return new ProducerFactory().addProducer(Topics.STR_SAMPLE, String.class)
				.addProducer(Topics.PLAN_SAMPLE, TravelPlanModel.class)
				.addProducer(Topics.PLAN_EVENT_JSON, String.class);

	}
}