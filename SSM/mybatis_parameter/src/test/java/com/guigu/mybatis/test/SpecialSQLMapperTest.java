package com.guigu.mybatis.test;

import com.guigu.mybatis.mapper.SelectMapper;
import com.guigu.mybatis.mapper.SpecialSQLMapper;
import com.guigu.mybatis.pojo.User;
import com.guigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import sun.net.ProgressSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Drew
 * @create 2023-03
 */
public class SpecialSQLMapperTest {

    @Test
    public void testGetUserByLike(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        List<User> list = mapper.getUserByLike("a");
        list.forEach(System.out::println);
    }

    public void testJDBC(){
        try {
            Class.forName("");
            Connection connection= DriverManager.getConnection("","","");
            /*String sql="select * from t_user where username like '%?%'";
            PreparedStatement ps=connection.prepareStatement(sql);*/
            String sql="insert into t_user values()";
            PreparedStatement ps=connection.prepareStatement(sql,1);
            ps.executeUpdate();
            ResultSet resultSet=ps.getGeneratedKeys();
            resultSet.next();
            int id=resultSet.getInt(1);
            //ps.setString(1,"a");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteMoreUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper=sqlSession.getMapper(SpecialSQLMapper.class);
        mapper.deleteMoreUser("3,4");
    }

    @Test
    public void testGetUserList(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper=sqlSession.getMapper(SpecialSQLMapper.class);
        List<User> list = mapper.getUserList("t_user");
        list.forEach(System.out::println);
    }
}
