
(function($){
    $.fn.validationEngineLanguage = function(){
    };
    $.validationEngineLanguage = {
        newLang: function(){
            $.validationEngineLanguage.allRules = {
                "required": { // Add your regex rules here, you can take telephone as an example
                    "regex": "none",
                    "alertText": "* 此项不能为空...",
                    "alertTextCheckboxMultiple": "* Please select an option",
                    "alertTextCheckboxe": "* This checkbox is required"
                },
                "checkname": {
                	"regex":  /^[^\/\\\[\]\"\:\;\|\<\>\+\=\,\?\*]{1,}$/,
                	"alertText": "* 汉字 字母 数字 下划线等非包含\/\\\[\]\"\:\;\|\<\>\+\=\,\?\*字符串",
                	 "alertTextCheckboxMultiple": "* Please select an option",
                     "alertTextCheckboxe": "* This checkbox is required"
                },
                "textvalue": {
                	"regex":  /^[^\/\\\[\]\"\:\;\|\<\>\+\=\,\?\*]{1,}$/,
                	"alertText": "*不能包含特殊字符",
                	 "alertTextCheckboxMultiple": "* Please select an option",
                     "alertTextCheckboxe": "* This checkbox is required"
                },
                "checkResName": {
                	"regex": /^[\u4E00-\u9FA5a-zA-Z0-9_\.\!\@\#\$\%\&\*\(\)\-\=\+]{1,255}$/,
                	"alertText": "*资源名称不能包含特殊字符",
                	"alertTextCheckboxMultiple": "* Please select an option",
                    "alertTextCheckboxe": "* This checkbox is required"
                },
                "minSize": {
                    "regex": "none",
                    "alertText": "* Minimum ",
                    "alertText2": " characters allowed"
                },
                "maxSize": {
                    "regex": "none",
                    "alertText": "* 最大允许 ",
                    "alertText2": " 个字符"
                },
                "min": {
                    "regex": "none",
                    "alertText": "* Minimum value is "
                },
                "max": {
                    "regex": "none",
                    "alertText": "* Maximum value is "
                },
                "past": {
                    "regex": "none",
                    "alertText": "* 密码期限必须是今天或未来... "
                },
                "future": {
                    "regex": "none",
                    "alertText": "* Date past "
                },	
                "maxCheckbox": {
                    "regex": "none",
                    "alertText": "* Checks allowed Exceeded"
                },
                "minCheckbox": {
                    "regex": "none",
                    "alertText": "* Please select ",
                    "alertText2": " options"
                },
                "equals": {
                    "regex": "none",
                    "alertText": "* 确认密码和密码不一致..."
                },
                "phone": {
                    // credit: jquery.h5validate.js / orefalo
                    //"regex": /^([\+][0-9]{1,3}[ \.\-])?([\(]{1}[0-9]{2,6}[\)])?([0-9 \.\-\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/,
                	//"regex": /^(130|131|132|133|134|135|136|137|138|139|15[189])\d{8}$/,
                	  "regex": /^1[3|5|8]\d{9}$/, 
                    "alertText": "* 请输入正确的手机号码..."
                },
                "tel": {
                	  "regex": /^(0(\d{3,4}))[1-9]\d{6}$/, 
                    "alertText": "* 请输入正确的电话号码..."
                },
                "email": {
                    // Simplified, was not working in the Iphone browser
                    "regex": /^([A-Za-z0-9_\-\.\'])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,3})$/,
                    "alertText": "* 请输入正确的邮箱地址..."
                },
                "integer": {
                    "regex": /^[\-\+]?\d+$/,
                    "alertText": "* Not a valid integer"
                },
                "number": {
                    // Number, including positive, negative, and floating decimal. credit: orefalo
                    "regex": /^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/,
                    "alertText": "* Invalid floating decimal number"
                },
                "dateBasePortNumber": {
                	"regex": /^\d{1,4}$/,
                    "alertText": "* 请输入有效的数据库端口号..."
                },
                "date": {
                    // Date in ISO format. Credit: bassistance
                    "regex": /^\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}$/,
                    "alertText": "* Invalid date, must be in YYYY-MM-DD format"
                },
                "ipv4": {
                    "regex": /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/,
                    "alertText": "* 请输入合法的IP地址..."
                },
                "netmask": {
                	// 子网掩码验证
                    "regex": /^(254|252|248|240|224|192|128|0)\.0\.0\.0$|^(255\.(254|252|248|240|224|192|128|0)\.0\.0)$|^(255\.255\.(254|252|248|240|224|192|128|0)\.0)$|^(255\.255\.255\.(254|252|248|240|224|192|128|0))$/,
                    "alertText": "* 请输入有效的子网掩码..."
                },
                "url": {
                    "regex": /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/,
                    "alertText": "* Invalid URL"
                },
                "onlyNumberSp": {
                    "regex":/^[1-9]\d*$/,
                    "alertText": "* 请输入正整数..."
                },
                "onlyLetterSp": {
                    "regex": /^[a-zA-Z\ \']+$/,
                    "alertText": "* Letters only"
                },
                "onlyLetterNumber": {
                	"regex":  /^[^\/\\\[\]\"\:\;\|\<\>\+\=\,\?\*]{1,}$/,
                    "alertText": "* 请不要输入特殊字符..."
                },
                // --- CUSTOM RULES -- Those are specific to the demos, they can be removed or changed to your likings
                "ajaxUserCall": {
                    "url": "ajaxValidateFieldUser",
                    // you may want to pass extra data on the ajax call
                    "extraData": "name=eric",
                    "alertText": "* This user is already taken",
                    "alertTextLoad": "* Validating, please wait"
                },
                "ajaxNameCall": {
                    // remote json service location
                    "url": "ajaxValidateFieldName",
                    // error
                    "alertText": "* This name is already taken",
                    // if you provide an "alertTextOk", it will show as a green prompt when the field validates
                    "alertTextOk": "* This name is available",
                    // speaks by itself
                    "alertTextLoad": "* Validating, please wait"
                },
                "validate2fields": {
                    "alertText": "* Please input HELLO"
                },"dataBaseName": {
	            	   "regex": /^([a-zA-Z])[a-zA-Z\d_]*$/,
	            	   "alertText":"数据库名必须以字母开头，且只包含字母，数字，下划线"
	            },"validateLength": {
                	"regex": /^.{0,50}$/,
                	"alertText":"请输入1~50个字符"
                },"validateSpase": {
                	"regex": /^([^ ])*$/,
                	"alertText": "您输入的内容含有空格"
                },"validateScript": {
                	"regex": /^[^\<\>\/\>]{1,}$/,
                	"alertText": "您输入的内容含有<>/等字符"
                },"validateScriptT": {
                	"regex": /^[^\<\>\>]{1,}$/,
                	"alertText": "您输入的内容含有<>字符"
                },"sessionTime":{
                	"regex": /^([1-9]|[1-5][0-9]|60)$/,
                	"alertText": "超时时间必须大于等于1分钟且小于等于60分钟"
                },"ssoTimeout":{
                	"regex": /^([1-9]\d*)$/,
                	"alertText": "请输入大于1数字"
                }
            };
            
        }
    };
    $.validationEngineLanguage.newLang();
})(jQuery);


    
