package com.demo.dao;

import com.demo.entity.Function;
import com.demo.entity.RoleFunction;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IRoleFunctionMapper {

    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("funcId") Integer funcId);


    int insert(RoleFunction record);


    List<RoleFunction> selectAll();

	List<Function> findFunctionByRoleId(@Param("roleId") Integer roleId);

	//根据roleId删除
	int deleteByRoleId(@Param("roleId") int roleId);


	List<RoleFunction> findRoleByfuncId(int funcId);
}