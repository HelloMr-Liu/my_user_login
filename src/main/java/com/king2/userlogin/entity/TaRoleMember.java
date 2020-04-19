package com.king2.userlogin.entity;

public class TaRoleMember {
    private Integer roleMemberId;

    private String memberId;

    private Integer roleInfoId;

    public Integer getRoleMemberId() {
        return roleMemberId;
    }

    public void setRoleMemberId(Integer roleMemberId) {
        this.roleMemberId = roleMemberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public Integer getRoleInfoId() {
        return roleInfoId;
    }

    public void setRoleInfoId(Integer roleInfoId) {
        this.roleInfoId = roleInfoId;
    }
}