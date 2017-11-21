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
	<title>火灾热点管理</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-datetimepicker/3.1.4/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/datapicker/datepicker3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?dasttrxs">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">




	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>

	<link href="${pageContext.request.contextPath}/plug-in/demo.css" rel="stylesheet">

	<link href="${pageContext.request.contextPath}/plug-in/iconfont.css?35553" rel="stylesheet">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css">
	<script src="${pageContext.request.contextPath}/plug-in/iconfont.js"> </script>


	<!-- 高德地图插件 Start -->
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	<script src="http://webapi.amap.com/maps?v=1.3&key=fa744da6de658cb8b43af45dc91f399f"></script>
	<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.10"></script>
	<!-- 高德地图插件 End -->

	<style>
		#hot_list .iconfont{
			font-size: 12px;
		}
		#province, #city, #county{
			width: auto;
		}

		.ibox.ibox-datagrid .datagrid-talbe{
			height: calc(100% - 120px);
		}
		.ibox.ibox-datagrid .datagrid-talbe .table-bordered tbody {
			height: calc(100% - 37px);
		}
		.iconfont.icon-redian7{
			font-size: 26px;
			color: #FF5A5A;
		}
		.none{
			display: none;
		}

		.iconfont{
			font-size:16px!important;
			position: relative;
			left:2px;
		}
		u{
			color: #fff;
			display: inline;
			position: absolute;
			top: -1px;
			font-size: 12px;
			text-decoration: none;
			cursor: pointer;
			min-width: 30px;
		}

		a.firefor{
			width: 200px;
			/* height: 37px; */
			background: #1ab394;
			color: #fff;
			display: block;
			border-radius: 22px;
			line-height: 37px;
			text-align: center;
			font-size: 15px;
			font-family: sans-serif;
			margin: 0 auto;
			margin: 15px auto;
			margin-top: 0;
		}

		.firetitle{

			background: #f6f4f2;
			line-height: 35px;
			text-align: center;
			margin: 0;
			font-size: 14px;
			position: relative;
			top: -5px;

		}
		.amap-info-outer, .amap-menu-outer{
			padding:0px;

			border-radius: 9px;
		}
		.firebox{
			padding: 15px 34px;
			line-height: 26px;
			font-size: 13px;
			color: #6a6a6a;

		}
		.amap-info-close {
			position: absolute;
			right: 5px;
			top: 12px;
			color: #807d7d;
			text-decoration: none;
			font: 700 19px/14px Tahoma,Verdana,sans-serif;
			width: 16px;
			height: 16px;
		}
	</style>
	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?' + new Date().getTime() + '">');</script>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {};
		var temp = {};
		var this_data = {
			last_rows: 0,
			totalPages: 1,
			total: 0,
			page: 1,
			rows: 30,
			serialNumber: 0,


			pac: '${user.pac }',
			pac_level: 0,
			accountType: '',
			competence: '',
			name: '',
			sort: 'receiptt',
			order: 'desc',
			date_sdf: 3,
			type: '',
			reporttime_start: new Date(new Date().setMonth(new Date().getMonth() - 1)).Format('yyyy-MM-dd'),
			reporttime_end: new Date().Format('yyyy-MM-dd'),
			fetchType: 3,
			data_type: 1,

			map_zoom: 16,
		};
	</script>

</head>


<body class="gray-bg">


