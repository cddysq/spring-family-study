package com.spring.data;

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
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/22 14:38
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
        // 打印总记录数，此方法未回滚，返回一条对应结果
        log.info( "aaa {}",
                jdbcTemplate
                        .queryForObject( "SELECT COUNT(*) FROM FOO WHERE BAR='aaa'", Long.class ) );
        try {
            fooService.insertThenRollback();
        } catch (RollbackException e) {
            // 打印总记录数，此方法进行回滚，返回零条对应结果
            log.info( "bbb {}",
                    jdbcTemplate
                            .queryForObject( "SELECT COUNT(*) FROM FOO WHERE BAR='bbb'", Long.class ) );
        }
        try {
            fooService.invokeInsertThenRollback();
        } catch (RollbackException e) {
            // 打印总记录数，此方法未回滚，返回一条对应结果
            log.info( "bbb {}",
                    jdbcTemplate
                            .queryForObject( "SELECT COUNT(*) FROM FOO WHERE BAR='bbb'", Long.class ) );
        }
    }
}