package com.yileaf.filepassword.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 异常日志实体
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/6 19:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ExceptionLog implements Serializable {
    private static final long serialVersionUID = 5382510699649524705L;
    @Id
    private String id;

    /**
     * 异常信息json
     */
    private String exceptionJson;

    /**
     * 异常信息
     */
    private String exceptionMessage;

    /**
     * 异常产生时间
     */
    private Date happenTime;
}
