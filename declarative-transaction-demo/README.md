## Spring-Boot 声明式事务演示

> 声明式事务其实是通过AOP(面向切面编程)使用动态代理机制，在方法的前后进行增强从而实现事务的操作

**使用 Spring 提供的 `Transactional` 注解开启事务，并设置 `rollbackFor` 属性，指定进行回滚的条件，查看数据的前后变化，观察事务的特性。**

**结合AOP的事务代理流程图如下：**

![transactionworkflow](https://user-images.githubusercontent.com/34121589/70375318-6533a300-1937-11ea-8b23-d355504d946e.png)

**本次演示首次出现注解如下：**

1.  `@EnableTransactionManagement`
2.  `@ComponentScan`
3.  `@Component`
4.  `@Transactional`




