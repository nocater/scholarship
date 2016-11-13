<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
  <head>
	<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css">
		
	<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>	
	<!-- dialog -->
	<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
	<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min_2.js"></script>	
	<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
	$(document).ready(function (){
		/* 选择学院对话框  */
		$("#student").dialog({
			autoOpen : false,
			width : 620,
			height : 400
		});
		$("#teacher").dialog({
			autoOpen : false,
			width : 650,
			height : 480
		});
	})
	</script>
  </head>
  
  <body>
  	<div id="student" title="黄冈师范学院奖助学金管理系统操作流程（学生）">
  		<table>
  			<tr>
   				<td>
   					<h2>黄冈师范学院奖助学金管理系统操作流程（学生）</h2></br>
	  				账号：本人学号</br>
					初始密码：本人学号</br></br>
					<font color="red">退出时请务必点击右上角“注销”后再关闭网页</font></br></br>
					<font color="blue">1、不申请奖助学金请进入操作：</font></br>
					右上角个人设置——修改密码——完善个人信息——保存</br></br>
					<font color="blue">2、申请奖助学金请进入操作：</font></br>
					第一步——右上角个人设置——修改密码——保存</br>
					第二步——填写个人信息（打*号必填）——家庭信息——成绩信息——保存信息——申请奖助学金</br></br>
					重要提示：</br>
					（1）信息的准确性关系到后期资金的发放，请每个学生务必认真修改本人信息，包括电话号码、银行卡号、身份证号码（学生本人可修改）；认真核对院系、班级信息，有错误向辅导员提出修改。</br>
					（2）学生本人填写信息后一定要点击申请奖助学金按钮，申请信息才会提交给班主任和辅导员，班主任和辅导员才能操作。如只是“仅保存信息”，班主任和辅导员无法看到学生的申请。</br></br>
	  			</td>
   			</tr>
  		</table>
  	</div>
  	<div id="teacher" title="黄冈师范学院奖助学金管理系统操作流程（班主任）">
  		<table>
   			<tr>
   				<td>
   					<h2>黄冈师范学院奖助学金管理系统操作流程（班主任）</h2></br>
	  				账号：教职工号</br>
					初始密码：教职工号</br></br>
					<font color="red">退出时请务必点击右上角“注销”后再关闭网页</font></br></br>
					操作注意事项：</br>
					<font color="blue">1、第一步请修改密码：右上角个人设置——修改密码——保存</font></br></br>
					<font color="blue">2、审批申请</font></br>
					查看审批申请</br>
					——点击“今年未审批”可看到全部申请学生的家庭信息，可按班级筛选，分班级打印，小组评议。</br>
					班主任进行网上审批</br>
					——点击“今年未审批”——按班级筛选——在学生信息前勾选——选择等级——在第一排“审批”处选择“通过”——点击“执行”。</br>
					查看审批结果</br>
					——点击“所有审批历史”——按班级筛选查看</br></br>
					<font color="blue">3、账户管理</font></br>
					班主任在此项内容中，只可进行此操作——当学生丢失密码时，可修改学生密码，但不要修改其它信息。</br></br>
					<b></b><font color="red">特别是不能随意添加、删除账户！</font></b></br>
					信息的安全关系到学生的切身利益，因此请班主任务必将账号密码由本人掌握，不得交由学生掌握，避免学生信息泄露对其造成损失。</br>
	  			</td>
   			</tr>
  		</table>
  	</div>
    <div class="aboutContainer">
    	<div class="aboutBg">
    		<div class="aboutInfo">
    			<table width="100%" border="0" cellpadding="15" cellspacing="0">
    			<tr>
    				<td><p onclick="$('#student').dialog('open');"><font color="blue"><u>单击显示学生操作说明</u></font></td>
    			</tr>
    			<tr>
    				<td><p onclick="$('#teacher').dialog('open');"><font color="blue"><u>单击显示教师操作说明</u></font></td>
    			</tr>
    			<tr>
    				<td>版本类型：正式版本</td>
    			</tr>
    			<tr>
    				<td>版本号：V1.528(113)</td>
    			</tr>
    			<tr>
    				<td>作者：陈帅</td>
    			</tr>
    			</table>
    		</div>
    	</div>
    </div>
  </body>
</html>
