<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
  <head>
  	<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	
	<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">

	<META HTTP-EQUIV="expires" CONTENT="0">
	<link href="${ctx}/styles/help.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
	<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
	
	<script type="text/javascript">
	jQuery(function(){
		function adjust(){
			if ($(window).width() > 1024 + 60) {
				$('#nav').removeClass('qn-slow');
			}
			else {
				$('#nav').addClass('qn-slow');
			}
		}
		
		$(window).resize(function(){
			adjust();
		});
		adjust();
	});
	</script>
  </head>
  
  <body>
  <table width="99%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 4px; margin-top: 2px">
			<tr>
			<td valign="top">
  <div style="width:100%;height:680px;border: 1px solid #5AA4D1;">
  <!--  左侧table-->
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td class="r2titler">
					 <b>使用手册</b>
					
				</td>
			</tr>

			<tr>
				<td>
    <div class="aboutContainer">
    	<div class="nav" style="margin-top:29px;margin-bottom:10px;padding-left:8px;padding-bottom:15px;">
  
    		<ul>
				<li>
			        <a href="#doc005">第一章 前言</a>
					<ol>
						<li><a href="#doc005">&nbsp;&nbsp;文档目的</a></li>
						<li><a href="#doc005">&nbsp;&nbsp;阅读对象</a></li>
						<li><a href="#doc006">&nbsp;&nbsp;文档结构</a></li>
						<li><a href="#doc006">&nbsp;&nbsp;技术支持</a></li>
						<li><a href="#doc006">&nbsp;&nbsp;意见反馈</a></li>
					</ol>
				</li>
				
				<li>
			        <a href="#doc007">第二章 初始化配置</a>
					<ol>
						<li><a href="#doc007">&nbsp;&nbsp;部署结构</a></li>
						<li><a href="#doc008">&nbsp;&nbsp;系统初始化登录</a></li>
						<li><a href="#doc009">&nbsp;&nbsp;系统登录步骤</a></li>
					</ol>
				</li>
				
				<li>
			        <a href="#doc013">第三章 快速部署</a>
					<ol>
						<li><a href="#doc013">&nbsp;&nbsp;系统管理员</a></li>
						<li><a href="#doc022">&nbsp;&nbsp;运维工程师</a></li>
					</ol>
				</li>
				
				<li>
			        <a href="#doc025">第四章 基本功能</a>
					<ol>
						<li><a href="#doc025">&nbsp;&nbsp;用户管理</a></li>
						<li><a href="#doc028">&nbsp;&nbsp;角色管理</a></li>
						<li><a href="#doc030">&nbsp;&nbsp;资源管理</a></li>
						<li><a href="#doc034">&nbsp;&nbsp;授权管理</a></li>
						<li><a href="#doc037">&nbsp;&nbsp;审计管理</a></li>
						<li><a href="#doc039">&nbsp;&nbsp;系统管理</a></li>
						<li><a href="#doc043">&nbsp;&nbsp;单点登录</a></li>
					</ol>
				</li>
				
				<li>
			        <a href="#doc046">第五章 进阶功能</a>
					<ol>
						<li><a href="#doc046">&nbsp;&nbsp;多种服务器认证方式</a></li>
						<li><a href="#doc049">&nbsp;&nbsp;添加web应用资源</a></li>
						<li><a href="#doc050">&nbsp;&nbsp;策略管理</a></li>
						<li><a href="#doc058">&nbsp;&nbsp;组态报表查阅</a></li>
						<li><a href="#doc061">&nbsp;&nbsp;运维脚本的使用</a></li>
						<li><a href="#doc062">&nbsp;&nbsp;审计实时监控与阻断</a></li>
						<li><a href="#doc064">&nbsp;&nbsp;双机热备</a></li>
						<li><a href="#doc065">&nbsp;&nbsp;数据备份</a></li>
						<li><a href="#doc066">&nbsp;&nbsp;应用发布服务器配置</a></li>
					</ol>
				</li>
			</ul>
    	</div>
    	<div id="block"></div>
    	<div id="content" style="margin-top:29px;margin-bottom:10px;padding-bottom:15px;OVERFLOW-y:auto;float:left;position:fixed; width:79%; height:90%; margin-left:-330px; top:5px; left:50%; z-index:20; _position:absolute; _top:expression(eval(document.documentElement.scrollTop + 135));">
    		<div id="doc001"></div>
    		<div id="doc002"></div>
    		<div id="doc003"></div>
    		<div id="doc004"></div>
    		<div id="doc005"></div>
    		<div id="doc006"></div>
    		<div id="doc007"></div>
    		<div id="doc008"></div>
    		<div id="doc009"></div>
    		<div id="doc010"></div>
    		<div id="doc011"></div>
    		<div id="doc012"></div>
    		<div id="doc013"></div>
    		<div id="doc014"></div>
    		<div id="doc015"></div>
    		<div id="doc016"></div>
    		<div id="doc017"></div>
    		<div id="doc018"></div>
    		<div id="doc019"></div>
    		<div id="doc020"></div>
    		<div id="doc021"></div>
    		<div id="doc022"></div>
    		<div id="doc023"></div>
    		<div id="doc024"></div>
    		<div id="doc025"></div>
    		<div id="doc026"></div>
    		<div id="doc027"></div>
    		<div id="doc028"></div>
    		<div id="doc029"></div>
    		<div id="doc030"></div>
    		<div id="doc031"></div>
    		<div id="doc032"></div>
    		<div id="doc033"></div>
    		<div id="doc034"></div>
    		<div id="doc035"></div>
    		<div id="doc036"></div>
    		<div id="doc037"></div>
    		<div id="doc038"></div>
    		<div id="doc039"></div>
    		<div id="doc040"></div>
    		<div id="doc041"></div>
    		<div id="doc042"></div>
    		<div id="doc043"></div>
    		<div id="doc044"></div>
    		<div id="doc045"></div>
    		<div id="doc046"></div>
    		<div id="doc047"></div>
    		<div id="doc048"></div>
    		<div id="doc049"></div>
    		<div id="doc050"></div>
    		<div id="doc051"></div>
    		<div id="doc052"></div>
    		<div id="doc053"></div>
    		<div id="doc054"></div>
    		<div id="doc055"></div>
    		<div id="doc056"></div>
    		<div id="doc057"></div>
    		<div id="doc058"></div>
    		<div id="doc059"></div>
    		<div id="doc060"></div>
    		<div id="doc061"></div>
    		<div id="doc062"></div>
    		<div id="doc063"></div>
    		<div id="doc064"></div>
    		<div id="doc065"></div>
    		<div id="doc066"></div>
    	</div>
    </div>
    </td>
    </tr>
    </table>
    </div>
    </td>
    </tr>
    </table>
  </body>
</html>
