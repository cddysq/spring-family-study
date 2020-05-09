package com.spring.security.demo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.spring.security.demo.util.map.GetMapFenceResult;
import com.spring.security.demo.util.map.MapFenceData;
import com.spring.security.demo.util.map.MapFenceUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 高德地图工具类测试
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/9 14:53
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapFenceUtilsTest {
    @Resource
    private MapFenceUtils mapUtil;

    @Test
    public void insertMapTest() {
        MapFenceData mapFenceData = new MapFenceData();
        mapFenceData.setName( "测试添加围栏1" );
        mapFenceData.setRepeat( "Mon,Tues,Wed,Thur,Fri,Sat,Sun" );
        mapFenceData.setFixed_date( "2020-05-09;2020-05-10;2020-05-11" );
        ArrayList<String> list = new ArrayList<>();
        list.add( "104.147018,30.807318" );
        list.add( "104.150881,30.809125" );
        list.add( "104.150366,30.80378" );
        list.add( "104.149121,30.821557" );
        mapFenceData.setPoints( list );
        String s = mapUtil.addMap( mapFenceData );
        System.out.println( JSON.toJSONString( mapFenceData, SerializerFeature.PrettyFormat ) );
    }

    @Test
    public void test() {
        ArrayList<String> list = new ArrayList<>();
        list.add( "104.147018,30.807318" );
        list.add( "104.150881,30.809125" );
        list.add( "104.150366,30.80378" );
        list.add( "104.149121,30.821557" );
        System.out.println( list.toString() );
        String s = String.join( ";", list );
        System.out.println( s );
    }

    @Test
    public void selectMap() {
        GetMapFenceResult map = mapUtil.selectMap( "" );
        System.out.println( JSON.toJSONString( map, SerializerFeature.PrettyFormat ) );
    }

    @Test
    public void updateMap() {
        MapFenceData mapFenceData = new MapFenceData();
        mapFenceData.setName( "修改测试" );
        //insertMapData.setPoints( "104.147018,30.807318;104.150366,30.80378;104.150881,30.809125;104.149121,30.823567" );
        mapFenceData.setRepeat( "Mon,Tues,Wed,Thur,Fri,Sat" );
        mapUtil.updateMap( mapFenceData, "d2e5477a-bfdf-4c28-8906-d679a607046c" );
    }

    @Test
    public void delete() {
        mapUtil.deleteMap( "2992c804-f65b-4e01-9aec-ec28dbb63c53" );
    }
}