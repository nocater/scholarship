<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
	<head>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">	
     	<script>
           function upgrade(){
        	   var filePicker = document.getElementById("upUpgrade"); 
        	   if(!checkImgType(filePicker.value)){ 
        			alert("文件不匹配"); 
        			return; 
        		} 
        	   $("#logo").attr("action","${ctx}/logo/logo.action");
        	   $("#logo").submit();
           }
           
           function upLogo(){
        	   document.getElementById("font1").innerText=""; 
        	   var filePicker = document.getElementById("toplogo"); 
        	   if(!checkImgType(filePicker.value)){ 
        			alert("文件不匹配"); 
        			return; 
        		}
        	   $("#logo").attr("action","${ctx}/logo/UpLogo.action");
        	   $("#logo").submit();
           }
           
           //验证上传文件
           var right_type=new Array(".jpg",".png"); 
           function checkImgType(fileURL) { 
        		var right_typeLen=right_type.length; 
        		var imgUrl=fileURL.toLowerCase(); 
        		var postfixLen=imgUrl.length; 
        		var len4=imgUrl.substring(postfixLen-4,postfixLen); 
        		var len5=imgUrl.substring(postfixLen-5,postfixLen); 
        		for (i=0;i<right_typeLen;i++){ 
        			if((len4==right_type[i])||(len5==right_type[i])){ 
        				return true; 
        			} 
        		} 
        	}
     	</script>
	</head>

	<body style="margin-top: 2px">
     <form enctype="multipart/form-data" id="logo" action="" method="post">
     	
		<!--  左侧table-->
		<table width="50%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 8px; margin-top: 0px">
			<tr><td colspan="2"><center><font color = "red"><s:actionmessage/></center></font></td></tr>
			<tr>
			<td width="100%" valign="top">
			<div class="framDiv">
			
			<!--  左侧table-->
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td class="r2titler">
					<b class="fontStyle">LOGO替换</b>
				</td>
			</tr>

			<tr>
				<td>
					<table width="94%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 18px;">
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
						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>	
						
						<tr>
							<td colspan="4"><div class="xuxian"></div></td>
						</tr>
						
						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>	
									
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>	
						<tr>
							<td>
								1、替换LOGO
							</td>
							<td>
							 	<input id="upUpgrade"  name="upUpgrade" type="file"/>
							</td>
							<td align="left"><input type="button" value="开始替换"  onclick="upgrade()" class="btnyh"/></td>
						</tr>
						
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>	
						<tr>
							<td>
								2、替换top-LOGO
							</td>
							<td>
							 	<input id="toplogo"  name="toplogo" type="file"/>
							</td>
							<td align="left"><input type="button" value="开始替换" onclick="upLogo()" class="btnyh"/></td>
						</tr>
						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>	
						<tr>
							<td class="td_detail_separator">
								<font color="red" id="font1">${ requestScope.exportLogMessage }</font>
							</td>
						</tr>
						
						</table>
				</td>
			</tr>
		</table>
		</div>
		</td>
		</tr>
		</table>
		
		</form>
	</body>
</html>
