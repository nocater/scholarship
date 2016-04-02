<%@ include file="/pages/commons/meta.jsp" %>
<%@ include file="/pages/commons/taglibs.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出错啦</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/error.css" media="screen" />
</head>

<body class="lgbody">
<center>
<div id="container">
<!-- content star-->
<div class="m-content">
<div class="cp-content1">
    <div class="error-div"></div>
	<div class="clear"></div>
	<div class="clear"></div>
	<table width="100%" border="0" cellpadding="5" cellspacing="1">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td width="200" rowspan="3"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr>
            <td align="right"><img src="${ctx}/images/500_pic.jpg" width="78" height="75" /></td>
                <td align="center"><span class="h_1">Error</span></td>
              </tr>
        </table></td>
        <td height="7"></td>
      </tr>
      <tr>
        <td width="645"><span class="h_1">您的请求中带有不合法参数,已被系统拦截!</span></td>
      </tr>
       <tr>
        <td width="645"><span class="h_1">可能原因:您提交的内容包含危险的攻击请求</span></td>
      </tr>
      <tr>
        <td rowspan="2" class="text-error">1、请单击<a href="javascript:history.go(-1);">刷新</a>按钮，或稍后再试。 <br />
2、如果错误依旧存在，请联系管理员，谢谢！ <br />
</td>
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
