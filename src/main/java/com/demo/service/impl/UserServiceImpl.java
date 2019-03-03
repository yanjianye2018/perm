package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.IUserMapper;
import com.demo.entity.User;
import com.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserMapper userMapper;
	@Override
	public User login(String username, String password) {
		return userMapper.findByName(username,password);
	}
	@Override
	public List<User> findByPage(int offset, int pageSize) {
		
		return userMapper.findByPage(offset,pageSize);
	}
	@Override
	public int count() {
		return userMapper.selectAll().size();
	}
	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}
	@Override
	public int deleteById(Integer id) {	
		return userMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int updateStatusById(Integer id,int status) {
		User user = new User();
		user.setUserId(id);
		user.setStatus(status);
		return userMapper.updateStatusById(user);
	}
	@Override
	public User findById(Integer id) {
		//根据主键查询,
		return userMapper.selectByPrimaryKey(id);
	}
	@Override//修改用户
	public int updateUser(User user) {
		
		return userMapper.updateByPrimaryKey(user);
	}
	
}
