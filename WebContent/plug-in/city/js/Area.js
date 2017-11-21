/**
 * Created by Administrator on 2016/3/11.
 */
/**
 *  省,市,县 3级联动js
 * @author
 * @date    2015-10-10
 * @version Ver 0.0.1
 */
function initComplexArea(a, k, h, p, q, z, d, b, l) {
	var f = initComplexArea.arguments;
	var m = document.getElementById(a);//省
	var o = document.getElementById(k);//市下拉框
	var n = document.getElementById(h);//区县
	var e = 0;
	var c = 0;
	//
	//var arr = d+""+b;//代表市的数组编号
	//var dbl = d+""+b + ""+l;//代表区的数组编号

	var arr = b;//代表市的数组编号
	var dbl = l;//代表区的数组编号

	if (p != undefined) {
		if (d != undefined) {
			d = parseInt(d);
		}
		else {
			d = 0;
		}
		if (b != undefined) {
			b = parseInt(b);
		}
		else {
			b = 0;
		}
		if (l != undefined) {
			l = parseInt(l);
		}
		else {
			l = 0
		}
		n[0] = new Option("请选择 ", 0);
		for (e = 0; e < p.length; e++) {
			if (p[e] == undefined) {
				continue;
			}
			if (f[7]) {
				if (f[7] == true) {
					if (e == 0) {
						continue
					}
				}
			}
			//m[c] = new Option(p[e], e);
			var ms = new Option(p[e], p[e]);
			ms.setAttribute('data-code',e);
			if (d == e) {
				ms.selected = true;//为省一级赋默认值
			}
			m[c] = ms;
			c++
		}
		o[0] = new Option("请选择 ", 0);//为市一级赋“请选择”初始值
		if (q[d] != undefined) {
			//o[0] = new Option("请选择 ", 0);
			c = 0;
			for (e = 0; e < q[d].length; e++) {
				if (q[d][e] == undefined) { continue }
				if (f[7]) {
					if ((f[7] == true) && (d != 71) && (d != 81) && (d != 82)) {
						if ((e % 100) == 0) { continue }
					}
				}
				var mv = new Option(q[d][e], q[d][e]);//alert(q[d][e])为市一级赋默认值
				mv.setAttribute('data-code',e);
				if (arr == e) {
					mv.selected = true;
				}
				o[c] = mv;
				c++
			}
		}

		/*为第三级列表赋默认值 */
		if (z[arr] != undefined) {
			c = 0; for (e = 0; e < z[arr].length; e++) {
				if (z[arr][e] == undefined) { continue }
				if (f[7]) {
					if ((f[7] == true) && (d != 71) && (d != 81) && (d != 82)) {
						if ((e % 100) == 0) { continue }
					}
				}
				var ms = new Option(z[arr][e], z[arr][e]);
				ms.setAttribute('data-code',e);
				if (dbl == e) { ms.selected = true }
				n[c] = ms;
				c++
			}
		}
		//编辑时如果两级把第三级隐藏
		if (d == 11 || d == 12 || d == 31 || d == 71 || d == 50 || d == 81 || d == 82) {
			if ($("#" + h + "_div"))
			{ $("#" + h + "_div").hide();}
		}

	}
}
//改变省下拉框事件
function changeComplexProvince(f, k, e, d ) {
	f = formatAdds(f).province;
	var c = changeComplexProvince.arguments; var h = document.getElementById(e);
	var g = document.getElementById(d); var b = 0; var a = 0; removeOptions(h); f = parseInt(f);

	h[0] = new Option("请选择", "请选择");//改变时第2 个下拉框显示"请选择"

	if (k[f] != undefined) {
		for (b = 0; b < k[f].length; b++) {
			if (k[f][b] == undefined) { continue }
			if (c[3]) { if ((c[3] == true) && (f != 71) && (f != 81) && (f != 82)) { if ((b % 100) == 0) { continue } } }
			var ms =  new Option(k[f][b], k[f][b]);
			ms.setAttribute('data-code', b);
			h[a] = ms;
			a++
		}
	}
	removeOptions(g); g[0] = new Option("请选择", "请选择");
	if (f == 11 || f == 12 || f == 31 || f == 71 || f == 50 || f == 81 || f == 82) {
		if ($("#" + d + "_div"))
		{ $("#" + d + "_div").hide();}
	}
	else {
		if ($("#" + d + "_div")) { $("#" + d + "_div").show(); }
	}
}


//改变市下拉框事件
function changeCity(c, a, t) {
	var $this = $(c);
	var ProvincenNum= c.value;
	$this.find('option').each(function(index,dom){
		if(ProvincenNum == $(this).attr('value')){
			ProvincenNum = $(this).attr('data-code')
		}
	});

	if ( ProvincenNum== 11 || ProvincenNum == 12 || ProvincenNum == 31 || ProvincenNum == 71 || ProvincenNum == 50 || ProvincenNum == 81 	|| ProvincenNum == 82) {
		return;
	}

	$("#" + a).html('<option value="请选择" data-code="0">请选择</option>');
	$("#" + a).unbind("change");
	c =  formatAdds(document.getElementById(t).value, c.value).city;
	var _d = sub_arr[c];
	var str = "";
	str += "<option value='请选择' data-code='0'>请选择</option>";
	for (var i = c * 100; i < _d.length; i++) {
		if (_d[i] == undefined) continue;
		str += "<option value='" + _d[i] + "' data-code=\"" + i +"\" >" + _d[i] + "</option>";
	}
	$("#" + a).html(str);

}

//移除option选项
function removeOptions(c) {
	if ((c != undefined) && (c.options != undefined)) {
		var a = c.options.length;
		for (var b = 0; b < a; b++) {
			c.options[0] = null;
		}
	}
}

//拓展

/**
 * add by YanWei
 * 根据地区码查询地区名
 * @param areaID
 * @returns {{}}
 */
function getAreaNamebyID(areaID) {
	var area = {};
	if (areaID.length == 2) {
		area.province = area_array[areaID];
	} else if (areaID.length == 4) {
		var index1 = areaID.substring(0, 2);
		area.province = area_array[index1];
		area.city = sub_array[index1][areaID];
	} else if (areaID.length == 6) {
		var index1 = areaID.substring(0, 2);
		var index2 = areaID.substring(0, 4);
		area.province = area_array[index1];
		area.city = sub_array[index1][index2];
		area.county = sub_arr[index2][areaID];
	}
	return area;
}

/**
 * add by YW
 * 城市转代号
 * @param a 省
 * @param b 市
 * @param c 区/县
 * @returns {{}}
 */
function formatAdds(a,b,c){
	var adds = {};
	for(var i in area_array){
		if(area_array[i]== a){
			adds.province = i;

			var sub = sub_array[i];
			for(var j in sub){
				if(sub[j] == b){
					adds.city = j;

					var larr = sub_arr[j];
					for(var l in larr){
						if(larr[l] == c){
							adds.county = l;
						}
					}

				}
			}

		}
	}

	return adds;
}