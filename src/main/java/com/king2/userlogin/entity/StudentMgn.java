package com.king2.userlogin.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class StudentMgn {

    @JSONField(serialize=false)
    private String studentcode;

    private String name;

    private String password;

    public String getStudentcode() {
        return studentcode;
    }

    public void setStudentcode(String studentcode) {
        this.studentcode = studentcode == null ? null : studentcode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}