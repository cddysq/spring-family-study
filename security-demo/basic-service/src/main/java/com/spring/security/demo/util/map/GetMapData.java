package com.spring.security.demo.util.map;

import lombok.Data;

/**
 * 查询电子围栏数据封装类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/9 12:07
 */
@Data
public class GetMapData {
    /**
     * 围栏id
     */
    private String id;

    /**
     * 围栏全局id
     */
    private String gid;

    /**
     * 围栏名称
     */
    private String name;

    /**
     * 当前页码 默认1
     */
    private Integer page_no;

    /**
     * 每页数量 默认20
     */
    private Integer page_size;

    /**
     * 围栏激活状态
     */
    private Boolean enable;

    /**
     * 按创建时间查询的起始时间
     * <p>
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    private String start_time;

    /**
     * 按创建时间查询的结束时间
     * <p>
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    private String end_time;
}