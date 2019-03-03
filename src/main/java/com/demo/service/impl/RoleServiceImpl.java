package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.IRoleMapper;
import com.demo.entity.Role;
import com.demo.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleMapper roleMapper;

	@Override
	public List<Role> findAll() {
		return roleMapper.selectAll();
	}

	@Override
	public List<Role> findByPage(int start, int end) {
		
		return roleMapper.findByPage(start, end);
	}
	//获取总记录数据
	@Override
	public int count() {	
		return roleMapper.selectAll().size();
	}

}
