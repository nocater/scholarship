<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
	<head>
		<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script language="javascript">
		function newrole(order){
			parent.mainFrame.location.href="${ctx}/college/query.action?order="+order;
		}
		</script>
	</head>

	<body>
	 	<div class="titleTop">
	 	<div style="background-image:url(${ctx}/images/leftDh.jpg)">
	   	  <table width="97%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt="" width="25" height="28" /></td>
	            <td class="leftDhxx">提示信息</td>
	          </tr>
	      </table>       
	    </div>
		<table width="99%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center">
					<table width="99%" border="0" cellspacing="10" cellpadding="10" >
						<!-- 空行 -->
						<tr>
			  			</tr>
			  				<td>
			  					<span>
			  						【近一年家庭变故】请填写去年申请后至今年申请一年时间内的变故
			  					</span>
			  				</td>
			  			<tr>
			  			</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
	</body>
</html>
