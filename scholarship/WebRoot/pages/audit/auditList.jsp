<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
  <head>
    <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet" type="text/css">
    <script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
    <script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
    <script type='text/javascript' src="${ctx}/scripts/util.js"></script>
    <script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
    <script type='text/javascript'	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
    <script language="javascript">
    //导出excel
    function exportExcel(){
    	 var ids="";
    	 $("[name=ids]:checkbox:checked").each(function(){   
			if(ids!=""){
				ids+=","+$(this).val(); 
			}   
			else{
				ids=$(this).val();   
			} 
    	});    	
    	//导出当前页日志
		if ($("#chkAll").attr("checked")) {
			location.href="${ctx}/systemAudit/export.action?ids="+ids
						 +"&keyword=" + encodeURIComponent(encodeURIComponent($("#keyword").val()))
    					 + "&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val();
		}
		//导出全部日志
		if (!$("#chkAll").attr("checked")) {
			if(ids ==""){
				if (confirm("您是要导出全部的系统日志...")) {
					location.href="${ctx}/systemAudit/export.action?ids="+ids
						 +"&keyword=" + encodeURIComponent(encodeURIComponent($("#keyword").val()))
    					 + "&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val();
				}
			}
			else{
			//导出当前选定的日志
				location.href="${ctx}/systemAudit/export.action?ids="+ids
							 +"&keyword=" + encodeURIComponent(encodeURIComponent($("#keyword").val()))
    						 + "&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val();
			}
		}
    }
    
      //sousuo
    function query() {
    	var keyword = $("#keyword").val();
    	if ((/\'/g).test(keyword)){
			alert('请不要输入单引号“‘”');
		}else{
    		location.href="${ctx}/audit/query.action?keyword=" + encodeURIComponent(encodeURIComponent($("#keyword").val()));
    	}
    }
    $(document).ready(function(){
		$("#keyword").keydown(function(event){ 
			if(event.keyCode==13){  
				query();
			}  
		});  
		
		// Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen: false,
			width: 450,
			height: 250,
			buttons: {
				"查询[Enter]": function() {
				if ((/\'/g).test($('#empName').val())||(/\'/g).test($('#sourceIP').val())||(/\'/g).test($('#oper').val())||(/\'/g).test($('#note').val())){
					alert('请不要输入单引号“‘”');
				}else{
					extQuery();
					$(this).dialog("close"); 
				}
				},
				
				"取消[Esc]": function() { 
					$(this).dialog("close"); 
				} 
			}
		});
	});
	
	function deleteSelect() {
    	var ids="";
    	$("[name=ids]:checkbox:checked").each(function(){   
			if(ids!="")   
				ids+=","+$(this).val();   
			else 
				ids=$(this).val();   
                   	
    	});   
    	if($(":checkbox[checked = true]").size()<1) {
			alert("最少选择一个复选框!");
			return;
		}
    	location.href='${ctx}/systemAudit/delete.action?ids='+ids;
    }
    
	$("#chkAll").live("click",function(event) {
		if($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked',true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked',false);
		}
	});
	
	$(document).click(function(){
		//shiftForCheckBoxFun(event);
		destroyOverDlg();
	});
	
	function overDlg(e,header,content,num) {
		content = content.replace(new RegExp(/(~)/g),"'");
		content = content.replace(new RegExp(/(&)/g),'"');
		$('#blk_header').html(header);
		$('#blk_content').html(content);
	
		createOverDiv(e,10,10,$('#blk').html());
	}
	
	function extQueryDlg() {
		$('#dialog-extQuery').dialog('open');
	}
	
	function extQuery() {
		$('#selAccountName').val($('#empName').val());
		$('#selResourceIP').val($('#sourceIP').val());
		$('#selOperation').val($('#oper').val());
		$('#selNote').val($('#note').val());
		$('#keyword').val("");
		$('#startDate').val($('#sdate').val());
		$('#endDate').val($('#edate').val());
		$('#auditForm').submit();
	}
    </script>
  </head>
  
  <body>
    <s:form action="query" namespace="/audit" method="post" theme="simple" id="auditForm" name="auditForm">
		<s:hidden id="selOperation" name="selOperation" />
		<s:hidden id="selAccountName" name="selAccountName" />
		<s:hidden id="selSourceIP" name="selSourceIP" />
		<s:hidden id="selNote" name="selNote" />
		<s:hidden id="startDate" name="startDate" />
		<s:hidden id="endDate" name="endDate" />
		<table width="99%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 4px; margin-top: 2px">
		<!-- 空行 -->
		<tr>
			<td>
			</td>
		</tr>	
		  	
	  	<tr>
	  		<td>
	  		<div class="caozuobox">
	  			<!-- toolbar area -->
	  			<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr height="30">
	  			<!--  
	  			<td width="8%" valign="top" align="center"  style="padding-top:1px;">操作
	  					<select  id="status">
		  					<OPTION value="100">
								--更多操作--
							</OPTION>
							<OPTION value="-1">
								删除审计
							</OPTION>
	  					</select>	
	  				<input type="button"  style="padding-top:1px;padding-left:1px;" value="执 行" class="btnstyle1" onclick="if(confirm('确认执行操作吗？'))deleteSelect();"/>
	  			</td>
	  			-->
	  			<td align="left">
	  			<table width="300px" border="0" cellspacing="0" cellpadding="0">
	  				<tr>
	  				<td width="15"></td>
	  				<td width="25" align="left" style="padding-top:3px;">搜索</td>
	  				<td width="6"></td>
	  				<td width="30"><input type="text" id="keyword" value="${keyword}" name="keyword" />
	  				</td>
	  				<td width="6"></td>
	  				<td width="20"><img src="${ctx}/images/search.jpg" class="hand" onclick="query();" /></td>
	  				<td width="6"></td>
	  				<td width="" style="padding-top:3px;"><a href="#" onclick="extQueryDlg();">高级</a></td>
	  				</tr>
	  			</table>
	  			</td>
	  			<td align="right">
	  			<table width="150" border="0" cellspacing="0" cellpadding="0">
	  				<tr>
	  				<td width="" align="right"><!--<input type="button" value="导出Excel" class="btnstyle" onclick="exportExcel();"/><span style="display:none;"><a href="#">高级</a></span>--></td>
	  				<td width="15"></td>
	  				</tr>
	  			</table>
	  			</td>
	  			</tr>
	  			</table>
	  			<!-- toolbar area -->
	  			</div>
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>
	  		<div class="sbox">   
  				<div class="cont">
  			<!-- information area -->
  			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2">
  			<tr height="28">
	  				<td width="6%" align="center" class="biaoti"><input type="checkbox" id="chkAll" name="chkAll" class="check-box not_checked" /></td>
	  				<td width="16%" align="center" class="biaoti">登录名</td>
	  				<td width="18%" align="center" class="biaoti">源IP</td>
	  				<td width="21%" align="center" class="biaoti">时间</td>
	  				<td width="25%" align="center" class="biaoti">操作日志</td>
	  				<td width="14%" align="center" class="biaoti">操作类型</td>
	  			</tr>
	  			<c:set value="1" var="data" />
	  			<s:iterator value="auditList" status="stat">
				<tr>
	  				<td valign="middle" class="biaocm" align="center"><input type="checkbox" name="ids" id="${id}" value="${id}" class="check-box" /></td>
	  				<td valign="middle" class="td_list_data" align="center">${accountName}</td>
	  				<td valign="middle" class="td_list_data" align="center">${sourceIP}</td>
	  				<td valign="middle" class="td_list_data" align="center"><s:date name="operationTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	  					  				
	  				<td valign="middle" align="center" class="hand italic" onmouseover="overDlg(this,'${accountName}的操作日志','${note}','');">
						操作日志
	  				</td>
	  				<td valign="middle" class="td_list_data" align="center">${operation}</td>
	  			</tr>
	  			<c:set value="${data+1}" var="data" />
	  			</s:iterator>
	  			<tr>
	  				<td colspan="6"><jsp:include page="../commons/page.jsp"></jsp:include></td>
	  			</tr>
	  			</table>
	  			</div>
	  			</div>
	  			</td>
	  	</tr>
	  	</table>
	  </s:form>
	  
	  <div id="blk" style="display:none;">
	  	<div class="blk"> 
	         <div class="head"><div class="head-right"></div></div> 
	         <div class="main"> 
	             <h2 id="blk_header">#header#</h2>  
	             <div id="blk_content">#content#</div>
	         </div> 
	         <div class="foot"><div class="foot-right"></div></div> 
	    </div> 
	  </div>

	  <!-- ui-dialog -->
	  <div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5" style="margin-left:20px;">
        <tr>
        	<td width="25%">操作人:</td>
        	<td>
        		<input id="empName" name="empName" type="text" style="width:256px;" onkeypress="if(event.keyCode==13)extQuery();" />
        	</td>
        </tr>
		<tr>
			<td>操作人IP:</td>
			<td>
				<input id="sourceIP" name="sourceIP" type="text" style="width:256px;" onkeypress="if(event.keyCode==13)extQuery();" />
			</td>
		</tr> 
		<tr>
			<td>操作类型:</td>
			<td>
				<input id="oper" name="oper" type="text" style="width:256px;" onkeypress="if(event.keyCode==13)extQuery();" />
			</td>
		</tr> 
		<tr>
			<td>
				时间周期:
			</td>
			<td>
				<input id="sdate" name="sdate" type="text" onclick="WdatePicker()"
					class="Wdate" style="width: 113px;" />
					&nbsp;到&nbsp;
				<input id="edate" name="edate" type="text" onclick="WdatePicker()"
					class="Wdate" style="width: 113px;" />
			</td>
		</tr>
		<tr>
			<td>行为日志:</td> 
			<td>
				<input id="note" name="note" type="text" style="width:256px;" onkeypress="if(event.keyCode==13)extQuery();" />
			</td>
		</tr>
		</table>
	</div>
  </body>
</html>
