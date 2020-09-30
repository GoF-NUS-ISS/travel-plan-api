package com.gof.springcloud.elasticsearch;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface IElasticService {

	void save(DocBean docBean);

	void saveAll(List<DocBean> list);

	Iterator<DocBean> findAll();

	Page<DocBean> findByContent(String content);

	Page<DocBean> findByFirstCode(String firstCode);

	Page<DocBean> findBySecordCode(String secordCode);

	Optional<DocBean> query(Long key);
}