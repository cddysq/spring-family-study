## Spring-Boot 声明式事务演示

> 声明式事务其实是通过AOP(面向切面编程)使用动态代理机制，在方法的前后进行增强从而实现事务的操作

### 简述：

**使用 Spring 提供的 `Transactional` 注解开启事务，并设置 `rollbackFor` 属性，指定进行回滚的条件，查看数据的前后变化，观察事务的特性。**

#### 结合AOP的事务代理流程图如下：

![transactionworkflow](https://user-images.githubusercontent.com/34121589/70375318-6533a300-1937-11ea-8b23-d355504d946e.png)

#### 本次演示首次出现注解如下：

##### Spring 相关：

1.  `@EnableTransactionManagement`：启用Spring注解驱动的事务管理功能，等同于xml配置方式的 `<tx:annotation-driven />`。
2.  `@ComponentScan`：组件扫描指令。扫描指定路径下的包，把符合扫描规则的类自动装配到spring的bean容器中。相当于xml配置方式的 `<context:component-scan>`。
3.  `@Component`：泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注，用于将普通pojo实例化到spring容器中。
4.  `@Transactional`：基于AOP动态代理的机制实现事务。在类级别，此注释默认应用于声明类及其子类的所有方法。在方法则对应当前方法开启事务支持。