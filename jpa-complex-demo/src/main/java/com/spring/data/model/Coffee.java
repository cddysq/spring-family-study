package com.spring.data.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 咖啡实体类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/26 17:10
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "T_MENU")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 514952953776866678L;

    private String name;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    // 将数字货币进行转换然后映射到对应字段
    private Money price;
}
