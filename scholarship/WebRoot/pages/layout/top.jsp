<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
  <head>
	<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css">
	<%-- <script type='text/javascript' src="${ctx}/scripts/jquery-2.1.0.min.js"></script> --%>
	<link href="${ctx}/styles/custom_ui.css" type="text/css" rel="stylesheet"/>
		<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
		<%-- <script type="text/javascript" src="${ctx}/scripts/custom_ui_action.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/custom_ui_tree.js"></script> --%>
		
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
			} else if(linkType == 'configure'){
				// 审批模块
				parent.leftFrame.location.href = '${ctx}/pages/config/configMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/config/queryApplySwitch.action';
			} else if(linkType == 'about'){
				// 关于模块
				parent.leftFrame.location.href = '${ctx}/pages/about/aboutMenu.jsp';
				parent.mainFrame.location.href = '${ctx}/abouts/about.action';
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
	</script>
</head>
<body onload="subRoleName();">
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
										<%-- <s:if test="@com.fort.module.conf.AppConfig@DIS_USE_NUM >= 90"> </s:if> --%>
			                <!-- <td>
			                <marquee direction=left behavior=scroll scrolldelay=150 onmouseover=this.stop() onmouseout=this.start()>
			                	<font color="red" size="5">
			                		<b>系统提示：系统将于5-6 23：30-23：40升级 届时网站不能登录请注意</b>
			                	</font>
			                	</marquee>
			                </td> -->
		               
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
															onclick="parent.mainFrame.location.href='${ctx}/account/queryMe.action';" /></td>
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


		<table width="99%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="frameDh">
					<ul>
						<c:if test="${sessionScope.LOGON_ROLE.id eq 2}">
						<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
						<li><a href="javascript:linkTo('datas');">奖/助学金</a></li>
						</c:if>
						<c:if test="${sessionScope.LOGON_ROLE.id != 2}">
							<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
							<li><a href="javascript:linkTo('apply');">审批申请</a></li>
							<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
							<li><a href="javascript:linkTo('account');">账户管理</a></li>
							<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
								<c:if test="${sessionScope.LOGON_ROLE.id eq 1}">
									<li><a href="javascript:linkTo('role');">角色管理</a></li>
									<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
									<li><a href="javascript:linkTo('college');">学院管理</a></li>
									<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
								</c:if>
								<c:if test="${sessionScope.SHOWGRADE eq 1}">
									<li><a href="javascript:linkTo('grade');">班级管理</a></li>
									<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
								</c:if>
								<c:if test="${sessionScope.LOGON_ROLE.id eq 1}">
									<li><a href="javascript:linkTo('audit');">审计管理</a></li>
									<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
									<li><a href="javascript:linkTo('configure');">系统设置</a></li>
									<li style="width:5px"><span style="color:#FFFFFF;">|</span></li>
								</c:if>
						</c:if>
						<li><a href="javascript:linkTo('about');">关于产品</a></li>
					</ul>
				</td>
			</tr>
		</table>
	</center>
	<div class="ui-overlay">
		<div id="mack"></div>
	</div>
  </body>
</html>
