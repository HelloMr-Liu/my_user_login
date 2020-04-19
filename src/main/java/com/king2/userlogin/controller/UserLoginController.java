package com.king2.userlogin.controller;

import com.king2.userlogin.service.UserLoginService;
import com.king2.userlogin.vo.SystemResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

/**
 * ================================================================
 * 说明：用户登录控制器
 * <p>
 * 作者          时间                    注释
 * 刘梓江	2020/4/19  22:23            创建
 * =================================================================
 **/
@Controller
@Validated
public class UserLoginController {


    @Autowired
    private UserLoginService userLoginService;  //注入用户登录业务接口
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
    @RequestMapping("/login")
    @ResponseBody
    SystemResult login(@NotNull  String userName,@NotNull String password, HttpSession session){
        return userLoginService.login(userName,password,session);
    }

}
    
    
    