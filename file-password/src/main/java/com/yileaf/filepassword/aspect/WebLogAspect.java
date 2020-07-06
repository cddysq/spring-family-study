package com.yileaf.filepassword.aspect;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yileaf.filepassword.entity.ExceptionLog;
import com.yileaf.filepassword.entity.NormalLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 接口访问日志记录
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/5 20:07
 */
@Aspect
@Order(1)
@Slf4j
@Component
public class WebLogAspect {
    @Resource
    private MongoTemplate mongoTemplate;

    private final NormalLog normalLog = new NormalLog();

    private Date startTime;

    /**
     * 配置织入点，记录请求接口下的所有方法
     */
    @Pointcut("execution(* com.yileaf.filepassword.controller..*.*(..))")
    public void logPointCut() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint 切面对象相关信息
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        normalLog.setMethodName( className + "." + methodName + "()" );
        startTime = new Date();
        normalLog.setStartTime( DateUtil.offsetHour( startTime, 8 ) );
        log.info( "===前置通知==={}", className + "." + methodName + "()" );
    }

    /**
     * 返回通知,在目标方法正常结束之后执行
     *
     * @param returnValue 返回值
     */
    @AfterReturning(value = "logPointCut()", returning = "returnValue")
    public void doAfterReturning(Object returnValue) {
        Date endTime = new Date();
        normalLog.setEndTime( DateUtil.offsetHour( endTime, 8 ) );
        normalLog.setFinishTime( DateUtil.between( endTime, startTime, DateUnit.MS ) + "毫秒" );
        normalLog.setReturnData( JSON.toJSONString( returnValue, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue ) );
        log.info( "===返回通知==={}", JSON.toJSONString( returnValue, SerializerFeature.PrettyFormat ) );
        // mongo主键异常处理
        if (StrUtil.isNotEmpty( normalLog.getId() )) {
            normalLog.setId( null );
        }
        mongoTemplate.insert( normalLog );
    }

    /**
     * 异常通知，在目标方法非正常结束，发生异常或者抛出异常时执行
     *
     * @param e 异常信息对象
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        ExceptionLog exceptionLog = ExceptionLog.builder()
                .exceptionJson( JSON.toJSONString( e, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue ) )
                .exceptionMessage( e.getMessage() )
                .happenTime( DateUtil.offsetHour( new Date(), 8 ) )
                .build();
        mongoTemplate.save( exceptionLog );
        log.error( "exception {}", e.getMessage() );
    }

}
