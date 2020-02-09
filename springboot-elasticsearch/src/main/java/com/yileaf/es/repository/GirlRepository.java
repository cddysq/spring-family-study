package com.yileaf.es.repository;

import com.yileaf.es.entity.Girl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Haotian
 * @Date: 2020/2/8 18:57
 * @Description: 用户操作接口
 */
@Component
public interface GirlRepository extends ElasticsearchRepository<Girl, Long> {
    /**
     * 按照规则自定义查询方法
     *
     * @param name 名字
     * @return 匹配内容
     */
    List<Girl> findByName(String name);

    /**
     * 按照规则自定义查询方法
     *
     * @param name    名字
     * @param content 内容
     * @return or满足其一匹配内容
     */
    List<Girl> findByNameOrContent(String name, String content);

    /**
     * 按照规则自定义查询方法
     *
     * @param name    名字
     * @param content 内容
     * @return and都得满足
     */
    List<Girl> findByNameAndContent(String name, String content);

    /**
     * 按照规则自定义查询方法并分页
     *
     * @param name     名字
     * @param content  内容
     * @param pageable 分页信息
     * @return 满足其一匹配内容
     */
    List<Girl> findByNameOrContent(String name, String content, Pageable pageable);
}