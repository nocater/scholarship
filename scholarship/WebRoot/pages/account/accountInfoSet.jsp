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
			$("#accountForm").validationEngine();
		});
		
		var c_number2 = 0;//是否已操作过确认密码 0:否，1：是
	  	var c_number = 0; //是否已操作过密码 0:否，1：是
	  	//点击密码文本框触发
	  	function onClickPwd(){
	  	}
	  	//确认密码文本框内容被改变触发
	  	function onChangePwd(pwd){
	  		$("#password2").val("");
	  	}
	  	//确认密码文本框内容被改变触发
		function onChangePwd2(pwd2){
			$("#message").empty();
			$("#icon").empty();
			var pwd = $("#password").val();
			if(pwd==pwd2){
				var img = $("<img src='${ctx}/images/ok.png' height='20px' width='20px'/>");
				$("#icon").append(img).show("slow");
			}else{
				$("#message").append("两次密码不一致！");
			}
	  	}
		
	  	/* 验证Accno */
		function checkAccno(accountAccno){
			var accountId=$("#accountId").val();
			accountAccno = encodeURIComponent(encodeURIComponent(accountAccno));
			$.ajax({
				url:"${ctx}/account/checkAccountAccno.action",
				type:"GET",
				dataType:"text",
				data:"&accountAccno="+accountAccno+"&accountId="+accountId,
				success : function(result){
					if(result=="true"){
						$("#accountAccno_msg").addClass("spanred");
						$("#accountAccno_msg").html("该名称已占用");
						$("#accountAccno").val("");
					}else{
						$("#accountAccno_msg").removeClass("spanred");
						var accno = $("#accountAccno").val();
						if (accno != "")
							$("#accountAccno_msg").html(
									"<img border=0 src=\"${ctx}/images/ok.png\" />");
					}
				}
			});
		}
	  	
		/* 修改班级下拉列表  */
		function setGrades(id){
			$.ajax({
				url:"${ctx}/grade/queryGradesAjax",
				type:"GET",
				dataType:"json",
				data:"id="+id,
				success:function(result){
					$("#select-grade").empty();
					$("#select-grade").append("<option value='0'>--未选择--</option>");
					for(var i=0;i<result.length;i++){
						$("#select-grade").append("<option value="+result[i].id+">"+result[i].name+"</option>");
					}
				}
			});
		}
	  	
		/* 提交表单  */
		function save(){
			$("#collegeId").val($("#select-college option:selected").val());
			$("#gradeId").val($("#select-grade option:selected").val());
			$("#accountSex").val($("#select-sex option:selected").val());
			
			if ($("#password").val() != "" && $("#password2").val() != "") {
				if($("#password").val() == $("#password2").val()){
					$("#accountPassword").val($("#password").val());
				}else{
					alert("密码不一致！");
					return;
				}
			} else if($("#accountId").val()==0){
				alert("密码未填写！");
			}
			if($("#accountForm").validationEngine("validate")){ //校验通过禁用按钮防止二次提交
				$("#btnSave").attr("disabled",true);
				$("#accountForm").submit();
			}
		}
	</script>
  </head>
  
  <body>
	<s:form action="infoset" namespace="/account" method="post" theme="simple" id="accountForm">
		<s:hidden name="account.id" id="accountId"/>
		<!-- main table -->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- 左TABLE -->
				<td width="50%" valign="top">
				
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
								style="padding-top: 1px; padding-left: 1px; padding-right: 1px;">
								
								
							<tr><td class="r2titler" colspan="2" ><b>账户信息</b></td></tr>
							<!-- 分隔符黑线 -->
							<tr><td height="3px"></td></tr>
							
							<tr>
								<!-- left2 area -->
								<td width="99%" valign="top">
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
										
										<!-- 账户名称 -->
										<tr>
											<td align="right" style="font-size: 12px;" width="30%"><span class="spanred">*</span>姓名：</td>
											<td style="padding-left: 20px"><input name="account.name"
												type="text" id="accountName" maxlength="255"
												value="${account.name}"
												<span id="accountName_msg"></span></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"/>
										</tr>
										
										<!-- 角色 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>角色:</td>
											<td style="padding-left: 20px">
												<span id="">${role.name}</span>
											</td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 学院 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>学院:</td>
											<td style="padding-left: 20px">
												<s:hidden name="collegeId" id="collegeId"/>
												<select style="width:135px;" id="select-college" onchange="setGrades(this.value)">
													<option value="0">无</option>
													<c:forEach items="${collegeList}" var="c" varStatus="stat">
														<option value="${c.id}"
															<c:if test="${c.id eq college.id}"> selected="" </c:if>
														>${c.name}</option>
													</c:forEach>
													<%-- <s:iterator value="collegeList" var="c" status="stat">
														<option value="${c.id}"
															<c:if test="${c.id eq college.id}"> selected="" </c:if>
														>${c.name}</option>
													</s:iterator> --%>
												</select>
												<span id=""></span>
											</td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 班级 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>班级:</td>
											<td style="padding-left: 20px">
												<s:hidden name="gradeId" id="gradeId"/>
												<select style="width:135px;" id="select-grade">
													<option value="0">无</option>
													<c:forEach items="${gradeList}" var="g" varStatus="stat">
														<option value="${g.id}"
															<c:if test="${g.id eq grade.id}"> selected="" </c:if>
														>${g.name}</option>
													</c:forEach>
													<%-- <option value="0">${grade.id}</option>
													<s:iterator value="gradeList" var="g" status="stat">
														<option value="${g.id}"
															<c:if test="${g.id eq grade.id}"> selected="" </c:if>
														>${g.name}</option>
													</s:iterator>
													<option value="0">${grade.id}</option> --%>
												</select>
												<span id=""></span>
											</td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="2">
												<div class="xuxian"></div>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 性别 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>性别:</td>
											<td style="padding-left: 20px">
												<s:hidden name="accountSex" id="accountSex"/> 
												<select style="width:135px;" id="select-sex">
													<option
														<c:if test="${account.sex=='男'}"> selected="" </c:if>
													>男</option>
													<option
														<c:if test="${account.sex=='女'}"> selected="" </c:if>
													>女</option>
												</select>
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
										
										<!-- 手机 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>手机号码:</td>
											<td style="padding-left: 20px"><input name="account.phone"
												type="text" id="accountPhone" maxlength="255"
												value="${account.phone}"
												<span id="phone_msg"></span></td>
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
										
										<!-- 银行卡号 -->
										<tr>
											<td align="right" style="font-size: 12px;">QQ号码:</td>
											<td style="padding-left: 20px"><input name="account.qq"
												type="text" id="accountQQ" maxlength="255"
												value="${account.qq}"
												<span id="phone_msg"></span></td>
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
										
										<!-- 邮箱 -->
										<tr>
											<td align="right" style="font-size: 12px;">邮箱地址:</td>
											<td style="padding-left: 20px"><input name="account.email"
												type="text" id="accountEmail" maxlength="255"
												value="${account.email}"
												<span id="email_msg"></span></td>
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
						</table>
					</div>
				</td>
				<!-- 左右中间间隔 -->
				<td width="5px"></td>
				<!-- 右TABLE -->
				<td valign="top">
				
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
								style="padding-top: 1px; padding-left: 1px; padding-right: 1px;">
								
								
							<tr><td class="r2titler" width="100%"><b>登录信息</b></td></tr>
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
										
										<!-- 账户名称 -->
										<tr>
											<td align="right" style="font-size: 12px;" width="30%"><span class="spanred">*</span>账户名称:</td>
											<td style="padding-left: 20px"><input name="accountAccno"
												type="text" id="accountAccno" maxlength="255"
												value="${account.accno}" 
												<c:if test="${account.id != null and account.id !=0}">readonly = "readonly"</c:if>/><span id="accountAccno_msg"></span></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 密码 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>密码:</td>
											<td style="padding-left: 20px">
												<s:hidden name="accountPassword" id="accountPassword"/>
												<input type="password" id="password" onclick="onClickPwd()" onchange="onChangePwd(this.value)"
												/>
												<span  id="message" style="color:red"></span>
												<span id="icon" style="display: none"></span>
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

										<!-- 确认密码 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>确认密码:</td>
											<td style="padding-left: 20px">
												<input type="password" id="password2" onchange="onChangePwd2(this.value)"/>
											</td>
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
				<td width="50%">
					<table border="0" cellspacing="0" cellpadding="0">
						<!-- 空行 -->
						<tr>
							<td height="10px">
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" class="btnyh" id="btnSave"
									onclick="save();" value="保  存" />
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
