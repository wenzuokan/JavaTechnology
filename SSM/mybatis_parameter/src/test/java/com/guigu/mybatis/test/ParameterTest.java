package com.guigu.mybatis.test;


import com.guigu.mybatis.mapper.UserMapper;
import com.guigu.mybatis.pojo.User;
import com.guigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author Drew
 * @create 2023-03
 */
public class ParameterTest {

    @Test
    public void testGetUserByUsername(){
        SqlSession sqlSession= SqlSessionUtil.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user=mapper.getUserByUsername("admin");
        System.out.println(user);
    }

    @Test
    public void testCheckLogin(){
        SqlSession sqlSession= SqlSessionUtil.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user=mapper.checkLogin("admin","123456");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        SqlSession sqlSession= SqlSessionUtil.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user=new User(1,"root","123456",33,"女","123@qq.com");
        mapper.insertUser(user);
       // System.out.println(user);
    }

    @Test
    public void testCheckLoginByParam(){
        SqlSession sqlSession= SqlSessionUtil.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user=mapper.checkLoginByParam("root","123456");
        System.out.println(user);
    }
}
