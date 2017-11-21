<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title></title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css">


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/default/easyui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/bootstrap/tree.css">
	<style>
		.border-input {
			border-left: 4px solid #1ab394;
		}

		.form-inline .col-wid40 {
			width: 70%;
		}

		.form-inline .group-db {
			display: block;

			margin-bottom: 20px;
		}

		label {
			margin-right: 10px;
		}

		.form-inline {
			width: 50%;
		}

	</style>
</head>
<body>

<div class="wrapper wrapper-content  animated fadeInRight">


	<div class="row">

		<div class="col-sm-12">
			<div class="from-box ovel">
				<form class="form-inline fl">
					<div class="form-group group-db">
						<label for="pac" class="col-sm-2">pac</label>
						<input type="text" class="form-control border-input col-wid40" name="pac" id="pac"
						       value="${user.pac}" readonly>
					</div>

					<div class="form-group group-db">
						<label for="password" class="col-sm-2">password</label>
						<input type="text" class="form-control border-input col-wid40" name="password" id="password"
						       value="${user.password}" readonly>
					</div>



				</form>

				<form class="form-inline fl">


					<div class="form-group group-db">
						<label for="accountType" class="col-sm-2">accountType</label>
						<input type="text" class="form-control border-input col-wid40" name="accountType"
						       id="accountType" value="${user.accountType}" readonly>
					</div>


					<div class="form-group group-db">
						<label for="headPortrait" class="col-sm-2">headPortrait</label>
						<input type="text" class="form-control border-input col-wid40" name="headPortrait"
						       id="headPortrait" value="${user.headPortrait}" readonly>
					</div>


					<div class="form-group group-db">
						<label for="competence" class="col-sm-2">competence</label>
						<input type="text" class="form-control border-input col-wid40" name="competence" id="competence"
						       value="${user.competence}" readonly>
					</div>

					<div class="form-group group-db">
						<label for="id" class="col-sm-2">id</label>
						<input type="text" class="form-control border-input col-wid40" name="id" id="id" value="${user.id}" readonly>
					</div>




				</form>

			</div>
			<from class="form-inline ">
				<div class="form-group group-db">
					<label class="col-sm-1">行政区</label>

					<select id="city" onchange="changeCity(this)" disabled="disabled" class="form-control border-input  "></select>
					<select id="county" onchange="" disabled="disabled" class="form-control border-input "></select>
				</div>
			</from>

		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
<script>
	//模板
	var temp = {
		selected_option_count : '<option value="{code}">{text}</option>'
	};

</script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script>
	var $dom = {};
	$(function(){

		$dom.city = $('#city');
		$dom.county = $('#county');

		initPac('${user.pac}')
	})

	//初始化行政区
	function initPac(pac, callback){

		//判断用户是什么级别的用户
		switch (formatPacLevel(pac)){
			case 1:
				//省级用户
				getPac($dom.city,pac,2)
				break;
			case 2:
				//市级用户
				var pac_province = pac.substring(0,2);
				getPac($dom.city, formatPacUnderline(pac_province), 2,function(){

					getPac($dom.county,pac,3,function(){
						$dom.city.val(formatPacUnderline( pac) );
						$dom.county.val(formatPacUnderline(pac) );
					})
				})

				break;
			case 3:
				//县级用户
				var pac_province = pac.substring(0,2);
				getPac($dom.city, formatPacUnderline(pac_province), 2,function(){

					var pac_city = pac.substring(0,4);
					getPac($dom.county, formatPacUnderline(pac_city) ,3,function(){

						var pac_county = pac.substring(0,6);
						getPac($dom.county, formatPacUnderline(pac_city) ,3,function(){
							$dom.city.val(formatPacUnderline(pac_city));
							$dom.county.val(formatPacUnderline(pac_county))
						})

					})
				})
				break;
		}

	}

	//工具-拉取pac数据
	function getPac(item,id,level,callback) {

		console.log(item)

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 210002,//接口
				pac: id,
				fetchType: 1
			},
			success: function(data){
				data = JSON.parse(data);

				item.empty();

				var sub = '';
				var rows = data.rows;
				for (var i in rows){
					if(rows.hasOwnProperty(i)){

						sub += temp.selected_option_count.format({
							code: rows[i].code,
							text: rows[i].name
						})
					}
				}
				if (level < 2 ){
					item.append(sub);
				}
				item.html(sub);

				try {
					callback(rows)
				} catch ( e ){

				}
			}
		})
	}

</script>

</body>
</html>