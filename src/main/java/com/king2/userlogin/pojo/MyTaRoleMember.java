package com.king2.userlogin.pojo;

import com.king2.userlogin.entity.TaRoleMember;
import lombok.Data;

/**
 * ================================================================
 * 说明：角色成员拓展表
 * <p>
 * 作者          时间                    注释
 * 刘梓江	2020/4/19  17:59            创建
 * =================================================================
 **/
@Data
public class MyTaRoleMember  extends TaRoleMember {

    private String myMemberId;        //成员id
    private String memberRoleIds;   //成员角色id

}
    
    
    