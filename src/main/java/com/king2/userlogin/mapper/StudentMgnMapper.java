package com.king2.userlogin.mapper;

import com.king2.userlogin.entity.StudentMgn;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMgnMapper {
    int deleteByPrimaryKey(String studentcode);

    int insert(StudentMgn record);

    int insertSelective(StudentMgn record);

    StudentMgn selectByPrimaryKey(String studentcode);

    int updateByPrimaryKeySelective(StudentMgn record);

    int updateByPrimaryKey(StudentMgn record);

    @Select("  select * from  student_mgn ")
    List<StudentMgn> getStudentMgns();
}