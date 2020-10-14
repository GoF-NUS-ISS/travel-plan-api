package com.gof.springcloud.service.impl;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import javax.ws.rs.core.Application;

import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gof.springcloud.model.TravelPlanModel;
import com.gof.springcloud.repository.TravelPlanRepository;

import io.github.majusko.pulsar.producer.PulsarTemplate;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TravelPlanServiceImplTest {

	@Mock
	private PulsarTemplate<String> eventJsonProducer;

	@Mock
	private TravelPlanRepository travelPlanRepository;


	@InjectMocks
	@Autowired
	private TravelPlanServiceImpl planService;

	@Before
	public void before() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddPlan() {
		TravelPlanModel plan = new TravelPlanModel();
		// Mock MongoDB
		when(travelPlanRepository.save(plan)).thenReturn(plan);
		// Compare result
		TravelPlanModel plan1 = new TravelPlanModel();
		Assert.assertEquals(plan1, planService.addPlan(plan));
	}


	@Test(expected = JsonProcessingException.class)
	public void testAddPlanJsonProcessingException() throws JsonProcessingException {
		ObjectMapper objectMapper = mock(ObjectMapper.class);
		// Cannot be injected into planService
		JsonProcessingException e = new JsonProcessingException("borked") {};
		doThrow(e).when(objectMapper).writeValueAsString(Mockito.any());
		TravelPlanModel plan = new TravelPlanModel();
		// planService.addPlan(plan);
		String eventJsonStr = objectMapper.writeValueAsString(plan);
	}


	@Test(expected = PulsarClientException.class)
	public void testAddPlanPulsarClientException() throws PulsarClientException {
		doThrow(PulsarClientException.class).when(eventJsonProducer).send(Mockito.any(), Mockito.any());
		TravelPlanModel plan = new TravelPlanModel();
		// when(planServiceImpl.addPlan(plan)).thenThrow(JsonProcessingException.class);
		planService.addPlan(plan);
		// eventJsonProducer.send(Topics.PLAN_EVENT_JSON, plan.toString());
	}

	@Test
	public void testGetByName() {
		// Mock MongoDB
		when(travelPlanRepository.findByName("Leox")).thenReturn(new ArrayList<TravelPlanModel>());
		Assert.assertEquals(new ArrayList<TravelPlanModel>(), planService.getByName("Leox"));
	}

	@Test
	public void testGetById() {
		// Mock MongoDB
		TravelPlanModel plan = new TravelPlanModel();
		Optional<TravelPlanModel> opt = Optional.of(plan);
		when(travelPlanRepository.findById("1")).thenReturn(opt);
		Assert.assertEquals(new TravelPlanModel(), planService.getById("1"));
	}

}
