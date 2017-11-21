/**
 * Created by Administrator on 2017/4/29.
 */
(function ($) {
	$.getUrlParam = function (name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return decodeURI(r[2]); return null;
	}
})(jQuery);

/**
 * @description 将传入的url参数对象解析组装成字符串做为queryString中的一部分
 * @param {Object} params 请求参数的数组
 * @param {string} cgi 请求串
 * @return {String} queryString部分字符串
 * @example ： param1=value1&param2=value2&param3=value3......
 */
function convert_params(params, cgi){
	var paramsArray = [];
	for (var name in params) {
		if (paramsArray.length == 0) {
			cgi && cgi.indexOf('?') != -1 ? paramsArray.push("&") : paramsArray.push("?");
		}
		else {
			paramsArray.push("&");
		}
		paramsArray.push(name);
		paramsArray.push("=");
		paramsArray.push(params[name]);
	}
	return paramsArray.join("");
}
// console.log(convert_params({"param": "value1", "param2": "value2"}, "/post/"));
// // ?param=value1&param2=value2
// console.log(convert_params({"param": "value1", "param2": "value2"}, "/post/?page=1"));
// // &param=value1&param2=value2