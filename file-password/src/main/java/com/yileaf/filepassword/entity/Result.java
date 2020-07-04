package com.yileaf.filepassword.entity;

import cn.hutool.http.HttpStatus;
import com.yileaf.filepassword.constant.Messages;

import java.util.LinkedHashMap;

/**
 * 返回结果对象
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/3 21:16
 **/
public class Result extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 7575364393972626005L;

    /**
     * 默认请求失败
     */
    public static Result error() {
        return error( false, HttpStatus.HTTP_INTERNAL_ERROR, Messages.BAD_ERROR, "" );
    }

    /**
     * 请求失败
     *
     * @param code    状态码
     * @param message 错误提示
     * @return 消息对象
     */
    public static Result error(int code, String message) {
        return error( false, code, message, "" );
    }

    /**
     * 请求成功
     *
     * @param data    返回数据
     * @param message 返回消息
     * @return 消息对象
     */
    public static Result success(Object data, String message) {
        return success( true, HttpStatus.HTTP_OK, data, message );
    }

    /**
     * 请求失败
     *
     * @param flag    请求是否成功
     * @param code    状态码
     * @param message 返回消息
     * @param data    返回数据
     * @return 消息对象
     */
    public static Result error(boolean flag, int code, String message, Object data) {
        Result json = new Result();
        json.put( "flag", flag );
        json.put( "code", code );
        json.put( "msg", message );
        json.put( "data", data );
        return json;
    }

    /**
     * 请求成功
     *
     * @param flag    请求是否成功
     * @param code    状态码
     * @param data    返回数据
     * @param message 返回消息
     * @return 消息对象
     */
    public static Result success(boolean flag, int code, Object data, String message) {
        Result json = new Result();
        json.put( "flag", flag );
        json.put( "code", code );
        json.put( "data", data );
        json.put( "msg", message );
        return json;
    }

    @Override
    public Result put(String key, Object value) {
        super.put( key, value );
        return this;
    }

}