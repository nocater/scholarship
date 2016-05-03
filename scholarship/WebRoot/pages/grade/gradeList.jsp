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
	    	location.href="${ctx}/grade/query.action?keyword=" +encodeURIComponent(encodeURIComponent($("#keyword").val()));
	    	}
		}
		
		/* 执行  */
		function execute(){
			var choose = $("#method option:selected").val();
			if(choose==100){alert("请选择操作...");return;}
			if($("input[type='checkbox'][name='ids']:checked").size()<1) {alert("请至少选择一个角色信息...");return;}
			var ids="";
	    	$("input[type='checkbox'][name=ids]:checked").each(function(){   
				if(ids!="")   
					ids+=","+$(this).val();   
				else 
					ids=$(this).val();   
	                   	
	    	});
	    	
	    	if(confirm("确定执行此操作？")){
	    		location.href="${ctx}/grade/execute.action?ids="+ids+"&method="+choose;
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
					$("#sel-grade").empty();
					$("#sel-grade").append("<option value='0'>--未选择--</option>");
					for(var i=0;i<result.length;i++){
						$("#sel-grade").append("<option value="+result[i].id+">"+result[i].name+"</option>");
					}
				}
			});
		}
		
		/* 筛选(高级查询) */
		function filter(){
			var status = $("#select-status option:selected").val();
			var collegeId = $("#select-college option:selected").val();
			var gradeGrade = $("#select-grade").val();
			var gradeEdubg = $("#select-edubg option:selected").val();
			location.href="${ctx}/grade/query.action?status="+status+"&collegeId="+collegeId+"&gradeGrade="+gradeGrade+"&gradeEdubg="+gradeEdubg;
		}
	</script>
  </head>
  
  <body>
  	<s:form action="query" namespace="/grade" method="post" theme="simple">
    <s:hidden name="order" id="order"/>
    <s:hidden name="status" id="status"/>
    <s:hidden name="collegeId" id="collegeId"/>
    <s:hidden name="gradeGrade" id="gradeGrade"/>
    <s:hidden name="gradeEdubg" id="gradeEdubg"/>
  	<div>
  		<table width="99%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 4px; margin-top: 0px" align="center">
  			<tr>
  				<td>
  					<div class=caozuobox style="height:70px;">
		  			<!-- toolbar area -->
		  			<table width="90%" border="0" cellspacing="0" cellpadding="0">
			  			<tr height="30px">
			  				<td width="2%"></td>
			  				<td width="8%" valign="middle" align="center" style="padding-top:1px;">
			  					<input type="button" class="btnstyle" style="padding-top:1px;" value="添加班级" onclick="location.href='${ctx}/grade/queryById.action';" />
			  				</td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center" style="padding-top:3px;">操作:</td>
			  				<td width="1%"/>
			  				<td	width="10%" valign="middle" align="left" style="padding-top:0px;">
			  					<select style="width:100%;" id="method">
				  					<option value="100">--更多操作--</option>
				  					<option value="-1">删除班级</option>
				  					<option value="1">批量激活</option>
				  					<option value="2">批量锁定</option>
			  					</select>	
			  				</td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center"><input type="button"  value="执 行" class="btnstyle1" onclick="execute()" style="padding-top:1px;"/></td>
			  				<td width="1%"/>
			  				<td width="12%" valign="middle" align="center" style="padding-top:3px;">搜索(学院名称):</td>
			  				<td width="1%"/>
			  				<td width="5%"/>
			  				<td width="5%" valign="middle"><input type="text" id="keyword" name="keyword" value="${keyword}" /></td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center" style="padding-top:1px;"colspan="1"><img src="${ctx}/images/search.jpg"  class="hand" onclick="querys();" /></td>
			  				<td width="1%"/>
			  				<td width="10%"  valign="middle" align="center">
			  					<input type="button" class="btnstyle" style="padding-top:1px;" value="班级导入" onclick="location.href='${ctx}/grade/queryImport.action';" />
			  				</td>
			  				<td width="5%"/>
			  				<td/>
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
			  					<select style="width:100%;" id="select-status">
				  					<option value="">--所有---</option>
				  					<option value="1"
				  						<c:if test="${status eq 1}">selected=""</c:if>
				  					>激活</option>
				  					<option value="-1"
				  						<c:if test="${status eq -1}">selected=""</c:if>
				  					>锁定</option>
			  					</select>	
			  				</td>
			  				<td/>
			  				<td valign="middle" align="center" style="padding-top:3px;">学院:</td>
			  				<td/></td>
			  				<td width="105px" valign="middle" align="left" style="padding-top:0px;">
			  					<select style="width:100%;" id="select-college">
				  					<option value="">--所有--</option>
				  					<c:forEach items="${collegeList}" var="c" varStatus="stat">
				  						<option value="${c.id}"
				  							<c:if test="${collegeId eq c.id}">selected=""</c:if>
				  						>${c.name}</option>
				  					</c:forEach>
			  					</select>	
			  				</td>
			  				<td/>
			  				<td valign="middle" align="center" style="padding-top:3px;">年级:</td>
			  				<td width="90px" valign="middle" align="left" style="padding-top:0px;">
			  					<input type="text" id="select-grade" value="${gradeGrade}" />
			  					<%-- <select style="width:100%;" id="sel-grade">
				  					<option value="0">--未选择--</option>
				  					<c:forEach items="${gradeList}" var="g" varStatus="stat">
				  						<option value="${g.id}"
				  							<c:if test="${gradeId eq g.id}">selected=""</c:if>
				  						>${g.name}</option>
				  					</c:forEach>
			  					</select> --%>	
			  				</td>
			  				<td/>
			  				<td valign="middle" align="center" style="padding-top:3px;">学历:</td>
			  				<td/>
			  				<td width="90px" valign="middle" align="left" style="padding-top:0px;">
			  					<select style="width:100%;" id="select-edubg">
				  					<option value="">--所有--</option>
				  					<option value="1"
				  						<c:if test="${gradeEdubg eq 1}">selected=""</c:if>
				  					>本科</option>
				  					<option value="-1"
				  						<c:if test="${gradeEdubg eq -1}">selected=""</c:if>
				  					>专科</option>
			  					</select>	
			  				</td>
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
  				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2">
		    		<thead>
		    			<tr>
		    				<th width="5%" onclick="checkAll()" align="center" class="biaoti"><input type="checkbox" id="chkAll" onclick="checkAll()"/></th>
		    				<th width="20%" align="center" class="biaoti">班级</th>
		    				<th width="20%" align="center" class="biaoti">学院</th>
		    				<th width="5%"  align="center" class="biaoti">状态</th>
		    				<th width="15%" align="center" class="biaoti">专业</th>
		    				<th width="10%" align="center" class="biaoti">年级</th>
		    				<th width="10%" align="center" class="biaoti">学历</th>
		    				<th width="15%" align="center" class="biaoti">备注</th>
		    			</tr>
		    		</thead>
		    		<tbody>
		    			<s:iterator value="gradeList" status="stat">
		    				<tr>
		    					<td valign="middle" align="center" class="biaocm" onclick="checkOne(${id})">
		    						<input type="checkbox" name="ids" id="${id}" value="${id}" onclick="checkOne(${id})"/>
		    					</td>
		    					<td align="center"><span><a href="${ctx}/grade/queryById?gradeId=${id}">${name}</a></span></td>
		    					<td align="center"><span><a href="${ctx}/college/queryById?collegeId=${college.id}">${college.name}</span></td>
		    					<td align="center"><span>
		    						<c:if test="${status eq 1}">
		    							<font color="green">激活</font></span>
		    						</c:if>
		    						<c:if test="${status eq -1}">
		    							<font color="red">锁定</font></span>
		    						</c:if>
		    					</td>
		    					<td align="center"><span>${major}</span></td>
		    					<td align="center"><span>${grade}</span></td>
		    					<td align="center"><span>${edubg}</span></td>
		    					<td align="center"><span>${memo}</span></td>
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