<div class="wrapper wrapper-content animated fadeInRight height_per_100">


	<div class="row height_per_100">

		<div class="col-sm-12 height_per_100">


			<div class="ibox ibox-datagrid">


				<div class="ibox-title">

					<div class="ibox-tools-filtrate">
						<select id="province" class="margin-10 form-control"></select>
						<select id="city" onchange="_city(this);" class="form-control margin-10"></select>
						<select id="county" onchange="_county(this)" class="form-control margin-10"></select>
					</div>

					<div class="ibox-tools ibox-tools-one">
						<%--<a onclick="add()" class=" "><i class="fa fa-plus"></i> 添加信息</a>--%>
						<a onclick="excelExport()" class=" "><i class="fa fa-file-excel-o"></i> 导出</a>
						<a onclick="mapView(this)" class="fa fa-map-o"> 地图模式</a>

						<a id="dLabel" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"> <i class="glyphicon glyphicon-list"></i></a>

						<ul class="dropdown-menu bg-warning" aria-labelledby="dLabel" id="cols">
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="row"><i></i>行号</label></div></li>

							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="no"><i></i>编号</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="satellite"><i></i>接收卫星</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="longitude"><i></i>经度</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="latitude"><i></i>纬度</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="pixels"><i></i>像素数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="smoke"><i></i>有烟否</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="continuous"><i></i>是否连续林火事件</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox"   property="landtype"><i></i>地类</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="receiptt"><i></i>接受时间</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox"   checked property="type"><i></i>火灾类型</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="reporter"><i></i>报告人</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="reporttime"><i></i>报告时间</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="opinion"><i></i>处理意见</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox"  checked property="duty"><i></i>值班人</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="note"><i></i>备注</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="source"><i></i>source</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="shape"><i></i>形状</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="county"><i></i>区/县</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked  property="status"><i></i>状态</label></div></li>
						</ul>

					</div>
				</div>


				<div class="ibox-content">

					<form class="form-inline" style="position: relative">


						<div class="form-group margin-10" id="data_5">
							<label class="  control-label font-noraml margin-10">报告时间</label>

							<div class="input-daterange input-group" id="datepicker">
								<input type="text" class=" form-control" onchange="this_data.reporttime_start = this.value; initDataGrid();" id="start_time" style="width: 120px;">
								<span class="input-group-addon">到</span>
								<input type="text" class="form-control" onchange="this_data.reporttime_end = this.value; initDataGrid();" id="end_time" style="width: 120px;">
							</div>

						</div>

						<div class="form-group margin-10">
							<label for="type_" class=" control-label font-noraml margin-10">火灾类型 </label>

							<select class="form-control" id="type_" onchange="this_data.type = this.value;initDataGrid()">
								<option value="">全部</option>
								<option value="5">火灾</option>
								<option value="1">炼山</option>
								<option value="2">农用火</option>
								<option value="3">境外火</option>
								<option value="4">其他</option>
							</select>

						</div>



					</form>

					<hr>
					<div id="tr_datagrid_list" class="height_per_100" style="height: inherit;">

						<div class="datagrid-talbe form-datagrid" style="height: calc(100% - 45px);">
							<table class="table table-bordered table-hover" id="hot_list">
								<thead>
								<tr>
									<%--<th data-property="checkbox" style="width:45px;"><div class="checkbox i-checks checkbox_all"><label><input type="checkbox" class="checkbox_all_input"><i></i></label></div></th>--%>
									<th data-property="row">#</th>

									<th class="sort" data-property="no">编号<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="satellite">接收卫星<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="longitude">径度<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="latitude">纬度<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="pixels">像素数<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="smoke">有烟否<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="continuous">是否连续林火事件<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="landtype">地类<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="receiptt">接受时间<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="type">火灾类型<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="reporter">报告人<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="reporttime">报告时间<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="opinion">处理意见<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="duty">值班人<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="note">备注<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="source">source<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="shape">形状<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="county">区/县<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="status">状态<i class="iconfont icon-paixu"></i></th>
									<th class="text-center">操作</th>
								</tr>
								</thead>

							</table>
						</div>
						<div class="paging-footer paging" style="overflow:hidden;">
							<div class="fl" style="float: left;width:80%">
								<spaan>
									<select class="pagsele" onchange="selectChangeRows(this)">
										<option value="10">10</option>
										<option value="20">20</option>
										<option value="30" selected>30</option>
										<option value="50">50</option>
										<option value="100">100</option>
										<option value="300">300</option>
									</select>
								</spaan>
								<span><i onclick="page_first()" class="fa fa-step-backward" aria-hidden="true"></i></span>
								<span><i onclick="page_add()" class="fa fa-caret-left"  aria-hidden="true"></i></span>
								<span>当前页 <input type="text" value="1" id="page" onchange="pageChange(this)" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="5" class="pageinput"> </span>
								<span><i onclick="page_next()" class="fa fa-caret-right" aria-hidden="true"></i></span>
								<span><i onclick="page_last()" class="fa fa-step-forward" aria-hidden="true"></i></span>
								<span><i onclick="initDataGrid()" class="fa fa-rotate-left" aria-hidden="true"></i></span>
							</div>

							<span class="fr" style="float: right;">总页籹：<span id="total_pages"></span>， 总记录数：<span id="total"></span></span>
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
		<%--<td data-property="checkbox" style="width:45px;"><div class="checkbox i-checks"><label><input type="checkbox"   property="row"><i></i></label></div></td>--%>
		<td data-property="row">{row}</td>

		<td>{no}</td>
		<td>{satellite}</td>
		<td>{longitude}</td>
		<td>{latitude}</td>
		<td>{pixels}</td>
		<td>{smoke}</td>
		<td>{continuous}</td>
		<td>{landtype}</td>
		<td>{receiptt}</td>
		<td>{type}</td>
		<td>{reporter}</td>
		<td>{reporttime}</td>
		<td>{opinion}</td>
		<td>{duty}</td>
		<td>{note}</td>
		<td>{source}</td>
		<td>{shape}</td>
		<td>{county}</td>
		<td>{status}</td>
		<td class="text-center">{opt}</td>
	</tr>-->
