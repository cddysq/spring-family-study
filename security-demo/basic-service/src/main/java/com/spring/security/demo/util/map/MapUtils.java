package com.spring.security.demo.util.map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 地图工具类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/8 20:03
 */
@Slf4j
@Component
public class MapUtils {
    @Resource
    private RestTemplate restTemplate;
    /**
     * 高德地图请求key
     */
    private static final String GAO_DE_MAP_KEY = "";
    /**
     * 高德地图请求地址
     */
    private static final String GAO_DE_MAP_URI = "https://restapi.amap.com/v4/geofence/meta?key=";
    /**
     * 高德地图插入电子围栏请求地址
     */
    private static final String GAO_DE_MAP_URL = GAO_DE_MAP_URI + GAO_DE_MAP_KEY;

    /**
     * 请求成功状态码
     */
    private static final int SUCCESS_STATUS = 0;

    /**
     * 添加地理围栏
     *
     * @param insertMap 电子围栏数据封装类
     * @return 围栏全局id
     */
    public String addMap(InsertMapData insertMap) {
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求类型为json
        httpHeaders.setContentType( MediaType.APPLICATION_JSON );
        // 设置返回类型为json
        httpHeaders.add( "Accept", MediaType.APPLICATION_JSON.toString() );
        // 封装请求
        HttpEntity<String> formEntity = new HttpEntity<String>( JSON.toJSONString( insertMap ), httpHeaders );
        MapResult result = restTemplate.postForObject( GAO_DE_MAP_URL, formEntity, MapResult.class );
        if (StrUtil.isBlank( result.getData().getGid() )) {
            log.error( "add map exception {}", result );
            throw new RuntimeException( result.getData().getMessage() );
        }
        return result.getData().getGid();
    }

    /**
     * 查询电子围栏
     *
     * @param gid 围栏全局id 为空默认查询全部
     * @return 电子围栏结果解析类
     */
    public GetMapResult selectMap(String gid) {
        GetMapResult result = null;
        if (StrUtil.isBlank( gid )) {
            // 未传值查询所有
            result = restTemplate.getForObject( GAO_DE_MAP_URL, GetMapResult.class );
        } else {
            String newUrl = GAO_DE_MAP_URL + "&gid={gid}";
            result = restTemplate.getForObject( newUrl, GetMapResult.class, gid );
        }
        if (CollUtil.isEmpty( result.getData().getRs_list() )) {
            log.error( "get map exception gid={}", gid );
            throw new RuntimeException( "暂未查询到对应电子围栏记录" );
        }
        return result;
    }

    /**
     * 更新地理围栏
     *
     * @param insertMap 电子围栏数据封装类
     * @param gid       围栏全局id
     * @return true:更新成功 false:更新失败
     */
    public void updateMap(InsertMapData insertMap, String gid) {
        // 拼接更新请求
        String newUrl = GAO_DE_MAP_URL + "&gid=" + gid;
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求类型为json
        httpHeaders.setContentType( MediaType.APPLICATION_JSON );
        // 设置返回类型为json
        httpHeaders.add( "Accept", MediaType.APPLICATION_JSON.toString() );
        // 封装请求
        HttpEntity<String> formEntity = new HttpEntity<String>( JSON.toJSONString( insertMap ), httpHeaders );
        MapResult result = restTemplate.patchForObject( newUrl, formEntity, MapResult.class );
        if (result.getData().getStatus() != SUCCESS_STATUS) {
            log.error( "update map exception gid={} data={} ", gid, insertMap );
            throw new RuntimeException( result.getData().getMessage() );
        }
    }

    /**
     * 删除地理围栏
     *
     * @param gid 围栏全局id
     */
    public void deleteMap(String gid) {
        String newUrl = GAO_DE_MAP_URL + "&gid=" + gid;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MapResult> result = restTemplate.exchange( newUrl, HttpMethod.DELETE, null, MapResult.class );
        MapResult msg = result.getBody();
        if (msg.getData().getStatus() != SUCCESS_STATUS) {
            log.error( "delete map exception gid={}", gid );
            throw new RuntimeException( msg.getData().getMessage() );
        }
    }
}