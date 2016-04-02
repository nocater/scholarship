<!DOCTYPE HTML>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<head>
		<title>奖/助学金登记系统-黄冈师范学院 V1.0</title>
		<link rel="shortcut icon" href="${ctx}/images/favicon.ico" /><!-- 地址栏图标 -->
		<link rel="Bookmark" href="${ctx}/images/favicon.ico" />	<!-- 选项卡图标 -->
		<script type="text/javascript">
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
			
			function reload()
			{
				setTimeout("location.reload(true)",60000); 
			}
			
		</script>
	</head>
	
	<frameset rows="100,*,40" frameborder="no" border="0" framespacing="0">
		<frame src="<tiles:getAsString name="topFrame"/>" name="topFrame"
			id="topFrame" frameborder="0" scrolling="No" noresize="noresize"
			title="topFrame" />
		<frameset cols="190,0,*" frameborder="no" border="0" framespacing="0">
			<frame 
				src="<tiles:getAsString name="leftFrame" />"
				name="leftFrame" id="leftFrame" frameborder="0" noresize="noresize"
				title="leftFrame" />
			<frame src="" frameborder="0" name="mapFrame" scrolling="NO"
				noresize="noresize" title="mapFrame" />
			<frame
				src="<tiles:getAsString name="mainFrame" />"
				name="mainFrame" id="mainFrame" frameborder="0" title="mainFrame" />
		</frameset>
		<frame src="<tiles:getAsString name="bottomFrame"/>" name="bottomFrame"
			id="bottomFrame" frameborder="0" scrolling="No" noresize="noresize"
			title="bottomFrame" />
	</frameset>
	<noframes>
		<body onload="load()">
		</body>
	</noframes>
</html>
