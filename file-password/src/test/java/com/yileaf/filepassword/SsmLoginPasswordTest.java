package com.yileaf.filepassword;

import cn.hutool.core.util.StrUtil;
import com.yileaf.filepassword.utils.ChineseCharToEn;
import org.jooq.lambda.Seq;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    public void test() {
        String name = "起名字太难了";
        String password = Seq.rangeClosed( 'A', 'Z' )
                .map( Object::toString )
                .toList().stream().map( m -> {
                    ChineseCharToEn convert = ChineseCharToEn.getInstance();
                    String tempStr = convert.getAllFirstLetter( name ).toUpperCase();
                    return m + tempStr;
                } ).filter( f ->
                        f.startsWith( "S" )
                                || f.startsWith( "A" )
                                || f.startsWith( "K" )
                                || f.startsWith( "U" )
                                || f.startsWith( "R" )
                                || f.startsWith( "A" ) )
                .skip( 1 )
                .limit( 3 )
                .collect( Collectors.joining( "HH" ) )
                .substring( name.length() + 1, 1 << 4 )
                .toLowerCase();

        System.out.println( password );
    }

}
