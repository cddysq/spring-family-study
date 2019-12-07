package com.spring.data.programmatictransactiondemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Author: Haotian
 * @Date: 2019/12/7 15:10
 **/
@SpringBootApplication
@Slf4j
public class ProgrammaticTransactionDemoApplication implements CommandLineRunner {

    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProgrammaticTransactionDemoApplication(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run( ProgrammaticTransactionDemoApplication.class, args );
    }

    @Override
    public void run(String... args) {
        //打印事务开启前表中记录总数
        log.info( "COUNT BEFORE TRANSACTION：{}", getCount() );
        //开启事务，使用无结果的事务回调
        transactionTemplate.execute( new  TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                //执行插入数据
                jdbcTemplate.execute( "INSERT INTO FOO (ID,BAR) VALUES(1,'test') " );
                jdbcTemplate.execute( "INSERT INTO FOO (ID,BAR) VALUES(2,'test') " );
                //打印插入数据后，但还未进行事务回滚，表中记录总数
                log.info( "COUNT IN TRANSACTION：{}", getCount() );
                //设置事务回滚
                transactionStatus.setRollbackOnly();
            }
        } );
        //打印事务回滚后表中记录总数
        log.info( "COUNT AFTER TRANSACTION：{}", getCount() );
    }

    /**
     * 统计表中数据总数
     *
     * @return 当前表总记录条数
     */
    private long getCount() {
        return (long) jdbcTemplate.queryForList( "SELECT COUNT(*) AS length FROM FOO" )
                .get( 0 ).get( "length" );
    }
}