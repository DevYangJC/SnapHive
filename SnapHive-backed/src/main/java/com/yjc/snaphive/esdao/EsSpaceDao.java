package com.yjc.snaphive.esdao;

import com.yjc.snaphive.model.entity.es.EsSpace;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsSpaceDao extends ElasticsearchRepository<EsSpace, Long> {
}
