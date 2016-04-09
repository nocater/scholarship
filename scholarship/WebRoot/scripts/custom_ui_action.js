// JavaScript Document
function trigger_icon(evn)
{	
	/**
		获取被点击对象class属性，判断是否已经展开子级树
	*/
	var element_class =  $(evn).attr("class");
	var num = element_class.indexOf("tree_ui_icon_bg_r");
	if(num != -1)
	{
		$(evn).removeClass("tree_ui_icon_bg_r");
		$(evn).addClass("tree_ui_icon_bg_b");	
		$(evn).nextAll("ul").show();		
	}
	else
	{
		$(evn).removeClass("tree_ui_icon_bg_b");
		$(evn).addClass("tree_ui_icon_bg_r");			
		$(evn).nextAll("ul").hide();
	}
}

function trigger_dblclick_action(evn)
{	
	var element_lable = $(evn).prev().prev();
	
	/**
		获取被点击对象紧邻的前一个同辈元素的class属性，判断是否已经展开子级树
	*/
	var element_class =  $(element_lable).attr("class");
	var num = element_class.indexOf("tree_ui_icon_bg_r");
	if(num != -1)
	{
		$(element_lable).removeClass("tree_ui_icon_bg_r");
		$(element_lable).addClass("tree_ui_icon_bg_b");	
		$(element_lable).nextAll("ul").show();		
	}
	else
	{
		$(element_lable).removeClass("tree_ui_icon_bg_b");
		$(element_lable).addClass("tree_ui_icon_bg_r");			
		$(element_lable).nextAll("ul").hide();
	}
}

//用户定义动作
function custom_action(evn){
	var next_checkbox_value = $(evn).prev().children().val();
	var lable = $(evn).text();
	$("#te").text("当前点击："+lable+"   ID:"+next_checkbox_value);
}
var spanBold="";
//改变span标签样式
function changeStyle(tree,change){
/*	var spans = $("#"+tree+",span");
    $.each(spans,function(i,n){
		$(n).attr("style","font-size:normal");
	});
	$("#all").attr("style","font-size:normal");
	$("#no").attr("style","font-size:normal");*/
	if(spanBold == ""){
		spanBold = change;
		$(change).attr("style","font-size:13px;font-weight:bold;");
	}else{
		$(spanBold).attr("style","font-size:normal");
		spanBold = change;
		$(change).attr("style","font-size:13px;font-weight:bold;");
	}
	
} 
//删除动作
function delete_lable(){
	var check = $("#Tree :checkbox[checked = true]");
		var group_ids = "";
		$.each(check,function(i,n){
			if(group_ids == "")
			{
				group_ids = $(n).val();
			}
			else
			{
				group_ids += ","+$(n).val();
			}
			});
			$("#te").text("删除节点ID:"+group_ids);
}