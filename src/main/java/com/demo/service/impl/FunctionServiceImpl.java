package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.IFunctionMapper;
import com.demo.entity.Function;
import com.demo.service.IFunctionService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
@Service
public class FunctionServiceImpl implements IFunctionService {
	@Autowired
	private IFunctionMapper functionMapper;
	@Override
	public List<Function> findFunctionList() {
		//查询所有,
		return functionMapper.selectAll();
	}
	@Override
	public int insertFunction(Function function) {
		return functionMapper.insert(function);
	}
	@Override
	public Function findByFuncName(String funcName) {
		
		return functionMapper.findByFuncName(funcName);
	}
	@Override
	public List<Function> findByParentId(int parentId) {
		return functionMapper.findByParentId(parentId);
	}
	@Override
	public int deleteByFuncId(int funcId) {
		return functionMapper.deleteByPrimaryKey(funcId);
	}
	@Override
	public Function findByFuncId(int id) {
		return functionMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Function> findTreeByParentId(String id) {
		return functionMapper.findTreeByParentId(id);
	}
	@Override
	public int updateFunction(Function function) {
		return functionMapper.updateByPrimaryKey(function);
	}

}
