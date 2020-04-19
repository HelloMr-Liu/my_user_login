package com.king2.userlogin.mapper;

import com.king2.userlogin.entity.TaRoleInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TaRoleInfoMapper {
    int deleteByPrimaryKey(Integer roleInfoId);

    int insert(TaRoleInfo record);

    int insertSelective(TaRoleInfo record);

    TaRoleInfo selectByPrimaryKey(Integer roleInfoId);

    int updateByPrimaryKeySelective(TaRoleInfo record);

    int updateByPrimaryKey(TaRoleInfo record);

    @Select(" select * from ta_role_info ")
    List<TaRoleInfo> getRoles();
}