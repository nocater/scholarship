<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
	<head>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
      	<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
      	<script>
           function subdata(){
				$("#btnSave").attr("disabled", true);
        	   	$("#configForm").submit();
           }
     	</script>
     <style type="text/css">
      ul li{
      	list-style: none;
      }
     </style>
	</head>

	<body style="margin-top: 2px">
	  <s:form id="configForm" action="changeAlertInfo" namespace="/config" method="post" theme="simple">
		<!--  左侧table-->
		<table width="50%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 4px; margin-top: 0px">
		<tr>
			<td width="500px" valign="top">
			<div class="framDiv">
			
			<!--  左侧table-->
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class="r2titler">
					<b>提示信息</b>&nbsp;&nbsp;&nbsp;
					<font id="message1"></font>
				</td>
			</tr>
			
			<tr>
				<td align="right"></td>
			</tr>	
					
			<tr>
				<td>
					<table width="90%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 18px;">
						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>
						<tr>
							<td width="30%" style="margin-left: 30px;">
								学生提交/修改奖/助学金页面强制警告内容：
							</td>
						</tr>
						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>	
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>
						<tr>
							<td align="left" width="60%">
								<s:textarea cols="50" rows="10" name="alertInfo" id="alertInfo"
								></s:textarea>
							</td>
						</tr>
						<tr>
						<td><span id="ipMessage" style="color: red"></span></td>
						</tr>
						
						<!-- 空行 -->
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
	
		<tr>
			<td class="td_detail_separator">
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table><tr><td>
				<input type="button" class="btnyh" id="btnSave" value="更改设置"  onclick="subdata()"/></td>
				<td>&nbsp;&nbsp;</td>
				<td id="actionmessage"><font color="green"><s:actionmessage/></font></td>
			</tr></table></td>
			
		</tr>
		</table>
		</s:form>
	</body>
</html>
