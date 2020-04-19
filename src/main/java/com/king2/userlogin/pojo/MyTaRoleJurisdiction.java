package com.king2.userlogin.pojo;

import com.king2.userlogin.entity.TaRoleJurisdiction;
import lombok.Data;

/**
 * ================================================================
 * 说明：对数据库实体类中的 角色权限类的拓展类
 * <p>
 * 作者          时间                    注释
 * 刘梓江	2020/4/19  17:28            创建
 * =================================================================
 **/
@Data
public class MyTaRoleJurisdiction extends TaRoleJurisdiction {
    private Integer roleId; //角色id
    private String jurIds;  //角色权限id集  已逗号分开
}
    
    
    