package com.guigu.mybatis.mapper;

import com.guigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Drew
 * @create 2023-03
 */
public interface SelectMapper {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User getUserById(@Param("id") Integer id);

    /**
     * 查询用户总数量
     */
    Integer getCount();

}
