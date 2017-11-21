<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/7/10
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>添加火灾档案</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.3.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>
		/*#city,#county,#county1{*/
			/*width: 45%;*/
			/*margin-right: 5px;*/
		/*}*/

		/*!******************************************* 上传进度条 Start ********************************************!*/
		/*#bar-progress {*/
			/*display: block;*/
			/*height: 5px;*/
			/*width: 0%;*/
			/*position: fixed;*/
			/*top: 0;*/
			/*left: 0;*/
			/*background-color: #1AB394;*/
			/*z-index: 99999;*/
		/*}*/

		/*#bar-progress-text {padding-top: 5px;}*/
		/*!******************************************* 上传进度条 End  *********************************************!*/
		.firetablebox{

			height:auto;
			margin: 0 auto;
		}
		.firetabletitle{
			width:100%;
			height:50px;
			background-color: #f3f3f3;;
			color:#505050;
			line-height:50px;
			font-size:16px;
			text-align: center;
		}
		.firetablebox table tbody>tr>td:nth-child(1),.firetablebox table tbody>tr.tabletrj>td.tabletrd {
			width: 122px;

			text-align: right;
			box-sizing: border-box;
		}
		.firetablebox table tbody>tr>td:nth-child(2){
			padding-left: 20px;
			box-sizing: border-box;
		}
		.input100{
			width: 100px;
		}
		.input50{
			width: 50px;
		}
		.mbr{
			margin-right:10px;
			margin-bottom: 10px;
		}
		.flspan{
			display: inline-block;
			width:54px;text-align: center;
		}
		.flspan1{
			width:90px;
			display: inline-block;text-align: center;
		}
	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var this_data = {
			pac:  formatPacUnderline(cg_vte.initPac),
		};
	</script>
</head>

