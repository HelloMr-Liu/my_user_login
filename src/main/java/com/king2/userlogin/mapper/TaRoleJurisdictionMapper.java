package com.king2.userlogin.mapper;

import com.king2.userlogin.entity.TaRoleJurisdiction;
import com.king2.userlogin.pojo.MyTaRoleJurisdiction;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TaRoleJurisdictionMapper {
    int deleteByPrimaryKey(Integer roleJurisdictionId);

    int insert(TaRoleJurisdiction record);

    int insertSelective(TaRoleJurisdiction record);

    TaRoleJurisdiction selectByPrimaryKey(Integer roleJurisdictionId);

    int updateByPrimaryKeySelective(TaRoleJurisdiction record);

    int updateByPrimaryKey(TaRoleJurisdiction record);

    @Select(" select role_info_id roleId, GROUP_CONCAT(jurisdiction_list_id) jurIds\n" +
            "from ta_role_jurisdiction   GROUP BY role_info_id ")
    List<MyTaRoleJurisdiction> getRoleJurIds();
}