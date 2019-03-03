<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理页面</title>
<%@ include file="script.html"%>
<script>
	$(function(){
		$("#roleList").datagrid({
			url : "findRoleByPage.do",
			pagination : true,
			toolbar : "#toolbar",
			fitColumns : true,
			idField : "roleId",
			rownumbers : true,
			//singleSelect:true,
			columns : [ [
			//跟封装的数据匹配,
			{
				field : "roleId",
				title : "选择",
				checkbox : true
			}, {
				field : "roleName",
				title : "角色名",
				sortable : true,
				width : 20
			}, {
				field : "note",
				title : "备注信息",
				width : 15
			}, {
				field : "createTime",
				title : "创建时间",
				width : 15
			}, {
				field : "updateTime",
				title : "修改时间",
				width : 15
			}, {
				field : "status",
				title : "角色状态",
				formatter : function(value, rowData, index) {
					if (value == 1) {
						return "可用";
					} else if (value == 0) {
						return "禁用";
					} else if (value == 2) {
						return "已删除";
					} else {
						return "";
					}
				}
			}

			] ],
			loadFilter : function(data) {
				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
				return {
					"total" : data.total,
					"rows" : data.rows
				};
			}
		});
	});
	
	function addPrem(url){
		var rows = $("#roleList").datagrid("getChecked");
		if(rows.length != 1){
			$.messager.alert("警告","必须选择一个角色");
			return;
		}
		
		var roleId = rows[0].roleId;
		//跳转到权限列表页面
		//调用父页面的弹出方法,
		parent.openTopWindow({
			width : 700,
			height : 500,
			title : "添加用户",
			url : url +"?id="+roleId,
			close : function() {
				//页面重新加载刷新,
				$("#roleList").datagrid("reload");
			}
		});
	};
	

</script>
</head>
<body>
<!-- 按钮 -->
	<div id="toolbar">
		<!-- 添加方法 -->
		<a href="javascript:void(0);" onclick="return add('addRole.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">添加</a> <a
			href="javascript:void(0);" onclick="return del('delRole.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a> <a
			href="javascript:void(0);" onclick="return edit('updateRole.do');"
			id="editBtn" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a> <a
			href="javascript:void(0);" onclick="return addPrem('addPerm.do')"
			id="setBtn" class="easyui-linkbutton"
			data-options="iconCls:'icon-man',plain:true">授权</a>
	</div>
	<table id="roleList"></table>
</body>
</html>