<body>


	<div class="wrapper wrapper-content">


		 <div class="firetablebox">
			 <div class="firetabletitle">
				 森林火灾基本信息采集表
			 </div>
			 <table class="table table-bordered table-hover ">
				 <thead>

				 </thead>
				 <tbody>
				 <tr>
					 <td>火灾编号</td>
					 <td colspan="3">
						 <input type="text" id="no" class="margin-10">
						 <span>&nbsp&nbsp注: (*自动生成)</span>
					 </td>

				 </tr>
				 <tr>
					 <td>火灾名称</td>
					 <td colspan="3"><input type="text" id="name"> <span>&nbsp&nbsp注: (*自动生成)</span></td>
				 </tr>
				 <tr>
					 <td>起火地点</td>
					 <td colspan="3">
						 <div class="wreck fl  ">
							 <span class=" flspan">省</span>
							 <input type="text" id="province" class="input50">

						 </div>
						 <div class="wreck fl mbr">
							 <span class="flspan">市 </span>
							 <input type="text" id="city" class="input50">

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">区/县</span>
							 <input type="text" id="county" class="input50">

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">乡镇名</span>
							 <input type="text" id="town_name" class="input50">

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">村名</span>
							 <input type="text" id="village_name" class="input50">

						 </div>

					 </td>
				 </tr>
				 <tr class="tabletrj">
					 <td class="tabletrd">经度</td>
					 <td><input type="text" id="longitude"></td>

					 <td class="tabletrd">纬度</td>
					 <td><input type="text" id="latitude"></td>
				 </tr>
				 <tr>
					 <td>起火时间</td>
					 <td colspan="3">
						 <input type="date" id="s_time">
					 </td>

				 </tr>
				 <tr>
					 <td>发现时间</td>
					 <td colspan="3"><input type="date" id="f_time"></td>

				 </tr>
				 <tr>
					 <td>扑灭时间</td>
					 <td colspan="3"><input type="date" id="e_time"></td>
				 </tr>
				 <tr>
					 <td>延续时间</td>
					 <td colspan="3"><input type="date" id="duration"></td>
				 </tr>
				 <tr>
					 <td>起火原因</td>

				 </tr>

				 <tr>
					 <td>火场总面积</td>
					 <td colspan="3"><input type="text" id="firearea"><span class=" ">&nbsp公顷&nbsp&nbsp </span></td>
				 </tr>
				 <tr>
					 <td>受害森林面积</td>
					 <td colspan="3">
						 <div class="wreck fl  ">

							 <input type="text" id="forestarea" class="input100">
							 <span class=" ">公顷&nbsp&nbsp其中:&nbsp&nbsp</span>
						 </div>
						 <div class="wreck fl">
							 <span class="flspan1">新造林地面积&nbsp</span>
							 <input type="text" id="newforestarea" class="input100">
							 <span class=" ">&nbsp公顷&nbsp&nbsp </span>
						 </div>
						 <div class="wreck fl  ">
							 <span class=" flspan">天然林</span>
							 <input type="text" id="nf_area" class="input100">
							 <span class=" ">&nbsp公顷&nbsp&nbsp </span>
						 </div>
						 <div class="wreck fl  ">
							 <span class=" flspan">人工林</span>
							 <input type="text" id="af_proport" class="input100">
							 <span class=" ">&nbsp公顷&nbsp&nbsp </span>
						 </div>


					 </td>
				 </tr>
				 <tr>
					 <td>林种</td>
					 <td colspan="3">
						 <div class="wreck fl">
							 <select name="" id="t_categ" class="input100" style="height:25px;">
								 <option value="1">2</option>
							 </select>

						 </div>
						 <div class="wreck fl">
							 <span class="flspan">龄组</span>

							 <select name="" id="ta_categ" class="input100" style="height:25px;">
								 <option value="1">2</option>
							 </select>
						 </div>
						 <div class="wreck fl  ">
							 <span class=" flspan1">优势树种</span>

							 <select name="" id="d_categ" class="input100 " style="height:25px;">
								 <option value="1">2</option>
							 </select>
						 </div>

					 </td>
				 </tr>
				 <tr>
					 <td>树种组成</td>
					 <td colspan="3">
						 <input type="text" id="treeform">
					 </td>
				 </tr>
				 <tr>
					 <td>人员伤亡</td>
					 <td colspan="3">
						 <div class="wreck fl  ">
							 <span class="flspan">轻伤</span>
							 <input type="text" id="inj_minor" class="input100">
							 <span class=" ">(人)</span>
						 </div>
						 <div class="wreck fl  ">
							 <span class=" flspan">重伤</span>
							 <input type="text" id="inj_severe" class="input100">
							 <span class=" ">(人)</span>
						 </div>
						 <div class="wreck fl  ">
							 <span class=" flspan">死亡</span>
							 <input type="text" id="inj_death" class="input100">
							 <span class=" ">(人)</span>
						 </div>


					 </td>
				 </tr>

				 <tr>
					 <td>林木损失</td>
					 <td colspan="3">
						 <div class="wreck fl  ">

							 <input type="text" id="dd" class="input100">
							 <span class="flspan1">(立方米)&nbsp&nbsp其中:</span>
						 </div>
						 <div class="wreck fl  ">
							 <span class=" flspan">成林蓄积</span>
							 <input type="text" id="iwoodloss" class="input100">
						 </div>
						 <div class="wreck fl  ">
							 <span class=" flspan">幼林株数</span>
							 <input type="text" id="saplloss" class="input100">
						 </div>

					 </td>
				 </tr>
				 <tr>
					 <td>火场指挥员</td>
					 <td colspan="3">
						 <div class="wreck fl  ">
							 <span class="flspan">姓名</span>
							 <input type="text" id="conductor" class="input100">
						 </div>
						 <div class="wreck fl  ">
							 <span class=" flspan">职务</span>
							 <input type="text" id="con_post" class="input100">
						 </div>

					 </td>
				 </tr>


				 <tr>
					 <td>出动扑火人员数</td>
					 <td colspan="3">
						 <div class="wreck fl   ">

							 <input type="text" id="artificialfireout" class="input50">
							 <span class=" ">(人)&nbsp&nbsp其中:&nbsp&nbsp</span>
						 </div>
						 <div class="wreck fl mbr">
							 <span class="flspan">专业队 </span>
							 <input type="text" id="brigade" class="input50">
							 <span class=" ">(人) </span>
						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">森警</span>
							 <input type="text" id="for_police" class="input50">
							 <span class=" ">(人) </span>
						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">军队</span>
							 <input type="text" id="army" class="input50">
							 <span class=" ">(人) </span>
						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">武警</span>
							 <input type="text" id="arm_police" class="input50">
							 <span class=" ">(人) </span>
						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">群众</span>
							 <input type="text" id="masses" class="input50">
							 <span class=" ">(人) </span>
						 </div>


					 </td>
				 </tr>

				 <tr>
					 <td>出动飞机架次</td>
					 <td colspan="3">
						 <div class="wreck fl   ">

							 <input type="text" id="planeoutnumber" class="input50">
							 <span class=" ">架次&nbsp&nbsp</span>
						 </div>
						 <div class="wreck fl mbr">
							 <span class="flspan">机型 </span>
							 <input type="text" id="planetype" class="input50">

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">飞行时间</span>
							 <input type="text" id="flighttime" class="input50">
							 <span class=" ">(时) </span>
						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">飞行费</span>
							 <input type="text" id="flightcost" class="input50">
							 <span class=" ">（万元） </span>
						 </div>

					 </td>
				 </tr>

				 <tr>
					 <td>机降架次</td>
					 <td colspan="3">
						 <div class="wreck fl  ">

							 <input type="text" id="planefall" class="input50">
							 <span class=" ">架次 </span>
						 </div>
						 <div class="wreck fl mbr">
							 <span class="flspan">机降人次 </span>
							 <input type="text" id="fallpeople" class="input50">

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">化灭架次</span>
							 <input type="text" id="p_chemical" class="input50">

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">吊桶次数</span>
							 <input type="text" id="p_bucket" class="input50">

						 </div>

					 </td>
				 </tr>

				 <tr>
					 <td>出动车辆</td>
					 <td colspan="3">
						 <div class="wreck fl  ">

							 <input type="text" id="totavehiclenumber" class="input50">
							 <span class=" ">(辆)&nbsp&nbsp其中:&nbsp&nbsp</span>
						 </div>
						 <div class="wreck fl mbr">
							 <span class="flspan">指挥车 </span>
							 <input type="text" id="commandcar" class="input50">
							 <span class=" ">(辆) </span>

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">运输车</span>
							 <input type="text" id="trans_car" class="input50">
							 <span class=" ">(辆) </span>

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">装甲车</span>
							 <input type="text" id="armoredcar" class="input50">
							 <span class=" ">(辆) </span>

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">其他车辆</span>
							 <input type="text" id="othercar" class="input50">
							 <span class=" ">(辆) </span>

						 </div>
					 </td>
				 </tr>

				 <tr>
					 <td>投入扑火机具数</td>
					 <td colspan="3">
						 <div class="wreck fl  ">

							 <input type="text" id="nono" class="input50">
							 <span class=" ">&nbsp&nbsp其中:&nbsp&nbsp</span>
						 </div>
						 <div class="wreck fl mbr">
							 <span class="flspan1">风力灭火机 </span>
							 <input type="text" id="w_equipm" class="input50">
							 <span class=" ">(台) </span>

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan">水枪</span>
							 <input type="text" id="w_cannons" class="input50">
							 <span class=" ">(支) </span>

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan1">二、三号工具</span>
							 <input type="text" id="n2n3tool" class="input50">
							 <span class=" ">(把) </span>

						 </div>
						 <div class="wreck fl mbr ">
							 <span class=" flspan1">其他工具</span>
							 <input type="text" id="otherequip" class="input50">
							 <span class=" ">(件) </span>

						 </div>
					 </td>
				 </tr>
				 <tr>
					 <td>总损失</td>
					 <td colspan="3">
						 <div class="wreck fl  ">

							 <input type="text" id="totalfightcost" class="input50">
							 <span class=" ">(万元)&nbsp&nbsp其中: &nbsp</span>
						 </div>
						 <div class="wreck fl  ">
							 <span class="flspan1">扑火经费 </span>
							 <input type="text" id="fightcost" class="input50">
							 <span class=" ">(万元) </span>

						 </div>
						 <div class="wreck fl   ">
							 <span class=" flspan1">其他损失折款</span>
							 <input type="text" id="otherloss" class="input50">
							 <span class=" ">(万元) </span>

						 </div>

					 </td>
				 </tr>
				 <%--<tr>--%>
					 <%--<td style="width:25%;"><span>指挥车</span> <input type="text" id="commandcar" style="width:100px;margin-left: 10px;"></td>--%>

					 <%--<td style="width:25%;"><span>运输车</span><input type="text" id="trans_car" style="width:100px;margin-left: 10px;"></td>--%>

					 <%--<td style="width:25%;"><span>装甲车</span><input type="text" id="armoredcar" style="width:100px;margin-left: 10px;"></td>--%>

					 <%--<td style="width:25%;"><span>其它车辆</span><input type="text" id="othercar" style="width:100px;margin-left: 10px;"></td>--%>

				 <%--</tr>--%>

				 <tr>
					 <td>携带电台</td>
					 <td colspan="3">
						 <div class="wreck fl  ">
							 <span class="flspan">短波</span>
							 <input type="text" id="shortwave" class="input100">
							 <span class=" ">(部) </span>
						 </div>
						 <div class="wreck fl  ">
							 <span class=" flspan">超短波</span>
							 <input type="text" id="uswave" class="input100">
							 <span class=" ">(部) </span>
						 </div>


					 </td>
				 </tr>
				 <tr>
					 <td>肇事者</td>
					 <td colspan="3">
						 <div class="wreck fl mbr">
							 <span class="flspan">姓名</span>
							 <input type="text" id="wrecker" class="input100">
						 </div>
						 <div class="wreck fl mbr">
							 <span class=" flspan">年龄</span>
							 <input type="text" id="wreckerage" class="input100">
						 </div>
						 <div class="wreck fl mbr">
							 <span class="flspan">职业</span>
							 <input type="text" id="wreckerpro" class="input100">
						 </div>
						 <div class="wreck fl mbr">
							 <span class="flspan">单位</span>
							 <input type="text" id="wreckerunit" class="input100">
						 </div>


					 </td>
				 </tr>
				 <tr>
					 <td>肇事者处理结果</td>
					 <td colspan="3">

						 <div class="wreck fl  ">
							 <span class="flspan">行政处分</span>
							 <input type="text" id="wapunish" class="input100">
						 </div>
						 <div class="wreck fl  ">
							 <span class="flspan">刑事处罚</span>
							 <input type="text" id="wcpunish" class="input100">
						 </div>

					 </td>
				 </tr>
				 <tr>
					 <td>行政领导处理结果</td>
					 <td colspan="3">

						 <div class="wreck fl  ">
							 <span class="flspan">行政处分</span>
							 <input type="text" id="hapunish" class="input100">
						 </div>
						 <div class="wreck fl  ">
							 <span class="flspan">刑事处罚</span>
							 <input type="text" id="hcpunish" class="input100">
						 </div>

					 </td>
				 </tr>
				 <tr>
					 <td>火场天气情况</td>
					 <td colspan="3">
						 <div class="wreck fl mbr">
							 <span class="flspan">气温</span>
							 <input type="text" id="tempera" class="input100">
							 <span>(度)</span>
						 </div>
						 <div class="wreck fl mbr">
							 <span class=" flspan">湿度</span>
							 <input type="text" id="humidity" class="input100">
							 <span>(%)</span>
						 </div>
						 <div class="wreck fl mbr">
							 <span class="flspan">风向</span>
							 <input type="text" id="winddir" class="input100">
						 </div>
						 <div class="wreck fl mbr">
							 <span class="flspan">风力</span>
							 <input type="text" id="windpower" class="input100">
						 </div>
						 <div class="wreck fl mbr">
							 <span class="flspan">风速</span>
							 <input type="text" id="windspeed" class="input100">
							 <span>（m/s）</span>
						 </div>

						 <div class="wreck fl mbr">
							 <span class="flspan">降雨量</span>
							 <input type="text" id="rainfall" class="input100">
							 <span>(毫米)</span>
						 </div>


					 </td>
				 </tr>
				 <tr>
					 <td>火情简介或说明</td>
					 <td colspan="3">
						 <textarea name="" id="" cols="100" rows="10"></textarea>
					 </td>
				 </tr>
				 <tr>

					 <%--<td colspan="4>--%>
						  <%--<div class="wreck fl mbr">--%>
					 <%--<span class="flspan">建立时间</span>--%>
					 <%--<input type="text" id="rainfall" class="input100">--%>
					 <%--<span>(毫米)</span>--%>
					 <%--</div>--%>
					 <%--</td>--%>
				 </tr>
				 </tbody>

			 </table>
		 </div>


	</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>

