package com.spring.data.multidatasourcedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 多数据源启动类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/20 19:10
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class}) // 排除Spring Boot数据自动装配依赖
@Slf4j
public class MultiDatasourceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run( MultiDatasourceDemoApplication.class, args );
    }

    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties() {
        // 将指定配置进行封装返回一个新的数据源基类
        return new DataSourceProperties();
    }

    @Bean
    public DataSource fooDataSource() {
        DataSourceProperties dataSourceProperties = fooDataSourceProperties();
        // 日志打印数据源连接
        log.info( "foo datasource：{}", dataSourceProperties.getUrl() );
        // 初始化数据源并返回
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    /**
     * 创建一个事务
     *
     * @param fooDataSource 指定交由此事务管理的数据源
     * @return 自定义的事务
     */
    @Bean
    @Resource
    public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
        // 将数据源交由事务管理
        return new DataSourceTransactionManager( fooDataSource );
    }

    @Bean
    @ConfigurationProperties("zi.datasource")
    public DataSourceProperties ziDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource ziDataSource() {
        DataSourceProperties dataSourceProperties = ziDataSourceProperties();
        log.info( "zi datasource：{}", dataSourceProperties.getUrl() );
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager ziTxManager(DataSource ziDataSource) {
        return new DataSourceTransactionManager( ziDataSource );
    }

}