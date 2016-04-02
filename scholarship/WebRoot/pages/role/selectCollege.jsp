<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	
  </head>
  
  <body>
	   	<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center">
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator">
					请选择：
				</td>
			</tr>
			<tr height="6px"><td></td></tr>
			<tr>
				<td align="right">
					<table width="80%" border="0" cellspacing="0" cellpadding="0" align="left">
						<s:iterator value="collegeList" status="stat">
							<tr>
								<td width="5%">
									<input type="checkbox" id="collegeId" name="collegeId" value="${id}" />
								</td>
								<td>&nbsp;</td>
								<td width="95%" id="td-${id}" align="left" style="font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;font-size: 1.1em;">
									${name}
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</tr>

		</table>
  </body>
</html>
