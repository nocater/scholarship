<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
	<head>
		<!-- 遮罩 -->
		<link href="${ctx}/styles/custom_ui.css" type="text/css" rel="stylesheet"/>
		<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
		<script language="javascript">
		function newrole(order){
			parent.mainFrame.location.href="${ctx}/college/query.action?order="+order;
		}
		
		//遮罩
		function reload() {
	        document.onmousedown = ContextMenu;
	        document.onmousewheel = scrollFunc;
	        $("#mack").addClass("ui-widget-overlay");
	    }
	    
	    function cancel()
	    {
	        document.onmousedown=cancelContext;
	      	//取消绑定事件
	        document.onmousewheel=null;
	        $("#mack").removeClass("ui-widget-overlay");
	    }
	    
	    function ContextMenu() {
	        if (event.button==1 || event.button==0 || event.button == 2 || event.button == 3) {
	            //alert("无法进行操作");
	            return false;
	        }
	    }
	    function cancelContext(){
	    	if(event.button==1 || event.button==0 || event.button == 2 || event.button == 3){
	    	}
	    }
	  	//设置滚轮滑动
		var scrollFunc = function() {
			//alert("无法进行操作");
			return false;
		};
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
		
		<div class="ui-overlay">
		<div id="mack"></div>
	</div>
	</body>
</html>
