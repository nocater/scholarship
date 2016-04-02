<%@ include file="/pages/commons/meta.jsp" %>
<%@ include file="/pages/commons/taglibs.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>您的操作被策略限制</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/error.css" media="screen" />
</head>

<body class="lgbody">
	<c:if test="${requestScope.type eq 'alert'}">
		<script language="javascript">
 	    parent.window.alert('您受到禁止/允许策略限制，不能访问此设备！');
 	    </script>
 	</c:if>
<center>
<div id="container">

<div class="clear"></div>

<!-- content star-->
<div class="news-error">禁止访问-策略限制</div>
<div class="m-content">
<div class="cp-content1">
    <div class="error-div"></div>
	<div class="clear"></div>
	<div class="clear"></div>
	<table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td>&nbsp;</td>
        <td width="632">&nbsp;</td>
      </tr>
      <tr>
        <td width="213" rowspan="3" valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr>
            <td width="59%" align="right"><img src="${ctx}/images/403_pic.jpg" width="78" height="75" /></td>
                <td width="41%" align="center"><span class="h_1">warning</span></td>
              </tr>
        </table></td>
        <td height="7"></td>
      </tr>
      <tr>
        <td rowspan="2" class="text-error">${requestScope.message }</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>
	</div>
</div>
<!-- content end-->

</div>
</center>
</body>
</html>
