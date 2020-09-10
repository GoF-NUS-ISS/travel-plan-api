package com.gof.springcloud.controller;

import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gof.springcloud.config.Topics;
import com.gof.springcloud.entities.interaction.AjaxResponse;
import com.gof.springcloud.model.TravelPlanModel;

import io.github.majusko.pulsar.producer.PulsarTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Pulsar API")
@RequestMapping("/pulsar")
public class PulsarProducerController {
	private Logger log = LoggerFactory.getLogger(PulsarProducerController.class);

	@Autowired
	private PulsarTemplate<String> producer;
	@Autowired
	private PulsarTemplate<TravelPlanModel> planProducer;

	@PostMapping("/pulsarProduce")
	@ApiOperation(value = "pulsar produce str msg")
	public AjaxResponse pulsarProduce(String message) {
		try {
			producer.send(Topics.STR_SAMPLE, message);
		} catch (PulsarClientException e) {
			log.error("pulsar produce str fail:{} reason:{}", message, e);
		}
		log.info("pulsar produce str succeed:{}", message);
		return AjaxResponse.success("pulsar produce str succeed");
	}

	@PostMapping("/pulsarPlanProduce")
	@ApiOperation(value = "pulsar produce plan msg")
	public AjaxResponse pulsarPlanProduce(@RequestBody TravelPlanModel message) {
		try {
			planProducer.send(Topics.PLAN_SAMPLE, message);
		} catch (PulsarClientException e) {
			log.error("pulsar produce plan fail:{} reason:{}", message.toString(), e);
		}
		log.info("pulsar produce plan succeed:{}", message.toString());
		return AjaxResponse.success("pulsar produce plan succeed");
	}
}