<%--<script>--%>

	<%--var $dom = {};--%>

	<%--//模板--%>
	<%--var temp = {--%>
		<%--selected_option_count : '<option value="{code}">{text}</option>'--%>
	<%--};--%>

	<%--$(document).ready(function() {--%>


		<%--$dom.city = $('#city');--%>
		<%--$dom.county = $('#county');--%>


		<%--getPac($dom.city, this_data.pac, 2, function () {--%>

			<%--//表单校验--%>
			<%--validate_form();--%>

		<%--});--%>


	<%--});--%>

	<%--function readyFile(files) {--%>
		<%--files = files.files;--%>
		<%--$('#medit_file_input').val('');--%>
		<%--var fileNames = '';--%>
		<%--for (var i in files){--%>
			<%--if (files.hasOwnProperty(i)){--%>
				<%--if (i == 0){--%>
					<%--fileNames =  files[i].name;--%>
					<%--continue;--%>
				<%--}--%>
				<%--fileNames += "," + files[i].name;--%>
			<%--}--%>
		<%--}--%>
		<%--$('#medit_file_input').val(fileNames)--%>
	<%--}--%>

	<%--function validate_form() {--%>
		<%--$("form#form1").validate({--%>
			<%--rules: {--%>

			<%--},--%>

			<%--messages: {--%>

			<%--},--%>

			<%--// Form Processing via AJAX--%>
			<%--submitHandler: function(form)--%>
			<%--{--%>
				<%--_submit(form);--%>

			<%--}--%>
		<%--});--%>
	<%--}--%>


	<%--function _submit(form){--%>

		<%--var $form = $(form);--%>

		<%--var formData = new FormData();--%>

		<%--formData.append("f", 4211001 );--%>
		<%--formData.append("pac", this_data.pac );--%>
		<%--formData.append("name", $form.find('input[name="name"]').val() );--%>
		<%--formData.append("shape", $form.find('input[name="shape"]').val() );--%>
		<%--formData.append("city", $form.find('input[name="city"]').val() );--%>
		<%--formData.append("county", $form.find('input[name="county"]').val() );--%>
		<%--formData.append("no", $form.find('input[name="no"]').val() );--%>
		<%--formData.append("hotid", $form.find('input[name="hotid"]').val() );--%>
		<%--formData.append("longitude", $form.find('input[name="longitude"]').val() );--%>
		<%--formData.append("latitude", $form.find('input[name="latitude"]').val() );--%>
		<%--formData.append("s_time", $form.find('input[name="s_time"]').val() );--%>
		<%--formData.append("f_time", $form.find('input[name="f_time"]').val() );--%>
		<%--formData.append("e_time", $form.find('input[name="e_time"]').val() );--%>
		<%--formData.append("duration", $form.find('input[name="duration"]').val() );--%>
		<%--formData.append("firetype", $form.find('input[name="firetype"]').val() );--%>
		<%--formData.append("cause", $form.find('input[name="cause"]').val() );--%>
		<%--formData.append("firearea", $form.find('input[name="firearea"]').val() );--%>
		<%--formData.append("nf_area", $form.find('input[name="nf_area"]').val() );--%>
		<%--formData.append("af_proport", $form.find('input[name="af_proport"]').val() );--%>
		<%--formData.append("t_categ", $form.find('input[name="t_categ"]').val() );--%>
		<%--formData.append("ta_categ", $form.find('input[name="ta_categ"]').val() );--%>
		<%--formData.append("d_categ", $form.find('input[name="d_categ"]').val() );--%>
		<%--formData.append("treeform", $form.find('input[name="treeform"]').val() );--%>
		<%--formData.append("woodloss", $form.find('input[name="woodloss"]').val() );--%>
		<%--formData.append("iwoodloss", $form.find('input[name="iwoodloss"]').val() );--%>
		<%--formData.append("saplloss", $form.find('input[name="saplloss"]').val() );--%>
		<%--formData.append("inj_minor", $form.find('input[name="inj_minor"]').val() );--%>
		<%--formData.append("inj_severe", $form.find('input[name="inj_severe"]').val() );--%>
		<%--formData.append("inj_death", $form.find('input[name="inj_death"]').val() );--%>
		<%--formData.append("conductor", $form.find('input[name="conductor"]').val() );--%>
		<%--formData.append("con_post", $form.find('input[name="con_post"]').val() );--%>
		<%--formData.append("brigade", $form.find('input[name="brigade"]').val() );--%>
		<%--formData.append("for_police", $form.find('input[name="for_police"]').val() );--%>
		<%--formData.append("army", $form.find('input[name="army"]').val() );--%>
		<%--formData.append("arm_police", $form.find('input[name="arm_police"]').val() );--%>
		<%--formData.append("masses", $form.find('input[name="masses"]').val() );--%>
		<%--formData.append("plane", $form.find('input[name="plane"]').val() );--%>
		<%--formData.append("planetype", $form.find('input[name="planetype"]').val() );--%>
		<%--formData.append("flighttime", $form.find('input[name="flighttime"]').val() );--%>
		<%--formData.append("flightcost", $form.find('input[name="flightcost"]').val() );--%>
		<%--formData.append("planefall", $form.find('input[name="planefall"]').val() );--%>
		<%--formData.append("fallpeople", $form.find('input[name="fallpeople"]').val() );--%>
		<%--formData.append("p_chemical", $form.find('input[name="p_chemical"]').val() );--%>
		<%--formData.append("p_bucket", $form.find('input[name="p_bucket"]').val() );--%>
		<%--formData.append("commandcar", $form.find('input[name="commandcar"]').val() );--%>
		<%--formData.append("trans_car", $form.find('input[name="trans_car"]').val() );--%>
		<%--formData.append("armoredcar", $form.find('input[name="armoredcar"]').val() );--%>
		<%--formData.append("othercar", $form.find('input[name="othercar"]').val() );--%>
		<%--formData.append("shortwave", $form.find('input[name="shortwave"]').val() );--%>
		<%--formData.append("uswave", $form.find('input[name="uswave"]').val() );--%>
		<%--formData.append("windequip", $form.find('input[name="windequip"]').val() );--%>
		<%--formData.append("watergun", $form.find('input[name="watergun"]').val() );--%>
		<%--formData.append("n2n3tool", $form.find('input[name="n2n3tool"]').val() );--%>
		<%--formData.append("otherequip", $form.find('input[name="otherequip"]').val() );--%>
		<%--formData.append("fightcost", $form.find('input[name="fightcost"]').val() );--%>
		<%--formData.append("otherloss", $form.find('input[name="otherloss"]').val() );--%>
		<%--formData.append("wrecker", $form.find('input[name="wrecker"]').val() );--%>
		<%--formData.append("wreckerage", $form.find('input[name="wreckerage"]').val() );--%>
		<%--formData.append("wreckerpro", $form.find('input[name="wreckerpro"]').val() );--%>
		<%--formData.append("wreckerunit", $form.find('input[name="wreckerunit"]').val() );--%>
		<%--formData.append("wapunish", $form.find('input[name="wapunish"]').val() );--%>
		<%--formData.append("wcpunish", $form.find('input[name="wcpunish"]').val() );--%>
		<%--formData.append("hapunish", $form.find('input[name="hapunish"]').val() );--%>
		<%--formData.append("hcpunish", $form.find('input[name="hcpunish"]').val() );--%>
		<%--formData.append("tempera", $form.find('input[name="tempera"]').val() );--%>
		<%--formData.append("humidity", $form.find('input[name="humidity"]').val() );--%>
		<%--formData.append("winddir", $form.find('input[name="winddir"]').val() );--%>
		<%--formData.append("windpower", $form.find('input[name="windpower"]').val() );--%>
		<%--formData.append("windspeed", $form.find('input[name="windspeed"]').val() );--%>
		<%--formData.append("rainfall", $form.find('input[name="rainfall"]').val() );--%>
		<%--formData.append("description", $form.find('input[name="description"]').val() );--%>
		<%--formData.append("province", $form.find('input[name="province"]').val() );--%>
		<%--formData.append("town_name", $form.find('input[name="town_name"]').val() );--%>
		<%--formData.append("village_name", $form.find('input[name="village_name"]').val() );--%>
		<%--formData.append("elevation", $form.find('input[name="elevation"]').val() );--%>
		<%--formData.append("aspect", $form.find('input[name="aspect"]').val() );--%>
		<%--formData.append("slope_position", $form.find('input[name="slope_position"]').val() );--%>
		<%--formData.append("slope", $form.find('input[name="slope"]').val() );--%>
		<%--formData.append("land_type", $form.find('input[name="land_type"]').val() );--%>
		<%--formData.append("front_command_name", $form.find('input[name="front_command_name"]').val() );--%>
		<%--formData.append("front_command_post", $form.find('input[name="front_command_post"]').val() );--%>
		<%--formData.append("rear_command_name", $form.find('input[name="rear_command_name"]').val() );--%>
		<%--formData.append("rear_command_post", $form.find('input[name="rear_command_post"]').val() );--%>
		<%--formData.append("cadre", $form.find('input[name="cadre"]').val() );--%>
		<%--formData.append("for_cop", $form.find('input[name="for_cop"]').val() );--%>
		<%--formData.append("brigade_people", $form.find('input[name="brigade_people"]').val() );--%>
		<%--formData.append("hbrigade_people", $form.find('input[name="hbrigade_people"]').val() );--%>
		<%--formData.append("hbrigade", $form.find('input[name="hbrigade"]').val() );--%>
		<%--formData.append("communication_car", $form.find('input[name="communication_car"]').val() );--%>
		<%--formData.append("support_car", $form.find('input[name="support_car"]').val() );--%>
		<%--formData.append("hpw_tanker", $form.find('input[name="hpw_tanker"]').val() );--%>
		<%--formData.append("bulldozer", $form.find('input[name="bulldozer"]').val() );--%>
		<%--formData.append("interphone", $form.find('input[name="interphone"]').val() );--%>
		<%--formData.append("other_communication", $form.find('input[name="other_communication"]').val() );--%>
		<%--formData.append("nav_handheld", $form.find('input[name="nav_handheld"]').val() );--%>
		<%--formData.append("nav_vehicle", $form.find('input[name="nav_vehicle"]').val() );--%>
		<%--formData.append("high_pressure_fex", $form.find('input[name="high_pressure_fex"]').val() );--%>
		<%--formData.append("pump", $form.find('input[name="pump"]').val() );--%>
		<%--formData.append("artificial_rain", $form.find('input[name="artificial_rain"]').val() );--%>
		<%--formData.append("fire_bombs", $form.find('input[name="fire_bombs"]').val() );--%>
		<%--formData.append("b_cutter", $form.find('input[name="b_cutter"]').val() );--%>
		<%--formData.append("chainsaw", $form.find('input[name="chainsaw"]').val() );--%>
		<%--formData.append("camera1", $form.find('input[name="camera1"]').val() );--%>
		<%--formData.append("camera2", $form.find('input[name="camera2"]').val() );--%>
		<%--formData.append("caseInfo", $form.find('input[name="caseInfo"]').val() );--%>
		<%--formData.append("wayofaccident", $form.find('input[name="wayofaccident"]').val() );--%>
		<%--formData.append("weather", $form.find('input[name="weather"]').val() );--%>
		<%--formData.append("high_tempera", $form.find('input[name="high_tempera"]').val() );--%>
		<%--formData.append("low_tempera", $form.find('input[name="low_tempera"]').val() );--%>
		<%--formData.append("low_humidity", $form.find('input[name="low_humidity"]').val() );--%>
		<%--formData.append("code", $form.find('input[name="code"]').val() );--%>
		<%--formData.append("forestarea", $form.find('input[name="forestarea"]').val() );--%>
		<%--formData.append("newforestarea", $form.find('input[name="newforestarea"]').val() );--%>
		<%--formData.append("artificialfireout", $form.find('input[name="artificialfireout"]').val() );--%>
		<%--formData.append("carnumber", $form.find('input[name="carnumber"]').val() );--%>
		<%--formData.append("planeoutnumber", $form.find('input[name="planeoutnumber"]').val() );--%>
		<%--formData.append("WingAirPlaneNumber", $form.find('input[name="WingAirPlaneNumber"]').val() );--%>
		<%--formData.append("WingAirPlaneTime", $form.find('input[name="WingAirPlaneTime"]').val() );--%>
		<%--formData.append("HelicopterNumber", $form.find('input[name="HelicopterNumber"]').val() );--%>
		<%--formData.append("HelicopterTime", $form.find('input[name="HelicopterTime"]').val() );--%>
		<%--formData.append("WuRenJiNumber", $form.find('input[name="WuRenJiNumber"]').val() );--%>
		<%--formData.append("WuRenjiTime", $form.find('input[name="WuRenjiTime"]').val() );--%>
		<%--formData.append("IsDealWith", $form.find('input[name="IsDealWith"]').val() );--%>
		<%--formData.append("ProcessedNumber", $form.find('input[name="ProcessedNumber"]').val() );--%>
		<%--formData.append("CriminalNumber", $form.find('input[name="CriminalNumber"]').val() );--%>
		<%--formData.append("APenaltyNumber", $form.find('input[name="APenaltyNumber"]').val() );--%>
		<%--formData.append("ASanctionNumber", $form.find('input[name="ASanctionNumber"]').val() );--%>
		<%--formData.append("RecordDispositionNumber", $form.find('input[name="RecordDispositionNumber"]').val() );--%>
		<%--formData.append("Reporter", $form.find('input[name="Reporter"]').val() );--%>
		<%--formData.append("ReportTime", $form.find('input[name="ReportTime"]').val() );--%>


		<%--//媒体文件--%>
		<%--var files = document.getElementById('mediaFile');--%>
		<%--files = files.files;--%>
		<%--for(var i in files){--%>
			<%--if (files.hasOwnProperty(i)){--%>
				<%--formData.append("file" + i, files[i] );--%>
			<%--}--%>
		<%--}--%>

		<%--$.ajax({--%>
			<%--url: cg_vte.service,--%>
			<%--type: 'post',--%>
			<%--processData: false,  // 不处理数据--%>
			<%--contentType: false,   // 不设置内容类型--%>
			<%--xhr: function () {--%>
				<%--//这里我们先拿到jQuery产生的 XMLHttpRequest对象，为其增加 progress 事件绑定，然后再返回交给ajax使用--%>
				<%--var xhr = $.ajaxSettings.xhr();--%>
				<%--if (onprogress && xhr.upload) {--%>
					<%--xhr.upload.addEventListener("progress", onprogress, false);--%>
					<%--return xhr;--%>
				<%--}--%>
			<%--},--%>
			<%--dataType: 'text',--%>
			<%--data: formData,--%>
			<%--success: function (data) {--%>
				<%--data = JSON.parse(data);--%>
				<%--console.log(data);--%>
				<%--if (data.status == 10000){--%>
					<%--swal({--%>
						<%--title: "提示",--%>
						<%--text: "success",--%>
						<%--type: "success",--%>
						<%--showCancelButton: false,--%>
						<%--closeOnConfirm: false,--%>
						<%--confirmButtonText: "OK",--%>
						<%--confirmButtonColor: "#1AB394"--%>
					<%--}, function() {--%>
						<%--window.parent.addOrUpdCallback();--%>
					<%--});--%>
				<%--} else {--%>
					<%--layer.msg(data.msg);--%>
				<%--}--%>
			<%--}--%>
		<%--})--%>
	<%--}--%>

	<%--//工具-拉取pac数据--%>
	<%--function getPac(item,id,level,callback) {--%>

		<%--$.ajax({--%>
			<%--url: cg_vte.service,--%>
			<%--type: 'post',--%>
			<%--dataType: 'text',--%>
			<%--data: {--%>
				<%--f: 210002,//接口--%>
				<%--pac: id,--%>
				<%--fetchType: 1--%>
			<%--},--%>
			<%--success: function(data){--%>
				<%--data = JSON.parse(data);--%>

				<%--$dom.county.empty();--%>

				<%--var sub = '';--%>
				<%--var rows = data.rows;--%>
				<%--for (var i in rows){--%>
					<%--if(rows.hasOwnProperty(i)){--%>

						<%--sub += temp.selected_option_count.format({--%>
							<%--code: rows[i].code,--%>
							<%--text: rows[i].name--%>
						<%--})--%>
					<%--}--%>
				<%--}--%>
				<%--if (level < 2 ){--%>
					<%--item.append(sub);--%>
				<%--}--%>
				<%--item.html(sub);--%>

				<%--try {--%>
					<%--callback(rows)--%>
				<%--} catch ( e ){--%>

				<%--}--%>
			<%--}--%>
		<%--})--%>
	<%--}--%>

	<%--//当市高等框发生改变--%>
	<%--function changeCity(item) {--%>
		<%--getPac($dom.county,item.value,3);--%>
		<%--this_data.pac = item.value;--%>

	<%--}--%>
	<%--function changeCounty(item) {--%>
		<%--this_data.pac = item.value;--%>
	<%--}--%>

	<%--/**--%>
	 <%--*    侦查附件上传情况    ,这个方法大概0.05-0.1秒执行一次--%>
	 <%--*/--%>
	<%--function onprogress(evt) {--%>
		<%--var loaded = evt.loaded;                  //已经上传大小情况--%>
		<%--var tot = evt.total;                      //附件总大小--%>
		<%--var per = Math.floor(100 * loaded / tot);     //已经上传的百分比--%>

		<%--$("#bar-progress").css("width", per + "%");--%>
		<%--$('#bar-progress-text').text(per + "%")--%>
		<%--console.log(per)--%>
		<%--if (per == 100) {--%>

			<%--console.log("上传成功")--%>
			<%--$("#bar-progress").css("width", '0');--%>
			<%--$('#bar-progress-text').text('')--%>
		<%--}--%>

	<%--}--%>

<%--</script>--%>
</body>
</html>