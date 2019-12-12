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