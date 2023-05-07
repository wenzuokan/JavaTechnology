package com.guigu.mybatis.mapper;

import com.guigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Drew
 * @create 2023-03
 */
public interface SpecialSQLMapper {

    /**
     * 通过用户名模糊查询
     * @param mohu
     * @return
     */
    List<User> getUserByLike(@Param("mohu") String mohu);


    /**
     * 批量删除
     */
    void deleteMoreUser(@Param("ids")String id);

    /**
     * 动态设置表名，查询用户信息
     * @param tableName
     * @return
     */
    List<User> getUserList(@Param("tableName") String tableName);

    /**
     * 添加用户信息并获取自增主键
     * @param user
     */
    void insertUser(User user);
}
