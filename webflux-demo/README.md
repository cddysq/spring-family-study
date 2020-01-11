## Spring-Boot WebFlux  演示

### 简述：

**自定义接口继承`Spring data`提供的`ReactiveCrudRepository`接口，对mongodb进行响应式的crud。Mono用于封装单条数据，Flux用于封装多条数据。**

#### 注意事项：

- 需要修改`application.yml`中 mongo 配置的对应链接端口使用数据库及用户名密码。

#### 本次演示首次出现注解如下：

##### Spring  data mongodb 相关：

1. `@Document`：标识要持久化到MongoDB的哪个表，默认实体类型
2. `@Indexed`：标注该字段为MongoDB的索引字段