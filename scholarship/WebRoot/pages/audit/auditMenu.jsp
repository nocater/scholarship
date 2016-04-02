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
	            <td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt="" width="25" height="28" /></td>
	            <td class="leftDhxx">审计信息</td>
	          </tr>
	      </table>       
	    </div>
	    
		<table width="99%" border="0" cellspacing="10" cellpadding="10" style="margin-left:6px">
			<tr >
				<td  align="left">
					<a href="#" class="hand"
						onclick="parent.mainFrame.location.href='${ctx}/systemAudit/query.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						&nbsp;内部审计
					</a>
				</td>
			</tr>
			<tr  align="left">
				<td >
					<a href="#" class="hand"
						onclick="parent.mainFrame.location.href='${ctx}/conductAudit/query.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						&nbsp;行为审计
					</a>
				</td>
			</tr>
			<!-- SQL审计和交易审计是新点定制研发，当前版本为：Ver 3.6.1.198
			<tr  align="left">
				<td >
					<a href="#" class="hand"
						onclick="parent.mainFrame.location.href='${ctx}/conductAudit/query.action?dealType=1';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						&nbsp;交易审计
					</a>
				</td>
			</tr>
			<tr  align="left">
				<td >
					<a href="#" class="hand"
						onclick="parent.mainFrame.location.href='${ctx}/conductAudit/query.action?dbType=1';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						&nbsp;SQL语句审计
					</a>
				</td>
			</tr> -->
			<!--<tr align="left">
				<td >
					<a href="#" class="hand"
						onclick="parent.mainFrame.location.href='${ctx}/dbAudit/query.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif" />
						&nbsp;数据库审计
					</a>
				</td>
			</tr>-->
			<!--<tr align="left">
				<td >
					<a href="#" class="hand"
						onclick="parent.mainFrame.location.href='${ctx}/auditLogExport/query.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif" />
						&nbsp;导出日志审计
					</a>
				</td>
			</tr>
		--></table>
	</body>
</html>
