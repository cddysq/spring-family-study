package com.yileaf.filepassword.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpStatus;
import com.yileaf.filepassword.config.SystemParams;
import com.yileaf.filepassword.constant.Messages;
import com.yileaf.filepassword.entity.NormalLog;
import com.yileaf.filepassword.model.Result;
import com.yileaf.filepassword.model.Ssm;
import com.yileaf.filepassword.service.SsmPasswordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 解压密码请求接口
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/3 21:15
 **/
@Api(tags = "解压密码获取")
@RestController
public class PasswordController {
    @Resource
    private SystemParams systemParams;
    @Resource
    private SsmPasswordService ssmPasswordService;
    @Resource
    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "获取docker解压密码")
    @GetMapping("/docker")
    public Result returnDockerPassword(@ApiParam("用户名") @RequestParam(defaultValue = "") String username,
                                       @ApiParam("请求密码") @RequestParam(defaultValue = "") String password) {
        if (username.equalsIgnoreCase( systemParams.getLoginUsername() ) && password.equalsIgnoreCase( systemParams.getDockerPassword() )) {
            return Result.success(
                    "密文=" + Base64.encode( systemParams.getLoginUsername() ),
                    Messages.DOCKER_PASSWORD_OK
            );
        }
        return Result.error( HttpStatus.HTTP_OK, Messages.CHECK_PASSWORD_ERROR );
    }

    @ApiOperation("获取ssm解压密码")
    @PostMapping("/ssm")
    public Result returnSsmPassword(@Validated @RequestBody Ssm ssm) {
        boolean flag = ssmPasswordService.checkSsmLoginNameAndPassword( ssm );
        if (flag) {
            return Result.success(
                    "密文=" + Base64.encode( systemParams.getSsmPassword() ),
                    Messages.SSM_PASSWORD_OK );
        }
        return Result.error( HttpStatus.HTTP_OK, Messages.CHECK_PASSWORD_ERROR );
    }

    /**
     * 查询所有正常日志
     *
     * @return 正常日志集合
     */
    @ApiOperation(value = "查询mongo日志", hidden = true)
    @GetMapping("/findAll")
    public List<NormalLog> findAll() {
        return mongoTemplate.findAll( NormalLog.class );
    }

}