<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色信息</title>
    <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet" type="text/css">
	
	<%-- <script type='text/javascript' src="${ctx}/scripts/jquery-2.1.0.min.js"></script> --%>
	<!-- dialog -->
	<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
	<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min_2.js"></script>
	<!-- 工具JS -->
	<script type="text/javascript" src="${ctx}/scripts/util.js"></script>
	<!-- 验证框架 -->
	<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
	<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function (){
			$("#scholarshipForm").validationEngine();
		});
		
		/* 提交表单  */
		function save(){
			if($("#scholarshipForm").validationEngine("validate")){ //校验通过禁用按钮防止二次提交
				$("#btnSave").attr("disabled",true);
				$("#scholarshipForm").submit();
			}
		}
		
	</script>
  </head>
  
  <body>
	<s:form action="update" namespace="/scholarship" method="post" theme="simple" id="scholarshipForm">
		<s:hidden name="scholarship.id" id="scholarshipId"/>
		<!-- main table -->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left1 area -->
				<td width="70%" valign="top">
				
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
								style="padding-top: 1px; padding-left: 1px; padding-right: 1px;">
								
								
							<tr><td class="r2titler" width="100%"><b>奖/助学金信息</b></td></tr>
							<!-- 分隔符黑线 -->
							<tr><td height="3px"></td></tr>
							
							<tr>
								<!-- left2 area -->
								<td width="100%" valign="top">
									<table width="100%" border="0" cellspacing="1" cellpadding="0"
										style="padding-top: 1px; padding-left: 1px; padding-right: 1px;">

										<!-- 空行 -->
										<tr>
											<td width="20%" height="10px"></td>
											<td></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										
										<!-- 类别名称 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>类别:</td>
											<td style="padding-left: 20px"><input name="scholarship.category"
												type="text" id="scholarshipCategory" maxlength="255"
												value="${scholarship.category}"/>
												<!-- class="validate[required,custom[onlyLetterNumber],custom[checkname]] text-input" -->
												<span id="collegeName_msg"></span></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 类别名称 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>等级:</td>
											<td style="padding-left: 20px"><input name="scholarship.level"
												type="text" id="scholarshipCategory" maxlength="255"
												value="${scholarship.level}"/>
												<!-- class="validate[required,custom[onlyLetterNumber],custom[checkname]] text-input" -->
												<span id="collegeName_msg"></span></td>
										</tr>
										
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 类别名称 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>奖金:</td>
											<td style="padding-left: 20px"><input name="scholarship.money"
												type="text" id="scholarshipCategory" maxlength="255"
												value="${scholarship.money}"/>
												<!-- class="validate[required,custom[onlyLetterNumber],custom[checkname]] text-input" -->
												<span id="collegeName_msg"></span></td>
										</tr>
										
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
									</table>
								</td>
							</tr>
							
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator">
								</td>
							</tr>
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator">
								</td>
							</tr>
							<!-- 虚线分割线 -->
							<tr>
								<td colspan="2">
									<div class="xuxian"></div>
								</td>
							</tr>
							<tr>
								<td height="3px">
								</td>
							</tr>
							
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="780px" border="0" cellspacing="0" cellpadding="0">
						<!-- 空行 -->
						<tr>
							<td height="10px">
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" class="btnyh" id="btnSave"
									onclick="save();" value="保  存" />
								<input type="button" class="btnyh" id="btnCancel"
									onclick="window.location.href='${ctx}/scholarship/query.action?order=${order}';"
									value="取  消" />
								&nbsp;&nbsp;
								<span class="spanred"></span>

							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</s:form>
	
  </body>
</html>
