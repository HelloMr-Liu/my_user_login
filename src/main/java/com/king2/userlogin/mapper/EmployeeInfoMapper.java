package com.king2.userlogin.mapper;

import com.king2.userlogin.entity.EmployeeInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeInfoMapper {
    int deleteByPrimaryKey(Integer employeeid);

    int insert(EmployeeInfo record);

    int insertSelective(EmployeeInfo record);

    EmployeeInfo selectByPrimaryKey(Integer employeeid);

    int updateByPrimaryKeySelective(EmployeeInfo record);

    int updateByPrimaryKey(EmployeeInfo record);

    @Select(" select * from employee_info ")
    List<EmployeeInfo> getEmps();

}