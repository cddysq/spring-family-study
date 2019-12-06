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
 * @Author: Haotian
 * @Date: 2019/12/6 19:31
 * @Parameter : @Bean 注解用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理。
 * 产生这个Bean对象的方法Spring只会调用一次，随后这个Spring将会将这个Bean对象放在自己的IOC容器中。
 * 默认bean的名称就是其方法名。但是也可以使用name属性指定其名称。
 * @Parameter : @ConfigurationProperties 注解的作用是可以根据一个前缀读取配置文件的数据，只要属性名一致就能将数据自动封装。
 * @Parameter : @Resource 资源注释标记.将注释应用于字段或方法时，容器将在组件初始化时将请求的资源的实例注入到应用程序组件中。如果将注释应用于组件类，则该注释声明应用程序将在运行时查找的资源。
 **/

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class}) //排除Spring Boot数据自动装配依赖
@Slf4j
public class MultiDatasourceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run( MultiDatasourceDemoApplication.class, args );
    }

    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties() {
        //将指定配置进行封装返回一个新的数据源基类
        return new DataSourceProperties();
    }

    @Bean
    public DataSource fooDataSource() {
        DataSourceProperties dataSourceProperties = fooDataSourceProperties();
        //日志打印数据源连接
        log.info( "foo datasource：{}", dataSourceProperties.getUrl() );
        //初始化数据源并返回
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    /**
     * 创建一个事务
     *
     * @param fooDataSource 指定数据源
     * @return 自定义的事务
     */
    @Bean
    @Resource
    public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
        //将数据源交由事务管理
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
