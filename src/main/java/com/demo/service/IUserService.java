package com.demo.service;

import java.util.List;

import com.demo.entity.User;

public interface IUserService {
	public User login(String username ,String password);
	
	//分页方法 offset从那一行开始查,pageSize:查询多少条,
	public List<User> findByPage(int offset,int pageSize);
	
	//获取总记录数
	public int count();

	public int insert(User user);

	public int deleteById(Integer id);
	
	public int updateStatusById(Integer id,int status);

	public User findById(Integer id);

	public int updateUser(User user);
}
