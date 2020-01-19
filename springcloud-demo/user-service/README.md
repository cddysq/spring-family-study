## 本Demo提供用户服务

### 简述：

**使用 `Mybatis Plus` 进行数据库数据的查询，对外提供服务访问接口。yml 配置在注册中心通过config-service从git远程仓库拉取**。

#### 本次演示首次出现注解如下：

1. `@EnableDiscoveryClient`：开启Eureka客户端发现功能，才能与Eureka注册中心建立关系。
2. `@RefreshScope`：对类接口开启刷新功能。