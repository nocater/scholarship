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
			$("#gradeForm").validationEngine();
		});
		
		/* 提交表单  */
		function save(){
			$("#collegeId").val($("#select-college option:selected").val());
			$("#gradeEdubg").val($("#select-edubg").val());
			$("#gradeStatus").val($("#select-status").val());
			if($("#gradeForm").validationEngine("validate")){ //校验通过禁用按钮防止二次提交
				$("#btnSave").attr("disabled",true);
				$("#gradeForm").submit();
			}
		}
		
		function checkGradeName(gradeName){
			var gradeId=$("#gradeId").val();
			gradeName = encodeURIComponent(encodeURIComponent(gradeName));
			$.ajax({
				url:"${ctx}/grade/checkGradeName.action",
				type:"GET",
				dataType:"text",
				data:"&gradeName="+gradeName+"&gradeId="+gradeId,
				success : function(result){
					if(result=="true"){
						$("#gradeName_msg").addClass("spanred");
						$("#gradeName_msg").html("该名称已占用");
						$("#gradeName").val("");
					}else{
						$("#gradeName_msg").removeClass("spanred");
						var name = $("#gradeName").val();
						if (name != "")
							$("#gradeName_msg").html(
									"<img border=0 src=\"${ctx}/images/ok.png\" />");
					}
				}
			});
		}
	</script>
  </head>
  
  <body>
	<s:form action="update" namespace="/grade" method="post" theme="simple" id="gradeForm">
		<s:hidden name="grade.id" id="gradeId"/>
		<!-- main table -->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left1 area -->
				<td width="70%" valign="top">
				
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
								style="padding-top: 1px; padding-left: 1px; padding-right: 1px;">
								
								
							<tr><td class="r2titler" width="100%"><b>班级信息</b></td></tr>
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
										
										<!-- 角色名称 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>班级名称：</td>
											<td style="padding-left: 20px"><input name="grade.name"
												type="text" id="gradeName" maxlength="255"
												class="validate[required,custom[onlyLetterNumber],custom[checkname]] text-input"
												value="${grade.name}" onblur="checkGradeName(this.value)"/>
												<span id="gradeName_msg"></span></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 学院 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>学院:</td>
											<td style="padding-left: 20px">
												<s:hidden name="collegeId" id="collegeId"/>
												<select style="width:135px;" id="select-college">
													<c:forEach items="${collegeList}" var="c" varStatus="stat">
														<option value="${c.id}"
															<c:if test="${c.id eq grade.college.id}"> selected="" </c:if>
														>${c.name}</option>
													</c:forEach>
												</select>
												<span id=""></span>
											</td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 专业 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>专业:</td>
											<td style="padding-left: 20px"><input name="grade.major"
												type="text" id="gradeMajor" maxlength="255"
												value="${grade.major}"
												class="validate[required,custom[onlyLetterNumber],custom[checkname]] text-input"
												<span id="major_msg"></span></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 学历  -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>学历:</td>
											<td style="padding-left: 20px">
												<s:hidden name="gradeEdubg" id="gradeEdubg"/> 
												<select style="width:135px;" id="select-edubg">
													<option value="本科"
														<c:if test="${grade.edubg=='本科'}"> selected="" </c:if>
													>本科</option>
													<option value="专科"
														<c:if test="${grade.edubg=='专科'}"> selected="" </c:if>
													>专科</option>
												</select>
											</td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 年级 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>年级:</td>
											<td style="padding-left: 20px"><input name="grade.grade"
												type="text" id="gradeGrade" maxlength="255"
												value="${grade.grade}"
												class="validate[required] text-input"
												<span id="grade_msg"></span></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 入学年份 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>入学年份:</td>
											<td style="padding-left: 20px"><input name="grade.inyear"
												type="text" id="gradeGrade" maxlength="255"
												value="${grade.inyear}"
												class="validate[required] text-input"
												<span id="grade_msg"></span></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 学年制 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>学年制:</td>
											<td style="padding-left: 20px"><input name="grade.stay"
												type="text" id="gradeStay" maxlength="255"
												value="${grade.stay}"
												class="validate[required,custom[onlyNumberSp]] text-input"
												<span id="stay_msg"></span></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="2">
												<div class="xuxian"></div>
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 状态  -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>状态:</td>
											<td style="padding-left: 20px">
												<s:hidden name="gradeStatus" id="gradeStatus"/> 
												<select style="width:135px;" id="select-status">
													<option value="1"
														<c:if test="${grade.status eq 1}"> selected="" </c:if>
													>激活</option>
													<option value="-1"
														<c:if test="${grade.status eq -1}"> selected="" </c:if>
													>锁定</option>
												</select>
											</td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 备注 -->
										<tr>
											<td align="right" style="font-size: 12px;">备注：</td>
											<td style="padding-left: 20px"><s:textarea 
													name="grade.memo" id="grade.memo" cols="38" rows="5"
													cssStyle="width:300px"></s:textarea></td>
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
									onclick="window.location.href='${ctx}/grade/query.action?order=${order}';"
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
