//去除左空格
function LTrim(str)
{
    var i;
    for(i=0;i<str.length;i++)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(i,str.length);
    return str;
}

//去除右空格
function RTrim(str)
{
    var i;
    for(i=str.length-1;i>=0;i--)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(0,i+1);
    return str;
}

//去除左右空格
function Trim(str)
{
    return LTrim(RTrim(str));
}


function repstr(strExp,find,replace)
{
    var s = strExp;
    var t = find;
    var u = replace;
    re = new RegExp(t,"g");
    s = s.replace(re,u);
    return s;
}


// shift键选择checkbox
function shiftForCheckBoxFun(event) {
	;

}
//默认选中已被选中的项
function foreach(options,checkBoks){
		var count = 0;
		if(options.size()>0&&count<options.size()){
			options.each(function(){
				var option = $(this);
				if(option.attr("value")!=""){
					checkBoks.each(function(){
						var ckeckBok = $(this);
						if(ckeckBok.attr("value")==option.attr("value")){
							$(this).attr("checked","checked");
							count++;
						}
					});	
				}
			});
		}
}


//IFRAM 自动高度
function SetCwinHeight(frmId)
{
	var net=document.getElementById(frmId);
	if (document.getElementById) {
		if (net && !window.opera) {
			if (net.contentDocument && net.contentDocument.body.offsetHeight) {
				net.height = net.contentDocument.body.offsetHeight + 10; 
			} else if(net.document && net.document.body.scrollHeight) {
				net.height = net.document.body.scrollHeight + 10;
			}
		}
	}
}

// 用户浏览器
function userAgent() {
	
	if(navigator.userAgent.indexOf("MSIE")>0) { 
		return "MSIE"; 
	} 
	if(navigator.userAgent.indexOf("Firefox")>0){ 
        return "Firefox"; 
	} 
}



//创建div窗口返回div节点对象
var g_addstatus=0;
function creatDivWindow(){
	if(g_addstatus == 1) {
		destroyDlg();
		return;
	}
	var docWidth, docHeight;
    var dlgWidth = 400, dlgHeight = 400;
    docWidth = document.body.offsetWidth;
    docHeight = document.body.offsetHeight;
	destroyDlg();
	
	var objBgDiv = document.createElement("div");
	objBgDiv.setAttribute("id","bgDiv_addDlg");
	objBgDiv.style.position = "absolute";
	objBgDiv.style.background = "#eeeeee";
    if( window.ActiveXObject ) {
   		 objBgDiv.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=75,finishOpacity=75)";        
    }
    objBgDiv.style.left = "0px";
    objBgDiv.style.top = "0px";
    objBgDiv.style.width = docWidth + "px";
    objBgDiv.style.zIndex = "10000";
    objBgDiv.style.border = "0";
    objBgDiv.style.fontSize = "12px";   
    document.body.appendChild ( objBgDiv );
            
            var objBgIfr = document.createElement ( "iframe" );
            objBgIfr.setAttribute ( 'id', 'bgifr_addDlg' );
            objBgIfr.src = "#";
            objBgIfr.style.zIndex = -1;
            if( window.ActiveXObject ) {
            	objBgIfr.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)";
            } 
            objBgIfr.style.position = "absolute";
            objBgIfr.style.top = "0px";
            objBgIfr.style.left = "0px";
            objBgIfr.style.width = docWidth + "px"; 
            objBgIfr.style.height = docHeight + "px";
            objBgIfr.style.border = "0";
            document.getElementById ( "bgDiv_addDlg" ).appendChild ( objBgIfr );            
	        var objMsgDiv = document.createElement ( "div" );
	        var nowtime = new Date();
	        objMsgDiv.setAttribute ( 'id', 'dlgDiv' );
	        objMsgDiv.style.position = "absolute";
	        objMsgDiv.style.background = "#FFFFFF";
	        //objMsgDiv.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=75,finishOpacity=75)";
	        objMsgDiv.style.left = "100px";
	        objMsgDiv.style.top = "100px";
	        objMsgDiv.style.border = 1 + "px  solid #5AA4D1";
	        objMsgDiv.style.width = dlgWidth + "px";
	        objMsgDiv.style.height = dlgHeight + "px";
	        objMsgDiv.style.zIndex = "10000";
	        document.body.appendChild ( objMsgDiv );
	        g_addstatus = 1;  
	        return objMsgDiv;
} 
//删除div窗口
function destroyDlg(){
	 if ( g_addstatus == 1 ) {
          document.getElementById ( "bgDiv_addDlg" ).removeChild ( document.getElementById ( "bgifr_addDlg" ) );
          document.body.removeChild ( document.getElementById ( "bgDiv_addDlg" ) );
          document.body.removeChild ( document.getElementById ( "dlgDiv" ) );
          g_addstatus = 0;
     }
}
//把div窗口中选中项到页面的下拉框里
 /*function addOk(typeSelect) {
	 // 确定按钮
	$("#"+typeSelect).empty();
	$(".r2tCont input[type='checkbox']:checked").each(
	   function() {
		 var node = $(this).parent().next().children("a");
	        $("#"+typeSelect).append("<option value=\"" + $(this).val() + "\">" + node.text() + "</option>"); 
	         }
	);
	destroyDlg();
}*/
 function addOk(typeSelect, checkBoxDiv) {
	 // 确定按钮
	$("#"+typeSelect).empty();
	$("#"+checkBoxDiv+" input[type='checkbox']:checked").each(
	   function() {
		 var node = $(this).parent().next().children("a");
	        $("#"+typeSelect).append("<option value=\"" + $(this).val() + "\">" + node.text() + "</option>"); 
	         }
	);
	$("#"+checkBoxDiv).dialog('close');
	}
 
 function addOk_a(bid, id, typeSelect, checkBoxDiv) {
	 // 确定按钮
	$("#"+typeSelect).empty();
	$("#"+checkBoxDiv+" input[type='checkbox']").each(function() {
		if($(this).val()==id) {
			$(this).attr("checked",true);
			$("#"+typeSelect).append("<option value=\"" + id + "\">" + bid + "</option>"); 
			return false;
		 }
	});
	$("#"+checkBoxDiv+" input[type='checkbox']:checked").each(function() {
		if($(this).val()!=id) {
			var node = $(this).parent().next().children("a");
			$("#"+typeSelect).append("<option value=\"" + id + "\">" + node.text() + "</option>"); 
		}
	});
	$("#"+checkBoxDiv).dialog('close');
}
 
