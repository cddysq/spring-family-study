## Spring-Boot 整合 ElasticSearch

### 简述：

**自定义接口继承 Spring 提供的 ElasticsearchRepository 类，实现对 ElasticSearch 数据的CRUD。**

#### 本次演示首次出现注解如下：

##### Es 相关：

| 注解名| 解释 | 常用属性 |
| ---- | ---- | ---- |
| `@Document` | 作用在类，标记实体类为文档对象 | `indexName`：对应索引库名称 |
|        |      | `type`：对应在索引库中的类型 |
|     |      | `shards`：分片数量，默认5 |
|  | | `replicas`：副本数量，默认1 |
| `@Id` | 作用在成员变量，标记一个字段作为id主键 | |
| `@Field` | 作用在成员变量，标记为文档的字段，用于配置映射属性 | `type`：字段类型，取值是枚举为`FieldType` |
|  | | `index`：是否索引，布尔类型，默认为`true` |
|  | | `store`：是否存储，布尔类型，默认为`false` |
|  | | `analyzer`：分词器名称 |
