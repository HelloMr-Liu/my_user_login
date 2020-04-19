package com.king2.userlogin.mapper;

import com.king2.userlogin.entity.TaRoleMember;
import com.king2.userlogin.pojo.MyTaRoleMember;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TaRoleMemberMapper {
    int deleteByPrimaryKey(Integer roleMemberId);

    int insert(TaRoleMember record);

    int insertSelective(TaRoleMember record);

    TaRoleMember selectByPrimaryKey(Integer roleMemberId);

    int updateByPrimaryKeySelective(TaRoleMember record);

    int updateByPrimaryKey(TaRoleMember record);

    @Select(" select  member_id myMemberId,GROUP_CONCAT(role_info_id) memberRoleIds from ta_role_member GROUP BY member_id ")
    List<MyTaRoleMember> getMemberRoleIds();
}