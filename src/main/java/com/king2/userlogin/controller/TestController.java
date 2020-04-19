package com.king2.userlogin.controller;

import com.king2.userlogin.entity.StudentMgn;
import com.king2.userlogin.mapper.StudentMgnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ================================================================
 * 说明：当前类说说明
 * <p>
 * 作者          时间                    注释
 * 刘梓江	2020/4/19  16:19            创建
 * =================================================================
 **/

@RestController
public class TestController {

    @Autowired
    private StudentMgnMapper studentMgnMapper;

    @GetMapping("getStudents")
    public StudentMgn  getStudents(){
        return  studentMgnMapper.selectByPrimaryKey("201500000012");
    }
}
    
    
    