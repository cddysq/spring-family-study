package com.yileaf.utils.mapfence;

import lombok.Data;

/**
 * 插入电子围栏数据封装类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/9 9:53
 */
@Data
public class InsertMapFenceData {
    /**
     * 围栏名
     */
    private String name;
    /**
     * 多边形围栏坐标点，格式(lon1,lat1;lon2,lat2;lon3,lat3（3<=点个数<=5000）。多边形围栏外接圆半径最大为5000米。)
     */
    private String points;
    /**
     * 围栏监控状态
     * <p>
     * 默认：true
     */
    private Boolean enable;
    /**
     * 围栏有效截止日期，过期后不对此围栏进行维护（围栏数据过期删除）
     * <p>
     * 不能设定创建围栏时间点之前的日期；格式yyyy-MM-dd； 请设置2055年之前的日期
     * <p>
     * 默认：创建时间后90天
     */
    private String valid_time;
    /**
     * 一周内围栏监控日期的重复模式
     * <p>
     * 格式：用","或“;”隔开。
     * <p>
     * 样例："Mon,Sat" 表示周一和周六有效。
     * <p>
     * 星期简称如下（首字母大写）：Mon,Tues,Wed,Thur,Fri,Sat,Sun
     * <p>
     * repeat和fixed_date不能同时缺省或同时为空，二者所指出的监控日期为“或”关系
     */
    private String repeat;

    /**
     * 指定日期列表监控围栏
     * <p>
     * 格式"date1;date2;date3", date格式"yyyy-MM-dd"；
     * <p>
     * 最大个数180，不能设定过去日期
     * <p>
     * repeat和fixed_date不能同时缺省或同时为空，二者所指出的监控日期为“或”关系
     */
    private String fixed_date;

    /**
     * 一天内围栏监控时段
     * <p>
     * 拼接字符串格式如："startTime1,endTime1;startTime2,endTime2"；
     * <p>
     * 最大个数24;不可为空;范围00:00-23:59;时间段不可重叠；时间段长度>15min
     * <p>
     * 默认为00:00,23:59；
     */
    private String time;

    /**
     * 围栏描述信息
     */
    private String desc;

    /**
     * 配置触发围栏所需动作
     * <p>
     * 触发动作分号分割,enter;leave（进入、离开触发）
     */
    private String alert_condition;
}