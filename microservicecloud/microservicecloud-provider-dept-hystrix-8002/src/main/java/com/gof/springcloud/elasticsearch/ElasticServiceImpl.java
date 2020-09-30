package com.gof.springcloud.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gof.springcloud.model.TravelPlanModel;
import com.gof.springcloud.model.TravelPlanQueryParam;

@Service("elasticService")
public class ElasticServiceImpl implements IElasticService {
	private final static Logger log = LoggerFactory.getLogger(ElasticServiceImpl.class);

	private String IndexName = "travel-plan";

	@Override
	public boolean createIndex() {
		CreateIndexRequest request = new CreateIndexRequest(IndexName);
		request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
		request.mapping(MappingBuilder.mappingBuild());
		return ESUtils.creatIndice(request);
	}

	@Override
	public void save(TravelPlanModel plan) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonStr = objectMapper.writeValueAsString(plan);
			String Id = ESUtils.addData(jsonStr, IndexName, null);
			log.info("insert successfully and get id{}: {}", Id, jsonStr);
		} catch (IOException e) {
			log.error("insert fail error:{}", e);
		}
	}

	@Override
	public void removeById(String id) {
		ESUtils.deleteById(IndexName, id);
	}

	@Override
	public void removeAll() {
		ESUtils.deleteByQuery(IndexName, QueryBuilders.matchAllQuery());
	}

	private QueryBuilder generateQueryBuilder(TravelPlanQueryParam param) {
		QueryBuilder queryBuilder = null;
		if (null == param || param.checkEmpty()) {
			queryBuilder = QueryBuilders.matchAllQuery();
		} else {
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			// totalCost
			if (null != param.getTotalCostStart() || null != param.getTotalCostEnd()) {
				RangeQueryBuilder range = QueryBuilders.rangeQuery("totalCost").from(param.getTotalCostStart())
						.to(param.getTotalCostEnd());
				boolQueryBuilder.should(range);
			}
			// keyword
			if (StringUtils.isNotBlank(param.getKeyword())) {
				BoolQueryBuilder b1 = QueryBuilders.boolQuery();
				b1.should(new MatchQueryBuilder("title", param.getKeyword()));
				NestedQueryBuilder nestedQuery = new NestedQueryBuilder("days.nodes",
						new MatchQueryBuilder("days.nodes.review", param.getKeyword()), ScoreMode.Total);
				b1.should(nestedQuery);
				boolQueryBuilder.should(b1);
			}
			// site
			if (StringUtils.isNotBlank(param.getSite())) {
				BoolQueryBuilder b1 = QueryBuilders.boolQuery();
				b1.should(new MatchQueryBuilder("days.nodes.location", param.getSite()));
				b1.should(new MatchQueryBuilder("days.nodes.from", param.getSite()));
				b1.should(new MatchQueryBuilder("days.nodes.to", param.getSite()));
				NestedQueryBuilder nestedQuery = new NestedQueryBuilder("days.nodes", b1, ScoreMode.Total);
				boolQueryBuilder.should(nestedQuery);
			}
			queryBuilder = boolQueryBuilder;
		}
		return queryBuilder;
	}

	@Override
	public List<TravelPlanModel> pageByParam(TravelPlanQueryParam param, int startPage, int pageSize) {
		List<TravelPlanModel> result = new ArrayList<TravelPlanModel>();
		QueryBuilder queryBuilder = generateQueryBuilder(param);
		try {
			SearchResponse response = ESUtils.searchDataPage(IndexName, startPage, pageSize, queryBuilder);
			for (SearchHit hit : response.getHits()) {
				ObjectMapper objectMapper = new ObjectMapper();
				TravelPlanModel plan = objectMapper.readValue(hit.getSourceAsString(), TravelPlanModel.class);
				result.add(plan);
			}
		} catch (Exception e) {
			log.error("findAllPlanByPage error:{}", e);
		}
		return result;
	}

	@Override
	public Long countByParam(TravelPlanQueryParam param) {
		QueryBuilder queryBuilder = generateQueryBuilder(param);
		return ESUtils.getDataCount(IndexName, queryBuilder);
	}

}