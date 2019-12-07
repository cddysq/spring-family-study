package com.spring.data.declarativetransactiondemo;

import com.spring.data.exception.RollbackException;
import com.spring.data.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: Haotian
 * @Date: 2019/12/7 16:48
 * @Parameter : @EnableTransactionManagement 启用Spring注解驱动的事务管理功能，等同于xml配置方式的 <tx:annotation-driven />
 * @Parameter : @ComponentScan 组件扫描指令。扫描指定路径下的包，把符合扫描规则的类自动装配到spring的bean容器中。相当于xml配置方式的 <context:component-scan>。
 **/
@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@ComponentScan("com.spring.data.service")
@Slf4j
public class DeclarativeTransactionDemoApplication implements CommandLineRunner {

    private final FooService fooService;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DeclarativeTransactionDemoApplication(FooService fooService, JdbcTemplate jdbcTemplate) {
        this.fooService = fooService;
        this.jdbcTemplate = jdbcTemplate;
    }


    public static void main(String[] args) {
        SpringApplication.run( DeclarativeTransactionDemoApplication.class, args );
    }

    @Override
    public void run(String... args) throws Exception {
        fooService.insertRecord();
        //打印总记录数，此方法未回滚，返回一条对应结果
        log.info( "aaa {}",
                jdbcTemplate
                        .queryForObject( "SELECT COUNT(*) FROM FOO WHERE BAR='aaa'", Long.class ) );
        try {
            fooService.insertThenRollback();
        } catch (RollbackException e) {
            //打印总记录数，此方法进行回滚，返回零条对应结果
            log.info( "bbb {}",
                    jdbcTemplate
                            .queryForObject( "SELECT COUNT(*) FROM FOO WHERE BAR='bbb'", Long.class ) );
        }
        try {
            fooService.invokeInsertThenRollback();
        } catch (RollbackException e) {
            //打印总记录数，此方法未回滚，返回一条对应结果
            log.info( "bbb {}",
                    jdbcTemplate
                            .queryForObject( "SELECT COUNT(*) FROM FOO WHERE BAR='bbb'", Long.class ) );
        }
    }
}