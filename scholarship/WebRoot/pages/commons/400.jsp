<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
  <head>
  	<title>新增资源异常</title>
    <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css" />
    <script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
    <script type='text/javascript' src="${ctx}/scripts/util.js"></script>
    <script language="javascript">

    </script>
  </head>
  
  <body>
  	<center>
  	<br/>
  	<p><s:actionmessage /> <font color="red"><s:fielderror></s:fielderror></font></p>
  	</center>

  </body>
</html>
