package com.king2.userlogin.service;

import com.king2.userlogin.vo.SystemResult;

import javax.servlet.http.HttpSession;

/**
 * ================================================================
 * 说明：用户登录业务接口
 * <p>
 * 作者          时间                    注释
 * 刘梓江	2020/4/19  22:23            创建
 * =================================================================
 **/
public interface UserLoginService {

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
     SystemResult login(String userName, String password, HttpSession session);

}
