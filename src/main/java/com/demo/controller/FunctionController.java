package com.demo.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.Function;
import com.demo.entity.RoleFunction;
import com.demo.service.IFunctionService;
import com.demo.service.IRoleFunctionService;

@Controller
public class FunctionController {
	@Autowired
	private IFunctionService functionService;

	@Autowired
	private IRoleFunctionService roleFunctionService;

	@RequestMapping("/functionList.do")
	public String functionList() {
		return "functionList";
	}

	@RequestMapping("/findFunctionList.do")
	public void findFunctionList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 查询所有的权限列表
		List<Function> list = functionService.findFunctionList();
		JSONObject json = new JSONObject();
		if (list != null || list.size() > 0) {
			json.put("data", list);
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}

	@RequestMapping("/toAddPerm.do")
	public String addPerm(HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		request.setAttribute("parentId", parentId);
		// 跳转到addFunction.jsp
		return "addFunction";
	}

	@RequestMapping("/savePerm.do")
	public void savePerm(Function function, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if (function != null) {
			String funcName = function.getFuncName();
			// 根据菜单名称查询，判断该名称是否存在
			if (funcName != null && !"".equals(funcName)) {
				Function func = functionService.findByFuncName(funcName);
				if (func != null) {
					json.put("success", false);
					json.put("msg", "该权限菜单已经存在，请重新输入！");
				} else {
					// 设置创建时间
					function.setCreateTime(new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").format(new Date()));
					// 保存权限信息
					int result = functionService.insertFunction(function);
					if (result == 1) {
						json.put("success", true);
						json.put("msg", "权限添加成功");
					} else {
						json.put("success", false);
						json.put("msg", "权限添加失败");
					}
				}
			}

		} else {
			json.put("success", false);
			json.put("msg", "获取权限信息失败！");
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}

	@RequestMapping("/delPermById.do")
	public void delPermById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		String ids = request.getParameter("ids");
		String[] ids2 = ids.split(",");
		int result = 0;
		String msg = "";
		boolean flag = false;
		if (ids != null && ids.length() > 0) {
			for (String id : ids2) {
				// 根据id查询看角色权限表中是否是否存在关联,如果存在关联关系不能删除！
				List<RoleFunction> list = roleFunctionService
						.findRoleByFuncId(Integer.parseInt(id));
				if (list != null && list.size() > 0) {
					msg = "存在角色关联关系,请先解除关联再删除!";
					break;
				} else {
					// 判断当前id是否存在子菜单,如果存在,不能删除,
					List<Function> funcs = functionService
							.findByParentId(Integer.parseInt(id));
					if (funcs != null && funcs.size() > 0) {
						msg = "存在子菜单,请先删除子菜单再删除该菜单!";
						break;
					} else {
						// 进行删除操作
						result += functionService.deleteByFuncId(Integer
								.parseInt(id));
						// 删除成功更改flag
						flag = true;
					}
				}
			}
			if (result >= 1) {
				json.put("msg", "删除成功");
				json.put("success", true);
			} else {
				json.put("msg", msg);
				json.put("success", flag);
			}
		} else {
			json.put("msg", "获取删除id失败");
			json.put("success", flag);
		}

		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}

	// 跳转到修改页面
	@RequestMapping("/updatePerm.do")
	public String updatePerm(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			// 通过id查询当前菜单权限信息
			Function function = functionService.findByFuncId(Integer
					.parseInt(id));
			request.setAttribute("function", function);
		}
		return "editFunction";
	}

	@RequestMapping("/findTree.do")
	public void findTree(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		
		List<Function> funcs = functionService.findTreeByParentId(id);
		List<Map<String,Object>> treeNodes = new ArrayList<Map<String,Object>>();
		if(funcs != null && funcs.size() > 0){
			for(Function fun : funcs){
				Map<String,Object> treeNode = new HashMap<String,Object>();
				treeNode.put("id", fun.getFuncId());
				treeNode.put("text", fun.getFuncName());
				//state  open|closed
				if(fun.getParentId() == null){
					treeNode.put("state", "closed");
				}
				treeNodes.add(treeNode);
			}
		}
		if(treeNodes != null && treeNodes.size() > 0){
			json.put("data",treeNodes);
			json.put("success",true);
			json.put("msg", "获取权限列表成功！");
			
		}else{
			json.put("success",false);
			json.put("msg", "获取权限列表失败！");
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	//修改权限
	@RequestMapping("/updatePermById.do")
	public void updatePerm(Function function,HttpServletRequest request,HttpServletResponse response)
	throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if(function != null){
			//设置创建时间
			function.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//保存权限信息
			int result = functionService.updateFunction(function);
			if(result == 1){
				json.put("success", true);
				json.put("msg", "权限修改成功");
			}else{
				json.put("success", false);
				json.put("msg", "权限修改失败");
			}
		}
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
}
