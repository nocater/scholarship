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
		<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>
		<link href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet" type="text/css">
		<script language="javascript">
		
		//遮罩
		function reload() {
	        document.onmousedown = ContextMenu;
	        document.onmousewheel = scrollFunc;
	        $("#mack").addClass("ui-widget-overlay");
	    }
	    
	    function cancel()
	    {
	        document.onmousedown=cancelContext;
	        $("#mack").removeClass("ui-widget-overlay");
	    }
	    
	    function ContextMenu() {
	
	        if (event.button==1 || event.button==0 || event.button == 2 || event.button == 3) {
	            alert("无法进行操作");
	            return false;
	        }
	    }
	    function cancelContext(){
	    	if(event.button==1 || event.button==0 || event.button == 2 || event.button == 3){
	    	}
	    }
	    //设置滚轮滑动
		var scrollFunc = function() {
			alert("无法进行操作");
			return false;
		};
		//遮罩完
		
		
			jQuery(document).ready(function() {
				var order = GetQueryString("order");
				openMenu(3);
			});
			
			
			function reload() {
				document.onmousedown = ContextMenu;
				$("#mack").addClass("ui-widget-overlay");
			}
			
			function ContextMenu() {
				if (event.button == 2 || event.button == 3) {
				alert("切换中无法操作");
				return false;
				}
			}
			
			//菜单折叠
			function openMenu(index){
				
				var status = $("#"+index+"_table").is(":hidden");
				if(status){
					$("#"+index+"_img").attr("src",'${ctx}/images/jtTitleOpen.jpg');
					$("#"+index+"_table").show();
				}else{
					$("#"+index+"_img").attr("src",'${ctx}/images/jtTitle.jpg');
					$("#"+index+"_table").hide();
				}
				for(var i=1;i<=5;i++){
					if(i==index){
						continue;
					}
					$("#"+i+"_table").hide();
					$("#"+i+"_img").attr("src",'${ctx}/images/jtTitle.jpg');
				}
			}
			
			//获取URL地址中的参数
			function GetQueryString(name){
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)return  unescape(r[2]); return null;
			}
			
		</script>
	</head>

	<body>
	<div class="titleTop">
			<%-- <div style="background-image:url(${ctx}/images/leftDh.jpg)" onclick="openMenu('1');">
				<table width="97%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="25px">
							<img src="${ctx}/images/jtTitle.jpg" alt="" width="25"
								height="28" id="1_img"/>
						</td>
						<td class="leftDhxx">
							系统信息
						</td>
					</tr>
				</table>
			</div>
			<table width="99%" border="0" cellspacing="10" cellpadding="10"
				style="line-height: 15px;display: none;" id="1_table" >
			<tr>
				<td align="left">
					<a href="#"
						onclick="parent.mainFrame.location.href='${ctx}/setting-upgrade/upgradeUI.action';">
						&nbsp;
						<img src="${ctx}/images/arrow_03.gif"/>
						&nbsp;&nbsp;系统升级
					</a>
				</td>
			</tr>
		</table>
		<div style="background-image:url(${ctx}/images/leftDh.jpg)" onclick="openMenu('2');">
				<table width="97%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="25px">
							<img src="${ctx}/images/jtTitle.jpg" alt="" width="25"
								height="28" id="2_img"/>
						</td>
						<td class="leftDhxx">
							网络设置
						</td>
					</tr>
				</table>
			</div>
			<table width="99%" border="0" cellspacing="10" cellpadding="10"
				style="line-height: 15px;display: none;" id="2_table" >
			</table> --%>
			<div style="background-image:url(${ctx}/images/leftDh.jpg)" onclick="openMenu('3');">
				<table width="97%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="25px">
							<img src="${ctx}/images/jtTitle.jpg" alt="" width="25"
								height="28" id="3_img"/>
						</td>
						<td class="leftDhxx">
							系统设置
						</td>
					</tr>
				</table>
			</div>
			<table width="99%" border="0" cellspacing="10" cellpadding="10"
				style="line-height: 15px;display: none;" id="3_table" display: none;>
				<tr>
					<td align="left">
						<a href="#" 
							 onclick="parent.mainFrame.location.href='${ctx}/config/queryApplySwitch.action';">
							   &nbsp;
							   <img src="${ctx}/images/arrow_03.gif"/>
							   &nbsp;&nbsp;奖/助学金开关
						</a>
					</td>	
				</tr>
				<tr>
					<td align="left">
						<a href="#"
							onclick="parent.mainFrame.location.href='${ctx}/config/queryAlertInfo.action';">
							&nbsp;
							<img src="${ctx}/images/arrow_03.gif"/>
							&nbsp;&nbsp;警告信息
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<a href="#"
							onclick="parent.mainFrame.location.href='${ctx}/config/queryCodesSwitch.action';">
							&nbsp;
							<img src="${ctx}/images/arrow_03.gif"/>
							&nbsp;&nbsp;验证码
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<a href="#"
							onclick="parent.mainFrame.location.href='${ctx}/scholarship/query.action';">
							&nbsp;
							<img src="${ctx}/images/arrow_03.gif"/>
							&nbsp;&nbsp;奖/助学金类别
						</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="ui-overlay">
			<div id="mack"></div>
		</div>
	</body>
</html>
