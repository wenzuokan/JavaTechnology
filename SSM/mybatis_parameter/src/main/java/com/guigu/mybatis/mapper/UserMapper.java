package com.guigu.mybatis.mapper;

import com.guigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Drew
 * @create 2023-03
 */
public interface UserMapper {

    /*
    根据用户名查询用户信息
     */
    User getUserByUsername(String username);


    /*
    验证登录
     */
    User checkLogin(String username, String password);

    void insertUser(User user);

    /**
     * 验证登录（使用@Param）
     * @param username
     * @param password
     * @return
     */
    User checkLoginByParam(@Param("username") String username,@Param("password") String password);

}
