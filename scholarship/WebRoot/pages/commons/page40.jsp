<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">

	function changeRecord(num){
	
	document.getElementById('startIndex').value = num;
		if ($("#fieldname").val() != "") {
			$("#resForm").attr("action", "sortQuery.action");
		} else if ($("#stat").val() == '1') {
			$("#resForm").attr("action", "queryWithRule.action");
		} else if ($("#stat").val() == '0') {
			$("#resForm").attr("action", "queryWithoutRule.action");
		} 
		document.forms[0].submit();
	}
		
	function gopage(lastnum) {
		var page = document.getElementById('page').value;

		var num = parseInt(page * 40) - parseInt(40);

		if ($("#fieldname").val() != "") {
			$("#resForm").attr("action", "sortQuery.action");
		} else if ($("#stat").val() == '1') {
			$("#resForm").attr("action", "queryWithRule.action");
		} else if ($("#stat").val() == '0') {
			$("#resForm").attr("action", "queryWithoutRule.action");
		} 

		//if(num>lastnum)num=lastnum;
		if (num > lastnum) {
			alert("错误页数");
			return false;
		}
		if (num < 0)
			num = 0;

		document.getElementById('startIndex').value = num
		document.forms[0].submit();
	}
</script>
 <c:if test="${!empty Page}">
 <table align="right">
 	<tr>
 		<td valign="middle">
     	共${Page.totalCount}记录
     	<input type="hidden" name="startIndex" id="startIndex" value="0">
     	<input type="hidden" name="lastIndex" id="lastIndex" value='${Page.lastIndex}'>
     	</td>
     	<td valign="middle">
     <c:choose>
      <c:when test="${Page.startIndex ne '0'}">
       <%--<img src="<%=request.getContextPath()%>/images/firstPage.gif" onclick="changeRecord('0')">--%>
       <a href="javascript:changeRecord('0')" >首页</a>
      </c:when>
      <c:otherwise>
      <!-- 首页 -->
       <%--<img src="<%=request.getContextPath()%>/images/firstPage.gif">--%>首页
      </c:otherwise>
     </c:choose>
     	</td>
     	<td valign="middle">
     <c:choose>
      <c:when test="${Page.startIndex ne '0'}">
      <%--<img src="<%=request.getContextPath()%>/images/prevPage.gif" onclick="changeRecord('${Page.previousIndex}')">--%>
    	 <a href="javascript:changeRecord('${Page.previousIndex}')" >上一页</a>
      </c:when>
      <c:otherwise>
       
      <%-- <img src="<%=request.getContextPath()%>/images/prevPage.gif">--%>
       上一页
      </c:otherwise>
     </c:choose>
     </td>
     <td valign="middle">
     <c:choose>
      <c:when test="${Page.nextIndex>Page.startIndex}">
      	<%--<img src="<%=request.getContextPath()%>/images/nextPage.gif" onclick="changeRecord('${Page.nextIndex}')">--%>
     	<a href="javascript:changeRecord('${Page.nextIndex}')" >下一页</a>
      </c:when>
      <c:otherwise>
      	<%--<img src="<%=request.getContextPath()%>/images/nextPage.gif">--%>
    	下一页
      </c:otherwise>
     </c:choose>
     </td>
     <td valign="middle">
     <c:choose>
      <c:when test="${Page.lastIndex eq Page.startIndex}">
       	<%--<img src="<%=request.getContextPath()%>/images/lastPage.gif"> --%>
       	末页
      </c:when>
      <c:otherwise>
      	<%--<img src="<%=request.getContextPath()%>/images/lastPage.gif" onclick="changeRecord('${Page.lastIndex}')"> --%>
      	<a href="javascript:changeRecord('${Page.lastIndex}')" >末页</a>
      </c:otherwise>
     </c:choose>
     </td>
     <td valign="middle">
     <input type="text" style="width: 30px" name="page" id="page"  size="3" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" >
     <input type="button" value="GO" class="btn1" onclick="gopage('${Page.lastIndex}')" >
     当前第${Page.currentPage}/${Page.pageCount}页
     </td>
 	<td>
  </tr>
  </table>
   </c:if>