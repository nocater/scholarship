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
	<!-- dialog 已引用 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css"> -->
	
	<!-- 工具JS -->
	<script type="text/javascript" src="${ctx}/scripts/util.js"></script>
	<!-- 验证框架 -->
	<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
	<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
	<!-- 学院树 -->
	<script type="text/javascript" src="${ctx}/scripts/custom_ui_tree.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/custom_ui_action.js"></script>
	<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/styles/custom_ui.css" />
	
	<script type="text/javascript">
		$(document).ready(function (){
			$("#roleFrame").validationEngine();
			/* 选择学院对话框  */
			$("#dialog-selectCollege").dialog({
				autoOpen : false,
				width : 400,
				height : 450,
				buttons : {
					"确定" : function() {
						$(this).dialog("close");
						selColDone();
					},
					"取消" : function() {
						$(this).dialog("close");
					}
				}
			});
			/* 选择班级对话框  */
			$("#dialog-selectGrade").dialog({
				autoOpen : false,
				width : 600,
				height : 450,
				buttons : {
					"确定" : function() {
						$(this).dialog("close");
						selGraDone();
					},
					"取消" : function() {
						$(this).dialog("close");
					}
				}
			});
			/* 创建学院树 */
			$.create_tree_html( {
				id : "checkTreeColleges",
				autoOpen : true,
				edit : false,
				chidren : '${collegesTree}'
			});
		});
		
		/* 添加按钮选择学院 */
		function showSelectCollegeDlg(){
			preshowCollege();
			$("#dialog-selectCollege").dialog("open");
		}
		/* 删除按钮选择学院 */
		function delCollegeOption(){
			/* 更新已选择  */
			var c = $("#collegesSelect option:selected");
			var str = c.val()+"/"+c.text()+",";
			var cols = $("#select_colleges").val();
			cols.replace(str,"");
			$("#select_colleges").val(cols);
			/* alert($("#select_colleges").val()); */
			/* 取消对勾  */
			$("input[@type='checkbox'][name='collegeId']").each(function() {
				if(this.value==$("#collegesSelect option:selected").val()){
					$(this).prop("checked", false);
					dealCollege($(this));
				}
			});
			/* 删除option */
			$("#collegesSelect option:selected").remove();
		}
		/* 添加按钮选择班级 */
		function showSelectGradeDlg(){
			preshowGrade();
			$("#dialog-selectGrade").dialog("open");
		}
		/* 删除按钮选择班级 */
		function delGradeOption(){
			/* 更新已选择  */
			var g = $("#gradesSelect option:selected");
			var str = g.val()+"/"+g.text()+",";
			var gras = $("#select_grades").val();
			gras.replace(str,"");
			$("#select_colleges").val(gras);
			/* alert($("#select_colleges").val()); */
			/* 取消对勾  */
			$("input[@type='checkbox'][name='gradeId']").each(function() {
				if(this.value==$("#gradesSelect option:selected").val()){
					$(this).prop("checked", false);
					dealGrade($(this));
				}
			});
			/* 删除option */
			$("#gradesSelect option:selected").remove();
		}
		/* 显示选择学院前数据处理 */
		function preshowCollege(){
			$("#collegeall").prop("checked",false);
			//已选中的学院
			var cols = "";
			$("#collegesSelect option").each(function (){
				if(this.text.indexOf("已选择")>-1) return;
				cols+=this.value+"/"+this.text+",";
			});
			$("#select_colleges").val(cols);
			
			$.ajax({
				type:"GET",
				url:"${ctx}/role-selectCollege/queryAllCollegeAjax.action",
				dataType:"json",
				success : function(result){
					var colleges = $("#select_colleges").val();
					var html = "<table width=\"80%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">"
							 + "<tr><td class=\"td_detail_separator\">请选择：</td></tr>"
							 + "<tr height=\"6px\"><td></td></tr>"
							 + "<tr><td align=\"right\">"
							 + "<table width=\"80%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\">";
					for(var i = 0; i<result.length;i++){
						/* alert((result[i].id+"/"+result[i].name+",")+"--"+colleges.trim()); */
						html += "<tr>";
						if(colleges.indexOf(result[i].id+"/"+result[i].name+",")>-1){
							html+="<td width=\"5%\"><input type=\"checkbox\" id=\"collegeId\" name=\"collegeId\" onclick=\"dealCollege($(this));\" value=\""+result[i].id+"\" checked=\"checked\" /></td>";
						}else{
							html+="<td width=\"5%\"><input type=\"checkbox\" id=\"collegeId\" name=\"collegeId\" onclick=\"dealCollege($(this));\" value=\""+result[i].id+"\" /></td>";
						}
						html+="<td>&nbsp;</td>";
						html+="<td width=\"95%\" id=\"td-"+result[i].id+"\" align=\"left\" style=\"font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;font-size: 1.1em;\">"+result[i].name+"</td>";
						html += "</tr>";
					}
					html += "</table>";
					html += "</td>";
					html += "</tr>";
					html += "</table>";
					$("#frmCollegeDiv").empty();
					$("#frmCollegeDiv").append(html);
				}
			});
		}
		
		/* 学院全选按钮 */
		function collegeAll(){
			$("#select_colleges").val("");
			var flag = $("#collegeall").prop("checked");
			$("input[@type='checkbox'][name='collegeId']").each(function() {
						if(flag){
							$(this).prop("checked", true);
							dealCollege($(this));
						}else{
							$(this).prop("checked", false);
							dealCollege($(this));
						}
					});
		}
		
		/* 处理学院选择框 */
		function dealCollege(e){
			var colleges = $("#select_colleges").val();
			var str_col = e.val()+"/"+$.trim(e.parent().next().next().text())+",";
			if(e.prop("checked")){
				colleges+=str_col;
			}else{
				colleges = colleges.replace(str_col,"");
			}
			$("#select_colleges").val(colleges);
			/* alert($("#select_colleges").val()); */
		}
		
		/* 学院添加确定按钮 */
		function selColDone(){
			var c = $("#collegesSelect");
			/* 清空  */
			c.empty();
			c.append("<option value=\"\" disabled=\"true\">已选择学院：</option>");
			var colleges = $("#select_colleges").val();	
			//console.log(colleges);
			var t_col = colleges.split(",");
			for(var i=0;i<t_col.length-1;i++){
				c.append("<option class='selectEmployee' value=\""
						+t_col[i].split("/")[0]
						+"\">"
						+t_col[i].split("/")[1]
						);
			}
		}
		
		/* 班级全选按钮 */
		function gradeAll(){
			$("#select_grades").val("");
			var flag = $("#gradeall").prop("checked");
			$("input[@type='checkbox'][name='gradeId']").each(function() {
						if(flag){
							$(this).prop("checked", true);
							dealGrade($(this));
						}else{
							$(this).prop("checked", false);
							dealGrade($(this));
						}
					});
		}
		
		/* 处理班级选择框 */
		function dealGrade(e){
			var grades = $("#select_grades").val();
			var str_gra = e.val()+"/"+$.trim(e.parent().next().next().text())+",";
			if(e.prop("checked")){
				grades+=str_gra;
			}else{
				grades = grades.replace(str_gra,"");
			}
			$("#select_grades").val(grades);
			/* alert($("#select_grades").val()); */
		}
		
		/* 显示选择班级前数据处理 */
		function preshowGrade(){
			$("#gradeall").prop("checked",false);
			//已选中的学院
			var gras = "";
			$("#gradesSelect option").each(function (){
				if(this.text.indexOf("已选择")>-1) return;
				gras+=this.value+"/"+this.text+",";
			});
			$("#select_grades").val(gras);
			
			/* alert($("#select_grades").val()); */
			
			$.ajax({
				type:"GET",
				url:"${ctx}/role-selectGrade/queryAllGradeAjax.action",
				dataType:"json",
				success : function(result){
					var grades = $("#select_grades").val();
					var html = "<table width=\"80%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">"
							 + "<tr><td class=\"td_detail_separator\">请选择：</td></tr>"
							 + "<tr height=\"6px\"><td></td></tr>"
							 + "<tr><td align=\"right\">"
							 + "<table width=\"80%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\">";
					for(var i = 0; i<result.length;i++){
						/* alert((result[i].id+"/"+result[i].name+",")+"--"+colleges.trim()); */
						html += "<tr>";
						if(grades.indexOf(result[i].id+"/"+result[i].name+",")>-1){
							html+="<td width=\"5%\"><input type=\"checkbox\" id=\"gradeId\" name=\"gradeId\" onclick=\"dealGrade($(this));\" value=\""+result[i].id+"\" checked=\"checked\" /></td>";
						}else{
							html+="<td width=\"5%\"><input type=\"checkbox\" id=\"gradeId\" name=\"gradeId\" onclick=\"dealGrade($(this));\" value=\""+result[i].id+"\" /></td>";
						}
						html+="<td>&nbsp;</td>";
						html+="<td width=\"95%\" id=\"td-"+result[i].id+"\" align=\"left\" style=\"font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;font-size: 1.1em;\">"+result[i].name+"</td>";
						html += "</tr>";
					}
					html += "</table>";
					html += "</td>";
					html += "</tr>";
					html += "</table>";
					$("#frmGradeDiv").empty();
					$("#frmGradeDiv").append(html);
				}
			});
		}
		/* 年级添加确定按钮 */
		function selGraDone(){
			var g = $("#gradesSelect");
			/* 清空  */
			g.empty();
			g.append("<option value=\"\" disabled=\"true\">已选择班级：</option>");
			var grades = $("#select_grades").val();	
			//console.log(grades);
			var t_gra = grades.split(",");
			for(var i=0;i<t_gra.length-1;i++){
				g.append("<option class='selectEmployee' value=\""
						+t_gra[i].split("/")[0]
						+"\">"
						+t_gra[i].split("/")[1]
						);
			}
		}
		
		/* 提交表单  */
		function save(){
			$("#colleges").val("");
			$("#collegesSelect").children("option").each(function() {
				if ($(this).parent("select").size() > 0) {
					$("#colleges").val($("#colleges").val() + $(this).val() + ",");
				}
			});
			$("#grades").val("");
			$("#gradesSelect").children("option").each(function() {
				if ($(this).parent("select").size() > 0) {
					$("#grades").val($("#grades").val() + $(this).val() + ",");
				}
			});
			if($("#roleFrame").validationEngine("validate")){ //校验通过禁用按钮防止二次提交
				$("#btnSave").attr("disabled",true);
				$("#roleFrame").submit();
			}
		}
		
		function checkRoleName(roleName){
			var roleId=$("#roleId").val();
			roleName = encodeURIComponent(encodeURIComponent(roleName));
			//alert(roleName);
			$.ajax({
				url:"${ctx}/role/checkRoleName.action",
				type:"GET",
				dataType:"text",
				data:"&roleName="+roleName+"&roleId="+roleId,
				success : function(result){
					if(result=="true"){
						$("#RoleName_msg").addClass("spanred");
						$("#RoleName_msg").html("该名称已占用");
						$("#roleName").val("");
					}else{
						$("#RoleName_msg").removeClass("spanred");
						var roleName = $("#roleName").val();
						if (roleName != "")
							$("#RoleName_msg").html("<img border=0 src=\"${ctx}/images/ok.png\" />");
					}
				}
			});
		}
		
		// 学院树点击
		function custom_action(evn) {
			changeStyle("checkTreeColleges", evn);
			var collegeId = $(evn).prev().children().val();
			$.ajax({
				type : "GET",
				url:"${ctx}/role-selectGrade/queryByCollegeIdAjax.action",
				dataType : "json",
				data : {"collegeId":collegeId},
				success : function(result){
					var grades = $("#select_grades").val();
					var html = "<table width=\"80%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">"
							 + "<tr><td class=\"td_detail_separator\">请选择：</td></tr>"
							 + "<tr height=\"6px\"><td></td></tr>"
							 + "<tr><td align=\"right\">"
							 + "<table width=\"80%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\">";
					for(var i = 0; i<result.length;i++){
						/* alert((result[i].id+"/"+result[i].name+",")); */
						html += "<tr>";
						if(grades.indexOf(result[i].id+"/"+result[i].name+",")>-1){
							html+="<td width=\"5%\"><input type=\"checkbox\" id=\"gradeId\" name=\"gradeId\" onclick=\"dealGrade($(this));\" value=\""+result[i].id+"\" checked=\"checked\" /></td>";
						}else{
							html+="<td width=\"5%\"><input type=\"checkbox\" id=\"gradeId\" name=\"gradeId\" onclick=\"dealGrade($(this));\" value=\""+result[i].id+"\" /></td>";
						}
						html+="<td>&nbsp;</td>";
						html+="<td width=\"95%\" id=\"td-"+result[i].id+"\" align=\"left\" style=\"font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;font-size: 1.1em;\">"+result[i].name+"</td>";
						html += "</tr>";
					}
					html += "</table>";
					html += "</td>";
					html += "</tr>";
					html += "</table>";
					/* $("#frmGradeDiv").empty();
					$("#frmGradeDiv").append(html);
					
					$("#frmEmployee").css("display","none");
					$("#frmEmployee").css("visibility","hidden"); */
					
					$("#frmGradeDiv").empty();
					$("#frmGradeDiv").append(html);
					$("#frmGradeDiv").css("display","block");
				}
			});
		}
	</script>
  </head>
  
  <body>
	<s:form action="update" namespace="/role" method="post" theme="simple" id="roleFrame">
		<s:hidden name="role.id" id="roleId"/>
		<!-- main table -->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left1 area -->
				<td width="70%" valign="top">
				
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
								style="padding-top: 1px; padding-left: 1px; padding-right: 1px;">
								
								
							<tr><td class="r2titler" width="100%"><b>角色信息</b></td></tr>
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
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>角色名称：</td>
											<td style="padding-left: 20px"><input name="role.name"
												type="text" id="roleName" maxlength="255"
												class="validate[required,custom[onlyLetterNumber],custom[checkname]] text-input"
												value="${role.name}" onblur="checkRoleName(this.value)"
												<c:if test="${role.id==1||role.id==2}">disabled="true"</c:if>/><span id="RoleName_msg"></span></td>
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
													name="role.memo" id="role.memo" cols="38" rows="5"
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
							
							
							<!-- 关联学院及班级 -->
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0" height="250px">
										<tr>
											<!-- 关联学院 -->
											<td align="right" width="20%" style="font-size: 12px;">
												学院：
											</td>
											<td>
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td style="padding-left: 20px">
															<select name="collegesSelect" id="collegesSelect"
																class="employeesSelect" style="width: 230px;height: 120px" size="5"
																multiple="multiple">

																<c:if test="${role.id>2}">
																	<option disabled="true" value="">
																		已选择学院：
																	</option>
																</c:if>
																<c:if test="${role.id==1}">
																	<option value="-1">
																		已选择所有的学院
																	</option>
																</c:if>
																<c:if test="${role.id==2}">
																	<option disabled="true" value="-1">
																		无
																	</option>
																</c:if>
																<c:forEach items="${role.collegeList}" var="c" varStatus="cstat">
																	<c:if test="${c!=null}">
																	<option value="${c.id}">
																		${c.name}
																	</option>
																</c:if>
																</c:forEach>
															</select>
															
															<s:hidden name="colleges" id="colleges" />
															<input type="hidden" id="select_colleges"/>
														</td>
													</tr>
													<tr>
														<td height="10px">
														</td>
													</tr>
													<tr>
														<td style="padding-left: 20px">
															<input type="button" value="" class="btnadd"
																onclick="showSelectCollegeDlg();"
																<c:if test="${role.id<3}">disabled="true"</c:if> />
															<input type="button" value="" class="btndel"
																onclick="delCollegeOption();"
																<c:if test="${role.id<3}">disabled="true"</c:if> />
														</td>
													</tr>
												</table>
											</td>
											
											<!-- 关联班级 -->
											<td align="left" style="font-size: 12px;">
												班级:
											</td>
											<td>
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td>
															<select name="gradesSelect" id="gradesSelect"
																style="width: 230px;height: 120px" size="5" multiple="multiple">
																<c:if test="${role.id>2}">
																	<option disabled="true" value="">
																		已选择班级：
																	</option>
																</c:if>
																<c:if test="${role.id==1}">
																	<option value="-1">
																		已选择所有的班级
																	</option>
																</c:if>
																<c:if test="${role.id==2}">
																	<option disabled="true" value="-1">
																		无
																	</option>
																</c:if>
																<c:forEach items="${role.gradeList}" var="g" varStatus="gstat">
																	<c:if test="${g!=null}">
																		<option value="${g.id}">
																			${g.name}
																		</option>
																	</c:if>
																</c:forEach>
															</select>
															
															<s:hidden name="grades" id="grades" />
															<input type="hidden" id="select_grades"/>
														</td>
													</tr>
													<tr>
														<td height="10px"></td>
													</tr>
													<tr>
														<td>
															<input type="button" value="" class="btnadd"
																onclick="showSelectGradeDlg();"
																<c:if test="${role.id==1}">disabled="true"</c:if> />
															<input type="button" value="" class="btndel"
																onclick="delGradeOption();"
																<c:if test="${role.id==1}">disabled="true"</c:if> />
														</td>
													</tr>
												</table>
											</td>
											
										</tr>
									</table>
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
									onclick="window.location.href='${ctx}/role/query.action?order=${order}';"
									value="取  消" />
								&nbsp;&nbsp;
								<span class="spanred"> 注:角色修改完成后请重新登录</span>

							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</s:form>
	
	<!-- 选择学院弹出框 -->
	<div id="dialog-selectCollege" title="选择学院">
		<input type="hidden" id="hid-option-name" />
		<input type="hidden" id="hid-option-id" />
		<div id="tab_2" style="display: block;">
			<table width="100%" height="95%" border="0" cellspacing="3" cellpadding="0" 
				align="center">
				<tr>
					<%-- <td valign="top">
						<div class="big">
							<div class="left">
								<div style="height:10px;"></div>
								<div class="hand" style="margin-left: 10px">
									&nbsp;&nbsp;&nbsp;
									<img src="${ctx}/images/node_left.png" />
									&nbsp;
									<span id="allRes" onclick="checkCollege('all')">所有学院</span>
								</div>
								<div style="height:5px"></div>
								<div class="hand" style="margin-left: 10px">
									&nbsp;&nbsp;&nbsp;
									<img src="${ctx}/images/node_left.png" />
									&nbsp;
									<span id="noRes" onclick="checkCollege('no')">未分组</span>
								</div>
								<div id="checkTreeCol"></div>
							</div>
						</div>
					</td> 
					<td valign="top" class="myTd">
					&nbsp;
					</td>--%>
					<td valign="top" width="100%">
					<input type="checkbox" name="ids" id="collegeall" value="" onclick="collegeAll()" class="check-box"/>全选
						<%-- <iframe src='${ctx}/role-selectCollege/queryAll.action'
							id="frmCollege" name="frmCollege" frameborder="0" height="100%" width="100%"
							style="visibility: visible; display: block;">
						</iframe> --%>
						<div id="frmCollegeDiv" width="100%"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- 选择班级弹出框 -->
	<div id="dialog-selectGrade" title="选择班级">
		<input type="hidden" id="hid-option-name" />
		<input type="hidden" id="hid-option-id" />
		<!--  主table1-->
		<div id="tab_4" style="display: block;">
			<table width="100%" height="95%" border="0" cellspacing="3" cellpadding="0" 
				align="center">
				<tr>
					<td valign="top">
						<div class="big">
							<div class="left">
								<div style="height:10px;"></div>
								<div class="hand" style="margin-left: 10px">
									&nbsp;&nbsp;&nbsp;
									<img src="${ctx}/images/node_left.png" />
									&nbsp;
									<input type="hidden" value=""><!-- 显示所有班级 -->
									<span id="allRes" onclick="custom_action(this)">所有班级</span>
								</div>
								<div style="height:5px"></div>
								<%-- <div class="hand" style="margin-left: 10px">
									&nbsp;&nbsp;&nbsp;
									<img src="${ctx}/images/node_left.png" />
									&nbsp;
									<span id="noRes" onclick="checkCollege('no')">未分组</span>
								</div> --%>
								<div id="checkTreeColleges"></div>
							</div>
						</div>
					</td>
					<td valign="top" class="myTd">
					&nbsp;
					</td>
					<td valign="top" width="60%" height="100%">
					<input type="checkbox" name="ids" id="gradeall" value="" onclick="gradeAll()" class="check-box"/>全选
						<%-- <iframe src='${ctx}/role-selectGrade/queryAll.action'
							id="frmGrade" name="frmGrade" frameborder="0" height="100%" width="100%"
							style="visibility: visible; display: block;">
						</iframe> --%>
						<div id="frmGradeDiv" width="100%"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
  </body>
</html>
