package com.king2.userlogin.service.impl;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.king2.userlogin.cache.JurisdictionCache;
import com.king2.userlogin.cache.MemberCache;
import com.king2.userlogin.entity.EmployeeInfo;
import com.king2.userlogin.entity.StudentInfo;
import com.king2.userlogin.entity.StudentMgn;
import com.king2.userlogin.entity.TaJurisdictionList;
import com.king2.userlogin.service.UserLoginService;
import com.king2.userlogin.vo.SystemResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * ================================================================
 * 说明：用户登录接口实现
 * <p>
 * 作者          时间                    注释
 * 刘梓江	2020/4/19  22:27            创建
 * =================================================================
 **/
@Service
public class UserLoginServiceImpl implements UserLoginService {

    //引入角色权限缓存容器类、成员信息缓存容器类
    @Autowired
    private JurisdictionCache jurisdictionCache;
    @Autowired
    private MemberCache memberCache;

    /**
     *  功能：用户登录
     *  时间：2020/4/19 22:26
     *  参数：名称            类型            描述
     *       userName      String          用户名
     *       password      String          密码
     *
     *  返回：SystemResult
     *  描述：封装了登录后相应的信息结果实体
     */
    public SystemResult login(String userName, String password, HttpSession session) {

        List<TaJurisdictionList> jurList=null;
        //判断当前是学生还是员工
        if(userName.length()==12){
            //获取当前学生拓展信息
            Map<String, StudentMgn> studentMgnMap = (Map<String, StudentMgn>)memberCache.getMemberCache().get(2);
            StudentMgn studentMgn = studentMgnMap.get(userName);
            if(studentMgn==null||!(studentMgn.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes())))) return SystemResult.build(301,"用户名或密码错误");

            //获取当前学生信息
            Map<String, StudentInfo> studentInfoMap = (Map<String, StudentInfo>)memberCache.getMemberCache().get(3);
            StudentInfo studentInfo = studentInfoMap.get(userName);
            //获取当前学生对应的权限
            jurList = jurisdictionCache.getMemberJurContent(studentInfo.getStudentid(), 2);
            if(jurList==null) return SystemResult.build(302,"当前用户没有对应的权限");


        }else{
            //获取员工信息
            Map<Integer, EmployeeInfo> employeeInfoMap = (Map<Integer, EmployeeInfo>)memberCache.getMemberCache().get(1);
            EmployeeInfo employeeInfo = employeeInfoMap.get(Integer.parseInt(userName));
            if(employeeInfo==null||!(employeeInfo.getPassword().equals(password))) return SystemResult.build(301,"用户名或密码错误");

            //获取当前员工对应的权限
            jurList = jurisdictionCache.getMemberJurContent(employeeInfo.getEmployeeid(), 1);
            if(jurList==null) return SystemResult.build(302,"当前用户没有对应的权限");
        }
        //将当前权限信息存储到Session中
        session.setAttribute("USER_INFO",jurList);
        return SystemResult.ok(jurList);
    }
}
    
    
    