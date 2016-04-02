
	var g_overflag = 0;
	//创建div窗口返回div节点对象
	function createOverDiv(e,width,height,html){
		if(document.getElementById ( "dlgOverDiv" ) != null) {
			destroyOverDlg();
		}
		var docWidth, docHeight;
	    var dlgWidth = width, dlgHeight = height;
	    docWidth = document.body.offsetWidth;
	    docHeight = document.body.offsetHeight;
	    
	    var t = e.offsetTop + e.offsetHeight;
		var l = e.offsetLeft;
		var w = e.offsetWidth;
		while (e = e.offsetParent) {
			t += e.offsetTop;
			l += e.offsetLeft;
		}
		
		            
	    var objMsgDiv = document.createElement ( "div" );
	    
	    objMsgDiv.setAttribute ( 'id', 'dlgOverDiv' );
	    objMsgDiv.style.position = "absolute";
	    objMsgDiv.style.background = "#FFFFFF";
	    //objMsgDiv.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=75,finishOpacity=75)";
	    objMsgDiv.style.left = (l-300) + "px";
	    objMsgDiv.style.top = t + "px";
	    //objMsgDiv.style.border = 1 + "px  solid #5AA4D1";
	    objMsgDiv.style.width = dlgWidth + "px";
	    objMsgDiv.style.height = dlgHeight + "px";
	    objMsgDiv.style.zIndex = "10000";
	    objMsgDiv.cancelBubble = true;
	    objMsgDiv.onclick = function(event) {if(userAgent()=='MSIE'){window.event.cancelBubble=true;}else{event.stopPropagation();event.preventDefault();}}
	    objMsgDiv.onmouseover = function(event) {if(userAgent()=='MSIE'){window.event.cancelBubble=true;}else{event.stopPropagation();event.preventDefault();g_overflag=1;}}
	    objMsgDiv.onmouseout = function(event) {if(userAgent()=='MSIE'){window.event.cancelBubble=false;}else{window.event.stopPropagation();event.preventDefault();}if(g_overflag == 1){destroyOverDlg();g_overflag = 0;}}
	    objMsgDiv.innerHTML = html;
	    
	    document.body.appendChild ( objMsgDiv );  
	    
	} 
	//删除div窗口
	function destroyOverDlg(){
		if(document.getElementById ( "dlgOverDiv" ) != null) {
	         document.body.removeChild ( document.getElementById ( "dlgOverDiv" ) );
	    }
	}
	