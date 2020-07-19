package com.yileaf.es.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 用户实体类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/19 22:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "index-boot", type = "girl")
public class Girl {
    @Id
    @Field(type = FieldType.Long, store = true)
    private long id;
    @Field(type = FieldType.Text, store = true, analyzer = "ik_smart")
    private String name;
    @Field(type = FieldType.Text, store = true, analyzer = "ik_smart")
    private String content;
}