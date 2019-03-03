package com.demo.entity;

import java.util.Date;

public class Role {
    
    private Integer roleId;

   
    private String roleName;

   
    private String note;

   
    private Boolean system;

    
    private Integer status;

    
    private String createTime;

   
    private String updateTime;

   
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

   
    public String getRoleName() {
        return roleName;
    }

  
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

  
    public String getNote() {
        return note;
    }

   
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

  
    public Boolean getSystem() {
        return system;
    }

   
    public void setSystem(Boolean system) {
        this.system = system;
    }

   
    public Integer getStatus() {
        return status;
    }

    
    public void setStatus(Integer status) {
        this.status = status;
    }

   
    public String  getCreateTime() {
        return createTime;
    }

  
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

   
    public String  getUpdateTime() {
        return updateTime;
    }

  
    public void setUpdateTime(String  updateTime) {
        this.updateTime = updateTime;
    }
}