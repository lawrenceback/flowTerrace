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
	<title>半专业森林消防队</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/datapicker/datepicker3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?555">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-datetimepicker/3.1.4/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?' + new Date().getTime() + '">');</script>

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {};
		var temp = {};
		var this_data = {
			serialNumber: 0,
			totalPages: 1,
			total: 0,
			page: 1,
			rows: 30,
			pac: '${user.pac }',
			accountType: '',
			competence: '',
			name: '',
			sort: '',
			order: 'asc',
			date_sdf: 9,
			type: 0,
			fetchType: 3,
			data_type: 1,
		};
		var ids = [];
	</script>
	<style>
		.iconfont{
			position: relative;
			left: 3px;
		}
		#province, #city, #county{
			width: auto;
		}
	</style>
</head>

<body class="gray-bg">



<div class="wrapper wrapper-content  animated fadeInRight height_per_100">

	<div class="row height_per_100">

		<div class="col-sm-12 height_per_100">

			<div class="ibox ibox-datagrid">

				<div class="ibox-title">
					<h5></h5>

					<div class="ibox-tools-filtrate">
						<select id="province" class="margin-10 form-control"></select>
						<select id="city" onchange="_city(this);" class="form-control margin-10"><option value="450000">全部</option><option value="450100">南宁市</option><option value="450200">柳州市</option><option value="450300">桂林市</option><option value="450400">梧州市</option><option value="450500">北海市</option><option value="450600">防城港市</option><option value="450700">钦州市</option><option value="450800">贵港市</option><option value="450900">玉林市</option><option value="451000">百色市</option><option value="451100">贺州市</option><option value="451200">河池市</option><option value="451300">来宾市</option><option value="451400">崇左市</option><option value="451500">ceshi_市</option><option value="451600">测试市1</option></select>
						<select id="county" onchange="_county(this)" class="form-control margin-10"></select>
					</div>

					<div class="ibox-tools ibox-tools-one">
						<a onclick="add()" ><i class="fa fa-plus"></i> 添加消防队</a>
						<a onclick="mapView(this)" class="fa fa-map-o"> 地图模式</a>
						<a onclick="deletes()"> 批量删除</a>

						<a id="dLabel" class="dropdown-toggle pull-right" data-toggle="dropdown" aria-expanded="true"> <i class="glyphicon glyphicon-list"></i></a>

						<ul class="dropdown-menu bg-warning" aria-labelledby="dLabel" id="cols">
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="row"><i></i>行号</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="name"><i></i>名称</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="pac"><i></i>区号代码</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="shape"><i></i>形状</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="address"><i></i>地址</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="phone"><i></i>值班电话</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="manager"><i></i>管理员</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="latitude"><i></i>经度</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="longitude"><i></i>纬度</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="fireengine"><i></i>fireengine</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="t_car"><i></i>t_car</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="n2n3tool"><i></i>n2n3tool</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="w_equipm"><i></i>w_equipm</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="pump"><i></i>pump</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="fire_bomb"><i></i>灭火弹</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="wsinjector"><i></i>水雾喷射器</label></div></li>

							<li><div class="checkbox i-checks"><label><input type="checkbox" property="chainsaw"><i></i>chainsaw</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="b_cutter"><i></i>割灌机</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="w_cannons"><i></i>灭火水枪</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="chainsaw"><i></i>chainsaw</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="interphone"><i></i>对讲机</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="zj_radio"><i></i>中继台</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="sc_radio"><i></i>手持台</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="jd_radio"><i></i>基地台</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="cz_radio"><i></i>车载台</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="gps"><i></i>GPS定位仪</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="machetes"><i></i>砍刀</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="r_clothing"><i></i>阻燃服、防火服</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="flashlight"><i></i>手电筒</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="helmet"><i></i>头盔</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="gloves"><i></i>手套</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="fire_shoes"><i></i>扑火鞋</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="o_equip"><i></i>其它灭火工具</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="dynamotor"><i></i>发电机</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="anemometer"><i></i>风速仪器等</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="communication_v"><i></i>通信车</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="ax"><i></i>大斧</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="sleeping_bag"><i></i>睡袋</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="high_pressure_fex"><i></i>高压细水雾灭火机</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="fire_shovel"><i></i>消防铲</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="lighter"><i></i>点火器</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="motorcycle"><i></i>摩托车</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="fire_detectors"><i></i>余火探测仪</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="fsf_extinguishers"><i></i> 风水灭火机</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="base_value"><i></i> 基地产值</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="fire_extinguisher"><i></i> 灭火水车</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="status"><i></i> 状态</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="tent"><i></i> 帐篷</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="type"><i></i> 类型</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="barracks_area"><i></i> 营房面积</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="base_type"><i></i> 基地类型</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="note"><i></i> 说明</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="nav_handheld"><i></i> 导航手持终端数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="nav_vehicle"><i></i> 导航车载终端数量</label></div></li>
						</ul>

					</div>
				</div>

				<div class="ibox-content ovelauto">

					<form class="form-inline" style="position: relative">






					</form>
					<hr>

					<div id="tr_datagrid_list" class="map_data_table">
						<div class="datagrid-talbe form-datagrid">
							<table class="table table-bordered table-hover" id="hot_list">
								<thead>
								<tr>
									<th data-property="checkbox" style="width:45px;"><div class="checkbox i-checks checkbox_all"><label><input type="checkbox" class="checkbox_all_input"><i></i></label></div></th>
									<th data-property="row">#</th>
									<th class="sort" data-property="name">name<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="pac">区号代码<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="shape">	几何<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="address">地址<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="phone">值班电话<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="manager"> 管理员<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="latitude">经度	<i class="iconfont icon-paixu"></i>	</th>
									<th class="sort" data-property="longitude">纬度<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="fireengine">fireengine<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="t_car">t_car<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="n2n3tool">二号工具<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="w_equipm">风力灭火机<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="pump">灭火水泵<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="fire_bomb">灭火弹<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="wsinjector">水雾喷射器<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="chainsaw">油锯<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="b_cutter">割灌机<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="w_cannons">灭火水枪<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="interphone">对讲机<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="zj_radio">中继台<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="sc_radio">手持台<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="jd_radio">基地台<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="cz_radio">车载台<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="gps"> GPS定位仪<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="machetes"> 砍刀<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="r_clothing"> 阻燃服、防火服<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="flashlight"> 手电筒<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="helmet"> 头盔<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="gloves"> 手套<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="fire_shoes"> 扑火鞋<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="o_equip"> 其它灭火工具<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="dynamotor"> 发电机<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="anemometer"> 风速仪器等<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="communication_v"> 通信车<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="ax"> 大斧<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="sleeping_bag"> 睡袋<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="high_pressure_fex"> 高压细水雾灭火机<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="fire_shovel"> 消防铲<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="lighter"> 点火器<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="motorcycle"> 摩托车<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="fire_detectors"> 余火探测仪<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="fsf_extinguishers"> 风水灭火机<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="base_value"> 基地产值<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="fire_extinguisher"> 灭火水车<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="status"> 状态<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="tent"> 帐篷<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="type"> 类型<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="barracks_area"> 营房面积<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="base_type"> 基地类型<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="note"> 说明<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="nav_handheld"> 导航手持终端数量<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="nav_vehicle"> 导航车载终端数量<i class="iconfont icon-paixu"></i></th>
									<th class="text-center">操作</th>
								</tr>
								</thead>
							</table>
						</div>

						<div class="paging-footer paging">
							<div class="col-md-12">
								<div class="pull-left" style="min-width: 300px;">
									<span>
										<select class="pagsele" onchange="selectChangeRows(this)">
											<option value="10">10</option>
											<option value="20">20</option>
											<option value="30" selected>30</option>
											<option value="50">50</option>
											<option value="100">100</option>
											<option value="300">300</option>
										</select>
									</span>
									<span><i onclick="page_first()" class="fa fa-step-backward" aria-hidden="true"></i></span>
									<span><i onclick="page_add()" class="fa fa-caret-left"  aria-hidden="true"></i></span>
									<span>当前页 <input type="text" value="1" id="page" onchange="pageChange(this)" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="5" class="pageinput"> </span>
									<span><i onclick="page_next()" class="fa fa-caret-right" aria-hidden="true"></i></span>
									<span><i onclick="page_last()" class="fa fa-step-forward" aria-hidden="true"></i></span>
									<span><i onclick="initDataGrid()" class="fa fa-rotate-left" aria-hidden="true"></i></span>
								</div>

								<span class="pull-right">总页数：<span id="total_pages"></span>， 总记录数：<span id="total"></span></span>
							</div>
						</div>
					</div>
					<div id="tr_datamap" class="height_per_100" style="height: inherit;display: none;">

						<div id="allmap" class="mpm"></div>

					</div>
				</div>


			</div>


		</div>
	</div>
