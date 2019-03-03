<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="script.html"%>
<script type="text/javascript">
	//id="roleList"
	$(function() {
		$("#roleList").datagrid(
				{
					url : "findAll.do",
					fitColumns : true,
					idField : "roleId",
					rownumbers : true,
					columns : [ [ {
						field : "roleId",
						title : "选择",
						checkbox : true
					}, {
						field : "roleName",
						title : "角色名",
						sortable : true,
						width : 10
					}, {
						field : "note",
						title : "角色描述",
						width : 10
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
							"rows" : data.data
						};
					},
					//当前表格中所有的数据加载完毕之后
					//加载成功后调用的方法
					//把具备的角色全部查出来
					//根据用户id查询
					onLoadSuccess : function() {
						//根据用户的id获取这个用户对应的角色信息
						$.get("findRoleByUserId.do", {
							"userId" : $("#user_id").val()
						}, function(data) {
							//判断用户是否已经有了角色，如果有，就将对应角色在表格中选中
							if (data && data.success) {
								$.each(data.data, function(index, obj) {
									$("#roleList").datagrid("selectRecord",
											obj.roleId);
								});
							}
						}, "json");
					}
				});

		//分配权限
		$("#assignBtn").click(function() {
			//获取到当前页面分配的角色选项
			var rows = $("#roleList").datagrid("getChecked");
			if (rows.length <= 0) {
				$.messager.alert("警告", "必须选中一个角色!");
				return;
			}
			var array = new Array();
			//把rows中的数据遍历放到数组中
			$.each(rows, function(index, obj) {
				array.push(obj.roleId);
			});
			var ids = array.join(",");
			var userId = $("#user_id").val();
			//提交到后台数据库
			$.get("save_assignRole.do", {
				"userId" : userId,
				"ids" : ids
			}, function(data) {
				//判断用户是否已经有了角色，如果有，就将对应角色在表格中选中
				if (data && data.success) {
					alert(data.msg);
					parent.closeTopWindow();
				}
			}, "json");
		});
	});
</script>
</head>
<body>
	<div style="text-align: center; margin-top: 10px;">
		<input type="hidden" name="userId" value="${userId}" id="user_id" />
		<a class="easyui-linkbutton" id="assignBtn"
			data-options="iconCls:'icon-man'">分配</a>
	</div>
	<table id="roleList"></table>
</body>
</html>