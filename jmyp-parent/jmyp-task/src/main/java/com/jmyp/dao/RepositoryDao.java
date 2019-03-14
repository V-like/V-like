package com.jmyp.dao;

import com.jmyp.es.EsData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by on 2018/12/20.
 */
public interface RepositoryDao extends ElasticsearchRepository<EsData,Long> {
}
