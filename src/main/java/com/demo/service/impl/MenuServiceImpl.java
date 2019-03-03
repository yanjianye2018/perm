package com.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.IFunctionMapper;
import com.demo.entity.Function;
import com.demo.entity.vo.MenuVo;
import com.demo.service.IMenuService;


@Service
public class MenuServiceImpl implements IMenuService {
	@Autowired
	private IFunctionMapper functionMapper;

	@Override
	public List<MenuVo> findMenu(int userId) {
		// 封装
		List<MenuVo> menus = new ArrayList<>();

		List<Function> functions = functionMapper.findMenu(userId);
		// 遍历
		if (functions != null && functions.size() > 0) {
			for (Function f : functions) {
				MenuVo mv = new MenuVo();
				mv.setId(f.getFuncId().toString());
				mv.setName(f.getFuncName());
				mv.setUrl(f.getFuncUrl());
				if (f.getParentId() != null) {
					mv.setpId(f.getParentId().toString());
					// 当父菜单不为空时,
					mv.setOpen(false);
				} else {
					// 是父菜单才打开
					mv.setOpen(true);
				}
				// 没有复选框,
				mv.setChecked(false);
				menus.add(mv);
			}
		}
		return menus;
	}
	//查询所有菜单,
	@Override
	public List<Function> findAllFunction() {
		return functionMapper.selectAll();
	}

}
