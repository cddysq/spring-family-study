package com.yileaf.filepassword.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件账户密码配置
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/3 21:28
 */
@Data
@Component
@ConfigurationProperties(prefix = "config")
public class SystemParams {
    /**
     * docker解压文件请求账户
     */
    private String loginUsername;

    /**
     * docker解压文件解压密码
     */
    private String dockerPassword;

    /**
     * ssm解压文件密码
     */
    private String ssmPassword;
}