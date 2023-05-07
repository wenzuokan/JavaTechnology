package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.Employee;

import java.util.List;

/**
 * @author Drew
 * @create 2023-03
 */

public interface EmployeeMapper {

    /**
     * 查询所属有员工信息
     * @return
     */
    List<Employee> getAllEmployee();
}
