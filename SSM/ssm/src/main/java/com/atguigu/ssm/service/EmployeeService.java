package com.atguigu.ssm.service;

import com.atguigu.ssm.pojo.Employee;

import java.util.List;

/**
 * @author Drew
 * @create 2023-03
 */
public interface EmployeeService {

    /**
     * 查询所有员工信息
     *
     * */
    List<Employee> getAllEmployee();

}
