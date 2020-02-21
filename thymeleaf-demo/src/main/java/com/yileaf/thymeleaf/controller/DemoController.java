package com.yileaf.thymeleaf.controller;

import com.yileaf.thymeleaf.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

/**
 * @Author: Haotian
 * @Date: 2020/2/20 21:59
 * @Description: 访问接口
 */
@Controller
@Slf4j
public class DemoController {

    @GetMapping("/demo")
    public String demo(Model model, String id) {
        model.addAttribute( "Hello", "Welcome to Thymeleaf ..." );

        log.info( "接收到提交数据：{}", id );

        List<User> userList = Arrays.asList(
                User.builder().id( 10L ).name( "伊卡洛斯" ).address( "日本九州福冈县空美町" ).build(),
                User.builder().id( 20L ).name( "楪祈" ).address( "葬仪社" ).build(),
                User.builder().id( 30L ).name( "赤夜萌香" ).address( "阳海学园" ).build() );
        model.addAttribute( "users", userList );

        Map<String, String> mapData = new HashMap<>( 0 );
        mapData.put( "name", "白" );
        mapData.put( "address", "艾尔奇亚" );
        model.addAttribute( "mapData", mapData );

        String[] names = {"一号", "二号", "三号"};
        model.addAttribute( "names", names );

        model.addAttribute( "now", new Date() );

        model.addAttribute( "age", 18 );

        return "demo";
    }
}
