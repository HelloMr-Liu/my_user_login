package com.king2.userlogin.mapper;

import com.king2.userlogin.entity.TaJurisdictionList;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TaJurisdictionListMapper {
    int deleteByPrimaryKey(Integer jurisdictionListId);

    int insert(TaJurisdictionList record);

    int insertSelective(TaJurisdictionList record);

    TaJurisdictionList selectByPrimaryKey(Integer jurisdictionListId);

    int updateByPrimaryKeySelective(TaJurisdictionList record);

    int updateByPrimaryKey(TaJurisdictionList record);

    @Select(" select * from  ta_jurisdiction_list ")
    List<TaJurisdictionList> getJurs();
}