package com.spring.data.repository;

import com.spring.data.model.CoffeeOrder;

import java.util.List;

/**
 * @Author: Haotian
 * @Date: 2019/12/10 20:17
 * @Description: 咖啡订单接口
 */
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    /**
     * 根据客户订单和Id查找咖啡订单
     *
     * @param customer 客户信息
     * @return 咖啡订单
     */
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    /**
     * 根据名称查找咖啡订单
     *
     * @param name 订单名
     * @return 咖啡订单
     */
    List<CoffeeOrder> findByItems_Name(String name);


}
