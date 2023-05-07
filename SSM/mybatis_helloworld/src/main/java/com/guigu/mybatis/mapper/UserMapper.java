package com.guigu.mybatis.mapper;

import com.guigu.mybatis.pojo.User;

import java.util.List;

/**
 * @author Drew
 * @create 2023-03
 */
public interface UserMapper {
    //添加用户信息
    int insertUser();

    //修改用户信息
    void updateUser();

    //删除用户信息
    void deleteUser();

    //根据id查询信息
    User getUserByID();

    //查询所有用户信息
    List<User> getAllUser();
}
