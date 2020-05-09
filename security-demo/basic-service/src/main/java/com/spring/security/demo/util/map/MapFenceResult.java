package com.spring.security.demo.util.map;

import lombok.Data;

/**
 * 插入电子围栏结果解析类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/9 9:38
 */
@Data
public class MapFenceResult {
    /**
     * 返回数据内容消息体
     */
    private DataBean data;
    /**
     * 错误码
     */
    private int errcode;
    private Object errdetail;
    /**
     * 错误描述信息
     */
    private String errmsg;
    private Object ext;

    @Data
    public static class DataBean {
        /**
         * 围栏全局id
         */
        private String gid;
        /**
         * 围栏id为预留字段，暂未使用，固定返回0。
         */
        private String id;
        /**
         * 状态说明信息
         */
        private String message;
        /**
         * 状态值
         */
        private Integer status;
    }
}