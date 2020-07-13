package com.yileaf.filepassword.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.yileaf.filepassword.config.SystemParams;
import com.yileaf.filepassword.model.Ssm;
import com.yileaf.filepassword.service.SsmPasswordService;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/4 23:21
 */
@Service
public class SsmPasswordServiceImpl implements SsmPasswordService {
    @Resource
    private SystemParams systemParams;

    @Override
    public boolean getSsmPassword(Ssm ssm) {
        // 24进制转换后的群号
        String loginName = Integer.toString( Convert.toInt( systemParams.getLoginUsername() ), 24 );
        String password = ssm.getPassword();
        // 校验用户名密码
        return loginName.equals( ssm.getUsername() ) && password.equals( this.generatePassword() );
    }

    /**
     * 生成密码
     *
     * @return 密码
     */
    public String generatePassword() {
        // 生成26个大写英文字母
        Seq.rangeClosed( 'A', 'Z' )
                .map( Object::toString )
                .toList();
        return "";
    }
}
