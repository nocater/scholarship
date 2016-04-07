<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
  <head>
	<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css">
	<script type='text/javascript' src="${ctx}/scripts/jquery-2.1.0.min.js"></script>
	<script language="javascript">
		$(document).ready(function() {
			
			$("ul li a").click(function() {
				$("ul li a").css("color", "");
				//$("ul li a").css("font-weight","");
				$(this).css("color", "#faec06");
				//$(this).css("font-weight","bold");
			});
		});
		function linkTo(linkType) {
			if (linkType == 'account') {
				// 账户模块
				parent.leftFrame.location.href = '${ctx}/pages/account/accountMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/account/query.action';
			} else if (linkType == 'role') {
				// 角色模块
				parent.leftFrame.location.href = '${ctx}/pages/role/roleMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/role/query.action';
			} else if (linkType == 'college') {
				// 学院模块
				parent.leftFrame.location.href = '${ctx}/pages/college/collegeMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/college/query.action';
			} else if (linkType == 'grade') {
				// 班级模块
				parent.leftFrame.location.href = '${ctx}/pages/grade/gradeMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/grade/query.action?';
			} else if (linkType == 'datas') {
				// 信息模块
				parent.leftFrame.location.href = '${ctx}/pages/datas/datasTip.jsp';
				parent.mainFrame.location.href = '${ctx}/datas/query.action';
			} else if (linkType == 'audit') {
				// 审计模块
				parent.leftFrame.location.href = '${ctx}/pages/audit/auditMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/audit/query.action';
			} else if (linkType == 'apply') {
				// 审批模块
				parent.leftFrame.location.href = '${ctx}/pages/apply/applyMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/apply/query.action';
				
			} else if (linkType == 'worksheet') {
				// 工单模块
				parent.leftFrame.location.href = '${ctx}/pages/worksheet/worksheetMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/worksheet/workSheetAction!queryWorkSheetByUserId.action';
			} else if (linkType == 'sso') {
				// SSO模块
				parent.leftFrame.location.href = '${ctx}/sso/displayDeviceMenu.action';
				parent.mainFrame.location.href = '${ctx}/sso/queryDevice.action';
			} else if (linkType == 'script') {
				// 脚本模块
				parent.leftFrame.location.href = '${ctx}/pages/scriptManager/scriptMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/script/query.action';
			} else if (linkType == 'task') {
				// 任务模块
				parent.leftFrame.location.href = '${ctx}/pages/task/taskMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/task/queryUp.action?type=0&status=1&order=task_update_date';
			} else if (linkType == 'report') {
				// 组态报表
				parent.leftFrame.location.href = '${ctx}/pages/reportForm/reportMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/reportFormQuery.do?method=initPage';
			} else if (linkType == 'about') {
				// 关于产品
				parent.leftFrame.location.href = '${ctx}/pages/about/aboutMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/abouts/about.action';
			} else if (linkType == 'approve') {
				// 审批
				parent.leftFrame.location.href = '${ctx}/pages/approve/approveMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/approve/query.action';
			}
		}
	
		function logout() {
			if (confirm("你确认要注销吗？")) {
				location.href = '${ctx}/login/logout.action';
			}
		}
	
		function reload() {
			document.onmousedown = ContextMenu;
			$("#mack").addClass("ui-widget-overlay");
		}
	
		function cancel() {
			document.onmousedown = cancelContext;
			$("#mack").removeClass("ui-widget-overlay");
		}
	
		function cancelContext() {
			if (event.button == 1 || event.button == 0 || event.button == 2
					|| event.button == 3) {
			}
		}
	
		function ContextMenu() {
			if (event.button == 2 || event.button == 3) {
				alert("切换中无法操作");
				return false;
			}
		}
	
		function showMes() {
			parent.leftFrame.location.href = '${ctx}/pages/worksheet/worksheetMenu.jsp';
			parent.mainFrame.location.href = '${ctx}/worksheet/checkMessage.action?method=checkMessage&readFlag=false';
		}
	
		function subRoleName() {
			var roleName = $("#roleNameStr").val();
			var role = "";
			if (roleName.length > 6) {
				role += roleName.substring(0, 5) + "...";
			} else {
				role += roleName;
			}
			$("#roleNames").html(role);
		}
		function load(){
			if(document.all)
			{
				if(undefined==window.opener)
				{
					window.open(window.location,"","fullScreen=no;scrollbar=no,resizable=yes,menubar=no,toolbar=no,location=no,status=yes");
					window.opener=null;
					window.close();
				}
				else
				{
					self.moveTo(0,0);
					self.resizeTo(screen.availWidth,screen.availHeight);
				}
			}
		}
	</script>
</head>
<body nload="load()">
	<center>
		<!-- banner area -->

		<table width="99%" height="65px" border="0" cellpadding="0"
			cellspacing="0" background="${ctx}/images/topRbj.jpg">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						height="65px;">
						<tr>
							<td><table width="100%" border="0" cellspacing="0"
									cellpadding="0">
									<tr>
										<td valign="top" align="left">
											<!-- LOGO --> <%-- <img src="${ctx}/images/logo.jpg" alt="" name="topLogo" width="300" height="65" id="topLogo" style="" /> --%>
										</td>
										<%-- <s:if test="@com.fort.module.conf.AppConfig@DIS_USE_NUM >= 90">
			                <td>
			                <marquee direction=left behavior=scroll scrolldelay=150 onmouseover=this.stop() onmouseout=this.start()>
			                	<font color="red">
			                		<b>磁盘空间已占用<s:property value="@com.fort.module.conf.AppConfig@DIS_USE_NUM"/>%，请联系系统管理员及时清理！</b>
			                	</font>
			                	</marquee>
			                </td>
		                </s:if> --%>
										<td align="right">
											<div class="topinfo" id="topinfo">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td><div id="workMes" onclick="showMes()">
																<span id="span1" class="topL0"></span>
															</div></td>
														<td class="topL1">
															<table width="75%" border="0" align="right"
																cellpadding="0" cellspacing="0">
																<tr>
																	<td align="left">帐&nbsp;&nbsp;号：${sessionScope.LOGON_ACCOUNT.name}</td>
																</tr>
																<tr>
																	<td align="left"
																		title="${sessionScope.LOGON_ROLE.name}">
																		角&nbsp;&nbsp;色：<span id="roleNames"></span> <input
																		type="hidden" id="roleNameStr"
																		value="${sessionScope.LOGON_ROLE.name}" />
																	</td>
																</tr>
															</table>
														</td>
														<td><input name="" type="button" class="topL2"
															value="个人设置"
															onclick="parent.mainFrame.location.href='${ctx}/employee/personSeting.action';" /></td>
														<td><input name="input" type="button" class="topL3"
															value="注销" onclick="logout()" /></td>
													</tr>
												</table>
											</div>
										</td>
									</tr>
								</table></td>
							<td width="1%">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- banner area -->
			<%@ include file="/pages/apply/applyList.jsp"%>
	</center>
	<div class="ui-overlay">
		<div id="mack"></div>
	</div>
  </body>
</html>
