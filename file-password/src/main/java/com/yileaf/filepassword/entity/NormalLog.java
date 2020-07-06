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
 * mongo 正常日志实体
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/6 17:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class NormalLog implements Serializable {
    private static final long serialVersionUID = 3148115798059817517L;

    /**
     * 注解属性id为ID
     */
    @Id
    private String id;

    /**
     * 请求ip
     */
    private String ip;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求方式 get post .
     */
    private String way;

    /**
     * 请求方法名
     */
    private String methodName;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 接口请求开始时间
     */
    private Date startTime;

    /**
     * 接口请求返回时间
     */
    private Date endTime;

    /**
     * 请求耗时
     */
    private String finishTime;

    /**
     * 接口返回值
     */
    private String returnData;
}