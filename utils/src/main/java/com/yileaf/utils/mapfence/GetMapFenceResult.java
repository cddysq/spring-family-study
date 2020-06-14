package com.yileaf.utils.mapfence;

import lombok.Data;

import java.util.List;

/**
 * 查询电子围栏结果解析类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/9 12:16
 */
@Data
public class GetMapFenceResult {
    /**
     * 返回内容消息体
     */
    private DataBean data;
    /**
     * 错误码
     */
    private int errcode;
    private Object errdetail;
    /**
     * 错误信息
     */
    private String errmsg;
    private Object ext;

    @Data
    public static class DataBean {
        /**
         * 当前页码
         */
        private int page_no;
        /**
         * 每页记录数
         */
        private int page_size;
        /**
         * 总记录数
         */
        private int total_record;
        /**
         * 围栏列表
         */
        private List<RsListBean> rs_list;

        @Data
        public static class RsListBean {
            /**
             * 围栏所在地区adcode
             */
            private String adcode;
            /**
             * 围栏监控触发条件
             */
            private String alert_condition;
            /**
             * 圆形围栏中心点
             */
            private String center;
            /**
             * 围栏创建时间
             */
            private String create_time;
            /**
             * 围栏激活状态
             */
            private boolean enable;
            /**
             * 指定日期
             */
            private String fixed_date;
            /**
             * 围栏全局id
             */
            private String gid;
            /**
             * 围栏id
             */
            private String id;
            /**
             * 开发者key
             */
            private String key;
            /**
             * 围栏名称
             */
            private String name;
            /**
             * 多边形围栏点
             */
            private String points;
            /**
             * 圆形围栏半径
             */
            private double radius;
            /**
             * 一周内围栏监控的重复星期
             */
            private String repeat;
            /**
             * 一天内监控的具体时段
             */
            private String time;
            /**
             * 过期日期
             */
            private String valid_time;
        }
    }
}