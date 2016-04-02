<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
	<head>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
     
    	<script language="javascript">
		function subdata(){
			var serialFile = $("#upFile");
        	if(serialFile.val().indexOf("fort.licence")==-1){
        		alert("注册失败，注册文件错误！");
        		return;
        	}
        	$("#serialForm").submit();
        }
		</script>
	</head>

	<body>
    	<form enctype="multipart/form-data" id="serialForm" action="${ctx}/serial/register.action" method="post">
		<!--  左侧table-->
		<table width="55%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 4px; margin-top: 2px">
			<tr>
			<td width="800px" valign="top">
			<div class="framDiv">
			
			<!--  左侧table-->
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td class="r2titler">
					 <b>产品注册</b>&nbsp;&nbsp;&nbsp;
					
				</td>
			</tr>

			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 18px;">
						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2"><font color="red"><s:actionmessage /></font></td>
						</tr>	
						
						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>	
						<tr>
							<td>
								硬件特征码
							</td>
							<td>
								<input type="text" style="width:450px" id="hardwareCode" readonly name="hardwareCode" value="${hardwareCode}"/>
							</td>
						</tr>
						
						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>	
						<tr>
							<td >
								注册文件
							</td>
							<td >
								<input id="upFile"  name="upFile" type="file"/>
							</td>
							
						</tr>
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
		</td>
		</tr>
		<!-- 空行 -->
		<tr>
			<td class="td_detail_separator"></td>
		</tr>	
		<tr>
			<td colspan="2">
				<input type="button" class="btnyh" id="btnSave" value="提交注册文件"  onclick="subdata()"/>
			</td>
		</tr>
		
		</table>
		</form>
	</body>
</html>