</div>


<div id="temp_hot_list">
	<!-- <tr>


	<td data-property="checkbox" style="width:45px;"><div class="checkbox i-checks"><label><input type="checkbox"   property="row"><i></i></label></div></td>
		<td data-property="row">{row}</td>
		<td>{name}</td>
		<td>{pac}</td>
		<td>{shape}</td>
		<td>{address}</td>
		<td>{phone}</td>
		<td>{manager}</td>
		<td>{latitude}</td>
		<td>{longitude}</td>
		<td>{fireengine}</td>
		<td>{t_car}</td>
		<td>{n2n3tool}</td>
		<td>{w_equipm}</td>
		<td>{pump}</td>
		<td>{fire_bomb}</td>
		<td>{wsinjector}</td>
		<td>{chainsaw}</td>
		<td>{b_cutter}</td>
		<td>{w_cannons}</td>
		<td>{interphone}</td>
		<td>{zj_radio}</td>
		<td>{sc_radio}</td>
		<td>{jd_radio}</td>
		<td>{cz_radio}</td>
		<td>{gps}</td>
		<td>{machetes}</td>
		<td>{r_clothing}</td>
		<td>{flashlight}</td>
		<td>{helmet}</td>
		<td>{gloves}</td>
		<td>{fire_shoes}</td>
		<td>{o_equip}</td>
		<td>{dynamotor}</td>
		<td>{anemometer}</td>
		<td>{communication_v}</td>
		<td>{ax}</td>
		<td>{sleeping_bag}</td>
		<td>{high_pressure_fex}</td>
		<td>{fire_shovel}</td>
		<td>{lighter}</td>
		<td>{motorcycle}</td>
		<td>{fire_detectors}</td>
		<td>{fsf_extinguishers}</td>
		<td>{base_value}</td>
		<td>{fire_extinguisher}</td>
		<td>{status}</td>
		<td>{tent}</td>
		<td>{type}</td>
		<td>{barracks_area}</td>
		<td>{base_type}</td>
		<td>{note}</td>
		<td>{nav_handheld}</td>
		<td>{nav_vehicle}</td>
		<td class="text-center">{opt}</td>
	</tr>-->
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/iCheck/icheck.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/datapicker/bootstrap-datepicker.js"></script>

