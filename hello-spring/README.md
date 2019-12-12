## Spring-Boot 的第一个程序，当然是跑个 Hello-World

#### 本次演示首次出现注解如下：

1. `@SpringBootApplication`：是一个组合注解，用于快捷配置启动类,等同于`@Configuration`+`@EnableAutoConfiguration`+`@ComponentScan`的合集。
2. `@RestController`：相当于`@Controller` + `@ResponseBody` 的作用结合,将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区。
3. `@RequestMapping`：用于建立请求URL和处理请求方法之间的对应关系。

