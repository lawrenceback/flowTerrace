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
	<title>编辑森林消防</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>
		#city,#county{
			width: 45%;
			margin-right: 5px;
		}

		video{
			width: 400px ! important
		}

		/******************************************* 上传进度条 Start ********************************************/
		#bar-progress {
			display: block;
			height: 5px;
			width: 0%;
			position: fixed;
			top: 0;
			left: 0;
			background-color: #1AB394;
			z-index: 99999;
		}

		#bar-progress-text {padding-top: 5px;}
		/******************************************* 上传进度条 End  *********************************************/

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

<div id="bar-progress"><label id="bar-progress-text"></label></div>

<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12 animated fadeInUp">
			<form method="post" class="form-horizontal" name="form1" id="form1">

				<input type="hidden" name="id" id="id">

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="name">名称</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="name" id="name" placeholder="name">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="pac">行政区划代码</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="pac" id="pac" placeholder="pac">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="shape">几何 </label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="shape" id="shape" placeholder="shape">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="address">地址 </label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="address" id="address" placeholder="address">

					</div>
				</div>


				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="phone">值班电话</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="phone" id="phone" placeholder="phone">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="manager">管理员</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="manager" id="manager" placeholder="manager">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="latitude">经度</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="latitude" id="latitude" placeholder="latitude">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="longitude">纬度</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="longitude" id="longitude" placeholder="longitude">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fireengine">fireengine</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fireengine" id="fireengine" placeholder="fireengine">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="t_car">t_car</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="t_car" id="t_car" placeholder="t_car">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="n2n3tool">二号工具</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="n2n3tool" id="n2n3tool" placeholder="n2n3tool">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="w_equipm">风力灭火机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="w_equipm" id="w_equipm" placeholder="w_equipm">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="pump">灭火水泵</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="pump" id="pump" placeholder="pump">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fire_bomb">灭火弹</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fire_bomb" id="fire_bomb" placeholder="fire_bomb">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="wsinjector">水雾喷射器</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="wsinjector" id="wsinjector" placeholder="wsinjector">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="chainsaw">油锯</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="chainsaw" id="chainsaw" placeholder="chainsaw">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="b_cutter">割灌机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="b_cutter" id="b_cutter" placeholder="b_cutter">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="w_cannons">灭火水枪</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="w_cannons" id="w_cannons" placeholder="w_cannons">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="interphone">对讲机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="interphone" id="interphone" placeholder="interphone">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="zj_radio">中继台</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="zj_radio" id="zj_radio" placeholder="zj_radio">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="sc_radio">手持台</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="sc_radio" id="sc_radio" placeholder="sc_radio">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="jd_radio">基地台</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="jd_radio" id="jd_radio" placeholder="jd_radio">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="cz_radio">车载台</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="cz_radio" id="cz_radio" placeholder="cz_radio">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="gps">GPS定位仪</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="gps" id="gps" placeholder="gps">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="machetes">砍刀</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="machetes" id="machetes" placeholder="machetes">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="r_clothing">防火服</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="r_clothing" id="r_clothing" placeholder="r_clothing">

					</div>
				</div>

				<div class="hr-line-dashed"></div>


				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="flashlight">手电筒</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="flashlight" id="flashlight" placeholder="flashlight">

					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="helmet">头盔</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="helmet" id="helmet" placeholder="helmet">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="gloves">手套</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="gloves" id="gloves" placeholder="gloves">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fire_shoes">扑火鞋</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fire_shoes" id="fire_shoes" placeholder="fire_shoes">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="o_equip">其它灭火工具</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="o_equip" id="o_equip" placeholder="o_equip">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="dynamotor">发电机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="dynamotor" id="dynamotor" placeholder="dynamotor">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="anemometer">风速仪器等</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="anemometer" id="anemometer" placeholder="anemometer">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="communication_v">通信车</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="communication_v" id="communication_v" placeholder="communication_v">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="ax">大斧</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="ax" id="ax" placeholder="ax">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="sleeping_bag">睡袋</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="sleeping_bag" id="sleeping_bag" placeholder="sleeping_bag">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="high_pressure_fex">高压细水雾灭火机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="high_pressure_fex" id="high_pressure_fex" placeholder="high_pressure_fex">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fire_shovel">消防铲</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fire_shovel" id="fire_shovel" placeholder="fire_shovel">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="lighter">点火器</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="lighter" id="lighter" placeholder="lighter">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="motorcycle">摩托车</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="motorcycle" id="motorcycle" placeholder="motorcycle">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fire_detectors">余火探测仪</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fire_detectors" id="fire_detectors" placeholder="fire_detectors">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fsf_extinguishers">风水灭火机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fsf_extinguishers" id="fsf_extinguishers" placeholder="fsf_extinguishers">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="base_value">基地产值</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="base_value" id="base_value" placeholder="base_value">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fire_extinguisher">灭火水车</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fire_extinguisher" id="fire_extinguisher" placeholder="fire_extinguisher">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="status">状态</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="status" id="status" placeholder="status">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="tent">帐篷</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="tent" id="tent" placeholder="tent">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="type">类型</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="type" id="type" placeholder="type">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="barracks_area">营房面积</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="barracks_area" id="barracks_area" placeholder="barracks_area">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="base_type">基地类型</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="base_type" id="base_type" placeholder="base_type">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="note">说明</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="note" id="note" placeholder="note">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="nav_handheld">导航手持终端数量</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="nav_handheld" id="nav_handheld" placeholder="nav_handheld">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="nav_vehicle">导航车载终端数量</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="nav_vehicle" id="nav_vehicle" placeholder="nav_vehicle">

					</div>
				</div>
				<div class="hr-line-dashed"></div>
				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label">媒体文件</label>
					<div class="col-xs-8 col-md-4 col-md-4">
						<style>	#mediaFile{opacity: 0;position: absolute;cursor: pointer}#medit_file_input{overflow: hidden; white-space: nowrap; text-overflow: ellipsis;}</style>
						<input type="file" class="form-control" id="mediaFile" accept="audio/*,video/*,image/*,*.mkv" onchange="readyFile(this)" multiple>
						<div class="input-append input-group" style="z-index: -1;">
								<span class="input-group-btn">
									<button class="btn btn-white" type="button">选择文件</button>
								</span>
							<input class="input-large form-control" type="text" id="medit_file_input">
						</div>
						<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 支持的类型有: <code>image/*,video/*,audio/*</code></span>
					</div>
				</div>



				<div id="imgs_box"></div>

				<div class="hr-line-dashed"></div>


				<div class="col-sm-4 col-md-offset-4 col-lg-offset-4 col-xs-offset-4">
					<button class="btn btn-primary" type="submit">提交内容</button>
				</div>


			</form>

		</div>
	</div>
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>

<script>

	var $dom = {};

	//模板
	var temp = {
		selected_option_count : '<option value="{code}">{text}</option>'
	};

	$(document).ready(function() {


		$dom.city = $('#city');
		$dom.county = $('#county');
		$dom.imgs_box = $('#imgs_box');

		setVlue(function () {
			var pac = $('#pac').val();
			initPac( pac)
		})


	});
	
	function setVlue(callback) {

		var data_tr = window.sessionStorage.getItem('data_tr');
		data_tr = JSON.parse(data_tr);

		console.log(data_tr)

		$('#id').val( data_tr.id );
		$('#name').val( data_tr.name );
		$('#pac').val( data_tr.pac );
		$('#shape').val( data_tr.shape );
		$('#address').val( data_tr.address );
		$('#phone').val( data_tr.phone );
		$('#manager').val( data_tr.manager );
		$('#latitude').val( data_tr.latitude );
		$('#longitude').val( data_tr.longitude);
		$('#fireengine').val( data_tr.fireengine);
		$('#t_car').val( data_tr.t_car);
		$('#n2n3tool').val( data_tr.n2n3tool);
		$('#w_equipm').val( data_tr.w_equipm);
		$('#pump').val( data_tr.pump);
		$('#fire_bomb').val( data_tr.fire_bomb);
		$('#wsinjector').val( data_tr.wsinjector);
		$('#chainsaw').val( data_tr.chainsaw);
		$('#b_cutter').val( data_tr.b_cutter);
		$('#w_cannons').val( data_tr.w_cannons);
		$('#interphone').val( data_tr.interphone);
		$('#zj_radio').val( data_tr.zj_radio);
		$('#sc_radio').val( data_tr.sc_radio);
		$('#jd_radio').val( data_tr.jd_radio);
		$('#cz_radio').val( data_tr.cz_radio);
		$('#gps').val( data_tr.gps);
		$('#machetes').val( data_tr.machetes);
		$('#r_clothing').val( data_tr.r_clothing);
		$('#flashlight').val( data_tr.flashlight);
		$('#helmet').val( data_tr.helmet);
		$('#gloves').val( data_tr.gloves);
		$('#fire_shoes').val( data_tr.fire_shoes);
		$('#o_equip').val( data_tr.o_equip);
		$('#dynamotor').val( data_tr.dynamotor);
		$('#anemometer').val( data_tr.anemometer);
		$('#communication_v').val( data_tr.communication_v);
		$('#ax').val( data_tr.ax);
		$('#sleeping_bag').val( data_tr.sleeping_bag);
		$('#high_pressure_fex').val( data_tr.high_pressure_fex);
		$('#fire_shovel').val( data_tr.fire_shovel);
		$('#lighter').val( data_tr.lighter);
		$('#motorcycle').val( data_tr.motorcycle);
		$('#fire_detectors').val( data_tr.fire_detectors);
		$('#fsf_extinguishers').val( data_tr.fsf_extinguishers);
		$('#base_value').val( data_tr.base_value);
		$('#fire_extinguisher').val( data_tr.fire_extinguisher);
		$('#status').val( data_tr.status);
		$('#tent').val( data_tr.tent);
		$('#type').val( data_tr.type);
		$('#barracks_area').val( data_tr.barracks_area);
		$('#base_type').val( data_tr.base_type);
		$('#note').val( data_tr.note);
		$('#nav_handheld').val( data_tr.nav_handheld);
		$('#nav_vehicle').val( data_tr.nav_vehicle);


		var mediaFiles = data_tr.mediaFiles;
		for(var i in mediaFiles){
			if (mediaFiles.hasOwnProperty(i)){
				switch (mediaFiles[i].fileType){
					case 1:
						$dom.imgs_box.append('<img src="${pageContext.request.contextPath}/'+mediaFiles[i].fileUrl+'"><hr>')
						break;
					case 2:
						$dom.imgs_box.append('<video width="320" height="240" controls><source src="${pageContext.request.contextPath}/'+mediaFiles[i].fileUrl+'" type="video/'+mediaFiles[i].fileSuffix+'">您的浏览器不支持Video标签。</video><hr>');
						break;
					case 3:
						$dom.imgs_box.append('<audio width="320" height="240" controls><source src="${pageContext.request.contextPath}/'+mediaFiles[i].fileUrl+'" type="audio/'+mediaFiles[i].fileSuffix+'">您的浏览器不支持Video标签。</audio><hr>');
						break;
				}

			}
		}

		try{
			callback()
		} catch(e){

		}


	}


	function validate_form() {
		$("form#form1").validate({
			rules: {
				pac: {
					required: true
				},
				name: {
					required: true
				},
				address: {
					required: true
				},
				phone: {
					required: true
				},
				manager: {
					required: true
				},
			},

			messages: {
				pac: {
					required: '行政区号代码不得为空.'
				},
				name: {
					required: '名称不得为空.'
				},
				address: {
					required: '地址不得为空.'
				},
				phone: {
					required: '值班电话不得为空.'
				},
				manager: {
					required: '管理员不得为空.'
				},

			},

			// Form Processing via AJAX
			submitHandler: function(form)
			{
				_submit(form);

			}
		});
	}


	function _submit(form){

		var $form = $(form);

		var formData = new FormData();

		formData.append("f", 4202001 );
		formData.append("pac", this_data.pac );
		formData.append("id", $form.find('input[name="id"]').val() );
		formData.append("name", $form.find('input[name="name"]').val() );
		formData.append("shape", $form.find('input[name="shape"]').val() );
		formData.append("address", $form.find('input[name="address"]').val() );
		formData.append("phone", $form.find('input[name="phone"]').val() );
		formData.append("manager", $form.find('input[name="manager"]').val() );
		formData.append("latitude", $form.find('input[name="latitude"]').val() );
		formData.append("longitude", $form.find('input[name="longitude"]').val() );
		formData.append("fireengine", $form.find('input[name="fireengine"]').val() );
		formData.append("t_car", $form.find('input[name="t_car"]').val() );
		formData.append("n2n3tool", $form.find('input[name="n2n3tool"]').val() );
		formData.append("w_equipm", $form.find('input[name="w_equipm"]').val() );
		formData.append("pump", $form.find('input[name="pump"]').val() );
		formData.append("fire_bomb", $form.find('input[name="fire_bomb"]').val() );
		formData.append("wsinjector", $form.find('input[name="wsinjector"]').val() );
		formData.append("chainsaw", $form.find('input[name="chainsaw"]').val() );
		formData.append("b_cutter", $form.find('input[name="b_cutter"]').val() );
		formData.append("w_cannons", $form.find('input[name="w_cannons"]').val() );
		formData.append("interphone", $form.find('input[name="interphone"]').val() );
		formData.append("zj_radio", $form.find('input[name="zj_radio"]').val() );
		formData.append("sc_radio", $form.find('input[name="sc_radio"]').val() );
		formData.append("jd_radio", $form.find('input[name="jd_radio"]').val() );
		formData.append("cz_radio", $form.find('input[name="cz_radio"]').val() );
		formData.append("gps", $form.find('input[name="gps"]').val() );
		formData.append("machetes", $form.find('input[name="machetes"]').val() );
		formData.append("r_clothing", $form.find('input[name="r_clothing"]').val() );
		formData.append("flashlight", $form.find('input[name="flashlight"]').val() );
		formData.append("gloves", $form.find('input[name="gloves"]').val() );
		formData.append("fire_shoes", $form.find('input[name="fire_shoes"]').val() );
		formData.append("o_equip", $form.find('input[name="o_equip"]').val() );
		formData.append("dynamotor", $form.find('input[name="dynamotor"]').val() );
		formData.append("anemometer", $form.find('input[name="anemometer"]').val() );
		formData.append("communication_v", $form.find('input[name="communication_v"]').val() );
		formData.append("ax", $form.find('input[name="ax"]').val() );
		formData.append("sleeping_bag", $form.find('input[name="sleeping_bag"]').val() );
		formData.append("high_pressure_fex", $form.find('input[name="high_pressure_fex"]').val() );
		formData.append("fire_shovel", $form.find('input[name="fire_shovel"]').val() );
		formData.append("lighter", $form.find('input[name="lighter"]').val() );
		formData.append("motorcycle", $form.find('input[name="motorcycle"]').val() );
		formData.append("fire_detectors", $form.find('input[name="fire_detectors"]').val() );
		formData.append("fsf_extinguishers", $form.find('input[name="fsf_extinguishers"]').val() );
		formData.append("base_value", $form.find('input[name="base_value"]').val() );
		formData.append("fire_extinguisher", $form.find('input[name="fire_extinguisher"]').val() );
		formData.append("status", $form.find('input[name="status"]').val() );
		formData.append("tent", $form.find('input[name="tent"]').val() );
		formData.append("type", $form.find('input[name="type"]').val() );
		formData.append("barracks_area", $form.find('input[name="barracks_area"]').val() );
		formData.append("base_type", $form.find('input[name="base_type"]').val() );
		formData.append("note", $form.find('input[name="note"]').val() );
		formData.append("nav_handheld", $form.find('input[name="nav_handheld"]').val() );
		formData.append("nav_vehicle", $form.find('input[name="nav_vehicle"]').val() );



		$.ajax({
			url: cg_vte.service,
			type: 'post',
			processData: false,  // 不处理数据
			contentType: false,   // 不设置内容类型
			xhr: function () {
				//这里我们先拿到jQuery产生的 XMLHttpRequest对象，为其增加 progress 事件绑定，然后再返回交给ajax使用
				var xhr = $.ajaxSettings.xhr();
				if (onprogress && xhr.upload) {
					xhr.upload.addEventListener("progress", onprogress, false);
					return xhr;
				}
			},
			dataType: 'text',
			data: formData,
			success: function (data) {
				data = JSON.parse(data);
				console.log(data);
				if (data.status == 10000){
					swal({
						title: "提示",
						text: "success",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonText: "OK",
						confirmButtonColor: "#1AB394"
					}, function() {
						window.parent.addOrUpdCallback();
					});
				} else {
					layer.msg(data.msg);
				}
			}
		})
	}

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

		validate_form();

	}

	//工具-拉取pac数据
	function getPac(item,id,level,callback) {

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

				$dom.county.empty();

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

	//当市高等框发生改变
	function changeCity(item) {
		getPac($dom.county,item.value,3);
		this_data.pac = item.value;
	}
	//当区/县发生改变
	function changeCounty(item) {
		this_data.pac = item.value;
	}

	/**
	 *    侦查附件上传情况    ,这个方法大概0.05-0.1秒执行一次
	 */
	function onprogress(evt) {
		var loaded = evt.loaded;                  //已经上传大小情况
		var tot = evt.total;                      //附件总大小
		var per = Math.floor(100 * loaded / tot);     //已经上传的百分比

		$("#bar-progress").css("width", per + "%");
		$('#bar-progress-text').text(per + "%")
		console.log(per)
		if (per == 100) {

			console.log("上传成功")
			$("#bar-progress").css("width", '0');
			$('#bar-progress-text').text('')
		}

	}

</script>
</body>
</html>