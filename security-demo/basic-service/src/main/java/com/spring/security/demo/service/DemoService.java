package com.spring.security.demo.service;

import com.spring.security.demo.model.PersonDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * spring security 方法权限测试
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/28 19:46
 */
@Service
@Slf4j
public class DemoService {

    /**
     * 只有拥有admin角色才能访问findAll方法。
     *
     * @return null
     */
    @PreAuthorize("hasAnyRole('admin')")
    public List<PersonDemo> findAll() {
        log.info( "@PreAuthorize 注解适合进入方法前的权限验证" );
        return null;
    }

    /**
     * 只有返回值的name等于authentication对象的name（当前登录用户名）才能正确返回
     *
     * @return admin
     */
    @PostAuthorize("returnObject.name==authentication.name")
    public PersonDemo findOne() {
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info( "@PostAuthorize 在方法执行后再进行权限验证,适合根据返回值结果进行权限验证。" );
        log.info( "当前登录用户{}", authName );
        return new PersonDemo( "admin" );
    }

    /**
     * 下文代码表示针对ids参数进行过滤，只有id为偶数的元素才被作为参数传入函数。
     */
    @PreFilter(filterTarget = "ids", value = "filterObject%2==0")
    public void delete(List<Integer> ids) {
        log.info( "@PreFilter 在方法执行前针对参数进行过滤" );
    }

    /**
     * 如果使用admin登录系统，上面的函数返回值list中kobe将被过滤掉，只剩下admin。
     *
     * @return 用户集合
     */
    @PostFilter("filterObject.name == authentication.name")
    public List<PersonDemo> findAllPD() {
        List<PersonDemo> list = new ArrayList<>();
        list.add( new PersonDemo( "kobe" ) );
        list.add( new PersonDemo( "admin" ) );
        log.info( "@PostFilter 针对返回结果进行过滤，特别适用于集合类返回值，过滤集合中不符合表达式的对象" );
        return list;
    }
}