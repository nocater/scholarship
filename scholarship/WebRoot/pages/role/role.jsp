<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<form action="role/query.action" namespace="/role" method="post">
			<input type="submit" value="Test"> <input type="text"
				name="roleId" value="9"> <input type="text" name="roleName"
				value="角色2">
		</form>
		${role.id}
		<br /> ${role.name}
		<br /> ${roleId}
	</body>
</html>
