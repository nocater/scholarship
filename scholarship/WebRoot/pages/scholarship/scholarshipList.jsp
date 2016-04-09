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
	    	location.href="${ctx}/account/query.action?keyword=" +encodeURIComponent(encodeURIComponent($("#keyword").val()));
	    	}
		}
		
		/* 执行  */
		function execute(){
			var choose = $("#status option:selected").val();
			if(choose==100){alert("请选择操作...");return;}
			if($("input[type='checkbox'][name='ids']:checked").size()<1) {alert("请至少选择一个信息...");return;}
			var ids="";
	    	$("input[type='checkbox'][name=ids]:checked").each(function(){   
				if(ids!="")   
					ids+=","+$(this).val();   
				else 
					ids=$(this).val();   
	                   	
	    	});
	    	
	    	if(confirm("确定执行此操作？")){
	    		location.href="${ctx}/scholarship/execute.action?ids="+ids+"&method="+choose;
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
			var onlyStudent = $("#sel-role").val();
			var collegeId = $("#sel-college").val();
			var gradeId = $("#sel-grade").val();
			location.href="${ctx}/account/query.action?onlyStudent="+onlyStudent+"&collegeId="+collegeId+"&gradeId="+gradeId;
		}
		
		/* 重置学生密码 */
		function resetPWD(){
			if(confirm("是否重置此角色下所有学生账户密码?")){
				$.ajax({
					url:"${ctx}/account/resetPWD.action",
					type:"POST",
					dataType:"text",
					success:function(result){
						alert(result);
					}
				});
			}
		}
	</script>
  </head>
  
  <body>
  	<s:form action="query" namespace="/scholarship" method="post" theme="simple">
    <s:hidden name="order" id="order"/>
  	<div>
  		<table width="99%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 4px; margin-top: 0px" align="center">
  			<tr>
  				<td>
  					<div class=caozuobox style="height:35px;">
		  			<!-- toolbar area -->
		  			<table width="900" border="0" cellspacing="0" cellpadding="0">
			  			<tr height="30px">
			  				<td width="2%"></td>
			  				<td width="10%" valign="middle" align="center" style="padding-top:1px;">
			  					<input type="button" class="btnstyle" style="padding-top:1px;" value="添    加" onclick="location.href='${ctx}/scholarship/queryById.action';" />
			  				</td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center" style="padding-top:3px;">操作:</td>
			  				<td width="1%"/>
			  				<td	width="10%" valign="middle" align="left" style="padding-top:0px;">
			  					<select style="width:100%;" id="status">
				  					<option value="100">--更多操作--</option>
				  					<option value="-1">删除</option>
				  					<option value="1">复制</option>
			  					</select>	
			  				</td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center"><input type="button"  value="执 行" class="btnstyle1" onclick="execute()" style="padding-top:1px;"/></td>
			  				<td width="1%"/>
			  				<td width="12%" valign="middle" align="center" style="padding-top:3px;"></td>
			  				<td width="1%"/>
			  				<td width="5%"/>
			  				<td width="5%" valign="middle"></td>
			  				<td width="1%"/>
			  				<td width="5%" valign="middle" align="center" style="padding-top:1px;"colspan="1"></td>
			  				<td></td>
			  				<td width="10%"  valign="middle" align="center">
			  				</td>
			  				<td width="10%"  valign="middle" align="center">
			  				</td>
			  				<td width="10%"  valign="middle" align="center">
			  				</td>
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
		    				<th width="20%" align="center" class="biaoti">类别</th>
		    				<th width="20%" align="center" class="biaoti">等级</th>
		    				<th width="20%" align="center" class="biaoti">奖金</th>
		    				<th width="20%" align="center" class="biaoti">操作</th>
		    			</tr>
		    		</thead>
		    		<tbody>
		    			<s:iterator value="scholarshipList" status="stat">
		    				<tr>
		    					<td valign="middle" align="center" class="biaocm" onclick="checkOne(${id})">
		    						<input type="checkbox" name="ids" id="${id}" value="${id}" onclick="checkOne(${id})"/>
		    					</td>
		    					<td align="center"><span>${category}</span></td>
		    					<td align="center"><span>${level}</span></td>
		    					<td align="center"><span>${money}</span></td>
		    					<td align="center"><span><a href="${ctx}/scholarship/queryById?scholarshipId=${id}">修改</a></span></td>
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
