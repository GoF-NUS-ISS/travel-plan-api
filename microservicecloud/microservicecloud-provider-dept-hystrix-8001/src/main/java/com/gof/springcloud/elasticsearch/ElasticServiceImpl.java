package com.gof.springcloud.elasticsearch;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

@Service("elasticService")
public class ElasticServiceImpl implements IElasticService {

	@Autowired
	private ElasticsearchRestTemplate elasticsearchTemplate;
	@Autowired
	private ElasticRepository elasticRepository;

	private Pageable pageable = PageRequest.of(0, 10);

	@Override
	public void save(DocBean docBean) {
		elasticRepository.save(docBean);
	}

	@Override
	public void saveAll(List<DocBean> list) {
		elasticRepository.saveAll(list);
	}

	@Override
	public Iterator<DocBean> findAll() {
		return elasticRepository.findAll().iterator();
	}

	@Override
	public Page<DocBean> findByContent(String content) {
		return elasticRepository.findByContent(content, pageable);
	}

	@Override
	public Page<DocBean> findByFirstCode(String firstCode) {
		return elasticRepository.findByFirstCode(firstCode, pageable);
	}

	@Override
	public Page<DocBean> findBySecordCode(String secordCode) {
		return elasticRepository.findBySecordCode(secordCode, pageable);
	}

	@Override
	public Optional<DocBean> query(Long key) {
		return elasticRepository.findById(key);
	}
}