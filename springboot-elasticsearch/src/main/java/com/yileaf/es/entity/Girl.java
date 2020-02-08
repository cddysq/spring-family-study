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
 * @Author: Haotian
 * @Date: 2020/2/8 18:54
 * @Description: 用户实体类
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