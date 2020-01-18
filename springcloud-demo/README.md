## Spring Cloud 体系案例

### 简述：

- 使用`Eureka`做为服务注册中心
- `Ribbon`用做负载均衡
- `Hystrix`作为熔断器
- `Feign`提供微服务与微服务之间的调用
- `Gateway`提供网关路由功能
- `Config`统一管理配置
- `Bus`配合`rabbit`实现无需重启刷新配置

#### 整体架构流程

![架构图](https://s2.ax1x.com/2020/01/18/1pXIht.png)