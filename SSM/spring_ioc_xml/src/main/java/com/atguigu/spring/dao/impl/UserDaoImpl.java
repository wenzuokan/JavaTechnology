package com.atguigu.spring.dao.impl;

import com.atguigu.spring.dao.UserDao;

/**
 * @author Drew
 * @create 2023-03
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void saveDao() {
        System.out.println("保存成功");
    }
}
