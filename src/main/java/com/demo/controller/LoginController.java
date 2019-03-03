package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.User;
import com.demo.service.IUserService;
import com.demo.util.EncryptUtil;

@Controller
public class LoginController {
	@Autowired
	private IUserService userService;

	@ResponseBody
	@RequestMapping("/login.do")
	public Map<String, Object> login(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
		// 根据用户名和密码查询数据库判断用户名和密码是否正确
		if (username != null && !"".equals(username) && password != null
				&& !"".equals(password)) {
			// 对密码进行加密,然后再去数据库中查询
			String password_secrety = EncryptUtil.encryptMD5(password);
			System.out.println(password_secrety);
			User user = userService.login(username, password_secrety);
			if (user != null) {
				// 把用户信息放到session中
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				map.put("success", true);
				map.put("msg", "登录成功");
			} else {
				map.put("success", false);
				map.put("msg", "用户名或密码错误");
			}

		} else {
			map.put("success", false);
			map.put("msg", "用户名或密码为空");
		}
		return map;
	}

	@RequestMapping("/toIndex.do")
	public String toIndex() {
		return "index";
	}

	// 注销功能，
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 获取到当前session,并销毁
		if (session != null) {
			session.invalidate();
		}
		//跳转到登录页面,
		return "login";
	}
}
