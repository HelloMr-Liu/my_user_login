package com.king2.userlogin.mapper;

import com.king2.userlogin.entity.TaModel;

public interface TaModelMapper {
    int deleteByPrimaryKey(String modelId);

    int insert(TaModel record);

    int insertSelective(TaModel record);

    TaModel selectByPrimaryKey(String modelId);

    int updateByPrimaryKeySelective(TaModel record);

    int updateByPrimaryKey(TaModel record);
}