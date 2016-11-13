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
			deal();
			if($("#collegeId").val()=='0'||$("#gradeId").val()=='0'){
				alert("学院班级不能为空");return;
			}
			if($("#datasForm").validationEngine("validate")){ //校验通过禁用按钮防止二次提交
				$("#btnSave").attr("disabled",true);
				$("#datasForm").submit();
			}
		}
		/* 提交表单  */
		function apply(){
			deal();
			if($("#collegeId").val()=='0'||$("#gradeId").val()=='0'){
				alert("学院班级不能为空");return;
			}
			if($("#datasForm").validationEngine("validate")){ //校验通过禁用按钮防止二次提交
				$("#datasForm").prop("action","apply");
				$("#btnSave").attr("disabled",true);
				$("#datasForm").submit();
			}
		}
		
		function deal(){
			$("#collegeId").val($("#select-college option:selected").val());
			$("#gradeId").val($("#select-grade option:selected").val());
			$("#datasArea").val($("#select-area").val());
			$("#datasIsLoan").val($("#select-isLoan").val());
			$("#datasSex").val($("#select-sex").val());
			$("#accountSex").val($("#select-sex").val());
			$("#accountName").val($("#datasName").val());
		}
		
		//遮罩
	    function ALERT(){
	    	if($("#message").val()!=""){
				showMessage($("#message").val());
			}else if($("#ALERT").val()!=""){
				document.onmousedown = ContextMenu;
			    document.onmousewheel = scrollFunc;
		    	showMessage($("#ALERT").val());
				$("#mack").attr("style","display:block");
				$("#mack").addClass("ui-widget-overlay");
				$("#mack").css('height','100%');
				$("#mack").css('width','99.5%');
				$("#mack").css('margin-right','2px');
				parent.frames[0].reload();
	            parent.frames[1].reload();
	            parent.frames[4].reload();
	            setTimeout(cancel,3000);
			}
		}
		
	    function showMessage(test){
  	        var docHe =  ($(document).height()/2)-60;
  	        var docWi =  ($(document).width()/2)-200;
  	        $("#tipDiv").css({top:60,left:docWi});
  	        $("#msg").text(test);
  	        $("#tipDiv").show();
         }
         
         //遮罩取消
         function cancel(){
        	//取消绑定事件
        	document.onmousedown = null;
 	        document.onmousewheel=null;
         	//$("#tipDiv").hide();
            parent.frames[0].cancel();
            parent.frames[1].cancel();
            parent.frames[4].cancel();
            //当前Frame
            //document.onmousedown=cancelContext;
 			$("#mack").removeClass("ui-widget-overlay");
         }
         
         function ContextMenu() {
 	        if (event.button==1 || event.button==0 || event.button == 2 || event.button == 3) {
 	            alert("无法进行操作");
 	            return false;
 	        }
 	    }
 	    function cancelContext(){
 	    	if(event.button==1 || event.button==0 || event.button == 2 || event.button == 3){
 	    	}
 	    }
 	  	//设置滚轮滑动
 		var scrollFunc = function() {
 			alert("无法进行操作");
 			return false;
 		};
	</script>
  </head>
  
  <body onload="ALERT();">
	<s:form action="update" namespace="/datas" method="post" theme="simple" id="datasForm">
		<s:hidden name="datas.id" id="datasId"/>
		<s:hidden name="accountId" id="accountId"/>
		<s:hidden name="message" id="message"/>
		<s:hidden name="ALERT" id="ALERT"/>
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
												<s:hidden name="accountName" id="accountName"/>
												<input type="text" name="datas.name" id="datasName" value="${account.name}" 
												class="validate[required,custom[onlyLetterNumber],custom[checkname]] text-input"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>学号:</td>
											<td style="padding-left: 20px"><input type="text" readonly="readonly" id="accountAccno" value="${account.accno}"
											/><span id=""></td>
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
												<select style="width: 137px" id="select-college" onchange="setGrades(this.value)" disabled="disabled">
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
												<select style="width: 137px" id="select-grade" disabled="disabled">
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
												<s:hidden name="datas.sex" id="datasSex"/>
												<s:hidden name="accountSex" id="accountSex"/>
												<select style="width: 137px" id="select-sex">
													<option  value="男"
														<c:if test="${datas.sex eq '男'}">selected=""</c:if>
													>男</option>
													<option	value="女"
														<c:if test="${datas.sex eq '女'}">selected=""</c:if>
													>女</option>
												</select><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;">民族:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.nation" id="datasNation" value="${datas.nation}"/><span id="">
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
										
										<!-- 身份证银行卡 -->
										<tr>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>手机号:</td>
											<td style="padding-left: 20px">
												<input type="text" name="accountPhone" id="accountPhone" value="${account.phone}"
												class="validate[required] text-input"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;"><span class="spanred"></span>QQ号:</td>
											<td style="padding-left: 20px">
												<input type="text" name="accountQq" id="accountQq" value="${account.qq}"
											/><span id=""></td>
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
											<td style="padding-left: 20px"><input type="text" name="datas.idnumber" id="datasIdnumber" value="${datas.idnumber}"
											class="validate[required] text-input"/><span id=""></td>
											<td align="right" style="font-size: 12px;"><span class="spanred">*</span>银行卡号:</td>
											<td style="padding-left: 20px"><input type="text" name="datas.bankcard" id="datasBankcard" value="${datas.bankcard}"
											class="validate[required,custom[bankNumber]] text-input"/><span id=""></td>
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
											<td align="right" style="font-size: 12px;">地区:</td>
											<td style="padding-left: 20px">
												<s:hidden name="datas.area" id="datasArea"/>
												<select style="width: 137px" id="select-area">
													<option value="省内"
														<c:if test="${datas.area eq '省内'}"> selected="" </c:if>
													>湖北省生源</option>
													<option	value="省外"
														<c:if test="${datas.area eq '省外'}"> selected="" </c:if>
													>外省生源</option>
													<%-- <option	value="西部"
														<c:if test="${datas.area eq '西部'}"> selected="" </c:if>
													>西部</option> --%>
												</select>
											</td>
											<td align="right" style="font-size: 12px;">离县城距离(km):</td>
											<td style="padding-left: 20px;font-size: 12px;"><input type="text" style="width: 70" name="datas.distance" id="datasDistance" value="${datas.distance}"/>&nbsp;千米<span id=""></td>
											<td align="right" style="font-size: 12px;">月生活费(元):</td>
											<td style="padding-left: 20px;font-size: 12px;"><input type="text" style="width: 70" name="datas.expenses" id="datasExpenses" value="${datas.expenses}"/>&nbsp;元<span id=""></td>
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
											<td align="right" style="font-size: 12px;" colspan="2"><span class="spanred">*</span>简明地址(五个字以内如：湖北英山):</td>
											<td style="padding-left: 20px"	colspan="2">
												<input type="text" style="width: 100px" name="datas.addressX" id="datasAddressX" value="${datas.addressX}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">生源地贷款:</td>
											<td style="padding-left: 20px">
												<s:hidden name="datas.isLoan" id="datasIsLoan"/>
												<select style="width: 137px" id="select-isLoan">
													<option value="0"
														<c:if test="${datas.isLoan eq 0}"> selected="" </c:if>
													>否</option>
													<option	value="1"
														<c:if test="${datas.isLoan eq 1}"> selected="" </c:if>
													>是</option>
												</select>
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
											<td align="right" style="font-size: 12px;">爷爷年龄:</td>
											<td style="padding-left: 20px">
												<input type="text" style="width: 80px;" name="datas.name_grandfather" id="datasNameGrandfather" value="${datas.name_grandfather}"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;">收入金额及来源:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.in_grandfather" id="datasInGranfather" value="${datas.in_grandfather}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">身体状况：</td>
											<td style="padding-left: 20px;font-size: 12px;" colspan="3">
												<s:textarea cols="40" rows="5" name="datas.health_grandfather" id="datasHealthGrandfather">
												</s:textarea>
												<br/>患有疾病的请写清楚具体病情
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
										
										<!-- 奶奶 -->
										<tr>
											<td align="right" style="font-size: 12px;">奶奶年龄:</td>
											<td style="padding-left: 20px">
												<input type="text" style="width: 80px;" name="datas.name_grandmother" id="datasNameGrandmother" value="${datas.name_grandmother}"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;">收入金额及来源:</td>
											<td style="padding-left: 20px">
												<input type="text" name="datas.in_grandmother" id="datasInGrandmother" value="${datas.in_grandmother}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">身体状况：</td>
											<td style="padding-left: 20px;font-size: 12px;" colspan="3">
												<s:textarea cols="40" rows="5" name="datas.health_grandmother" id="datasHealthGrandmother"></s:textarea>
												<br/>患有疾病的请写清楚具体病情
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
											<td align="right" style="font-size: 12px;">父亲年龄:</td>
											<td style="padding-left: 20px">
												<input type="text" style="width: 80px;" name="datas.name_father" id="datasNameFather" value="${datas.name_father}"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;">职业及工作地点:</td>
											<td style="padding-left: 10px">
												<input type="text" name="datas.career_father" id="datasCareerFather" value="${datas.career_father}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">年收入(元):</td>
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
											<td align="right" style="font-size: 12px;">母亲年龄:</td>
											<td style="padding-left: 20px">
												<input type="text" style="width: 80px;" name="datas.name_mother" id="datasNameMother" value="${datas.name_mother}"/><span id="">
											</span></td>
											<td align="right" style="font-size: 12px;">职业及工作地点:</td>
											<td style="padding-left: 10px">
												<input type="text" name="datas.career_mother" id="datasCareerMother" value="${datas.career_mother}"/><span id="">
											</td>
											<td align="right" style="font-size: 12px;">年收入(元):</td>
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
												<td align="right" style="font-size: 12px;">近一年家庭变故:</td>
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
									onclick="save();" value="仅保存信息" />
								<s:if test="@com.scholarship.module.conf.AppConfig@APPLY eq 1 ">
									<input type="button" class="btnyh" id="btnSave"
										onclick="apply();" value="申请奖/助学金" 
										/>
								</s:if>
								<input type="button" class="btnyh" id="btnCancel"
									onclick="history.back();"
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
	
	
	<div class="framDiv" id="tipDiv" onclick="this.style.display='none';"
		style="width:40%; display: none;position: absolute;z-index:10;background-color: white;">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class="r2titler">提示信息</td>
			</tr>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr>
				<td align="center"><font style="font-size:16px;color: #538DC2"><b
						id="msg"></b> </font></td>
			</tr>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr>
				<td align="right" id="button"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
		
	<div class="ui-overlay">
	<div id="mack"></div>
	<!-- 登录方式-帮助dialog -->
  </body>
</html>