<script src="${pageContext.request.contextPath}/plug-in/coordtransform/index.js"></script>

<!-- 高德地图插件 Start -->
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<script src="http://webapi.amap.com/maps?v=1.3&key=fa744da6de658cb8b43af45dc91f399f"></script>
<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.10"></script>
<!-- 高德地图插件 End -->

<script>

	$(document).ready(function () {

		temp.temp_hot_list = toComment($('#temp_hot_list').html());
		$dom.hot_list = $('#hot_list');
		$dom.province = $('#province');
		$dom.city = $('#city');
		$dom.county = $('#county');
		$dom.page = $('#page');
		$dom.total = $('#total');
		$dom.total_pages = $('#total_pages');




		$dom.tr_datamap = $('#tr_datamap');
		$dom.tr_datagrid_list = $('#tr_datagrid_list');


		this_data.pac_level = formatPacLevel('${user.pac }');


		peggingPac(function () {
			initPac(this_data.pac);
		})

	});

	function peggingPac(callback) {

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 210006,
				code: this_data.pac
			},
			success: function (data) {
				data = JSON.parse(data);
				if (data.status == 10000){
					var obj = data.obj;
					switch (obj.length){
						case 1:
							$dom.province.html('<option>'+obj[0].name+'</option>').attr('disabled', 'disabled');
							break;
						case 2:
							$dom.province.html('<option>'+obj[0].name+'</option>').attr('disabled', 'disabled')
							$dom.city.html('<option>'+obj[1].name+'</option>').attr('disabled', 'disabled')
							break;
						case 3:
							$dom.province.html('<option>'+obj[0].name+'</option>').attr('disabled', 'disabled')
							$dom.city.html('<option>'+obj[1].name+'</option>').attr('disabled', 'disabled')
							$dom.county.html('<option>'+obj[2].name+'</option>').attr('disabled', 'disabled')
							break;
					}
				}
				try{
					callback()
				} catch (e){

				}

			}
		})

	}


	function _county(dom){
		this_data.pac = dom.value;
		initDataGrid();
	}


	function _city(dom) {
		this_data.pac = dom.value;

		$dom.county.unbind("change");
		initPacCounty(dom.value, function () {
			initDatagrid();

			$dom.county.bind("change", function () {
				initDataGrid();
			})

		});

		initDataGrid();

	}

	//加载城市
	function initPac(id) {

		if (this_data.pac_level > 2){

			this_data.pac = id;

			//节点树加载完成后加载所有该地区的用户
			initDataGrid();

			return;
		}

		$.ajax({
			url: "/if/serviceController/action",
			data: {
				f: 210002,
				pac: id,
				fetchType: 1,
			},
			dataType: 'text',
			success: function (data) {

				data = JSON.parse(data);

				var children = data.rows;

				for (var i in children) {
					if (children.hasOwnProperty(i)) {
						if (i < 1){
							switch (this_data.pac_level){
								case 1:
									$dom.city.append('<option value="' + children[i].code + '">全部</option>');
									break;
								case 2:
									$dom.county.append('<option value="' + children[i].code + '">全部</option>');
									break;
							}
							this_data.pac_name = children[i].name;
							continue;
						}
						switch (this_data.pac_level){
							case 1:
								$dom.city.append('<option value="' + children[i].code + '">' + children[i].name + '</option>')
								break;
							case 2:
								$dom.county.append('<option value="' + children[i].code + '">' + children[i].name + '</option>')
								break
						}

					}
				}

				this_data.pac = id;

				if (this_data.pac_level == 1){
					$dom.county.html('<option>全部</option>')
				}

				//节点树加载完成后加载所有该地区的用户
				initDataGrid();
			}
		})
	}

	//加载区县
	function initPacCounty(id, callback) {

		if ($dom.city.val() == 450000) {
			$dom.county.html('<option>全部</option>');
			try {
				callback();
			} catch (e) {

			}
			return;
		}

		$.ajax({
			url: "/if/serviceController/action",
			data: {
				f: 210002,
				pac: id,
				fetchType: 1,
			},
			dataType: 'text',
			success: function (data) {

				data = JSON.parse(data);

				$dom.county.empty().show();

				var children = data.rows;

				for (var i in children) {
					if (children.hasOwnProperty(i)) {
						if (i == 0) {
							$dom.county.append('<option value="' + children[i].code + '">全部</option>');
							this_data.pac = children[i].code;
							continue;
						}
						$dom.county.append('<option value="' + children[i].code + '">' + children[i].name + '</option>')
					}
				}

				try {
					callback();
				} catch (e) {

				}

			}
		})
	}

	//上一页
	function page_add(){

		if (this_data.page <= 1){
			return;
		}
		this_data.page--;
		initDataGrid();
	}
	//下一页
	function page_next() {
		if (this_data.page >= this_data.totalPages){
			return;
		}
		this_data.page++;
		initDataGrid();
	}
	//最后一页
	function page_last() {
		this_data.page = this_data.totalPages;
		initDataGrid();
	}
	//第一页
	function page_first() {
		this_data.serialNumber = 0;
		this_data.page = 1;
		initDataGrid();
	}
	//手动更新页数
	function pageChange(item) {
		if ( item.value > this_data.totalPages ){
			item.value = this_data.totalPages;
		}
		if ( item.value < 1 ){
			item.value = 1;
		}

		this_data.page = item.value;
		initDataGrid()
	}
	//更新每页显示记录数
	function selectChangeRows(item) {
		this_data.rows = item.value;
		this_data.page = 1;
		initDataGrid();
	}


	function orderDataGrid() {
		$('table thead th.sort').unbind('click').bind('click', function (event) {
			var $this = $(this);
			$this.siblings().find("i.iconfont").attr("class", "iconfont icon-paixu");
			this_data.sort = $this.attr('data-property');

			var $i = $this.find('i');


			if ($i.hasClass("icon-paixu") || $i.hasClass("icon-paixuxia")) {
				this_data.order = 'desc';
				$i.get(0).className = 'iconfont icon-paixushang';

				initDataGrid();
				return;
			}

			if ($i.hasClass("icon-paixushang")) {
				this_data.order = 'asc';
				$i.get(0).className = 'iconfont icon-paixuxia';
			}
			initDataGrid()


		})
	}

	//加载表格数据
	function initDataGrid() {


		var index = layer.load(2, {shade: 0.6});

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 4220001,
				page: this_data.page,
				rows: this_data.rows,
				sort: this_data.sort,
				order: this_data.order,
				date_sdf: this_data.date_sdf,
				pac: this_data.pac,
				fetchType: this_data.fetchType,
				type: this_data.type,
			},
			success: function (data) {
				data = JSON.parse(data);

				layer.close(index);

				this_data.totalPages = Math.ceil(data.total / this_data.rows);
				this_data.total = data.total;
				$dom.total.text(this_data.total)
				$dom.page.val(this_data.page)
				$dom.total_pages.html(this_data.totalPages)
				this_data.serialNumber = (this_data.page-1)*this_data.rows;

				$dom.hot_list.find('tbody').remove();
				data = data.rows;
				this_data.data = data;
				var data_length = data.length;

				this_data.last_rows = data_length;

				var $tbody = $('<tbody>');
				for (var i = 0; i < data_length; i++) {
					if (data.hasOwnProperty(i)) {
						var sub = temp.temp_hot_list.format({
							row: this_data.serialNumber + 1,
							name: data[i].name,
							pac: data[i].pac,
							shape: data[i].shape,
							address: data[i].address,
							phone: data[i].phone,
							manager: data[i].manager,
							latitude: data[i].latitude,
							longitude: data[i].longitude,
							fireengine: data[i].fireengine,
							t_car: data[i].t_car,
							n2n3tool: data[i].n2n3tool,
							w_equipm: data[i].w_equipm,
							pump: data[i].pump,
							fire_bomb: data[i].fire_bomb,
							wsinjector: data[i].wsinjector,
							chainsaw: data[i].chainsaw,
							b_cutter: data[i].b_cutter,
							w_cannons: data[i].w_cannons,
							interphone: data[i].interphone,
							zj_radio: data[i].zj_radio,
							sc_radio: data[i].sc_radio,
							jd_radio: data[i].jd_radio,
							cz_radio: data[i].cz_radio,
							gps: data[i].gps,
							machetes: data[i].machetes,
							r_clothing: data[i].r_clothing,
							flashlight: data[i].flashlight,
							helmet: data[i].helmet,
							gloves: data[i].gloves,
							fire_shoes: data[i].fire_shoes,
							o_equip: data[i].o_equip,
							anemometer: data[i].anemometer,
							communication_v: data[i].communication_v,
							ax: data[i].ax,
							sleeping_bag: data[i].sleeping_bag,
							high_pressure_fex: data[i].high_pressure_fex,
							fire_shovel: data[i].fire_shovel,
							lighter: data[i].lighter,
							motorcycle: data[i].motorcycle,
							fire_detectors: data[i].fire_detectors,
							fsf_extinguishers: data[i].fsf_extinguishers,
							base_value: data[i].base_value,
							fire_extinguisher: data[i].fire_extinguisher,
							status: data[i].status,
							tent: data[i].tent,
							type: data[i].type,
							barracks_area: data[i].barracks_area,
							base_type: data[i].base_type,
							note: data[i].note,
							nav_handheld: data[i].nav_handheld,
							nav_vehicle: data[i].nav_vehicle,
							status: function () {
								switch (data[i].type) {
									case 1: return "优";
									case 2: return "良好";
									case 3: return "一般";
									case 4: return "较差";
									case 5: return "报废";
									default: return "其他";
								}
							},
							opt: function () {
								var btn = '';
								btn += '  <a onclick="edit(this)" class="linkceond" title="修改"><i class="iconfont icon-xiugai"></i></a>  ';
								btn += '   <a onclick="delbrigade(this)" class="linkceond" title="删除"><i class="iconfont icon-ttpodicon"> </i></a>  ';
								return btn.format({id: data[i].id});
							}
						});

						$tbody.append($(sub).data("data_tr", data[i]));
						this_data.serialNumber++;
					}
				}

				$dom.hot_list.append($tbody)

				$(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})

				bindCols()

				forChecks()

				orderDataGrid()

				getPacBoundary(this_data.pac)

				try{
					callback()
				} catch (d){

				}
			}
		})
	}

	//绑定显示列
	function bindCols() {

		$('#cols li input').on('ifChanged', function(event){

			var $this = $(this);
			var property_name = $this.attr('property');
			var index = $dom.hot_list.find('thead th[data-property="'+property_name+'"]').index();
			//ifCreated 事件应该在插件初始化之前绑定
			if ($this.is(':checked')){
				//选中
				$dom.hot_list.find('tbody tr').find( 'td:eq('+index+')').show()
				$dom.hot_list.find('thead th[data-property="'+property_name+'"]').show();
				return;
			}
			$dom.hot_list.find('tbody tr').find('td:eq('+index + ')').hide()
			$dom.hot_list.find('thead th[data-property="'+property_name+'"]').hide();
		});

		$('#cols li input').each(function(){
			var $this = $(this);

			var property_name = $this.attr('property');
			var index = $dom.hot_list.find('thead th[data-property="'+property_name+'"]').index();
			if ($this.is(':checked')){
				//选中
				$dom.hot_list.find('tbody tr').find( 'td:eq('+index+')').show()
				$dom.hot_list.find('thead th[data-property="'+property_name+'"]').show();
				return;
			}
			$dom.hot_list.find('tbody tr').find('td:eq('+index + ')').hide()
			$dom.hot_list.find('thead th[data-property="'+property_name+'"]').hide();
		})
	}


	function forChecksBind() {

		$(".checkbox_all").on('ifChecked ifUnchecked', function(event) {
			var $this = $(this);
			if (event.type == 'ifChecked') {
				$this.iCheck('check');
				$dom.hot_list.find('.i-checks').iCheck('check');
			} else {
				$this.iCheck('uncheck');
				$dom.hot_list.find('.i-checks').iCheck('uncheck');
			}
		});

	}
	forChecksBind()

	var ddsd = '<div class="checkbox i-checks checkbox_all"><label><input type="checkbox" class="checkbox_all_input" property="row"><i></i></label></div>';
	function forChecks() {

		var checkboxes = $("table tbody .i-checks");

		checkboxes.on('ifChecked ifUnchecked', function(event) {
			var data_tr = $(this).parents('tr').data('data_tr');
			var checkbox_all_parent = $('.checkbox_all').parent();
			$('.checkbox_all').remove();
			checkbox_all_parent.append(ddsd);
			$(".checkbox_all").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})
			if ($('tbody input:checkbox').length === $("tbody input:checked").length){
				$(".checkbox_all").iCheck('check');
			}

			switch (event.type){
				case 'ifChecked':
					ids.push(data_tr.id);
					break;
				case 'ifUnchecked':
					ids.remove(data_tr.id);
					break;
			}

			forChecksBind()

		});

	}



	//-------------------------------- 添加 防火林带 Start ------------------------------------//
	function add() {
		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			content: cg_vte.path + 'webpage/base_data/fireachieves/fireachieves_add.jsp',
			fix: false,
			scrollbar: false,
		});
	}
	function edit(item) {
		var tr_data = $(item).parents('tr').data('data_tr');
		//H5本地存储
		console.log(tr_data)
		window.sessionStorage.setItem('data_tr', JSON.stringify(tr_data));

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			content: cg_vte.path + 'webpage/base_data/fireachieves/fireachieves_edit.jsp',
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {layer.close( index );}
		});

	}
	//添加或者修改时成功后的回调
	function addOrUpdCallback() {

		layer.closeAll();
		initDataGrid();
	}
	//-------------------------------- 添加 防火林带 End ------------------------------------//


	function delbrigade(item) {

		var data = $(item).parents('tr').data('data_tr');
		swal({
			title: "提示",
			text: "你确定要删除吗，这一步将无法恢复！\n回车“确定”",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			closeOnConfirm: true

		}, function () {

			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 4203001,
					id: data.id
				},
				success: function (data) {
					data = JSON.parse(data);
					callbackDel(data.status, data.msg, item);
				}
			})


		});


	}

	//批量删除
	function deletes() {
		if (ids.length<1){
			layer.msg('请先选择数据');
			return;
		}
		var params = {}

		for (var i in ids){
			if (ids.hasOwnProperty(i)){
				params['brigades["'+i+'"].id'] = ids[i];
			}
		}

		$.ajax({
			url: cg_vte.path + 'if/hBrigadeController/deleteBrigades',
			type: 'post',
			dataType: 'text',
			data: params,
			success: function (data) {
				data = JSON.parse(data);
				if (data.status == 10000){
					initDataGrid();
					layer.msg("删除成功");
				} else{
					layer.msg(data.msg);
				}
			}
		})
	}

	function callbackDel(state, msg, item) {

		switch (state) {
			case 10000:
				setTimeout(function () {

					swal({
						title: "提示",
						text: msg,
						type: "success",
						showCancelButton: false,
						confirmButtonText: "确定",
						closeOnConfirm: true
					}, function () {
						initDataGrid()
					});

				}, 300);
				break;
			default:
				layer.msg(msg);
				break;
		}
	}


	function mapView(item) {
		//地图模式
		if (this_data.data_type == 1){
			$dom.tr_datagrid_list.hide();
			$dom.tr_datamap.show();
			this_data.data_type = 2;
			$(item).html(' 列表模式');
			return ;
		}
		$dom.tr_datagrid_list.show();
		$dom.tr_datamap.hide();
		this_data.data_type = 1;
		//location.href = cg_vte.path + 'webpage/base_data/fire_hot/fire_hot_map_view.jsp';
		$(item).html(' 地图模式');
	}

	//获得行政区地图边界
	function getPacBoundary(pac) {
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 210005,
				code: pac,
				format_boundary_type: 1
			},
			success: function (data) {
				data = JSON.parse(data);
				if (data.status == 10000){
					var area_boundary = data.obj;

					if (area_boundary.boundary){
						var boundary = JSON.parse(area_boundary.boundary);
						if (boundary.type == 'Polygon'){

							drawBoundary(boundary.coordinates);

						}
					}
				}
			}
		})
	}
	//绘制地图边界
	function drawBoundary(coordinates) {
		var tileLayer = new AMap.TileLayer();
		var roadNet = new AMap.TileLayer.RoadNet();
		var googleLayer = new AMap.TileLayer({
			getTileUrl: 'http://mt{1,2,3,0}.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&x=[x]&y=[y]&z=[z]&s=Galile',
			zIndex: 8,
		});

		var map = new AMap.Map('allmap', {
			zIndex: 5,
			resizeEnable: true,
			zoom : this_data.map_zoom,
			layers: [tileLayer,googleLayer,roadNet]
		});

		roadNet.hide();
		googleLayer.setMap(map);
		this_data.map = map;


		AMap.event.addListener(map,'zoomend',function(){
			this_data.map_zoom = map.getZoom();
			if (map.getZoom() >= 9){
				$('u').css({visibility: 'inherit'})
			} else {
				$('u').css({visibility: 'hidden'})
			}
		});


		var polygonOptions = {
			map: this_data.map,
			strokeColor: '#97EC71',
			strokeWeight: 2,
			fillColor: '',
			fillOpacity: 0.7
		};

		var polygon = new AMap.Polygon(polygonOptions);
		polygon.setPath(coordinates);
		this_data.map.setFitView();

		initHots();
	}

	function initHots(){

		var map = this_data.map;
		var rows = this_data.data;

		if (this_data.markers){
			for(var i in this_data.markers){
				map.remove(this_data.markers[i]);
			}
		}


		var markers = [];

		for(var i in rows){
			if (rows.hasOwnProperty(i)){

				var marker = new AMap.Marker({
					map: map,
					zIndex: 9999999,
					offset: new AMap.Pixel(-15, -15),
					content: '<i index="'+i+'" class="iconfont icon-redian7"><span class="none">' + JSON.stringify(rows[i])+'</span><u>' + rows[i].name + '</u></i> ',
					position: coordtransform.gcj02towgs84(rows[i].longitude, rows[i].latitude)
				});
				markers.push(marker);

				//marker 点击时打开
				AMap.event.addListener(marker, 'click', function(event) {

					var data = JSON.parse( $(event.target.Qi.content).find('span').html() );
					console.log(data)
					this_data.this_map_data = data;
					//构建信息窗体中显示的内容
					var info = [];
					info.push("<div >");
					info.push("<h5 class=\"firetitle\">热点信息</h5>");
					info.push("<div class=\"firebox\">");
					info.push("名称 : {name}");
					info.push("<br>");
					info.push("经度 : {latitude}");
					info.push("<br>");
					info.push("纬度 : {longitude}");
					info.push("<br>");
					info.push("消防车 : {t_car}");
					info.push("<br>");
					info.push("二号工具： {n2n3tool}");
					info.push("<br>");
					info.push("风力灭火机： {w_equipm}");
					info.push("<br>");
					info.push("行政区划代码： {pac}");
					info.push("</div>");
					info.push("<a onclick='mapDatail()' class=\"firefor\">详情</a>")
					info.push("</div>");
					var content = info.join("").format({
						name: data.name,
						latitude: data.latitude,
						longitude: data.longitude,
						t_car: data.t_car,
						n2n3tool: data.n2n3tool,
						w_equipm: data.w_equipm,
						pac: data.pac,
//                        status: function(){
//                            switch (data.status){
//                                case 0: return '未反馈';
//                                case 1: return '已反馈';
//                                default: return 0;
//                            }
//                        }
					})
					var infoWindow = new AMap.InfoWindow({
						offset: new AMap.Pixel(10, -5),
						content: content
					});

					infoWindow.open(map, coordtransform.gcj02towgs84( data.longitude, data.latitude ));
				});

			}
		}

		this_data.markers = markers;

		map.setZoomAndCenter(6, coordtransform.gcj02towgs84( rows[0].longitude, rows[0].latitude ));
	}

	function mapDatail() {

		console.log(this_data.this_map_data);
		var data = this_data.this_map_data;

		var content = $('#this_map_data').html().trim();

		content = content.format({
			name: data.name || '',
			pac: data.pac || '',
			shape: data.shape || '',
			address: data.address || '',
			phone: data.phone || '',
			manager: data.manager || '',
			latitude: data.latitude || '',
			longitude: data.longitude || '',
			fireengine: data.fireengine || '',
			t_car: data.t_car || '',
			n2n3tool: data.n2n3tool || '',
			w_equipm: data.w_equipm || '',
			pump: data.pump || '',
			fire_bomb: data.fire_bomb || '',
			wsinjector: data.wsinjector || '',
			chainsaw: data.chainsaw || '',
			b_cutter: data.b_cutter || '',
			w_cannons: data.w_cannons || '',
			interphone: data.interphone || '',
			zj_radio: data.zj_radio || '',
			sc_radio: data.sc_radio || '',
			jd_radio: data.jd_radio || '',
			cz_radio: data.cz_radio || '',
			gps: data.gps || '',
			machetes: data.machetes || '',
			r_clothing: data.r_clothing || '',
			flashlight: data.flashlight || '',
			helmet: data.helmet || '',
			gloves: data.gloves || '',
			fire_shoes: data.fire_shoes || '',
			o_equip: data.o_equip || '',
			dynamotor: data.dynamotor || '',
			anemometer: data.anemometer || '',
			communication_v: data.communication_v || '',
			ax: data.ax || '',
			sleeping_bag: data.sleeping_bag || '',
			high_pressure_fex: data.high_pressure_fex || '',
			fire_shovel: data.fire_shovel || '',
			lighter: data.lighter || '',
			motorcycle: data.motorcycle || '',
			fire_detectors: data.fire_detectors || '',
			fsf_extinguishers: data.fsf_extinguishers || '',
			base_value: data.base_value || '',
			fire_extinguisher: data.fire_extinguisher || '',
			status: data.status || '',
			tent: data.tent || '',
			type: data.type || '',
			barracks_area: data.barracks_area || '',
			base_type: data.base_type || '',
			note: data.note || '',
			nav_handheld: data.nav_handheld || '',
			nav_vehicle: data.nav_vehicle || '',


		});
		var mediaFiles = data.mediaFiles;
		console.log(mediaFiles)
		if (mediaFiles && mediaFiles.length > 0){
			for( var i in mediaFiles){
				if (mediaFiles.hasOwnProperty(i)){

				}
			}
		} else {
			$('#this_map_data .media_file').html('无媒体文件');
		}
		layer.open({
			type: 1,
			title: ' 热点详情',
			closeBtn: 1,
			maxmin: false, //开启最大化最小化按钮
			area: ['80%', '500px'],
			//offset: 'r',
			content: content,
			btn: ['关闭'],
			fix: false,
			shadeClose: true,
			scrollbar: false,
			yes: function (index, layero) {layer.close( index );}
		});

	}



