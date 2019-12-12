package com.spring.data;

import com.spring.data.model.Coffee;
import com.spring.data.model.CoffeeOrder;
import com.spring.data.model.OrderState;
import com.spring.data.repository.CoffeeOrderRepository;
import com.spring.data.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Haotian
 * @Date: 2019/12/10 20:25
 * @Description: 启动类
 **/
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class JpaComplexDemoApplication implements ApplicationRunner {
    private final CoffeeRepository coffeeRepository;
    private final CoffeeOrderRepository orderRepository;

    public JpaComplexDemoApplication(CoffeeRepository coffeeRepository, CoffeeOrderRepository orderRepository) {
        this.coffeeRepository = coffeeRepository;
        this.orderRepository = orderRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run( JpaComplexDemoApplication.class, args );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(ApplicationArguments args) throws Exception {
        //ApplicationRunner接口与CommandLineRunner做的事情是一样的，执行时机都是在容器启动完成的时候进行执行。唯一不同的是ApplicationRunner会封装命令行参数，可以获得更详细的命令行参数。
        initOrder();
        findOrders();
    }

    private void initOrder() {
        //添加一杯名为拉铁的咖啡，设置其金额为30.0 CNY
        Coffee latte = Coffee.builder().name( "latte" )
                //指定货币单位为CNY，使用joda-money将double类型的数据转换为金额
                .price( Money.of( CurrencyUnit.of( "CNY" ), 30.0 ) )
                .build();
        //保存数据
        coffeeRepository.save( latte );
        //日志打印咖啡信息
        log.info( "Coffee：{}", latte );

        //添加一杯浓缩咖啡，设置其金额为20.0 CNY
        Coffee espresso = Coffee.builder().name( "espresso" )
                .price( Money.of( CurrencyUnit.of( "CNY" ), 20.0 ) )
                .build();
        coffeeRepository.save( espresso );
        log.info( "Coffee：{}", espresso );

        //创建咖啡订单，设置客户和订单状态
        CoffeeOrder order = CoffeeOrder.builder()
                //设置客户名
                .customer( "Li Lei" )
                //Collections.singletonList 创建不可变List的单个元素
                .items( Collections.singletonList( espresso ) )
                //设置订单状态
                .state( OrderState.INIT )
                .build();
        //保存订单
        orderRepository.save( order );
        //日志打印咖啡订单信息
        log.info( "Order：{}", order );

        //创建咖啡订单
        order = CoffeeOrder.builder()
                .customer( "Li Lei" )
                //Arrays.asList(strArray) 快速创建list 返回值是一个可变的集合
                .items( Arrays.asList( espresso, latte ) )
                .state( OrderState.INIT )
                .build();
        orderRepository.save( order );
        log.info( "Order：{}", order );
    }

    private void findOrders() {
        //查询所有的咖啡信息
        coffeeRepository
                //根据id降序排列
                .findAll( Sort.by( Sort.Direction.DESC, "id" ) )
                //打印日志信息
                .forEach( c -> log.info( "Loading {}", c ) );

        //查询指定数据
        List<CoffeeOrder> list = orderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info( "findTop3ByOrderByUpdateTimeDescIdAsc：{}", getJoinedOrderId( list ) );

        //查找客户名为Li Lei的订单id
        list = orderRepository.findByCustomerOrderById( "Li Lei" );
        //日志打印符合的订单id
        log.info( "findByCustomerOrderById: {}", getJoinedOrderId( list ) );

        // 不开启事务会因为没Session而报LazyInitializationException
        list.forEach( o -> {
            //打印客户名为Li Lei的订单单号
            log.info( "Order {}", o.getId() );
            //将客户名为Li Lei的订单信息进行打印
            o.getItems().forEach( i -> log.info( "Item {}", i ) );
        } );

        //查找订单名中为latte的订单
        list = orderRepository.findByItems_Name( "latte" );
        log.info( "findByItems_Name：{}", getJoinedOrderId( list ) );
    }

    /**
     * 将订单id取出，进行拼接
     */
    private String getJoinedOrderId(List<CoffeeOrder> list) {
        //通过list.stream()转换为流，.map进行类型转换，.collect进行收集
        return list.stream().map( o -> o.getId().toString() )
                .collect( Collectors.joining( "," ) );
    }
}