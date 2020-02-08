package com.yileaf.es.repository;

import com.yileaf.es.entity.Girl;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Author: Haotian
 * @Date: 2020/2/8 18:57
 * @Description: 用户操作接口
 */
@Component
public interface GirlRepository extends ElasticsearchRepository<Girl, Long> {
}