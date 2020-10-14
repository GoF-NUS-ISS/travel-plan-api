package com.gof.springcloud.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class ESUtils {

	@Qualifier("highLevelClient")
	@Autowired
	private RestHighLevelClient rhlClient;

	private static RestHighLevelClient client;

	@PostConstruct
	public void init() {
		client = this.rhlClient;
	}

	public Boolean creatIndice(CreateIndexRequest request) {
		CreateIndexResponse createIndexResponse;
		try {
			GetIndexRequest request_eixst = new GetIndexRequest(request.index());
			boolean exists = client.indices().exists(request_eixst, RequestOptions.DEFAULT);
			if (!exists) {
				createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
				boolean acknowledged = createIndexResponse.isAcknowledged();
				boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
				return acknowledged && shardsAcknowledged;
			}
		} catch (IOException e) {
			log.error("creatIndice fail{}", e);
		}
		return false;
	}


	/**
	 * @param jsonStr insert content in form of json
	 * @param index
	 * @param id
	 * @return Id
	 */
	public String addData(String jsonStr, String index, String id) {
		String Id = null;
		try {
			IndexRequest request = new IndexRequest(index).id(id).source(jsonStr, XContentType.JSON);
			IndexResponse response = client.index(request, RequestOptions.DEFAULT);
			Id = response.getId();
			log.info("index:{} - addData succeed, return status:{}, id:{}", index, response.status().getStatus(), Id);
		} catch (IOException e) {
			log.error("index:{} - addData fail, id:{}", index, id);
		}
		return Id;
	}

	/**
	 * @param jsonStr update content in form of json
	 * @param index
	 * @param id
	 * @return Id
	 */
	public String updateData(String jsonStr, String index, String id) {
		String Id = null;
		try {
			UpdateRequest request = new UpdateRequest(index, id).doc(jsonStr, XContentType.JSON);
			UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
			Id = response.getId();
			log.info("index:{} - updateData succeed, return status:{}, id:{}", index, response.status().getStatus(),
					Id);
		} catch (IOException e) {
			log.error("index:{} - updateData fail, id:{}", index, id);
		}
		return Id;
	}

	/**
	 * @param index
	 * @param builder
	 * batch size is 1000 here
	 */
	public void deleteByQuery(String index, QueryBuilder builder) {
		DeleteByQueryRequest request = new DeleteByQueryRequest(index);
		request.setQuery(builder);
		request.setBatchSize(1000);
		try {
			client.deleteByQuery(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			log.error("index:{} - deleteByQuery fail", index);
		}
	}

	/**
	 * @param index
	 * @param id
	 * @return state
	 */
	public String deleteById(String index, String id) {
		String state = null;
		DeleteRequest request = new DeleteRequest(index, id);
		try {
			DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
			int status = response.status().getStatus();
			state = Integer.toString(status);
			log.info("index:{} - deleteById succeed, id:{}", index, id);
		} catch (IOException e) {
			log.error("index:{} - deleteById fail, id:{}", index, id);
		}
		return state;
	}

	/**
	 * @param <T>
	 * @param index
	 * @param startPage
	 * @param pageSize
	 * @param queryBuilder
	 * @param type classType
	 * @return
	 */
	@SneakyThrows
	public <T> List<T> searchDataPage(String index, int startPage, int pageSize,
			QueryBuilder queryBuilder, Class<T> type) {
		List<T> result = new ArrayList<T>();
		SearchRequest request = new SearchRequest(index);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.timeout(new TimeValue(120, TimeUnit.SECONDS));
		sourceBuilder.explain(true);
		sourceBuilder.query(queryBuilder);
		sourceBuilder.from((startPage - 1) * pageSize);
		sourceBuilder.size(pageSize);
		request.source(sourceBuilder);
		SearchResponse response = client.search(request, RequestOptions.DEFAULT);
		if (null != response.getHits()) {
			for (SearchHit hit : response.getHits()) {
				ObjectMapper objectMapper = new ObjectMapper();
				T entity = objectMapper.readValue(hit.getSourceAsString(), type);
				result.add(entity);
			}
		}
		return result;
	}

	/**
	 * @param index
	 * @param queryBuilder
	 * @return
	 */
	public Long getDataCount(String index, QueryBuilder queryBuilder) {
		SearchRequest request = new SearchRequest(index);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.timeout(new TimeValue(120, TimeUnit.SECONDS));
		sourceBuilder.explain(true);
		sourceBuilder.query(queryBuilder);
		request.source(sourceBuilder);
		try {
			SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
			return searchResponse.getHits().getTotalHits().value;
		} catch (IOException e) {
			log.error("index:{} - searchDataPage by condition fail", index);
		}
		return null;
	}

}
