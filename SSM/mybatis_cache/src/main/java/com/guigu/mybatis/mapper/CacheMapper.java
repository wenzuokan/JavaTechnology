package com.guigu.mybatis.mapper;

import com.guigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

/**
 * @author Drew
 * @create 2023-03
 */
public interface CacheMapper {

    /**
     * 根据员工id查询员工信息
     * @param empId
     * @return
     */
    Emp getEmpById(@Param("empId") Integer empId);

    /**
     * 添加员工信息
     * @param emp
     */
    void insertEmp(Emp emp);
}
