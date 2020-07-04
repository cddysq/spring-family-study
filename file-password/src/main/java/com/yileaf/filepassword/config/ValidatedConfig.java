package com.yileaf.filepassword.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 自定义参数验证配置类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/4 22:40
 */
@Configuration
public class ValidatedConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        // 设置validator模式为快速失败返回
        postProcessor.setValidator( validator() );
        return postProcessor;
    }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                // 设置validator模式为快速失败（只要有一个校验不通过就立即返回错误）
                .failFast( true )
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
