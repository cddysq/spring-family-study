package com.yileaf.web.controller;

import com.yileaf.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @RestController 用于写API, 给移动客户端提供数据, 一般是返回json数据
 * @Controller 一般用于写后台(有页面)
 */
@Controller
@RequestMapping("stu")
/**
 * @Author：Haotian
 * @Date：2019/6/14 18:19
 */
public class StudentController {
    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute( "username", "pandora" );
        model.addAttribute( "age", 20 );
        List<Student> stuList = new ArrayList<Student>();
        stuList.add( new Student( 1001, "吉米", "男" ) );
        stuList.add( new Student( 1002, "艾莉丝", "女" ) );
        stuList.add( new Student( 1003, "零", "未知" ) );
        model.addAttribute( "stuList", stuList );

        //找到模板页面
        return "stu/list";
    }

    @RequestMapping("login")
    public String login(String name) {
        return "user/list";
    }
}