</script>

</body>
</html>


<div class="none">
	<style>

		.this_map_data td:nth-child(1),.this_map_data td:nth-child(3){
			background-color: #f7f7f7;
		}
	</style>
	<div id="this_map_data">
		<div class="ibox">
			<div class="ibox-content">
				<table class="table table-bordered this_map_data">
					<tr>
						<td style="width:140px;"> 名称</td>
						<td style="padding-left:20px;">{name}</td>
						<td style="width:140px;">行政区划代码</td>
						<td style="padding-left:20px;">{pac}</td>

					</tr>
					<tr>
						<td>几何</td>
						<td>{shape}</td>
						<td>地址</td>
						<td>{address}</td>
					</tr>
					<tr>
						<td>值班电话</td>
						<td>{phone}</td>
						<td>管理员</td>
						<td>{manager}</td>

					</tr>
					<tr>
						<td>经度</td>
						<td>{latitude}</td>
						<td>纬度</td>
						<td>{longitude}</td>
					</tr>
					<tr>
						<td>消防车</td>
						<td>{fireengine}</td>
						<td>消防车</td>
						<td>{t_car}</td>

					</tr>
					<tr>
						<td>二号工具</td>
						<td>{n2n3tool}</td>
						<td>风力灭火机</td>
						<td>{w_equipm}</td>
					</tr>
					<tr>
						<td>灭火水泵</td>
						<td>{pump}</td>

						<td>灭火弹</td>
						<td>{fire_bomb}</td>

					</tr>
					<tr>
						<td>水雾喷射器</td>
						<td>{wsinjector}</td>
						<td>油锯</td>
						<td>{chainsaw}</td>
					</tr>
					<tr>
						<td>割灌机</td>
						<td>{b_cutter}</td>
						<td>灭火水枪</td>
						<td>{w_cannons}</td>


					</tr>
					<tr>
						<td>对讲机</td>
						<td  >{interphone}</td>
						<td  >中继台</td>
						<td  >{zj_radio}</td>
					</tr>
					<tr>
						<td>手持台</td>
						<td  >{sc_radio}</td>
						<td  >基地台</td>
						<td  >{jd_radio}</td>
					</tr>
					<tr>
						<td>车载台</td>
						<td  >{cz_radio}</td>
						<td  >GPS定位仪</td>
						<td  >{gps}</td>
					</tr>
					<tr>
						<td>砍刀</td>
						<td  >{machetes}</td>
						<td  >阻燃服</td>
						<td  >{r_clothing}</td>
					</tr>
					<tr>
						<td>手电筒</td>
						<td  >{flashlight}</td>
						<td  >头盔</td>
						<td  >{helmet}</td>
					</tr>
					<tr>
						<td> 手套</td>
						<td  >{gloves}</td>
						<td  >扑火鞋</td>
						<td  >{fire_shoes}</td>
					</tr>
					<tr>
						<td> 其它灭火工具</td>
						<td  >{o_equip}</td>
						<td  >发电机</td>
						<td  >{dynamotor}</td>
					</tr>
					<tr>
						<td> 风速仪器</td>
						<td  >{anemometer}</td>
						<td  >通信车</td>
						<td  >{communication_v}</td>
					</tr>
					<tr>
						<td> 大斧</td>
						<td  >{ax}</td>
						<td  >睡袋</td>
						<td  >{sleeping_bag}</td>
					</tr>
					<tr>
						<td> 高压细水雾灭火机</td>
						<td  >{high_pressure_fex}</td>
						<td  >消防铲</td>
						<td  >{fire_shovel}</td>
					</tr>

					<tr>
						<td> 点火器</td>
						<td  >{lighter}</td>
						<td  >摩托车</td>
						<td  >{motorcycle}</td>
					</tr>
					<tr>
						<td> 余火探测仪</td>
						<td  >{fire_detectors}</td>
						<td  >风水灭火机</td>
						<td  >{fsf_extinguishers}</td>
					</tr>
					<tr>
						<td> 基地产值</td>
						<td  >{base_value}</td>
						<td  >灭火水车</td>
						<td  >{fire_extinguisher}</td>
					</tr>
					<tr>
						<td> 状态</td>
						<td  >{status}</td>
						<td  >	帐篷</td>
						<td  >{tent}</td>
					</tr>
					<tr>
						<td> 类型</td>
						<td  >{type}</td>
						<td  >	营房面积</td>
						<td  >{barracks_area}</td>
					</tr>
					<tr>
						<td> 基地类型</td>
						<td  >{base_type}</td>
						<td  >	说明</td>
						<td  >{note}</td>
					</tr>
					<tr>
						<td> 导航手持终端数量</td>
						<td  >{nav_handheld	}</td>
						<td  >	导航车载终端数量</td>
						<td  >{note}</td>
					</tr>

				</table>
				<hr>

				<h5>媒体文件</h5>
				<div class="media_file">

				</div>
			</div>
		</div>

	</div>
</div>