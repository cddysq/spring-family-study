package com.spring.security.demo.util.map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 地图工具类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/8 20:03
 */
@Slf4j
@Component
public class MapFenceUtils {
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
    public String addMap(MapFenceData mapFenceDate) {
        // 数据转换
        InsertMapFenceData insertMapFenceData = convertMapFenceData( mapFenceDate );
        // 设置请求头
        HttpHeaders httpHeaders = this.setHttpHeaders( MediaType.APPLICATION_JSON );
        // 封装请求
        HttpEntity<String> formEntity = new HttpEntity<String>( JSON.toJSONString( insertMapFenceData ), httpHeaders );
        MapFenceResult result = restTemplate.postForObject( GAO_DE_MAP_URL, formEntity, MapFenceResult.class );
        if (Objects.isNull( result )) {
            throw new RuntimeException( "添加电子围栏失败" );
        }
        if (result.getErrcode() != SUCCESS_STATUS) {
            log.error( "add map exception {}", result );
            throw new RuntimeException( result.getErrmsg() );
        }
        if (Objects.isNull( result.getData() )) {
            log.error( "add map exception {}", result );
            throw new RuntimeException( "添加电子围栏失败" );
        }
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
    public GetMapFenceResult selectMap(String gid) {
        GetMapFenceResult result = null;
        if (StrUtil.isBlank( gid )) {
            // 未传值查询所有
            result = restTemplate.getForObject( GAO_DE_MAP_URL, GetMapFenceResult.class );
        } else {
            String newUrl = GAO_DE_MAP_URL + "&gid={gid}";
            result = restTemplate.getForObject( newUrl, GetMapFenceResult.class, gid );
        }
        if (Objects.isNull( result )) {
            throw new RuntimeException( "查询电子围栏失败" );
        }
        if (result.getErrcode() != SUCCESS_STATUS) {
            log.error( "get map exception gid={},message={}", gid, result );
            throw new RuntimeException( result.getErrmsg() );
        }
        if (Objects.isNull( result.getData() )) {
            log.error( "get map exception gid={},message={}", gid, result );
            throw new RuntimeException( "查询电子围栏失败" );
        }
        if (CollUtil.isEmpty( result.getData().getRs_list() )) {
            log.error( "get map exception gid={},message={}", gid, result );
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
    public void updateMap(MapFenceData mapFenceDate, String gid) {
        // 拼接更新请求
        String newUrl = GAO_DE_MAP_URL + "&gid=" + gid;
        InsertMapFenceData insertMapFenceData = convertMapFenceData( mapFenceDate );
        HttpHeaders httpHeaders = this.setHttpHeaders( MediaType.APPLICATION_JSON );
        // 封装请求
        HttpEntity<String> formEntity = new HttpEntity<String>( JSON.toJSONString( insertMapFenceData ), httpHeaders );
        MapFenceResult result = restTemplate.patchForObject( newUrl, formEntity, MapFenceResult.class );
        if (Objects.isNull( result )) {
            throw new RuntimeException( "更新电子围栏失败" );
        }
        if (result.getErrcode() != SUCCESS_STATUS) {
            log.error( "update map exception gid={} data={} ", gid, insertMapFenceData );
            throw new RuntimeException( result.getErrmsg() );
        }
        if (Objects.isNull( result.getData() )) {
            log.error( "update map exception gid={} data={} ", gid, insertMapFenceData );
            throw new RuntimeException( "更新电子围栏失败" );
        }
        if (result.getData().getStatus() != SUCCESS_STATUS) {
            log.error( "update map exception gid={} data={} ", gid, insertMapFenceData );
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
        ResponseEntity<MapFenceResult> result = restTemplate.exchange( newUrl, HttpMethod.DELETE, null, MapFenceResult.class );
        MapFenceResult msg = result.getBody();
        if (Objects.isNull( msg )) {
            throw new RuntimeException( "删除地理围栏失败" );
        }
        if (msg.getErrcode() != SUCCESS_STATUS) {
            log.error( "delete map exception gid={},message={}", gid, msg );
            throw new RuntimeException( msg.getErrmsg() );
        }
        if (Objects.isNull( msg.getData() )) {
            log.error( "delete map exception gid={},message={}", gid, msg );
            throw new RuntimeException( "删除地理围栏失败" );
        }
        if (msg.getData().getStatus() != SUCCESS_STATUS) {
            log.error( "delete map exception gid={},message={}", gid, msg );
            throw new RuntimeException( msg.getData().getMessage() );
        }
    }

    /**
     * 数据格式转换
     *
     * @param mapFenceDate 传入数据
     * @return 对接高德数据对象
     */
    private InsertMapFenceData convertMapFenceData(MapFenceData mapFenceDate) {
        // 数据转换
        InsertMapFenceData insertMapFenceData = new InsertMapFenceData();
        BeanUtils.copyProperties( mapFenceDate, insertMapFenceData );
        // 排除更新可能不传
        if (CollUtil.isNotEmpty( mapFenceDate.getPoints() )) {
            insertMapFenceData.setPoints( String.join( ";", mapFenceDate.getPoints() ) );
        }
        return insertMapFenceData;
    }

    /**
     * 设置请求头
     *
     * @param applicationJsonUtf8 请求头类型
     * @return 请求头对象
     */
    private HttpHeaders setHttpHeaders(MediaType applicationJsonUtf8) {
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置请求类型为json
        httpHeaders.setContentType( applicationJsonUtf8 );
        // 设置返回类型为json
        httpHeaders.add( "Accept", MediaType.APPLICATION_JSON.toString() );
        return httpHeaders;
    }
}