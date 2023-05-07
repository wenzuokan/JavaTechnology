package com.guigu.mybatis.test;

import com.guigu.mybatis.mapper.SelectMapper;
import com.guigu.mybatis.pojo.User;
import com.guigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author Drew
 * @create 2023-03
 */
public class SelectMapperTest {

    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper=sqlSession.getMapper(SelectMapper.class);
        User user=mapper.getUserById(2);
        System.out.println(user);
    }

    @Test
    public void testGetCount(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper=sqlSession.getMapper(SelectMapper.class);
        Integer count = mapper.getCount();
        System.out.println(count);
    }


}
