package com.spring.data.repository;

import com.spring.data.model.CoffeeOrder;

import java.util.List;

/**
 * 咖啡订单接口
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/26 17:12
 **/
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    /**
     * 根据客户名查找订单通过id排序
     *
     * @param customer 客户信息
     * @return 咖啡订单
     */
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    /**
     * 根据咖啡名查找订单
     *
     * @param name 咖啡名
     * @return 咖啡订单
     */
    List<CoffeeOrder> findByItems_Name(String name);

}