<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<html>
  <head>
    <title></title>
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
			if($("#message").val()!=''){
				alert($("#message").val());
			}
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
	    	location.href="${ctx}/account/query.action?keyword=" +encodeURIComponent(encodeURIComponent($("#keyword").val()));
	    	}
		}
		
		/* 执行  */
		function execute(){
			var choose = $("#status option:selected").val();
			if(choose==100){alert("请选择操作...");return;}
			if($("input[type='checkbox'][name='ids']:checked").size()<1) {alert("请至少选择一条信息...");return;}
			var ids="";
			var ss="";
	    	$("input[type='checkbox'][name=ids]:checked").each(function(){   
				if(ids!="") {
					ids+=","+$(this).val();
					ss+=","+$(this).parent().next().next().next().next().children().val();
				}
				else {
					ids=$(this).val();   
					ss=$(this).parent().next().next().next().next().children().val();
				}      	
	    	});
	    	
	    	if(confirm("确定执行此操作？")){
	    		location.href="${ctx}/apply/executeAllYears.action?ids="+ids+"&ss="+ss+"&method="+choose;
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
			var select_status = $("#sel-role").val();
			var select_year = $("#select_year").val();
			var collegeId = $("#select-college").val();
			var gradeId = $("#select-grade").val();
			var scholarshipId = $("#filter-scholarship").val();
			location.href="${ctx}/apply/queryAllYears.action?select_status="+select_status+"&select_year="+select_year+"&collegeId="+collegeId+"&gradeId="+gradeId+"&scholarshipId="+scholarshipId;
		}
		
		/* 导出  */
		function exportXLS(){
			var template = $("#select-exportType").val();
			var exportYear = $("#exportYear").val();
			if(exportYear==""||template==""||exportYear.length!=4){
				alert("请输入正确4位年份及选择模板");
				return;
			}
			if(confirm("确定执行此操作？")){
	    		location.href="${ctx}/apply/export.action?template="+template+"&exportYear="+exportYear;
	    	}
		}
	</script>
  </head>
  
  <body>
  	<s:form action="queryAllYears" namespace="/apply" method="post" theme="simple">
    <s:hidden name="order" id="order"/>
    <s:hidden name="collegeId" id="collegeId"/>
    <s:hidden name="gradeId" id="gradeId"/>
    <s:hidden name="select_year"/>
    <s:hidden name="select_status" id="select_status"/>
    <s:hidden name="scholarshipId" id="scholarshipId"/>
    <s:hidden name="message" id="message"/>
  	<div>
  		<table width="99%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 4px; margin-top: 0px" align="center">
  			<tr>
  				<td>
  					<div class=caozuobox style="height:110px;">
		  			<!-- toolbar area -->
		  			<table width="99%" border="0" cellspacing="0" cellpadding="0">
			  			<tr height="30px">
			  				<td width="2%"></td>
			  				<td width="5%" valign="middle" align="center" style="padding-top:1px;">
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
			  				<td width="15%" valign="middle" align="center" style="padding-top:3px;">搜索(账号/姓名):</td>
			  				<td width="1%"/>
			  				<td width="5%"/>
			  				<td width="5%" valign="middle" align="center"><input type="text" style="width: 90px" id="keyword" name="keyword" value="${keyword}" /></td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center" style="padding-top:1px;"colspan="1"><img src="${ctx}/images/search.jpg"  class="hand" onclick="querys();" /></td>
			  				<td	width="5%"><input type="button"  value="宽屏" class="btnstyle1" onclick="window.open('${ctx}/apply/queryAllYears.action');" style="padding-top:1px;"/></td>
			  				<td width="5%" valign="middle" align="center"></td>
			  				<td/>
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
			  				<td valign="middle" align="center" style="padding-top:3px;">状态:</td>
			  				<td/>
			  				<td valign="middle" align="left" style="padding-top:0px;">
			  					<select style="width:100%;" id="sel-role">
				  					<option value="100">--所有---</option>
				  					<option value="2"
				  						<c:if test="${select_status eq 2}">selected=""</c:if>
				  					>已完成</option>
				  					<option value="1"
				  						<c:if test="${select_status eq 1}">selected=""</c:if>
				  					>已审批</option>
				  					<option value="0"
				  						<c:if test="${select_status eq 0}">selected=""</c:if>
				  					>未审批</option>
				  					<option value="-1"
				  						<c:if test="${select_status eq -1}">selected=""</c:if>
				  					>已废弃</option>
			  					</select>	
			  				</td>
			  				<td/>
			  				<td valign="middle" align="center" style="padding-top:3px;">学院:</td>
			  				<td/></td>
			  				<td width="10%" valign="middle" align="center" style="padding-top:0px;">
			  					<select style="width:90%;" id="select-college" onchange="setGrades(this.value)">
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
			  				<td width="10%" valign="middle" align="center" style="padding-top:0px;">
			  					<select style="width:90" id="select-grade">
				  					<option value="0">--未选择--</option>
				  					<c:forEach items="${gradeList}" var="g" varStatus="stat">
				  						<option value="${g.id}"
				  							<c:if test="${gradeId eq g.id}">selected=""</c:if>
				  						>${g.name}</option>
				  					</c:forEach>
			  					</select>	
			  				</td>
			  				<td/>
			  				<td valign="middle" align="center"><span>年:</span></td>
			  				<td valign="middle" align="center"><input style="width: 70px" id="select_year" value="${select_year}"></td>
			  				<td width="5%" valign="middle" align="center" style="padding-top:3px;"><span>奖项:</span></td>
			  				<td width="5%" valign="middle" align="center" style="padding-top:3px;">
			  					<select id="filter-scholarship">
	    							<option value="">所有</option>
	    							<c:forEach items="${scholarshipList}" var="s" varStatus="stats">
	    								<option value="${s.id}"
	    									<c:if test="${s.id eq scholarshipId}">selected="selected"</c:if>
	    								>${s.category}${s.level}</option>
	    							</c:forEach>
	    						</select>
			  				</td>
			  				<td valign="middle" align="center"><input type="button"  value="筛 选" class="btnstyle1" onclick="filter()" style="padding-top:1px;"/></td>
			  			</tr>
			  			<td width="2%"></td>
			  				<td width="5%" valign="middle" align="center" style="padding-top:1px;">
			  					<span>导&nbsp;&nbsp;出：</span>
			  				</td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center" style="padding-top:3px;"><span>年:</span></td>
			  				<td width="1%"/>
			  				<td	width="10%" valign="middle" align="left" style="padding-top:0px;">
			  					<input id="exportYear" value="${exportYear}">
			  				</td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center"><span>模板:</span></td>
			  				<td width="1%"/>
			  				<td width="10%" valign="middle" align="center" style="padding-top:3px;">
			  					<select style="width:90%;" id="select-exportType">
				  					<option value="1">国家奖励志学金初审表</option>
				  					<option value="2">国家助学金备案表</option>
				  					<option value="3">助学金中行卡号登记表</option>
				  					<option value="4">明珠励志奖学金名单登记表</option>
				  					<option value="5">明珠奖学金发放登记表</option>
			  					</select>
			  				</td>
			  				<td width="1%"/>
			  				<td width="5%"  valign="middle">
			  					<input type="button"  value="导  出" class="btnstyle1" onclick="exportXLS();"/>
			  				</td>
			  				<td width="5%">
			  				</td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center" style="padding-top:1px;"colspan="1"></td>
			  				<td	width="5%"></td>
			  				<td width="5%" valign="middle" align="center"></td>
			  				<td/>
	  				</table>
	  			</div>
  				</td>
  			</tr>
  			<tr>
  				<td>
  				<div class="sbox">   
  				<div class="cont">
  				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2">
		    		<thead>
		    			<tr>
		    				<th width="3%" onclick="checkAll()" align="center" class="biaoti"><input type="checkbox" id="chkAll" onclick="checkAll()"/></th>
		    				<th width="5%" align="center" class="biaoti">姓名</th>
		    				<th width="12%" align="center" class="biaoti">学院班级</th>
		    				<th width="10%" align="center" class="biaoti">审批状态</th>
		    				<th width="10%" align="center" class="biaoti">奖项</th>
		    				<th width="10%" align="center" class="biaoti">审批年份</th>
		    				<th width="10%" align="center" class="biaoti">审批人</th>
		    			</tr>
		    		</thead>
		    		<tbody>
		    			<s:iterator value="applyList" status="stat">
		    				<tr>
		    					<td valign="middle" align="center" class="biaocm" onclick="checkOne(${id})">
		    						<input type="checkbox" name="ids" id="${id}" value="${id}" onclick="checkOne(${id})"/>
		    					</td>
		    					<td align="center">
		    						<span>
		    							<a href="${ctx}/datas/queryByAccount.action?AccountId=${account.id}">
		    							${account.name}</a>
		    						</span>
		    					</td>
		    					<td align="center"><span>${account.college.name}-${account.grade.name}</span></td>
		    					<td align="center">
		    						<c:if test="${status eq 0}"><font>未审批</font></c:if>
		    						<c:if test="${status eq 1}"><font color="green">班主任已审批</font></c:if>
		    						<c:if test="${status eq 2}"><font color="blue">辅导员已审批</font></c:if>
		    						<c:if test="${status eq -1}"><font color="red">已废弃</font></c:if>
		    					</td>
		    					<td align="center">
		    						<select name="select-scholarship">
		    							<option value="0">无</option>
		    							<c:forEach items="${scholarshipList}" var="s" varStatus="stats">
		    								<option value="${s.id}"
		    									<c:if test="${s.id eq scholarship.id}">selected="selected"</c:if>
		    								>${s.category}${s.level}</option>
		    							</c:forEach>
		    						</select>
		    					</td>
		    					<td align="center">${year}</td>
		    					<td align="center"><span>${approve_Account.name}(${updateDate})</span></td>
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
