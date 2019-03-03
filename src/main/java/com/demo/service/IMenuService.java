package com.demo.service;

import java.util.List;

import com.demo.entity.Function;
import com.demo.entity.vo.MenuVo;

public interface IMenuService {
	//根据用户id查询用户角色在获取用户对应的菜单,
	public List<MenuVo> findMenu(int userId);

	public List<Function> findAllFunction();
}
