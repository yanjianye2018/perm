package com.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IRoleFunctionMapper;
import com.demo.entity.Function;
import com.demo.entity.RoleFunction;
import com.demo.service.IRoleFunctionService;
@Service
public class RoleFunctionServiceImpl implements IRoleFunctionService {
	@Resource
	IRoleFunctionMapper roleFunctionMapper;
	public List<Function> findFunctionByRoleId(Integer roleId) {
		return roleFunctionMapper.findFunctionByRoleId(roleId);
	}
	@Override
	public int deleteByRoleId(int roleId) {
		return roleFunctionMapper.deleteByRoleId(roleId);
	}
	@Override
	public int save(RoleFunction rf) {
		return roleFunctionMapper.insert(rf);
	}
	@Override
	public List<RoleFunction> findRoleByFuncId(int funcId) {
		return roleFunctionMapper.findRoleByfuncId(funcId);
	}
	
	


}
