/**
 * Created by YanWei on 2017/3/18.
 */
/**
 * 对字符串的拼凑
 * @param  {[type]} args [description]
 * @return {[type]}      [description]
 */
String.prototype.format = function(args) {
	var result = this;
	if (arguments.length > 0) {
		if (arguments.length == 1 && typeof(args) == "object") {
			for (var key in args) {
				if (args[key] != undefined) {
					var reg = new RegExp("({" + key + "})", "g");
					result = result.replace(reg, args[key]);
				}
			}
		} else {
			for (var i = 0; i < arguments.length; i++) {
				if (arguments[i] != undefined) {
					var reg = "/\\{"+i+"\\}/g";
					result = result.replace(eval(reg), arguments[i]);
				}
			}
		}
	}
	return result;
};

// 数组删除
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt)
{
	var o = {
		"M+" : this.getMonth()+1,                 //月份
		"d+" : this.getDate(),                    //日
		"h+" : this.getHours(),                   //小时
		"m+" : this.getMinutes(),                 //分
		"s+" : this.getSeconds(),                 //秒
		"q+" : Math.floor((this.getMonth()+3)/3), //季度
		"S"  : this.getMilliseconds()             //毫秒
	};
	if(/(y+)/.test(fmt))
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	return fmt;
};
function getYearMonth() {
	//直接对date1.getDate()加减就行
	var yearMonth = new Date().Format("yyyy-MM"); //"1989-09-13"
	if (new Date().Format("MM") == "00") {
		yearMonth = (new Date().Format("yyyy") - 1) + "-" + "12";
	}
	return yearMonth;
}


/**
 * 设置select选中
 * @param selectId select的id值
 * @param checkValue 选中option的值
 * @author lqy
 * @since 2015-08-21
 */
function setSelectChecked(selectId, checkValue){
	var select = document.getElementById(selectId);
	for(var i=0; i<select.options.length; i++){

		if(select.options[i].value == checkValue){
			select.options[i].selected = true;
			break;
		}
	}
};

/**
 * 清除节点注释
 * @param domStr string类型 如：<!--<div id="label">...</div>-->
 * @return string <div id="label">...</div>
 */
function toComment(domStr){
	return domStr.replace(/<\!--/g,'').replace(/-->/g,'');
}

function uuidEasy() {
	var s = [];
	var hexDigits = "0123456789abcdef";
	for (var i = 0; i < 36; i++) {
		s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
	}
	s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
	s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
	s[8] = s[13] = s[18] = s[23] = "-";

	var uuid = s.join("");
	return uuid;
}

/**
 * 是否为空
 * @param str
 */
function isNull(str){
	str = str.trim();
	if (str == null || str == undefined || str.length < 1){
		return true;
	}
	return false;
}
function isNotNull(str){
	if (str != null || str != undefined){
		return true;
	}
	return false;
}


function unique(arr) {

	var result = [], hash = {};

	for (var i = 0, elem; (elem = arr[i]) != null; i++) {

		if (!hash[elem]) {

			result.push(elem);

			hash[elem] = true;

		}

	}

	return result;

}

var deepCopy= function(source) {

	var result={};
	for (var key in source) {
		result[key] = typeof source[key]==='object'? deepCopy(source[key]): source[key];
	}
	return result;
}


/***
 * 获得对应 行政代码
 *
 * 传入完整的行政代码，如：360000：返回36；360100：返回3601；360102；返回360102
 *
 * @param str
 * @returns {*}
 */
function formatPac(str){
	str += '';
	if(str.length != 6){
		return '';
	}
	try {
		var province = Number( str.substring(0,2) );
		var city = Number( str.substring(2,4) ) > 0 ? str.substring(2,4) : '';
		var county = Number( str.substring(4,6) ) > 0 ? str.substring(4,6) : '' ;
		var village = Number( str.substring(6, 9) ) > 0 ? str.substring(6,9) : '' ;
		var village_1 = Number( str.substring(9, 12) ) > 0 ? str.substring(9, 12) : '' ;
		var village_2 = Number( str.substring(12, 15) ) > 0 ? str.substring(12, 15) : '' ;
		var str_ = province + city;

		if ( typeof county != Number ) {
			str_ += county;
		}
		if ( typeof village != Number ) {
			str_ += village;
		}
		if ( typeof village_1 != Number ) {
			str_ += village_1;
		}
		if ( typeof village_2 != Number ) {
			str_ += village_2;
		}

		return str_;

	} catch (e) {

		return '';

	}
}

/***
 * 获得对应 行政代码
 *
 * 传入完整的行政代码，如：360000：返回36；360100：返回3601；360102；返回360102
 *
 * @param str
 * @returns {*}
 */
function formatPacLevel(str){
	str += '';
	try {
		var province = Number( str.substring(0,2) ) > 0 ? str.substring(0,2) : '';
		var city = Number( str.substring(2,4) ) > 0 ? str.substring(2,4) : '';
		var county = Number( str.substring(4,6) ) > 0 ? str.substring(4,6) : '' ;
		var village = Number( str.substring(6, 9) ) > 0 ? str.substring(6,9) : '' ;
		var village_1 = Number( str.substring(9, 12) ) > 0 ? str.substring(9, 12) : '' ;
		var village_2 = Number( str.substring(12, 15) ) > 0 ? str.substring(12, 15) : '' ;
		var str_ = '';

		if ( typeof province != Number ) {
			str_ += province;
		}
		if ( typeof city != Number ) {
			str_ += city;
		}
		if ( typeof county != Number ) {
			str_ += county;
		}
		if ( typeof village != Number ) {
			str_ += village;
		}
		if ( typeof village_1 != Number ) {
			str_ += village_1;
		}
		if ( typeof village_2 != Number ) {
			str_ += village_2;
		}

		switch (str_.length){
			case 0: return 0;   //国家
			case 2: return 1;   //省
			case 4: return 2;   //市
			case 6: return 3;   //区/县
			case 9: return 4;   //乡镇
			case 12: return 5;  //村
			case 15: return 6;
			default: return 7;
		}

	} catch (e) {

		return '';

	}
}

function formatPacUnderline(str){
	var str_ = formatPac(str);
	switch (str_.length){
		case 2:return str_ + '0000';
		case 4:return str_ + '00';
		case 6:return str_;
		default:return '';
	}
}
function formatPacUnderline(str_,flog){

	switch (str_.length){
		case 2:return str_ + '0000';
		case 4:return str_ + '00';
		case 6:return str_;
		default:return '';
	}
}
function formatPacUnderlineNext(str){
	var str_ = formatPac(str);
	switch (str_.length){
		case 2:return str_ + '0000';
		case 4:return str_ + '00';
		case 6:return str_;
		default:return '';
	}
}