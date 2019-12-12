## Spring-Boot  JPA  演示

**本次演示首次出现注解如下：**

**Lombok 相关：**

1. `@Data`：注解在类上，相当于`@ToString`，`@EqualsAndHashCode`， `@RequiredArgsConstructor`，`@Getter`和`@Setter`五个注解的功能。
2. `@ToString`：用于生成`toString()`方法。只能用于类级别。`static`修饰的变量不能生成在`toString()`方法中。
3. `@NoArgsConstructor`：生成一个无参构造器。
4. `@AllArgsConstructor`：生成一个除`static`修饰字段外的全参构造器。
5. `@Builder`：被注解的类加个构造者模式。

**JPA 相关：**

1. `@MappedSuperclass`：标注的类将不是一个完整的实体类，不能再标注`@Entity`或`@Table`注解。他将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。
2. `@Entity`：表明该类为一个实体类,与数据库表进行映射。
3. `@Table(name = "表名")`：该标注与`@Entity`注解并列使用,常用选项是 name，用于指明数据库的表名。
4. `@Type`：定义Hibernate类型映射关系。
5. `@Id`：用于指定表的主键。
6. `@GeneratedValue`：用于标注主键的生成策略，通过`strategy`属性指定。默认情况下，JPA 自动选择一个最适合底层数据库的主键生成策略：`SqlServer`对应`identity`，`MySQL` 对应 `auto increment`。 该注释可以与`Id`注释一起应用于实体或映射超类的主键属性或字段。
7. `@Column`：用于标识实体类中属性与数据表中字段的对应关系。
8. `@Enumerated`：指定属性或字段作为枚举类型持久存储。
9. `@CreationTimestamp`：自动记录第一次创建的时间。
10. `@UpdateTimestamp`：自动记录更新的时间。
11. `@ManyToMany`：指定表的关系为多对多。
12. `@JoinTable(name = "表名")`：配置连接表，默认为两个关联的主实体表的连接名称，用下划线分隔。

**Spring 相关：**

1. `@NoRepositoryBean`：告诉Spring不需要为这个repository创建一个bean。
2. `@EnableJpaRepositories`：开启JPA存储库扫描，默认情况下，将扫描Spring数据存储库带注释的配置类包。
