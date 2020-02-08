package com.yileaf.es.controller;

import com.yileaf.es.entity.Code;
import com.yileaf.es.entity.Girl;
import com.yileaf.es.entity.Result;
import com.yileaf.es.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Haotian
 * @Date: 2020/2/8 19:41
 * @Description:
 */
@RestController
@RequestMapping("/es")
public class ElasticSearchController {
    @Autowired
    private GirlRepository girlRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("/create")
    public Result<String> createIndex() {
        return Result.<String>builder()
                .code( Code.SUCCESS )
                .data( "" )
                .message( "默认启动创建索引" ).build();
    }

    /**
     * 添加/修改 文档测试
     */
    @GetMapping("/add")
    public Result<Girl> addDocument() {
        //准备数据
        Girl girl = Girl.builder()
                //保证id相同再次添加数据即为修改
                .id( 1 )
                .name( "椎名真白" )
                .content( "出自樱花庄的宠物女孩,英日混血儿，拥有通透白皙的肌肤，微微的凤眼看起来有些成熟，迈步走起来像西表山猫。" ).build();
        //将数据存入索引库
        girlRepository.save( girl );
        return Result.<Girl>builder()
                .code( Code.SUCCESS )
                .data( girl )
                .message( "添加文档成功" ).build();
    }

}