package com.demo.entity;

import java.util.Date;

public class Function {

    private Integer funcId;


    private String funcName;


    private String funcUrl;


    private String funcCode;


    private Integer parentId;


    private Integer funcType;


    private Integer status;


    private Integer sortNum;


    private String createTime;


    private String updateTime;


    public Integer getFuncId() {
        return funcId;
    }


    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }


    public String getFuncName() {
        return funcName;
    }


    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }


    public String getFuncUrl() {
        return funcUrl;
    }


    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl == null ? null : funcUrl.trim();
    }


    public String getFuncCode() {
        return funcCode;
    }


    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode == null ? null : funcCode.trim();
    }


    public Integer getParentId() {
        return parentId;
    }

 
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public Integer getFuncType() {
        return funcType;
    }


    public void setFuncType(Integer funcType) {
        this.funcType = funcType;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getSortNum() {
        return sortNum;
    }


    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }


    public String getCreateTime() {
        return createTime;
    }


    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}