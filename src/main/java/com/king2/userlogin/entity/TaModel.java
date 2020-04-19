package com.king2.userlogin.entity;

import java.util.Date;

public class TaModel {
    private String modelId;

    private String modelName;

    private Date modelCreateTime;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public Date getModelCreateTime() {
        return modelCreateTime;
    }

    public void setModelCreateTime(Date modelCreateTime) {
        this.modelCreateTime = modelCreateTime;
    }
}