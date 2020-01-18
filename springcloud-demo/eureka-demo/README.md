## Eureka 服务注册中心

### 简述：

**Spring Cloud demo 的所有服务都将注册到此服务注册中心上，服务之间的调用从此将通过注册中心进行获取**。

#### 本次演示首次出现注解如下：

1. `@EnableEurekaServer`：声明当前应用为Eureka服务注册中心。