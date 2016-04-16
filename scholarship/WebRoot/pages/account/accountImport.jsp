<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
	<head>
		<link href="${ctx}/styles/jquery.progressbar.cus.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
		<%-- <script type='text/javascript' src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/jquery.select.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/paging.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script> --%>
		<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/util.js"></script>

		<SCRIPT type="text/javascript">
			$(document).ready(function (){
				cancel();
			});
            function sub(){
                var fileval=$("#upFile").val();
                if(fileval.substring(fileval.lastIndexOf(".")+1)!="xls"){
                	alert("请上传xls格式文件");
                	return;
                }
                if(fileval==""){
                    alert("请选择需要上传的资源文件...");
                    return;
                }else{
                	parent.frames[0].reload(); //top Frame
        			parent.frames[1].reload();
        			parent.frames[3].reload(); //main 右边frame
        			parent.frames[4].reload(); //foot frame
                    $("#accountForm").submit();
                }
            }
            function closeWin(){
                this.close();
            }
            
            $(document).ready(function(){
            	$('#dialog-account').dialog({
 	    				autoOpen : true,
 	    				width : 500,
 	    				height : 300,
 	    				buttons : {
 	    					"覆盖[Overlap]" : function() {
 	    						accountInfo("overlap");
 	    					},
 	    					"取消[Esc]" : function() {
 	    						$(this).dialog("close");
 	    						//accountInfo("esc");
 	    					}
 	    				}
 	    			});
            });
          
            function accountInfo(operateType){
            	var accnos="";
            	
            	if(operateType == "overlap"){
                	$("input[type='checkbox'][name='chk-account']:checked").each(
                		function(){
                			accnos += $(this).val() + ",";
                		}	
                	);
                	if(accnos == ","){
                		alert("请至少选择一个用户");
                		return;
                	}
            	}
            	
            	parent.frames[0].reload(); //top Frame
    			parent.frames[1].reload();
    			parent.frames[3].reload(); //main 右边frame
    			parent.frames[4].reload(); //foot frame
            	
           		$.ajax({
                   	type : "post",
       				url :"${ctx}/account/singleAjax.action",
       				timeout:2000000,		
       				dataType : "text",
       				data : "accnos="+accnos+"&operateType="+operateType,
       				success : function(result) {
       					cancel();
       					$('#dialog-account').dialog('close');
       					if(result == "refresh"){
       						alert("成功覆盖");
       						window.location.href="${ctx}/account/query.action?order=UPDATEDATE";
       					}else if(result == "error"){
       						alert("操作失败");
       						cancel();
       					}
       				}
                   }); 
            }
            
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
            
            //遮罩
            function reload(){
				openSuccess();
				$("#mack").attr("style","display:block");
				$("#mack").addClass("ui-widget-overlay");
				$("#mack").css('height','100%');
				$("#mack").css('width','99.5%');
				$("#mack").css('margin-right','2px');
			}
            function openSuccess()
            {
     	        var docHe =  ($(document).height()/2)-60;
     	        var docWi =  ($(document).width()/2)-200;
     	        $("#openSuccess").css({top:docHe,left:docWi});
     	        $("#msg").text("正在导入，请稍后...");
     	        $("#openSuccess").show();
            }
            
            //遮罩取消
            function cancel(){
            	$("#openSuccess").hide();
                parent.frames[0].cancel();
                parent.frames[1].cancel();
                parent.frames[4].cancel();
                //当前Frame
                //document.onmousedown=cancelContext;
    			$("#mack").removeClass("ui-widget-overlay");
            }
        </SCRIPT>
		<STYLE type="text/css">
.filestyle {
	width: 270px;
}
</STYLE>
	</head>

	<body style="margin-top: 2px">
		<s:form action="import" namespace="/account" method="post"
			id="accountForm" name="accountForm" enctype="multipart/form-data">
			<!--  主table-->
			<table width="100%" cellspacing="0" cellpadding="0"><tr><td>
			<table cellspacing="1" cellpadding="0" align="left"
				style="border: 1px solid #5AA4D1; margin-left: 4px; margin-top: 0px;margin-bottom:7px;"
				width="50%">
				<tr>
					<td class='r2titler'>
						<b>导入用户信息</b>
					</td>
				</tr>
				<tr style="height: 20;">
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<!-- left area -->
					<td width="630px" valign="top" align="left"
						style="padding-left: 35px;">
						<span style="color: red;">请先下载模板,依据模板填写必要的信息</span>——————
						<b><a style="cursor: pointer;"
							href="${ctx}/account/loadTemplate.action">账户模板下载</a>
						</b>
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="10px" width="65px">
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<b><s:actionmessage cssClass="spanred" /> </b>
								</td>
							</tr>
							<tr>
								<td height="10px">
								</td>
							</tr>
							<tr>
								<td align="left" style="padding-left: 0px;">
									数据文件：
								</td>
								<td>
									<input id="upFile" name="uploadPath" type="file"
										class="filestyle" align="left" />
								</td>
							</tr>

							<tr>
								<td height="10px">
								</td>
							</tr>

						</table>
					</td>
				</tr>
			</table>
			</td></tr>
			<tr><td style="height:4px;"></td></tr>
			<tr><td>
			<table width="50%" style="margin-left: 4px;">
				<tr>
					<td align="left" colspan="2">
						<input type="button" class="btnyh" id="btnSave" value="执    行"
							onclick="sub();" />
						<input type="button" class="btnyh" id="btnCancel" value="取   消"
							onclick="window.location.href='${ctx}/account/query.action'" />
					</td>
				</tr>
			</table>
			<table border="0" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td height="30px"></td>
				</tr>
			</table>
			</td></tr>
			</table>
			<c:if test="${errList!=null}">
				<table border="0" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<!-- <td>导入失败信息</td> -->
					</tr>
					<tr>
						<td height="10px">
						</td>
					</tr>
					<s:iterator value="errList" id="er">
						<tr>
							<c:if test="${er!=null }">
								<td>
									导入失败信息
								</td>
								<tr />
									<td>
										${er}
									</td>
							</c:if>
						</tr>
						<tr>
							<td height="10px">
							</td>
						</tr>
					</s:iterator>
				</table>
			</c:if>
			<c:if test="${accountList!=null}">
				<table border="0" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td>
							导入信息成功
						</td>
					</tr>
					<tr>
						<td height="10px">
						</td>
					</tr>
				</table>
			</c:if>

			<!-- ui-dialog-account -->
			<c:if test="${repeatAccountList != null}">
				<div id="dialog-account" title="重复学生信息">
					<table width="100%" border="0" cellspacing="5" cellpadding="5">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="dlg-table-account">
									<tr height="28">
										<td width="6%" align="center" class="biaoti" onclick="checkAll()">
											<input type="checkbox" id="chkAll" onclick="checkAll()"
												name="chkAll-account" class="check-box not_checked" />
										</td>
										<td width="30%" align="center" class="biaoti">
											学生姓名
										</td>
										<td width="70%" align="center" class="biaoti">
											学院班级
										</td>
									</tr>
									<s:iterator value="repeatAccountList" status="stat">
										<tr>
											<td width="20%" align="center" onclick="checkOne(${accno})">
												<input type="checkbox" name="chk-account"
													id="${accno}" value="${accno}"
													onclick="checkOne(${accno})"
													class="check-box" />
											</td>
											<td align="center">
												${name}</br>(${accno})
											</td>
											<td align="center">
												${college.name}-${grade.name}
											</td>
										</tr>
									</s:iterator>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</c:if>
		</s:form>
		
		<div class="framDiv" id="openSuccess"
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
	</body>
</html>
