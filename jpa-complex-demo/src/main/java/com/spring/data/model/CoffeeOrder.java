package com.spring.data.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 咖啡订单实体类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/26 17:12
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "T_ORDER")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeOrder extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2017824835750504616L;

    private String customer;

    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderState state;

}