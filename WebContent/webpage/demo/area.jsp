<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/6/20
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
	var $dom = {
		pac: ''
	};
	var this_data = {
		pac: ''
	};
	//模板
	var temp = {
		selected_option_count : '<option value="{code}">{text}</option>'
	};
</script>
<script src="${pageContext.request.contextPath}/config/cg.js"></script>
<select id="province" onchange="changeProvince(this)"></select>
<select id="city" onchange="changeCity(this)"></select>
<select id="county" onchange="this_data.pac = this.value;var pac = document.getElementById('pac');pac.value = this.value;"></select>
<script src="//cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js"></script>
<script>

	$( document ).ready( function(){

		$dom.province = $('#province');
		$dom.city = $('#city');
		$dom.county = $('#county');

		//初始化PAC
		getPac($dom.province,this_data.pac,1);

	});

	//工具-拉取pac数据
	function getPac(item,id,level) {

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 210001,//接口
				id: id
			},
			success: function(data){
				data = JSON.parse(data);

				var sub = '';
				sub += temp.selected_option_count.format({
					code: data.code,
					text: data.text,
				});
				var children = data.children;
				for (var i in children){
					if(children.hasOwnProperty(i)){
						sub += temp.selected_option_count.format({
							code: children[i].code,
							text: children[i].text,
						})
					}
				}
				if (level < 2 ){
					item.append(sub);
				}
				item.html(sub);
			}
		})
	}

	//当省选择框发生改变
	function changeProvince(item) {

		getPac($dom.city,item.value.substring(0,2),2);
		$dom.county.empty();
		this_data.pac = item.value;

	}
	//当市高等框发生改变
	function changeCity(item) {

		getPac($dom.county,item.value.substring(0,4),3);
		this_data.pac = item.value;

	}

</script>