package com.demo.service;

import java.util.List;

import com.demo.entity.UserRole;

public interface IUserRoleService {
	List<UserRole> findByUserId(Integer userId);

	int deleteByUserId(int userId);

	int insertUserRole(UserRole ur);
}
