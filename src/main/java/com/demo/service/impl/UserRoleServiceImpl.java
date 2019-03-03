package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.IUserRoleMapper;
import com.demo.entity.UserRole;
import com.demo.service.IUserRoleService;
@Service
public class UserRoleServiceImpl implements IUserRoleService {
	@Autowired
	private IUserRoleMapper userRoleMapper;
	@Override
	public List<UserRole> findByUserId(Integer userId) {
		
		return userRoleMapper.findByUserId(userId);
	}
	@Override
	public int deleteByUserId(int userId) {
		
		return userRoleMapper.deleteByUserId(userId);
	}
	@Override
	public int insertUserRole(UserRole ur) {
		return userRoleMapper.insert(ur);
	}

}
