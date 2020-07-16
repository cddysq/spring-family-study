package com.yileaf.filepassword.service.impl;

import cn.hutool.core.convert.Convert;
import com.yileaf.filepassword.config.SystemParams;
import com.yileaf.filepassword.model.Ssm;
import com.yileaf.filepassword.service.SsmPasswordService;
import com.yileaf.filepassword.utils.ChineseCharToEn;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.stream.Collectors;

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
    public boolean checkSsmLoginNameAndPassword(Ssm ssm) {
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
        String name = "起名字太难了";
        return Seq.rangeClosed( 'A', 'Z' )
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
                .distinct()
                .skip( 1 )
                .limit( 3 )
                .collect( Collectors.joining( "HH" ) )
                .substring( name.length() + 1, 1 << 4 )
                .toLowerCase();
    }

}
