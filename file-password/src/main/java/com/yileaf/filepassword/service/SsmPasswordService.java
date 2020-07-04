package com.yileaf.filepassword.service;

import com.yileaf.filepassword.model.Ssm;

/**
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/4 23:19
 */
public interface SsmPasswordService {
    /**
     * 获取ssm阶段解压密码
     *
     * @param ssm 请求信息
     * @return true:成功 false:失败
     */
    boolean getSsmPassword(Ssm ssm);
}