function addRadio_a(bid, id, name, idd, div) {
	$("#"+name).empty();
	$("#"+idd).empty();
	$("#"+id).attr("checked", true);
	$("#"+name).val(bid); 
	$("#"+idd).val(id);
	$("#"+div).dialog('close');
}

 
 //删除选中选项或删除全部选项
function delOption(typeSelect, checkName) {
	/*if ($('#' + type).get(0).selectedIndex == -1) {
		if (confirm('确认删除所有？')) {
			$('#' + type).empty();
		}
	} else {
		$('#' + type).find("option:selected").remove();
	}*/
	if($("#"+typeSelect).get(0).selectedIndex == -1) {
		if(confirm('确认删除所有？')) {
			$('input[type="checkbox"][name="'+checkName+'"]:checked').each(function(){
				$(this).attr("checked",false);
			});
			$("#"+typeSelect).empty();
		}
	} else {
		$('input[type="checkbox"][name="'+checkName+'"]:checked').each(function(){
			if($("#"+typeSelect).val() == parseInt($(this).val())){
				$(this).attr("checked",false);
				return false;
			}
		});
		$("#"+typeSelect).find("option:selected").remove();
		
	}
}
 //默认选中参数为select的id
function defualtSeleted(typeSelect){
	var options  = $("#"+typeSelect).children("option");
	var checkboxs = $(".r2tCont input[type='checkbox']");
	if(options.length>0){foreach(options,checkboxs);}
}
//鼠标经过与单击tr时触发事件
function mouseOverAndclick(){
	   var trNode=$(".r2tCont tr");
	   trNode.mouseover(function(){
	        	$(this).css("background-color","#cccccc");
	        });
	   trNode.mouseout(function(){
		   $(this).css("background-color","");
	   });
	   $(".r2tCont tr td").click(function(event){
		    if(event.target == this){
			   	var chkNode = $(this).parent("tr").children("td").children("input[type='checkbox']");
			   	chkNode.attr("checked",!chkNode.attr("checked"));
		   	}
	   });
	    $(".r2tCont tr td a").click(function(event){
		    if(event.target == this){
			   	var chkNode = $(this).parents("tr").children("td").children("input[type='checkbox']");
			   	chkNode.attr("checked",!chkNode.attr("checked"));
		   	}
	   });
}

//初始化dialog中的checkbox

function initCheckBox(typeSelect, checkName) {
	$('#'+typeSelect).children("option").each(function() {
		var i = $(this).val();
		$('input[type="checkbox"][name="'+checkName+'"]').each(function() {
			if($(this).val()==i) {
				$(this).attr("checked",true);
				return false;
			}
		});
	});
}
//初始化dialog中的radio
function initRadio(type, radioName) {
	var i = $($('#'+type)).val();
	$('input[type="radio"][name="'+radioName+'"]').each(function() {
		if($(this).val()==i) {
			$(this).attr("checked",true);
			return false;
		}
	});
}

