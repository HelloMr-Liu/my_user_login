package com.king2.userlogin.mapper;

import com.king2.userlogin.entity.StudentInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentInfoMapper {
    int deleteByPrimaryKey(Integer studentid);

    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    StudentInfo selectByPrimaryKey(Integer studentid);

    int updateByPrimaryKeySelective(StudentInfo record);

    int updateByPrimaryKey(StudentInfo record);

    @Select(" select * from  student_info ")
    List<StudentInfo> getStudentInfos();
}