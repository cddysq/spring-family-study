package com.spring.data.service.impl;

import com.spring.data.exception.RollbackException;
import com.spring.data.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Haotian
 * @Date: 2019/12/7 16:35
 */
@Component
public class FooServiceImpl implements FooService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FooServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRecord() {
        //开启事务，执行数据插入
        jdbcTemplate.execute( "INSERT INTO FOO(BAR) VALUES('aaa')" );
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        //开启事务，执行数据插入，抛出事务指定的回滚数据异常
        jdbcTemplate.execute( "INSERT INTO FOO(BAR) VALUES('bbb')" );
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        //直接调用会抛出异常的方法，由于此方法并没有开启事务，数据不会进行回滚
        insertThenRollback();
    }
}