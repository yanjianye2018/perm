package com.demo.service;

import java.util.List;

import com.demo.entity.Function;
import com.demo.entity.RoleFunction;

public interface IRoleFunctionService {

	public List<Function> findFunctionByRoleId(Integer roleId);

	public int deleteByRoleId(int roleId);
	
	public int save(RoleFunction rf);

	public List<RoleFunction> findRoleByFuncId(int parseInt);
	

}
