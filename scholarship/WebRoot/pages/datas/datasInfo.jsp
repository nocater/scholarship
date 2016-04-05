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
			$("#datasForm").validationEngine();
			if($("#message").val()!=""){
				alert($("#message").val());
			}
		});
		
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
			if($("#datasForm").validationEngine("validate")){ //校验通过禁用按钮防止二次提交
				$("#btnSave").attr("disabled",true);
				$("#datasForm").submit();
			}
		}
		/* 提交表单  */
		function apply(){
			$("#collegeId").val($("#select-college option:selected").val());
			$("#gradeId").val($("#select-grade option:selected").val());
			if($("#datasForm").validationEngine("validate")){ //校验通过禁用按钮防止二次提交
				$("#datasForm").prop("action","apply");
				$("#btnSave").attr("disabled",true);
				$("#datasForm").submit();
			}
		}
	</script>
  </head>
  
  <body>
	<s:form action="update" namespace="/datas" method="post" theme="simple" id="datasForm">
		<s:hidden name="datas.id" id="datasId"/>
		<s:hidden name="accountId" id="accountId"/>
		<s:hidden name="message" id="message"/>
		<!-- main table -->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- 个人信息 -->
				<td width="70%" valign="top">
				
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
								style="padding-top: 1px; padding-left: 1px; padding-right: 1px;">
								
								
							<tr><td class="r2titler" width="100%"><b>个人信息</b></td></tr>
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
										
										<!--  -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>姓名:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.name" id="datasName" value="${account.name}"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>学号:</td>
											<td style="padding-left: 20px"><input type="text" name="account.accno" id="accountAccno" value="${account.accno}"/><span id=""></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!--  -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>学院:</td>
											<td style="padding-left: 20px">
												<s:hidden name="collegeId" id="collegeId"/>
												<select style="width: 137px" id="select-college" onchange="setGrades(this.value)">
								  					<option value="0">--未选择--</option>
								  					<c:forEach items="${collegeList}" var="c" varStatus="stat">
								  						<option value="${c.id}"
								  							<c:if test="${account.college.id eq c.id}">selected=""</c:if>
								  						>${c.name}</option>
								  					</c:forEach>
							  					</select>
											</span></td>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>班级:</td>
											<td style="padding-left: 20px">
												<s:hidden name="gradeId" id="gradeId"/>
												<select style="width: 137px" id="select-grade">
								  					<option value="0">--未选择--</option>
								  					<c:forEach items="${gradeList}" var="g" varStatus="stat">
								  						<option value="${g.id}"
								  							<c:if test="${account.grade.id eq g.id}">selected=""</c:if>
								  						>${g.name}</option>
								  					</c:forEach>
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
										
										<!-- 性别申请 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>性别:</td>
											<td style="padding-left: 20px">
												<select style="width: 137px" id="select-sex">
													<option  value="男"
														<c:if test="${datas.sex eq '男'}">selected=""</c:if>
													>男</option>
													<option	value="女"
														<c:if test="${datas.sex eq '女'}">selected=""</c:if>
													>女</option>
												</select><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>申请:</td>
											<td style="padding-left: 20px"><input type="text"/><span id=""></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 身份证银行卡 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>身份证号:</td>
											<td style="padding-left: 20px"><input type="text" name="datas.idnumber" id="datasIdnumber" value="${datas.idnumber}"/><span id=""></td>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>银行卡号:</td>
											<td style="padding-left: 20px"><input type="text" name="datas.bankcard" id="datasBankcard" value="${datas.bankcard}"/><span id=""></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
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
			
			<!-- 状态信息 -->
			<tr>
				<td width="70%" valign="top">
				
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
								style="padding-top: 1px; padding-left: 1px; padding-right: 1px;">
								
								
							<tr><td class="r2titler" width="100%"><b>申请状态信息</b></td></tr>
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
										
										
										<tr>
											<td align="right" style="font-size: 12px;">当前申请信息:</td>
											<td style="padding-left: 20px">
												<span>${allpyMessage}</span>
											</td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										
										<tr>
											<td align="right" style="font-size: 12px;">以往申请信息:</td>
											<td style="padding-left: 20px">
												<span>${allliedMessage}</span>
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
								<td colspan="4">
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
				<!-- 家庭信息 -->
				<td width="70%" valign="top">
				
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
								style="padding-top: 1px; padding-left: 1px; padding-right: 1px;">
								
								
							<tr><td class="r2titler" width="100%"><b>家庭信息</b></td></tr>
							<!-- 分隔符黑线 -->
							<tr><td height="3px"></td></tr>
							
							<tr>
								<!-- left2 area -->
								<td width="100%" valign="top">
									<table width="100%" border="0" cellspacing="1" cellpadding="0"
										style="padding-top: 1px; padding-left: 1px; padding-right: 1px;">

										<!-- 空行 -->
										<tr>
											<td height="10px" style="margin-left: 10px"></td>
											<td></td>
										</tr>
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										
										<!-- 地区 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>地区:</td>
											<td style="padding-left: 20px">
												<select style="width: 137px" id="select-area">
													<option value="东部"
														<c:if test="${datas.area eq '东部'}"> selected="" </c:if>
													>东部</option>
													<option	value="中部"
														<c:if test="${datas.area eq '东部'}"> selected="" </c:if>
													>中部</option>
													<option	value="西部"
														<c:if test="${datas.area eq '东部'}"> selected="" </c:if>
													>西部</option>
												</select>
											</td>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>离县城距离:</td>
											<td style="padding-left: 20px"><input type="text" name="datas.distance" id="datasDistance" value="${datas.distance}"/><span id=""></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 家庭住址 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>家庭住址:</td>
											<td style="padding-left: 20px"	colspan="5">
												<input type="text" style="width: 400px" name="datas.address" id="datasAddress" value="${datas.address}"/><span id="">
											</td>
										</tr>
										
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 爷爷 -->
										<tr>
											<td align="right" style="font-size: 12px;">爷爷:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.name_grandfather" id="datasNameGrandfather" value="${datas.name_grandfather}"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;">收入(退休金或其它):</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.in_grandfather" id="datasInGranfather" value="${datas.in_grandfather}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">身体状况：</td>
											<td style="padding-left: 20px" colspan="3">
												<s:textarea cols="40" rows="5" name="datas.health_grandfather" id="datasHealthGrandfather">
											</s:textarea></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 奶奶 -->
										<tr>
											<td align="right" style="font-size: 12px;">奶奶:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.name_grandmother" id="datasNameGrandmother" value="${datas.name_grandmother}"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;">收入(退休金或其它):</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.in_grandmother" id="datasInGrandmother" value="${datas.in_grandmother}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">身体状况：</td>
											<td style="padding-left: 20px" colspan="3">
												<s:textarea cols="40" rows="5" name="datas.health_grandmother" id="datasHealthGrandmother"></s:textarea>
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
										
										<!-- 父亲 -->
										<tr>
											<td align="right" style="font-size: 12px;">父亲:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.name_father" id="datasNameFather" value="${datas.name_father}"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;">职业:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.career_father" id="datasCareerFather" value="${datas.career_father}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">月收入:</td>
											<td style="padding-left: 20px">
												<input type="text" style="width: 100px" name="datas.in_father" id="datasInFather" value="${datas.in_father}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">身体状况：</td>
											<td style="padding-left: 20px">
												<s:textarea cols="20" rows="5" name="datas.health_father" id="datasHealthFather"></s:textarea>
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
										
										<!-- 母亲 -->
										<tr>
											<td align="right" style="font-size: 12px;">母亲:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.name_mother" id="datasNameMother" value="${datas.name_mother}"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;">职业:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.career_mother" id="datasCareerMother" value="${datas.career_mother}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">月收入:</td>
											<td style="padding-left: 20px">
												<input type="text" style="width: 100px" name="datas.in_mother" id="datasInMother" value="${datas.in_mother}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">身体状况：</td>
											<td style="padding-left: 20px">
												<s:textarea cols="20" rows="5"
													name="datas.health_mother" id="datasHealth_mother"
												></s:textarea>
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
										
										<!-- 兄弟及其它 -->
										<tr>
											<td align="right" style="font-size: 12px;">兄弟姐妹情况:</td>
											<td style="padding-left: 20px" colspan="3">
												<s:textarea cols="50" rows="5" style="width: 300px"
													name="datas.others" id="datasOther"
												></s:textarea>
											</span></td>
											<%-- <c:if test="${datas.id gt 0}"> --%>
											<c:if test="${true}">
												<td align="right" style="font-size: 12px;">家庭变故:</td>
												<td style="padding-left: 20px" colspan="3">
													<s:textarea cols="50" rows="5" style="width: 300px"
														name="datas.accident" id="dataMemo"
													></s:textarea>
												</span></td>
											</c:if>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="8">
												<div class="xuxian"></div>
											</td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<!-- 收入 -->
										<tr>
											<td align="right" style="font-size: 12px;">家庭累计年收入(万):</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.in_family" id="datasInFamily" value="${datas.in_family}"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;">主要支出项:</td>
											<td style="padding-left: 20px" colspan="5">
												<%-- <input type="text" name="datas.out_main" id="datasOutMain" value="${datas.out_main}"/><span id=""> --%>
												<s:textarea cols="50" rows="5" style="width: 300px"
													name="datas.out_main" id="datasOutMain"
												></s:textarea>
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
										
										<!-- 结余 -->
										<tr>
											<td align="right" style="font-size: 12px;">结余:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.balance" id="datasBalance" value="${datas.balance}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">主要困难原因:</td>
											<td style="padding-left: 20px" colspan="5">
												<s:textarea cols="50" rows="5" style="width: 300px"
													name="datas.descripe" id="datasDescript"
												></s:textarea>
											</span></td>
										</tr>
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator">
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
									</table>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			
			<!-- 成绩信息 -->
			<tr>
				<td width="70%" valign="top">
				
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
								style="padding-top: 1px; padding-left: 1px; padding-right: 1px;">
								
								
							<tr><td class="r2titler" width="100%"><b>成绩信息</b></td></tr>
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
										
										
										<tr>
											<td align="right" style="font-size: 12px;">上年度班级平均成绩排名:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.score_place" id="datasScorePlace" value="${datas.score_place}"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;">上年度综合素质排名:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.quality_place" id="datasQualityPlace" value="${datas.quality_place}"/><span id="">
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
								<td colspan="4">
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
					<table width="" border="0" cellspacing="0" cellpadding="0">
						<!-- 空行 -->
						<tr>
							<td height="10px">
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" class="btnyh" id="btnSave"
									onclick="save();" value="保存信息" />
								<input type="button" class="btnyh" id="btnSave"
									onclick="apply();" value="提交申请" />
								<input type="button" class="btnyh" id="btnCancel"
									onclick="window.location.href='${ctx}/role/query.action?order=${order}';"
									value="取  消" />
								&nbsp;&nbsp;
								<span class="spanred">信息填写请确保完整准确</span>

							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</s:form>
  </body>
</html>
