package com.guigu.mybatis.mapper;

import com.guigu.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

/**
 * @author Drew
 * @create 2023-03
 */
public interface DeptMapper {

    /**
     * 通过分步查询员工以及所对应的部门信息的第二步
     * @return
     */
    Dept getEmpAndDeptByStepTwo(@Param("deptId") Integer deptId);

    /**
     * 查询部门以及部门中员工信息
     * @param deptId
     * @return
     */
    Dept getDeptAndEmpByDeptId(@Param("deptId") Integer deptId);

    /**
     * 通过分步查询部门以及部门中的员工信息第一步
     * @param deptId
     * @return
     */
    Dept getDeptAndEmpByStepOne(@Param("deptId") Integer deptId);
}
