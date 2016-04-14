<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet" type="text/css">
    
    <%-- <script type='text/javascript' src="${ctx}/scripts/util.js"></script>
    <script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script> --%>
	<%-- <script type='text/javascript' src="${ctx}/scripts/jquery-2.1.0.min.js"></script> --%>
	<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
		});
		
		/*全选*/
		function checkAll(){
			if($("#chkAll").prop("checked")){
				$("input[type='checkbox']").prop("checked",false);
			}else{
				$("input[type='checkbox']").prop("checked",true);
			}
		}
		
		function checkOne(id){
			if($("#"+id).prop("checked")){
				$("#"+id).prop("checked",false);
			}else{
				$("#"+id).prop("checked",true);
			}
		}
		
		/* 搜索 */
		function querys(){
			var order=$("#order").val();
	     	var keyword=$("#keyword").val();
	     	if ((/\'/g).test(keyword)){
				alert("请不要输入单引号“‘”");
			}else{
	    		location.href="${ctx}/apply/query.action?keyword=" +encodeURIComponent(encodeURIComponent($("#keyword").val()));
	    	}
		}
		
		/* 执行  */
		function execute(){
			var choose = $("#status option:selected").val();
			if(choose==100){alert("请选择操作...");return;}
			if($("input[type='checkbox'][name='ids']:checked").size()<1) {alert("请至少选择一条信息...");return;}
			var ids="";
	    	$("input[type='checkbox'][name=ids]:checked").each(function(){   
				if(ids!="")   
					ids+=","+$(this).val();   
				else 
					ids=$(this).val();   
	                   	
	    	});
	    	var ss="";
	    	$("select[name=select-scholarship]").each(function(){   
	    		if(ss!="")   
					ss+=","+$(this).val();
				else 
					ss=$(this).val();
	                   	
	    	});
	    	if(confirm("确定执行此操作？")){
	    		location.href="${ctx}/apply/execute.action?ids="+ids+"&ss="+ss+"&method="+choose;
	    	}
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
		
		/* 筛选(高级查询) */
		function filter(){
			var status = $("#select-status").val();
			var collegeId = $("#select-college").val();
			var gradeId = $("#select-grade").val();
			var showNewTab = $("#showNewTab").val();
			location.href="${ctx}/apply/query.action?collegeId="+collegeId+"&gradeId="+gradeId+"&showNewTab="+showNewTab;
		}
		
		function logout() {
			if (confirm("你确认要注销吗？")) {
				location.href = '${ctx}/login/logout.action';
			}
		}
	</script>
  </head>
  
  <body>
  	<s:hidden name="showNewTab" id="showNewTab"/><!-- 是否显示TopFrame -->
  	<s:if test="showNewTab eq 1">
  	<div>
  		<!-- TopFrame -->
		<table width="99%" height="65px" border="0" cellpadding="0"
			cellspacing="0" background="${ctx}/images/topRbj.jpg">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						height="65px;">
						<tr>
							<td><table width="100%" border="0" cellspacing="0"
									cellpadding="0">
									<tr>
										<td valign="top" align="left">
											<!-- LOGO --> <%-- <img src="${ctx}/images/logo.jpg" alt="" name="topLogo" width="300" height="65" id="topLogo" style="" /> --%>
										</td>
										<td align="right">
											<div class="topinfo" id="topinfo">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td><div id="workMes" onclick="showMes()">
																<span id="span1" class="topL0"></span>
															</div></td>
														<td class="topL1">
															<table width="75%" border="0" align="right"
																cellpadding="0" cellspacing="0">
																<tr>
																	<td align="left">帐&nbsp;&nbsp;号：${sessionScope.LOGON_ACCOUNT.name}</td>
																</tr>
																<tr>
																	<td align="left"
																		title="${sessionScope.LOGON_ROLE.name}">
																		角&nbsp;&nbsp;色：<span id="roleNames">${sessionScope.LOGON_ROLE.name}</span> 
																	</td>
																</tr>
															</table>
														</td>
														<%-- <td><input name="" type="button" class="topL2"
															value="个人设置"
															onclick="parent.mainFrame.location.href='${ctx}/employee/personSeting.action';" /></td>
														<td><input name="input" type="button" class="topL3"
															value="注销" onclick="logout()" /></td> --%>
													</tr>
												</table>
											</div>
										</td>
									</tr>
								</table></td>
							<td width="1%">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
  	</div>
  	</s:if>
  	<s:form action="query" namespace="/apply" method="post" theme="simple">
    <s:hidden name="order" id="order"/>
    <s:hidden name="collegeId" id="collegeId"/>
    <s:hidden name="gradeId" id="gradeId"/>
    <s:hidden name="select_year" id="select_year"/>
    <s:hidden name="select_status" id="select_status"/>
    <s:hidden name="scholarshipId" id="scholarshipId"/>
  	<div>
  		<table width="99%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 4px; margin-top: 0px" align="center">
  			<tr>
  				<td>
  					<div class=caozuobox style="height:70px;">
		  			<!-- toolbar area -->
		  			<table width="90%" border="0" cellspacing="0" cellpadding="0">
			  			<tr height="30px">
			  				<td width="2%"></td>
			  				<td width="10%" valign="middle" align="center" style="padding-top:1px;">
			  					<span>操&nbsp;&nbsp;作：</span>
			  				</td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center" style="padding-top:3px;">审批:</td>
			  				<td width="1%"/>
			  				<td	width="10%" valign="middle" align="left" style="padding-top:0px;">
			  					<select style="width:100%;" id="status">
				  					<option value="100">--未选择--</option>
				  					<option value="1">--通过--</option>
				  					<!-- <option value="0">--驳回--</option> -->
				  					<option value="-1">--废弃--</option>
			  					</select>	
			  				</td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center"><input type="button"  value="执 行" class="btnstyle1" onclick="execute()" style="padding-top:1px;"/></td>
			  				<td width="1%"/>
			  				<td width="12%" valign="middle" align="center" style="padding-top:3px;">搜索(账号/姓名):</td>
			  				<td width="1%"/>
			  				<td width="5%"/>
			  				<td width="5%" valign="middle"><input type="text" id="keyword" name="keyword" value="${keyword}" /></td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center" style="padding-top:1px;"colspan="1"><img src="${ctx}/images/search.jpg"  class="hand" onclick="querys();" /></td>
			  				<td	width="1%"/>
			  				<td width="5%" valign="middle" align="center"><input type="button"  value="宽屏" class="btnstyle1" onclick="window.open('${ctx}/apply/query.action?showNewTab=1');" style="padding-top:1px;"/></td>
			  				<td/>
			  				<!-- <td valign="top"><span><a href="#">高级</a></span></td> -->
			  				<!-- <td width="" valign="middle" style="padding-top:2px;"><span class="spanred">内置角色不可更改</span></td> -->
			  			</tr>
			  			<tr height="30px">
			  				<td/>
			  				<td valign="middle" align="center" style="padding-top:1px;">
			  					<span>筛&nbsp;&nbsp;选：</span>
			  				</td>
			  				<td/>
			  				<td valign="middle" align="center" style="padding-top:3px;">年:</td>
			  				<td/>
			  				<td valign="middle" align="left" style="padding-top:0px;">
			  					<select style="width:100%;" id="sel-role">
				  					<option value="0">--今年---</option>
				  				<%-- <option value="-1"
				  						<c:if test="${onlyStudent eq -1}">selected=""</c:if>
				  					>学生</option>
				  					<option value="1"
				  						<c:if test="${onlyStudent eq 1}">selected=""</c:if>
				  					>非学生</option> --%>
			  					</select>	
			  				</td>
			  				<td/>
			  				<td valign="middle" align="center" style="padding-top:3px;">学院:</td>
			  				<td/></td>
			  				<td width="105px" valign="middle" align="left" style="padding-top:0px;">
			  					<select style="width:100%;" id="select-college" onchange="setGrades(this.value)">
				  					<option value="0">--未选择--</option>
				  					<c:forEach items="${collegeList}" var="c" varStatus="stat">
				  						<option value="${c.id}"
				  							<c:if test="${collegeId eq c.id}">selected=""</c:if>
				  						>${c.name}</option>
				  					</c:forEach>
			  					</select>	
			  				</td>
			  				<td/>
			  				<td valign="middle" align="center" style="padding-top:3px;">班级:</td>
			  				<td width="90px" valign="middle" align="left" style="padding-top:0px;">
			  					<select style="width:100%;" id="select-grade">
				  					<option value="0">--未选择--</option>
				  					<c:forEach items="${gradeList}" var="g" varStatus="stat">
				  						<option value="${g.id}"
				  							<c:if test="${gradeId eq g.id}">selected=""</c:if>
				  						>${g.name}</option>
				  					</c:forEach>
			  					</select>	
			  				</td>
			  				<td/>
			  				<td valign="middle" align="center"><input type="button"  value="筛 选" class="btnstyle1" onclick="filter()" style="padding-top:1px;"/></td>
			  			</tr>
	  				</table>
	  			</div>
  				</td>
  			</tr>
  			<tr>
  				<td>
  				<div class="sbox">   
  				<div class="cont">
  				<table width="100%" border="1" frame="below" cellspacing="1" cellpadding="0" class="tab2">
		    		<thead>
		    			<tr>
		    				<th width="3%" onclick="checkAll()" align="center" class="biaoti"><input type="checkbox" id="chkAll" onclick="checkAll()"/></th>
		    				<th width="8%" align="center" class="biaoti">状态</th>
		    				<!-- <th width="5%" align="center" class="biaoti">姓名</th> -->
		    				<th width="10%" align="center" class="biaoti">父亲</th>
		    				<th width="10%" align="center" class="biaoti">母亲</th>
		    				<th width="15%" align="center" class="biaoti">爷爷奶奶</th>
		    				<th width="10%" align="center" class="biaoti">兄弟姐妹</th>
		    				<th width="10%" align="center" class="biaoti">家庭变故</th>
		    				<th width="5%"  align="center" class="biaoti">家庭年收入</th>
		    				<th width="8%" align="center" class="biaoti">主要支出项</th>
		    				<th width="8%" align="center" class="biaoti">主要困难原因</th>
		    				<th width="8%" align="center" class="biaoti">资助历史</th>
		    			</tr>
		    		</thead>
		    		<tbody>
		    			<s:iterator value="applyList" status="stat">
		    				<tr>
		    					<td valign="middle" align="center" class="biaocm" onclick="checkOne(${id})">
		    						<input type="checkbox" name="ids" id="${id}" value="${id}" onclick="checkOne(${id})"/>
		    					</td>
		    					<td align="center">
		    						<span>${datasList[stat.index].addressX}</span><br/>
		    						<span>
		    							<a href="${ctx}/datas/queryByAccount.action?AccountId=${account.id}">
		    							${datasList[stat.index].name}</a>
		    						</span><br/>
		    						<select name="select-scholarship" style="width: 80px">
		    							<option value="0">无</option>
		    							<c:forEach items="${scholarshipList}" var="s" varStatus="stats">
		    								<option value="${s.id}"
		    									<c:if test="${s.id eq scholarship.id}">selected="selected"</c:if>
		    								>${s.category}${s.level}</option>
		    							</c:forEach>
		    						</select><br/>
		    						<span>
		    							申请年份：${year}
		    						</span><br/>
		    						状态:<span>
			    						<c:if test="${status eq 0}"><font>未审批</font></c:if>
			    						<c:if test="${status eq 1}"><font color="green">班主任已审批</font></c:if>
			    						<c:if test="${status eq 2}"><font color="blue">辅导员已审批</font></c:if>
			    						<c:if test="${status eq -1}"><font color="red">已废弃</font></c:if>
		    						</span><br/>
		    						<span>审批人:${approve_Account.name}</span>
		    					</td>
		    					<%-- <td align="center">
		    						<span>${datasList[stat.index].grade}</span><br/>
		    						<span>${datasList[stat.index].name}</span>
		    					</td> --%>
		    					<td align="left">
		    						<font color="green">姓名:</font><span>${datasList[stat.index].name_father}</span><br/>
		    						<font color="green">职业&地点:</font><span>${datasList[stat.index].career_father}</span><br/>
		    						<font color="green">年收入:</font><span>${datasList[stat.index].in_father}</span><br/>
		    						<font color="green">健康:</font><span>${datasList[stat.index].health_father}</span>
		    					</td>
		    					<td align="left">
									<font color="green">姓名:</font><span>${datasList[stat.index].name_mother}</span><br/>
		    						<font color="green">职业&地点:</font><span>${datasList[stat.index].career_mother}</span><br/>
		    						<font color="green">年收入:</font><span>${datasList[stat.index].in_mother}</span><br/>
		    						<font color="green">健康:</font><span>${datasList[stat.index].health_mother}</span>
								</td>
		    					<td align="left">
		    						<font color="green">爷爷:</font><span>${datasList[stat.index].name_grandfather}</span><br/>
		    						<font color="green">收入:</font><span>${datasList[stat.index].in_grandfather}</span><br/>
		    						<font color="green">健康:</font><span>${datasList[stat.index].health_grandfather}</span><br/>
		    						<font color="green">奶奶:</font><span>${datasList[stat.index].name_grandmother}</span><br/>
		    						<font color="green">收入:</font><span>${datasList[stat.index].in_grandmother}</span><br/>
		    						<font color="green">健康:</font><span>${datasList[stat.index].health_grandmother}</span>
		    					</td>
		    					<td align="left">
		    						<span>${datasList[stat.index].others}</span>
		    					</td>
		    					<td align="center">
		    						<span>${datasList[stat.index].accident}</span>
		    					</td>
		    					<td align="center">
		    						<span>${datasList[stat.index].in_family}万</span>
		    					</td>
		    					<td align="center">
		    						<span>${datasList[stat.index].out_main}</span>
		    					</td>
		    					<td align="center">
		    						<span>${datasList[stat.index].descripe}</span>
		    					</td>
		    					<td align="center">
		    						<span>${appliedMessageList[stat.index]}</span>
		    					</td>
		    				</tr>
		    			</s:iterator>
		    		</tbody>
		    	</table>
		    	</div>
		    	</div>
  				</td>
  			</tr>
  			<tr>
  				<td colspan="5" width="100%"><jsp:include page="../commons/page.jsp"></jsp:include></td>
  			</tr>
  		</table>
    </div>
    </s:form>
  </body>
</html>
