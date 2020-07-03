package com.yileaf.filepassword;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * docker 获取解压密码测试
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/3 21:25
 **/
public class DockerLoginPasswordTest {
    @Test
    public void loginDockerPassword() {
        int number = 1 << 4;
        String str = "A a 0";
        StringBuilder strBuilder = new StringBuilder();
        Stream.of( str.split( " " ) )
                .parallel()
                .flatMap( s -> s.chars().boxed() )
                .forEachOrdered( strBuilder::append );
        strBuilder.append( number );
        //登录密码为打印输出内容
        System.out.println( strBuilder.toString() );
    }
}