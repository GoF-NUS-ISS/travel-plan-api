package com.gof.springcloud.elasticsearch;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Application;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gof.springcloud.model.TravelPlanModel;
import com.gof.springcloud.model.TravelPlanQueryParam;

@PrepareForTest({ ElasticServiceImpl.class, SearchHits.class, SearchHit.class })
@RunWith(MockitoJUnitRunner.class)
//@RunWith(PowerMockRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ElasticServiceImplTest {

	@Mock
	private ESUtils eSUtils;

	@InjectMocks
	@Autowired
	private ElasticServiceImpl elasticService;

	@Before
	public void before() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateIndex() {
		// Mock ElasticSearch
		when(eSUtils.creatIndice(Mockito.any())).thenReturn(true);
		// Compare result
		Assert.assertEquals(true, elasticService.createIndex());
	}

	@Test
	public void testSave() {
		// Mock ElasticSearch
		when(eSUtils.addData(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn("test id");
		TravelPlanModel plan = new TravelPlanModel();
		Assert.assertEquals("test id", elasticService.save(plan));
	}

	@Test
	public void testRemoveById() {
		when(eSUtils.deleteById(Mockito.any(), Mockito.any())).thenReturn("200");
		Assert.assertEquals("200", elasticService.removeById("test"));
	}

	@Test
	public void testRemoveAll() {
		Mockito.doNothing().when(eSUtils).deleteByQuery(Mockito.any(), Mockito.any());
		elasticService.removeAll();
	}

	@Test
	public void testPageByParam() throws Exception {
		List<TravelPlanModel> result = new ArrayList<TravelPlanModel>();
		when(eSUtils.searchDataPage("travel-plan", 1, 10, QueryBuilders.matchAllQuery(), TravelPlanModel.class))
				.thenReturn(result);
		TravelPlanQueryParam param = new TravelPlanQueryParam();
		Assert.assertEquals(new ArrayList<TravelPlanModel>(), elasticService.pageByParam(param, 1, 10));

		param.setKeyword("food");
		param.setSite("Singapore");
		param.setTotalCostStart(5000d);
		param.setTotalCostEnd(10000d);

		Assert.assertEquals(new ArrayList<TravelPlanModel>(), elasticService.pageByParam(param, 1, 10));

	}

	@Test
	public void testCountByParam() {
		when(eSUtils.getDataCount(Mockito.any(), Mockito.any())).thenReturn(1L);
		TravelPlanQueryParam param = new TravelPlanQueryParam();
		Assert.assertEquals(1L, elasticService.countByParam(param).longValue());
	}

}
