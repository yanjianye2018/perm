package com.demo.dao;

import com.demo.entity.Role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IRoleMapper {

    int deleteByPrimaryKey(Integer roleId);


    int insert(Role record);


    Role selectByPrimaryKey(Integer roleId);


    List<Role> selectAll();


    int updateByPrimaryKey(Role record);

	List<Role> findByPage(@Param("start")int start, @Param("end")int end);
}