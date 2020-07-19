package com.yileaf.es.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yileaf.es.entity.Code;
import com.yileaf.es.entity.Girl;
import com.yileaf.es.entity.Result;
import com.yileaf.es.repository.GirlRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 模拟接收前端数据传入es中
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/19 22:20
 **/
@Slf4j
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

    /**
     * 批量添加文档测试
     */
    @GetMapping("/addMore")
    public Result<List<Girl>> addMoreDocument() {
        ArrayList<Girl> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            //准备数据
            Girl girl = Girl.builder()
                    //保证id相同再次添加数据即为修改
                    .id( i )
                    .name( i + "莎缇莱萨·L·布丽姬" )
                    .content( i + "自尊心极强，冷若冰霜的外表下，内心其实相当柔弱，非常喜欢吃汉堡，不管什么时候都是吃汉堡。" ).build();
            //将数据存入索引库
            girlRepository.save( girl );
            list.add( girl );
        }
        return Result.<List<Girl>>builder()
                .code( Code.SUCCESS )
                .data( list )
                .message( "批量添加文档成功" ).build();
    }

    /**
     * 删除文档测试
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> deleteDocument(@PathVariable("id") Long id) {
        //根据id删除
        girlRepository.deleteById( id );
        //girlRepository.deleteAll(); 删除全部
        return Result.builder()
                .code( Code.SUCCESS )
                .data( id )
                .message( "删除文档成功" ).build();
    }

    /**
     * 查询所有文档测试
     */
    @GetMapping("/search")
    public Result<Object> findAllDocument() {
        //查询的文档按照 id 升序排列
        Iterable<Girl> girls = girlRepository.findAll( Sort.by( Sort.Direction.DESC, "id" ) );
        log.info( JSON.toJSONString( girls, SerializerFeature.PrettyFormat ) );
        return Result.builder()
                .code( Code.SUCCESS )
                .data( girls )
                .message( "查询所有文档成功" ).build();
    }

    /**
     * 根据id查询指定文档测试
     */
    @GetMapping("/search/{id}")
    public Result<Object> findDocumentById(@PathVariable("id") Long id) {
        //查询指定id文档
        Optional<Girl> girl = girlRepository.findById( id );
        log.info( JSON.toJSONString( girl, SerializerFeature.PrettyFormat ) );
        return Result.builder()
                .code( Code.SUCCESS )
                .data( girl )
                .message( "查询指定文档成功" ).build();
    }

    /**
     * 自定义查询姓名方法测试
     */
    @GetMapping("/search/demo1")
    public Result<Object> findDocumentByteName() {
        //查询指定名字文档
        List<Girl> girls = girlRepository.findByName( "真白" );
        log.info( JSON.toJSONString( girls, SerializerFeature.PrettyFormat ) );
        return Result.builder()
                .code( Code.SUCCESS )
                .data( girls )
                .message( "查询指定姓名文档成功" ).build();
    }

    /**
     * 自定义查询name或content方法测试
     */
    @GetMapping("/search/demo2")
    public Result<Object> findDocumentByNameOrContent() {
        //查询指定名字或者内容文档
        List<Girl> girls = girlRepository.findByNameOrContent( "真白", "汉堡" );
        log.info( JSON.toJSONString( girls, SerializerFeature.PrettyFormat ) );
        return Result.builder()
                .code( Code.SUCCESS )
                .data( girls )
                .message( "查询指定姓名或内容文档成功" ).build();
    }

    /**
     * 自定义查询name和content方法测试
     */
    @GetMapping("/search/demo3")
    public Result<Object> findDocumentByNameAndContent() {
        //查询指定名字或者内容文档
        List<Girl> girls = girlRepository.findByNameAndContent( "真白", "汉堡" );
        log.info( JSON.toJSONString( girls, SerializerFeature.PrettyFormat ) );
        return Result.builder()
                .code( Code.SUCCESS )
                .data( girls )
                .message( "查询指定姓名和内容文档成功" ).build();
    }

    /**
     * 自定义查询name或content方法并分页测试
     */
    @GetMapping("/search/demo4")
    public Result<Object> findDocumentByNameOrContent2() {
        //设置分页信息
        Pageable page = PageRequest.of( 1, 6 );
        //查询指定名字或者内容文档
        List<Girl> girls = girlRepository.findByNameOrContent( "真白与汉堡肉", "汉堡", page );
        log.info( JSON.toJSONString( girls, SerializerFeature.PrettyFormat ) );
        return Result.builder()
                .code( Code.SUCCESS )
                .data( girls )
                .message( "查询指定姓名或内容文档分页成功" ).build();
    }

    /**
     * 原生查询方式
     */
    @GetMapping("/search/demo5")
    public Result<Object> testNativeSearchQuery() {
        //创建查询对象
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(
                        //设置查询内容与查询域
                        QueryBuilders
                                .queryStringQuery( "真白与布丽姬" ).defaultField( "name" ) )
                .withPageable(
                        PageRequest
                                .of( 0, 4 ) )
                .withSort(
                        //设置排序字段与排序规则
                        SortBuilders
                                .fieldSort( "id" ).order( SortOrder.DESC ) )
                .build();
        //执行查询
        List<Girl> girls = elasticsearchTemplate.queryForList( query, Girl.class );
        log.info( JSON.toJSONString( girls, SerializerFeature.PrettyFormat ) );
        return Result.builder()
                .code( Code.SUCCESS )
                .data( girls )
                .message( "原生方式查询成功" ).build();
    }
}