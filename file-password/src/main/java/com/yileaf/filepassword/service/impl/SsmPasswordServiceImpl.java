package com.yileaf.filepassword.service.impl;

import cn.hutool.core.convert.Convert;
import com.yileaf.filepassword.config.SystemParams;
import com.yileaf.filepassword.model.Ssm;
import com.yileaf.filepassword.service.SsmPasswordService;
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

        return false;
    }
}