</div>




<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js?Ddsdss"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/iCheck/icheck.min.js"></script>

<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/coordtransform/index.js"></script>


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

		$('#start_time').val( new Date(new Date().setMonth(new Date().getMonth() - 1)).Format('yyyy-MM-dd') )
		$('#end_time').val( new Date().Format('yyyy-MM-dd') )

		$('#data_5 .input-daterange').datepicker({
			keyboardNavigation: false,
			forceParse: false,
			autoclose: true
		});


		//判断用户pac等级
		this_data.pac_level = formatPacLevel('${user.pac }');



		peggingPac(function(){
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
	function initPac(id,callback) {
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
					$dom.county.html('<option>全部</option>');
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
				console.log(data)

				$dom.county.empty();

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

	//上一页
	function page_add(){

		if (this_data.page <= 1){
			return;
		}
		this_data.page--;
		this_data.serialNumber = (this_data.page-1)*this_data.rows;
		initDataGrid();
	}
	//下一页
	function page_next() {
		if (this_data.page >= this_data.totalPages){
			return;
		}
		this_data.page++;
		this_data.serialNumber = (this_data.page-1)*this_data.rows;
		initDataGrid();
	}
	//最后一页
	function page_last() {

		this_data.serialNumber = (this_data.totalPages-1) * this_data.rows;
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
		this_data.serialNumber = (this_data.page-1)*this_data.rows;
		initDataGrid()
	}
	//更新每页显示记录数
	function selectChangeRows(item) {
		this_data.rows = item.value;
		this_data.serialNumber = 0;
		this_data.page = 1;
		initDataGrid();
	}

	function initDataGrid() {
		var index = layer.load(2, {	shade: 0.6});

		var data = {
			f: 4110001,
			page: this_data.page,
			rows: this_data.rows,
			sort: this_data.sort,
			order: this_data.order,
			date_sdf: this_data.date_sdf,
			pac: this_data.pac,
			fetchType: this_data.fetchType,
			reporttime_start: this_data.reporttime_start,
			reporttime_end: this_data.reporttime_end,
		}
		if (this_data.type && this_data.type.length > 0){
			data.type = this_data.type
		}
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: data,
			success: function (data) {
				data = JSON.parse(data);


				layer.close(index);

				this_data.totalPages = Math.ceil(data.total / this_data.rows);
				this_data.total = data.total;
				$dom.total.text(this_data.total)
				$dom.page.val(this_data.page)
				$dom.total_pages.html(this_data.totalPages)

				$dom.hot_list.find('tbody').remove();
				data = data.rows;
				this_data.data = data;

				this_data.last_rows = data.length;

				var data_length = data.length;
				var $tbody = $('<tbody>');
				for (var i = 0; i < data_length; i++) {
					if (data.hasOwnProperty(i)) {
						var sub = temp.temp_hot_list.format({
							row: this_data.serialNumber + 1,
							pac: data[i].pac || '',
							no: data[i].no || '',
							satellite: data[i].satellite || '',
							longitude: data[i].longitude || '',
							latitude: data[i].latitude || '',
							pixels: data[i].pixels || '',
							smoke: data[i].smoke || '',
							continuous: data[i].continuous || '',
							landtype: data[i].landtype || '',
							receiptt: data[i].receiptt || '',
							reporter: data[i].reporter || '',
							reporttime: new Date(data[i].reporttime).Format('yyyy-MM-dd') || '',
							opinion: data[i].opinion || '',
							duty: data[i].duty || '',
							note: data[i].note || '',
							source: data[i].source || '',
							shape: data[i].shape || '',
							county: data[i].county || '',
							type: function () {
								switch (data[i].type) {
									case 5:
										return "火灾";
									case 1:
										return "炼山";
									case 2:
										return "农用火";
									case 3:
										return "境外火_";
									case 4:
										return "农用火";
									default:
										return "其他";
								}
							},
							status: function () {
								switch (data[i].status) {
									case 1:
										return "已反馈";
									default:
										return "未反馈";
								}
							},
							opt: function () {
								var btn = '';
								btn += '  <a onclick="see(this)" title="查看" class="linkceond"><i class="iconfont icon-chakan"></i></a>  ';
								btn += '  <a onclick="edit(this)" title="修改" class="linkceond"><i class="iconfont icon-xiugai"></i></a>  ';
								btn += '  <a onclick="fankui(this)" title="反馈" class="linkceond""><i class="iconfont icon-fankui3"></i></a>  ';
//								btn += '   <a onclick="delHot(this)" title="删除" class="linkceond"><i class="iconfont icon-ttpodicon"> </i></a>  ';
								return btn.format({id: data[i].id});
							}
						})

						$tbody.append($(sub).data("data_tr", data[i]));

						this_data.serialNumber++;
					}
				}

				$dom.hot_list.append($tbody)

				$(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})

				forChecks()

				bindCols()

				orderDataGrid()

				getPacBoundary(this_data.pac)

				try{
					callback()
				} catch (e){

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
			var checkbox_all_parent = $('.checkbox_all').parent();
			$('.checkbox_all').remove();
			checkbox_all_parent.append(ddsd);
			$(".checkbox_all").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})
			if ($('tbody input:checkbox').length === $("tbody input:checked").length){
				$(".checkbox_all").iCheck('check');
			}

			forChecksBind()

		});

	}

	//------------------------------------- 添加热点 Start ----------------------------------------//
	function add() {
		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			offset: 'r',
			content: cg_vte.path + 'webpage/base_data/fire_hot/fire_hot_add.jsp',
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
			//shadeClose: true,
			yes: function (index, layero) {layer.close(index);},
		});
	}
	function see(item) {
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
			content: cg_vte.path + 'webpage/base_data/fire_hot/fire_hot_detail.jsp',
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {layer.close( index );}
		});
	}
	function edit(item){
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
			content: cg_vte.path + 'webpage/base_data/fire_hot/fire_hot_edit.jsp',
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {layer.close( index );}
		});
	}
	function fankui(item) {
		var tr_data = $(item).parents('tr').data('data_tr');

		if (tr_data.status == 1){
			layer.msg('已经反馈过了。');
			return;
		}
		//H5本地存储
		window.sessionStorage.setItem('data_tr', JSON.stringify(tr_data));
		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			content: cg_vte.path + 'webpage/base_data/fire_hot/fire_hot_fankui.jsp',
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {layer.close( index );}
		});
	}
	function addOrUpdCallback() {
		layer.closeAll();
		initDataGrid();
	}
	//------------------------------------- 添加热点 End ----------------------------------------//



	function delHot(item) {

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
					f: 4113001,
					id: data.id
				},
				success: function (data) {
					data = JSON.parse(data);
					callbackDel(data.status, data.msg, item);
				}
			})


		});


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

	function excelExport() {
		location.href = '${pageContext.request.contextPath}/if/serviceController/action?f=4120002';
	}

	function mapView(item) {
		//地图模式
		if (this_data.data_type == 1){
			$dom.tr_datagrid_list.hide();
			$dom.tr_datamap.show();
			this_data.data_type = 2;
			$(item).html(' 列表模式');
			getPacBoundary(this_data.pac)
			return ;
		}
		$dom.tr_datagrid_list.show();
		$dom.tr_datamap.hide();
		this_data.data_type = 1;
		//location.href = cg_vte.path + 'webpage/base_data/fire_hot/fire_hot_map_view.jsp';
		$(item).html(' 地图模式');
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
					offset: new AMap.Pixel(-5, -5),
					content: '<i index="'+i+'" class="iconfont icon-redian7"><span class="none">' + JSON.stringify(rows[i])+'</span><u>' + rows[i].no + '</u></i> ',
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
					info.push("编号 : {no}");
					info.push("<br>");
					info.push("卫星 : {satellite}");
					info.push("<br>");
					info.push("地类 : {landtype}");
					info.push("<br>");
					info.push("类型 : {type}");
					info.push("<br>");
					info.push("像素数： {pixels}");
					info.push("<br>");
					info.push("报告时间： {reporttime}");
					info.push("<br>");
					info.push("状态： {status}");
					info.push("</div>");
					info.push("<a onclick='hotMapDatail()' class=\"firefor\">详情</a>")
					info.push("</div>");
					var content = info.join("").format({
						no: data.no,
						satellite: data.satellite,
						type: data.type,
						pixels: data.pixels,
						reporttime: data.reporttime,
						landtype: data.landtype,
						status: function(){
							switch (data.status){
								case 0: return '未反馈';
								case 1: return '已反馈';
								default: return 0;
							}
						}
					})
					var infoWindow = new AMap.InfoWindow({
						offset: new AMap.Pixel(-5, -5),
						content: content
					});

					infoWindow.open(map, coordtransform.gcj02towgs84( data.longitude, data.latitude ));
				});

			}
		}

		this_data.markers = markers;

	}

	function hotMapDatail() {

		console.log(this_data.this_map_data);
		var data = this_data.this_map_data;

		var content = $('#this_map_data').html().trim();

		content = content.format({
			pac: data.pac || '',
			no: data.no || '',
			satellite: data.satellite || '',
			longitude: data.longitude || '',
			latitude: data.latitude || '',
			pixels: data.pixels || '',
			smoke: function () {
				if (data.smoke == 0){
					return '有'
				}
				return '没有'
			},
			continuous: function () {
				switch (data.continuous){
					case 1: return '是';
					case 2: return '否';
					default: return '';
				}
			},
			landtype: data.landtype || '',
			receiptt: function(){
				if (data.receiptt){
					return new Date(data.receiptt).Format('yyyy-MM-dd');
				}
				return '';
			},
			type: function () {
				switch (data.type){
					case 1: return '炼山';
					case 2: return '农用火';
					case 3: return '境外火';
					case 4: return '其他';
					case 5: return '火灾';
					default: return '';
				}
			},
			reporter: data.reporter || '',
			reporttime: function(){
				if (data.reporttime){
					return new Date(data.reporttime).Format('yyyy-MM-dd');
				}
			},
			opinion: data.opinion || '',
			duty: data.duty || '',
			note: data.note || '',
			source: data.source || '',
			shape: data.shape || '',
			county: data.county || '',
			status: function () {
				switch (data.status){
					case 1: return '已反馈';
					default: return '未反馈';
				}
			},

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
						<td style="width:140px;"> 地区代码</td>
						<td style="padding-left:20px;">{pac}</td>
						<td style="width:140px;">接收卫星</td>
						<td style="padding-left:20px;">{satellite}</td>

					</tr>
					<tr>
						<td>编号</td>
						<td>{no}</td>
						<td>火灾类型</td>
						<td>{type}</td>
					</tr>
					<tr>
						<td>径度</td>
						<td>{longitude}</td>
						<td>纬度</td>
						<td>{latitude}</td>

					</tr>
					<tr>
						<td>像素数</td>
						<td>{pixels}</td>
						<td>有烟否</td>
						<td>{smoke}</td>
					</tr>
					<tr>
						<td>是否连续林火事件</td>
						<td>{continuous}</td>
						<td>地类</td>
						<td>{landtype}</td>

					</tr>
					<tr>
						<td>接受时间</td>
						<td>{receiptt}</td>
						<td>报告人</td>
						<td>{reporter}</td>
					</tr>
					<tr>
						<td>报告时间</td>
						<td>{reporttime}</td>

						<td>处理意见</td>
						<td>{opinion}</td>

					</tr>
					<tr>
						<td>值班人</td>
						<td>{duty}</td>
						<td>source</td>
						<td>{source}</td>
					</tr>
					<tr>
						<td>区/县</td>
						<td>{county}</td>
						<td>状态</td>
						<td>{status}</td>


					</tr>
					<tr>
						<td>几何</td>
						<td colspan="3">{shape}</td>
					</tr>
					<tr>
						<td>备注</td>
						<td colspan="3">{note}</td>
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