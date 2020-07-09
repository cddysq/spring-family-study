package com.yileaf.filepassword;

import org.junit.Test;

/**
 * ssm 压缩文件相关测试
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/9 21:28
 */
public class SsmLoginPasswordTest {
    @Test
    public void demo() {
        int number = 186074301;
        // 任意进制转换
        System.out.println( number + "的二十四进制是:" + Integer.toString( number, 24 ) );
        String number2 = "n8k5fl";
        // 进制还原
        System.out.println( number + "还原后的进制:" + Integer.parseInt( number2, 24 ) );
    }
}
