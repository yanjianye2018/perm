<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户编辑页面</title>
<%@ include file="script.html"%>
<script type="text/javascript">
	$(function(){
		//点击事件
		$("#saveBtn").click(function(){
			//校验输入的信息是否合法,通过validate插件校验,
			var result = $("#form").form("validate");
			//通过ajax进行数据的提交
			//result=true才进行ajax提交
			if(result){
				//进行post ajax提交,
				$.post(
					"update_user.do",
					//参数
					$("#form").serialize(),
					//回调函数,
					function(data){
						alert(data.msg);
						if(data.success){
							//关闭当前弹出框,刷新父页面,
							parent.closeTopWindow();
						}
						return;
					},
					"json"
				);
			}
		});
	})
</script>
</head>
<body>
	<section class="info-section">
		<form id="form" method="post">
			<table>
				<tr>
					<td class="text-title">用户名称：</td>
					<td class="text-content">
						<input type="hidden" name="userId" value="${user.userId }"> 
						<input type="text" name="userName" value="${user.userName }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[6,20]'">
					</td>
				</tr>

				<tr>
					<td class="text-title">用户密码：</td>
					<td class="text-content">
						<input type="password" name="password" value="${user.password }" readonly="true"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[6,35]'">
					</td>
				</tr>

				<tr>
					<td class="text-title">联系电话：</td>
					<td class="text-content">
						<input type="text" name="phone" value="${user.phone }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">电子邮箱：</td>
					<td class="text-content">
						<input type="text" name="email" value="${user.email }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'email'"></td>
				</tr>
				<tr>
					<td class="text-title">用户状态：</td>
					<td class="text-content">
						<select name="status" class="easyui-combobox theme-textbox-radius">
							<option value="1" ${user.status == 1 ? "selected" : "" }>正常</option>
							<option value="0" ${user.status == 0 ? "selected" : "" }>禁用</option>
							<option value="2" ${user.status == 2 ? "selected" : "" }>已删除</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">创建时间：</td>
					<td class="text-content">
						<input type="text" name="createTime" value="${user.createTime }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="text-title">描述信息：</td>
					<td class="text-content">
						<textarea class="theme-textbox-radius" rows="5"  cols="20" name="note">${user.note }</textarea></td>
				</tr>
			
				<tr>
					<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="saveBtn" class="easyui-linkbutton button-primary">保存</a> 
						<a href="javascript:void(0);" id="resetBtn" class="easyui-linkbutton button-danger">重置</a>
					</td>
				</tr>
				</table>
		</form>
	</section>
</body>
</html>