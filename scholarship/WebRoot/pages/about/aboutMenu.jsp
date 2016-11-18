<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
	<head>
		<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
		 <link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
		<script language="javascript">
		</script>
	</head>

	<body>
	<div class="titleTop">
			<div style="background-image:url(${ctx}/images/leftDh.jpg)">
				<table width="97%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="25px">
							<img src="${ctx}/images/jtTitle.jpg" alt="" width="25"
								height="28" />
						</td>
						<td class="leftDhxx">
							说明
						</td>
					</tr>
				</table>
			</div>
			<table width="99%" border="0" cellspacing="10" cellpadding="10"
				style="line-height: 15px">
			<tr>
				<td align="left">
					<a href="#"
						onclick="parent.mainFrame.location.href='${ctx}/abouts/about.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						&nbsp;&nbsp;说明
					</a>
				</td>
			</tr>
			<%-- <tr>
				<td align="left">
					<a href="#"
						onclick="parent.mainFrame.location.href='${ctx}/serial/serial.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						&nbsp;&nbsp;注册码
					</a>
				</td>
			</tr> --%>
		</table>
		</div>
	</body>
</html>
