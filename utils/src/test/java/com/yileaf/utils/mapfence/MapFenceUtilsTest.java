package com.yileaf.utils.mapfence;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 高德地图电子围栏工具类测试
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
        mapFenceData.setName( "add" );
        mapFenceData.setRepeat( "Mon,Tues,Wed,Thur,Fri,Sat,Sun" );
        ArrayList<String> list = new ArrayList<>();
        list.add( "104.140579,30.629924" );
        list.add( "104.067795,30.606288" );
        list.add( "104.078781,30.642331" );
        mapFenceData.setPoints( list );
        System.out.println( JSON.toJSONString( mapFenceData, SerializerFeature.PrettyFormat ) );
        String s = mapUtil.addMap( mapFenceData );
        System.out.println( "gid=" + s );
    }

    @Test
    public void selectMap() {
        GetMapFenceResult map = mapUtil.selectMap( "" );
        System.out.println( JSON.toJSONString( map, SerializerFeature.PrettyFormat ) );
    }

    @Test
    public void updateMap() {
        MapFenceData mapFenceData = new MapFenceData();
        mapFenceData.setName( "update" );
        //insertMapData.setPoints( "104.147018,30.807318;104.150366,30.80378;104.150881,30.809125;104.149121,30.823567" );
        mapFenceData.setRepeat( "Mon,Tues,Wed,Thur,Fri,Sat" );
        mapUtil.updateMap( mapFenceData, "7e1d95bf-e3c9-4757-8861-6bb35a8a4ed1" );
    }

    @Test
    public void delete() {
        mapUtil.deleteMap( "e2857f06-cec0-4e06-9cc3-86866ad7e04c" );
    }
}