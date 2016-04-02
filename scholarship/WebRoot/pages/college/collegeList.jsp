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
	<script type='text/javascript' src="${ctx}/scripts/jquery-2.1.0.min.js"></script>
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
	    	location.href="${ctx}/college/query.action?keyword=" +encodeURIComponent(encodeURIComponent($("#keyword").val()));
	    	}
		}
		
		function execute(){
			var choose = $("#status option:selected").val();
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
	    		location.href="${ctx}/college/execute.action?ids="+ids+"&method="+choose;
	    	}
		}
	</script>
  </head>
  
  <body>
  	<s:form action="query" namespace="/college" method="post" theme="simple">
    <s:hidden name="order" id="order"/>
  	<div>
  		<table width="99%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 4px; margin-top: 0px" align="center">
  			<tr>
  				<td>
  					<div class=caozuobox>
		  			<!-- toolbar area -->
		  			<table width="600px" border="0" cellspacing="0" cellpadding="0">
			  			<tr height="30px">
			  				<td width="15px"></td>
			  				<td width="30px" valign="middle" align="left" style="padding-top:1px;">
			  					<input type="button" class="btnstyle" style="padding-top:1px;" value="添加学院" onclick="location.href='${ctx}/college/queryById.action';" />
			  				</td>
			  				<td width="15px"></td>
			  				<td width="25px" valign="middle" align="left" style="padding-top:3px;">操作</td>
			  				<td width="6px"></td>
			  				<td width="100px" valign="middle" align="left" style="padding-top:0px;">
			  					<select style="width:100%;" id="status">
				  					<option value="100">--更多操作--</option>
				  					<option value="-1">删除学院</option>
				  					<!-- <option value="1">复制角色</option> -->
			  					</select>	
			  				</td>
			  				<td width="6px"></td>
			  				<td width="25px" valign="middle" align="left"><input type="button"  value="执 行" class="btnstyle1" onclick="execute()" style="padding-top:1px;"/></td>
			  				<td width="15px"></td>
			  				<td width="25px" valign="middle" align="center" style="padding-top:3px;">搜索</td>
			  				<td width="6px"></td>
			  				<td width="50px" valign="middle"><input type="text" id="keyword" name="keyword" value="${keyword}" /></td>
			  				<td width="6px"></td>
			  				<td width="25px" valign="middle" align="left" style="padding-top:1px;"><img src="${ctx}/images/search.jpg"  class="hand" onclick="querys();" /></td>
			  				<!--  <td width="" valign="top"><span style="display:none;"><a href="#">高级</a></span></td>-->
			  				<td width="" valign="middle" style="padding-top:2px;"><span class="spanred"></span></td>
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
		    				<th width="15%" align="center" class="biaoti">学院</th>
		    				<th width="15%" align="center" class="biaoti">学院描述</th>
		    				<!-- <th width="30%" align="center" class="biaoti">下属班级</th> -->
		    			</tr>
		    		</thead>
		    		<tbody>
		    			<s:iterator value="collegeList" status="stat">
		    				<tr>
		    					<td valign="middle" align="center" class="biaocm" onclick="checkOne(${id})">
		    						<input type="checkbox" name="ids" id="${id}" value="${id}" onclick="checkOne(${id})"/>
		    					</td>
		    					<td align="center"><span><a href="${ctx}/college/queryById?collegeId=${id}">${name}</a></span></td>
		    					<td align="center"><span>${memo}</span></td>
		    					<!-- <td align="center"></td> -->
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
