package com.atguigu.spring.dao.impl;

import com.atguigu.spring.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @author Drew
 * @create 2023-03
 */

@Repository
public class UserDaoImpl implements UserDao {


    public void saveUser() {
        System.out.println("保存成功");
    }
}
