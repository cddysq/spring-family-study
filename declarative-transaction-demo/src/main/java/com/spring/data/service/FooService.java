package com.spring.data.service;

import com.spring.data.exception.RollbackException;

/**
 * @Author: Haotian
 * @Date: 2019/12/7 16:32
 */
public interface FooService {
    /**
     * 插入数据
     */
    void insertRecord();

    /**
     * 插入数据后回滚
     *
     * @throws RollbackException 自定义的异常
     */
    void insertThenRollback() throws RollbackException;

    /**
     * 直接调用插入回滚方法
     *
     * @throws RollbackException 自定义的异常
     */

    void invokeInsertThenRollback() throws RollbackException;
}