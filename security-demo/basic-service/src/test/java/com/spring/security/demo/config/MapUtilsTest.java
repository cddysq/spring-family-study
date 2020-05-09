package com.spring.security.demo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.spring.security.demo.util.map.GetMapResult;
import com.spring.security.demo.util.map.InsertMapData;
import com.spring.security.demo.util.map.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 高德地图工具类测试
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/9 14:53
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapUtilsTest {
    @Resource
    private MapUtils mapUtil;

    @Test
    public void insertMapTest() {
        InsertMapData insertMapData = new InsertMapData();
        insertMapData.setName( "测试添加围栏1" );
        insertMapData.setPoints( "104.147018,30.807318;104.150366,30.80378;104.150881,30.809125;104.149121,30.821557" );
        insertMapData.setRepeat( "Mon,Tues,Wed,Thur,Fri,Sat,Sun" );
        //insertMapData.setFixed_date( "2020-05-09;2020-05-10;2020-05-11" );
        String map = mapUtil.addMap( insertMapData );
        System.out.println( JSON.toJSONString( map, SerializerFeature.PrettyFormat ) );
    }

    @Test
    public void selectMap() {
        GetMapResult map = mapUtil.selectMap( "" );
        System.out.println( JSON.toJSONString( map, SerializerFeature.PrettyFormat ) );
    }

    @Test
    public void updateMap() {
        InsertMapData insertMapData = new InsertMapData();
        insertMapData.setName( "修改测试" );
        insertMapData.setPoints( "104.147018,30.807318;104.150366,30.80378;104.150881,30.809125;104.149121,30.823567" );
        insertMapData.setRepeat( "Mon,Tues,Wed,Thur,Fri,Sat,Sun" );
        mapUtil.updateMap( insertMapData, "2992c804-f65b-4e01-9aec-ec28dbb63c53" );
    }

    @Test
    public void delete() {
        mapUtil.deleteMap( "2992c804-f65b-4e01-9aec-ec28dbb63c53" );
    }
}