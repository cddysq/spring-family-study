package com.spring.security.demo.mapper;

import com.spring.security.demo.config.MyUserDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户授权信息服务数据接口
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/28 17:31
 */
public interface UserDetailsServiceMapper {
    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名或用户手机号
     * @return 用户信息
     */
    @Select("SELECT username,password,enabled\n" +
            "FROM sys_user u\n" +
            "WHERE u.username = #{username} or u.phone = #{username}")
    MyUserDetails findByUserName(@Param("username") String username);

    /**
     * 根据用户名查询用户角色
     *
     * @param username 用户名或用户手机号
     * @return 用户角色列表
     */
    @Select("SELECT role_code\n" +
            "FROM sys_role r\n" +
            "LEFT JOIN sys_user_role ur ON r.id = ur.role_id\n" +
            "LEFT JOIN sys_user u ON u.id = ur.user_id\n" +
            "WHERE u.username = #{username} or u.phone = #{username}")
    List<String> findRoleByUserName(@Param("username") String username);

    /**
     * 根据用户角色查询用户权限
     *
     * @param roleCodes 用户角色
     * @return 用户权限
     */
    @Select({
            "<script>",
            "SELECT url ",
            "FROM sys_menu m ",
            "LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id ",
            "LEFT JOIN sys_role r ON r.id = rm.role_id ",
            "WHERE r.role_code IN ",
            "<foreach collection='roleCodes' item='roleCode' open='(' separator=',' close=')'>",
            "#{roleCode}",
            "</foreach>",
            "</script>"
    })
    List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);

    @Update({" UPDATE sys_user u" +
            "  SET u.enabled = #{enabled}" +
            " WHERE u.username = #{username} or u.phone = #{username}"})
    int updateEnabledByUsername(MyUserDetails myUserDetails);
}