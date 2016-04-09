/*
 *自定义Tree插件
 *根据json数据字符串写出树
 *version 1.0
 *王纯
 */

(function($) {
jQuery.extend({
		parsing_json_data: function(){},
		create_tree_html: function(parameters){
				var tree_div_id = parameters.id;  //tree div ID
				var tree_div_width = parameters.width;  // tree div width
				var tree_div_height = parameters.height; // tree div height;
				var tree_json_data = $.parseJSON('['+parameters.chidren+']');// 树json数据
				var tree_checkbox_e = parameters.edit;  //是否显示可编辑选择框 true显示
				var tree_autoOpen = parameters.autoOpen;
				//var tree_lable_click = parameters.lableClick; //单击名称自定义函数
				
			
				var tree_div = $("#"+tree_div_id);
				
				$(tree_div).addClass("tree_ui_root");
				
				$(tree_div).css({width:tree_div_width,height:tree_div_height});
				var tree_ui_icon_bg = "tree_ui_icon_bg_b";
				if(!tree_autoOpen)
				{
					tree_ui_icon_bg = "tree_ui_icon_bg_r";
				}
				
				
				var i;
				for(i = 0; i < tree_json_data.length; i++)
				{

					var tree_ul = $('<ul></ul>');
					var tree_ul_li = $('<li></li>');
					//img
					var tree_ul_li_span_icon = $('<span></span>');
					//checkbox
					var tree_ul_li_span_input = $('<span></span>');
					var tree_ul_li_input = $('<input type="checkbox" class="tree_ui_checkbox tree_ui_align_box" value="'+tree_json_data[i].id+'"/>');
					//hidden
					var tree_ul_li_input_hidden = $('<input type="hidden" class="tree_ui_checkbox tree_ui_align_box" value="'+tree_json_data[i].id+'"/>');
					
					//add ul to html
					$(tree_ul).appendTo(tree_div);
				
					$(tree_ul_li).appendTo(tree_ul);
					
					$(tree_ul_li_span_icon).appendTo(tree_ul_li);
					$(tree_ul_li_span_icon).addClass("tree_ui_icon "+tree_ui_icon_bg+" tree_ui_float");

					
					//bind icon click even
					$(tree_ul_li_span_icon).bind('click', function() {
  						trigger_icon(this);
						});
										
					
					$(tree_ul_li_span_input).appendTo(tree_ul_li);
					if(tree_checkbox_e)
					{
						$(tree_ul_li_input).appendTo(tree_ul_li_span_input);
						$(tree_ul_li_input).bind('click',function(e){
							var inpput_start = $(this).attr("checked");
							var next_ul = $(this).parent().nextAll().children().find("input[type='checkbox']");
							$(next_ul).attr("checked",inpput_start == undefined ? false : inpput_start);
						});
						//未分组不允许操作
						var id = $(tree_ul_li_input).val();
						if(id == 0)
						{
							$(tree_ul_li_input).attr("disabled","disabled");
						}
					}
					else
					{
						$(tree_ul_li_input_hidden).appendTo(tree_ul_li_span_input);
					}
					
					var tree_ul_li_span = $('<span>'+tree_json_data[i].lable +'</span>');
					$(tree_ul_li_span).appendTo(tree_ul_li);
					$(tree_ul_li_span).addClass("tree_ui_ti tree_ui_align");
					//lable click action 处理click and dblclick冲突问题
					var _time = null;
					$(tree_ul_li_span).bind('click', function(e) {
						var _this = this;
						_time && clearTimeout(_time);
						_time = setTimeout(function(){
							//console.log(e.type);	//日志输出	
							custom_action(_this);
							},400);

					}).bind('dblclick',function(e){
						_time && clearTimeout(_time);
							//console.log(e.type);  //日志输出
							trigger_dblclick_action(this);
						});
				
					
					if(typeof tree_json_data[i].chidren != "undefined")
					{		
						//$($.create_chidren(tree_json_data[i].chidren),$(tree_ul_li)).appendTo(tree_ul_li);			
						$.create_chidren(tree_json_data[i].chidren,tree_ul_li,tree_checkbox_e,tree_autoOpen);
					}

				
				}
			},
			//create chidren tree
			create_chidren: function(_chidren,li_chidren,tree_checkbox_e,tree_autoOpen){
				var j ;
				for(j = 0; j < _chidren.length; j++)
				{
					var tree_ul = $('<ul></ul>');
					var tree_ul_li = $('<li></li>');
					var tree_ul_li_span_icon = $('<span></span>');
					
					var tree_ul_li_span_input = $('<span></span>');
					var tree_ul_li_input = $('<input type="checkbox" class="tree_ui_checkbox tree_ui_align_box" value="'+_chidren[j].id+'"/>');
					var tree_ul_li_input_hidden = $('<input type="hidden" class="tree_ui_checkbox tree_ui_align_box" value="'+_chidren[j].id+'"/>');
					//$(tree_ul).appendTo(tree_div);
					var tree_ui_icon_bg = "tree_ui_icon_bg_b";
					if(!tree_autoOpen)
					{
						tree_ui_icon_bg = "tree_ui_icon_bg_r";
						$(tree_ul).addClass("tree_ui_display_none");
					}
					
					
					$(tree_ul_li).appendTo(tree_ul);
					
					$(tree_ul_li_span_icon).appendTo(tree_ul_li);
					$(tree_ul_li_span_icon).addClass("tree_ui_icon "+tree_ui_icon_bg+" tree_ui_float");
					
					//bind icon click even
					$(tree_ul_li_span_icon).bind('click', function() {
  						trigger_icon(this);
					});
					
					
					$(tree_ul_li_span_input).appendTo(tree_ul_li);
					if(tree_checkbox_e)
					{
						$(tree_ul_li_input).appendTo(tree_ul_li_span_input);
						$(tree_ul_li_input).appendTo(tree_ul_li_span_input);
						$(tree_ul_li_input).bind('click',function(e){
							var inpput_start = $(this).attr("checked");
							var next_ul = $(this).parent().nextAll().children().find("input[type='checkbox']");
							$(next_ul).attr("checked",inpput_start == undefined ? false : inpput_start);
						});
						//未分组不允许操作
						var id = $(tree_ul_li_input).val();
						if(id == 0)
						{
							$(tree_ul_li_input).attr("disabled","disabled");
						}
					}
					else
					{
						$(tree_ul_li_input_hidden).appendTo(tree_ul_li_span_input);
					}
					
					
					var tree_ul_li_span = $('<span>'+_chidren[j].lable +'</span>');
					$(tree_ul_li_span).appendTo(tree_ul_li);
					$(tree_ul_li_span).addClass("tree_ui_ti tree_ui_align");
					
					//lable click action 处理click and dblclick冲突问题
					var _time = null;
					$(tree_ul_li_span).bind('click', function(e) {
						var _this = this;
						_time && clearTimeout(_time);
						_time = setTimeout(function(){
							//console.log(e.type);  //日志输出
							custom_action(_this);
							},400);

					}).bind('dblclick',function(e){
						_time && clearTimeout(_time);
							//console.log(e.type);   //日志输出
							trigger_dblclick_action(this);
						});
			
					
					$(tree_ul).appendTo(li_chidren);
					
					if(typeof _chidren[j].chidren != "undefined")
					{						
						$.create_chidren(_chidren[j].chidren,tree_ul_li,tree_checkbox_e,tree_autoOpen);		
					}
				
				}
			}
	});



jQuery.fn.extend({
	
	});
})(jQuery);