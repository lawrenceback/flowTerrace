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
	<title>火灾档案</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-datetimepicker/3.1.4/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/datapicker/datepicker3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?dsds">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?' + new Date().getTime() + '">');</script>

	<style>
		.ibox.ibox-datagrid .datagrid-talbe{
			height: calc(100% - 120px);
		}

		.iconfont{
			position: relative;
			left: 3px;
		}
		#province, #city, #county{
			width: auto;
		}

	</style>
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
			type: '',
			fetchType: 3
		};
	</script>

</head>

<body class="gray-bg height_per_100">

<div class="wrapper animated fadeInRight height_per_100">

	<div class="row height_per_100">

		<div class="col-sm-12 height_per_100">


			<div class="ibox ibox-datagrid">

				<div class="ibox-title">
					<h5></h5>

					<div class="ibox-tools-filtrate">
						<select id="province" class="form-control margin-10"></select>
						<select id="city" onchange="_city(this);" class="form-control margin-10" ></select>
						<select id="county"  onchange="_county(this)" class="form-control margin-10"></select>
					</div>

					<div class="ibox-tools ibox-tools-one">
						<a onclick="add()" ><i class="fa fa-plus"></i> 添加档案</a>

						<a id="dLabel" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"> <i class="glyphicon glyphicon-list"></i></a>

						<ul class="dropdown-menu bg-warning" aria-labelledby="dLabel" id="cols">
							<li><div class="checkbox i-checks"><label><input type="checkbox"   property="row"><i></i>行号</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="pac"><i></i>区号代码</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="shape"><i></i>几何</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="city"><i></i>市</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="county"><i></i>区/县</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="no"><i></i>火灾编号</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox"   checked property="name"><i></i>火灾名称</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox"   property="hotid"><i></i>热点编号</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="address"><i></i>起火地点</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="longitude"><i></i>经度</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="latitude"><i></i>	纬度</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="s_time"><i></i>起火时间</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="f_time"><i></i>发现时间</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="e_time"><i></i>扑灭时间</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="duration"><i></i>延续时间</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="firetype"><i></i>火灾类型</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="cause"><i></i>起火原因</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="firearea"><i></i>火场总面积</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="nf_area"><i></i>受害天然林</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="af_proport"><i></i>受害人工林</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="t_categ"><i></i>林种</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="ta_categ"><i></i>龄组</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="d_categ"><i></i>优势树种</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="treeform"><i></i>树种组成</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="woodloss"><i></i>损失林木</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="iwoodloss"><i></i>	损失林木_成林蓄积</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="saplloss"><i></i>	损失林木_幼林株数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="inj_minor"><i></i>	人员伤亡_轻伤</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="inj_severe"><i></i>	人员伤亡_重伤</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="inj_death"><i></i>	人员伤亡_死亡</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="conductor"><i></i>	火场指挥员</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="con_post"><i></i>火场指挥员职务</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="brigade"><i></i>出动专业队</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="for_police"><i></i>出动森警</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="army"><i></i>出动军队</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="arm_police"><i></i>出动武警</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="masses"><i></i>出动群众</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="plane"><i></i>出动飞机架次</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="planetype"><i></i>机型</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="flighttime"><i></i>飞行时间</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="flightcost"><i></i>飞行费</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="planefall"><i></i>机降架次</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="fallpeople"><i></i>机降人次</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="p_chemical"><i></i>化灭架次</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="p_bucket"><i></i>吊桶次数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="commandcar"><i></i>出动车辆_指挥车</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="trans_car"><i></i>出动车辆_运输车</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="armoredcar"><i></i>出动车辆_装甲车</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="othercar"><i></i>出动车辆_其它车辆</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="shortwave"><i></i>携带电台_短波</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="uswave"><i></i>携带电台_超短波</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="windequip"><i></i>出动风力灭火机</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="watergun"><i></i>出动水枪</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="n2n3tool"><i></i>	二、三号工具</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="otherequip"><i></i>其他工具</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="fightcost"><i></i>扑火经费</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="otherloss"><i></i>其他损失折款</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="wrecker"><i></i>肇事者姓名</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="wreckerage"><i></i>肇事者年龄</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="wreckerpro"><i></i>肇事者职业</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="wreckerunit"><i></i>肇事者单位</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="wapunish"><i></i>肇事者行政处分</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="wcpunish"><i></i>肇事者刑事处罚</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="wcpunish"><i></i>领导行政处分</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="hapunish"><i></i>领导行政处分</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="hcpunish"><i></i>领导刑事处罚</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="tempera"><i></i>气温</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="humidity"><i></i>湿度</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="winddir"><i></i>风向</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="windpower"><i></i>风力</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="windspeed"><i></i>风速</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="rainfall"><i></i>降雨量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="description"><i></i>火情简介或说明</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="province"><i></i>省</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="town_name"><i></i>乡镇名</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="village_name"><i></i>村名</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="elevation"><i></i>海拔</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="aspect"><i></i>坡向</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="slope_position"><i></i>坡位</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="slope"><i></i>坡度</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="land_type"><i></i>地类</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="front_command_name"><i></i>前线指挥姓名</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="front_command_post"><i></i>前线指挥职务</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="rear_command_name"><i></i>后方指挥姓名</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="rear_command_post"><i></i>后方指挥职务</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="cadre"><i></i>干部人数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="brigade_people"><i></i>专业扑火队人数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="hbrigade_people"><i></i>半专业扑火队人数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="hbrigade"><i></i>半专业扑火队</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="communication_car"><i></i>出动车辆_通讯车</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="support_car"><i></i>出动车辆_保障车</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="hpw_tanker"><i></i>出动车辆_高压水车</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="bulldozer"><i></i>出动车辆_推土机</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="interphone"><i></i>对讲机数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="satellitephone"><i></i>卫星电话数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="other_communication"><i></i>其它通讯工具数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="nav_handheld"><i></i>导航手持终端数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="nav_vehicle"><i></i>导航车载终端数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="high_pressure_fex"><i></i>高压细水雾灭火机</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="pump"><i></i>灭火水泵数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="artificial_rain"><i></i>人工增雨</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="fire_bombs"><i></i>灭火弹数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="b_cutter"><i></i>割灌机数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="chainsaw"><i></i>油锯数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="camera1"><i></i>相机数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="camera2"><i></i>摄像机数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="caseInfo"><i></i>案件处理</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="wayofaccident"><i></i>肇事方式</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="weather"><i></i>天气状况</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="high_tempera"><i></i>最高气温</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="low_tempera"><i></i>最低气温</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="low_humidity"><i></i>最低湿度</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="code"><i></i>信息所属行政区代码</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="forestarea"><i></i>受害森林面积</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="newforestarea"><i></i>受害新造林地面积</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="artificialfireout"><i></i>出动扑火人工（工日）</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="totavehiclenumber"><i></i>出动车辆总计</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="carnumber"><i></i>其中汽车</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="planeoutnumber"><i></i>出动飞机（架次）</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="WingAirPlaneNumber"><i></i>固定翼飞机数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="WingAirPlaneTime"><i></i>固定翼飞机飞行时间</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="HelicopterNumber"><i></i>直升机数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="HelicopterTime"><i></i>直升机飞行时间</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="WuRenJiNumber"><i></i>无人机数量</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="WuRenjiTime"><i></i>无人机飞行时间</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="IsDealWith"><i></i>是否处理</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="ProcessedNumber"><i></i>已处理人数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="CriminalNumber"><i></i>刑事处罚人数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="APenaltyNumber"><i></i>行政处罚人数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="ASanctionNumber"><i></i>行政处分人数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="RecordDispositionNumber"><i></i>纪律处分人数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="for_cop"><i></i>森林公安人数</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="reporter"><i></i>上报人</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox"  property="reportTime"><i></i>上报时间</label></div></li>


						</ul>

					</div>
				</div>

				<div class="ibox-content ovelauto">

					<form class="form-inline" style="position: relative">






					</form>
					<hr>

					<div class="datagrid-talbe">

						<table class="table table-bordered table-hover" id="hot_list">
							<thead>
							<tr>
								<%--<th data-property="checkbox" style="width:45px;"><div class="checkbox i-checks checkbox_all"><label><input type="checkbox" class="checkbox_all_input"><i></i></label></div></th>--%>
								<th data-property="row">#</th>
								<th class="sort" data-property="pac">行政区划代码<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="shape">几何<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="city">	市<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="county">区/县<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="no">火灾编号<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="name"> 火灾名称<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="hotid">热点编号	<i class="iconfont icon-paixu"></i>	</th>
								<th class="sort" data-property="address">	起火地点<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="longitude">经度<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="latitude">	纬度<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="s_time">起火时间<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="f_time">	发现时间<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="e_time">扑灭时间<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="duration">延续时间<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="firetype">火灾类型<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="cause">起火原因<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="firearea">火场总面积<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="nf_area">	受害天然林<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="af_proport">受害人工林<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="t_categ">林种<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="ta_categ">	龄组<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="d_categ">优势树种<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="treeform">树种组成<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="woodloss"> 损失林木<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="iwoodloss"> 损失林木_成林蓄积<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="saplloss"> 损失林木_幼林株数<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="inj_minor"> 人员伤亡_轻伤<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="inj_severe"> 人员伤亡_重伤<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="inj_death"> 人员伤亡_死亡<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="conductor"> 火场指挥员<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="con_post"> 火场指挥员职务<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="brigade"> 出动专业队<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="for_police"> 出动森警<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="army"> 出动军队<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="arm_police"> 出动武警<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="masses"> 	出动群众<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="plane"> 出动飞机架次<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="planetype"> 机型<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="flighttime"> 飞行时间<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="flightcost"> 飞行费<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="planefall"> 机降架次<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="fallpeople"> 机降人次<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="p_chemical"> 化灭架次<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="p_bucket"> 吊桶次数<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="commandcar"> 出动车辆_指挥车<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="trans_car"> 出动车辆_运输车<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="armoredcar"> 出动车辆_装甲车<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="othercar"> 出动车辆_其它车辆<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="shortwave"> 携带电台_短波<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="uswave"> 携带电台_超短波<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="windequip"> 出动风力灭火机<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="watergun"> 	出动水枪<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="n2n3tool"> 	二、三号工具<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="otherequip"> 	其他工具<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="fightcost"> 扑火经费<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="otherloss"> 其他损失折款<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="wrecker"> 肇事者姓名<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="wreckerage"> 肇事者年龄<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="wreckerpro"> 肇事者职业<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="wreckerunit"> 肇事者单位<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="wapunish"> 肇事者行政处分<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="wcpunish"> 肇事者刑事处罚<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="hapunish"> 领导行政处分<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="hcpunish"> 领导刑事处罚<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="tempera"> 气温<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="humidity"> 湿度<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="winddir"> 风向<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="windpower"> 风力<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="windspeed"> 风速<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="rainfall"> 降雨量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="description"> 火情简介<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="province"> 省<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="town_name"> 乡镇名<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="village_name"> 村名<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="elevation"> 海拔<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="aspect"> 坡向<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="slope_position"> 坡位<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="slope"> 坡度<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="land_type"> 地类<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="front_command_name"> 前线指挥姓名<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="front_command_post"> 前线指挥职务<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="rear_command_name"> 后方指挥姓名<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="rear_command_post"> 后方指挥职务<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="cadre"> 干部人数<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="for_cop"> 森林公安人数<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="brigade_people"> 专业扑火队人数<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="hbrigade_people"> 半专业扑火队人数<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="hbrigade"> 	半专业扑火队<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="communication_car"> 出动车辆_通讯车<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="support_car"> 出动车辆_保障车<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="hpw_tanker"> 出动车辆_高压水车<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="bulldozer"> 出动车辆_推土机<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="interphone"> 对讲机数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="satellitephone"> 卫星电话数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="other_communication"> 其它通讯工具数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="nav_handheld"> 导航手持终端数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="nav_vehicle"> 	导航车载终端数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="high_pressure_fex"> 高压细水雾灭火机<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="pump"> 灭火水泵数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="artificial_rain"> 人工增雨<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="fire_bombs"> 灭火弹数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="b_cutter"> 割灌机数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="chainsaw"> 油锯数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="camera1"> 相机数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="camera2"> 摄像机数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="caseInfo"> 案件处理<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="wayofaccident"> 肇事方式<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="weather">  天气状况<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="high_tempera">  最高气温<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="low_tempera">  最低气温<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="low_humidity">  最低湿度<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="code">信息所属行政区代码<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="forestarea">  受害森林面积<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="newforestarea">  受害新造林地面积<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="artificialfireout">  出动扑火人工（工日）<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="totavehiclenumber"> 出动车辆总计<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="carnumber"> 其中汽车<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="planeoutnumber"> 出动飞机（架次）<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="WingAirPlaneNumber">固定翼飞机数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="WingAirPlaneTime">固定翼飞机飞行时间<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="HelicopterNumber">直升机数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="HelicopterTime">直升机飞行时间<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="WuRenJiNumber">无人机数量<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="WuRenjiTime">无人机飞行时间<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="IsDealWith"> 是否处理<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="ProcessedNumber">已处理人数<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="CriminalNumber">刑事处罚人数<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="APenaltyNumber">行政处罚人数<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="ASanctionNumber">行政处分人数<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="RecordDispositionNumber">  	纪律处分人数<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="reporter">上报人<i class="iconfont icon-paixu"></i></th>
								<th class="sort" data-property="reportTime">上报时间<i class="iconfont icon-paixu"></i></th>
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


			</div>


		</div>
	</div>
</div>


<div id="temp_hot_list">
	<!-- <tr>
	<%--<td data-property="checkbox" style="width:45px;"><div class="checkbox i-checks"><label><input type="checkbox"   property="row"><i></i></label></div></td>--%>
		<td data-property="row">{row}</td>
		<td>{pac}</td>
		<td>{shape}</td>
		<td>{city}</td>
		<td>{county}</td>
		<td>{no}</td>
		<td>{name}</td>
		<td>{hotid}</td>
		<td>{address}</td>
		<td>{longitude}</td>
		<td>{latitude}</td>
		<td>{s_time}</td>
		<td>{f_time}</td>
		<td>{e_time}</td>
		<td>{duration}</td>
		<td>{firetype}</td>
		<td>{cause}</td>
		<td>{firearea}</td>
		<td>{nf_area}</td>
		<td>{af_proport}</td>
		<td>{t_categ}</td>
		<td>{ta_categ}</td>
		<td>{d_categ}</td>
		<td>{treeform}</td>
		<td>{woodloss}</td>
		<td>{iwoodloss}</td>
		<td>{saplloss}</td>
		<td>{inj_minor}</td>
		<td>{inj_severe}</td>
		<td>{inj_death}</td>
		<td>{conductor}</td>
		<td>{con_post}</td>
		<td>{brigade}</td>
		<td>{for_police}</td>
		<td>{army}</td>
		<td>{arm_police}</td>
		<td>{masses}</td>
		<td>{plane}</td>
		<td>{planetype}</td>
		<td>{flighttime}</td>
		<td>{flightcost}</td>
		<td>{planefall}</td>
		<td>{fallpeople}</td>
		<td>{p_chemical}</td>
		<td>{p_bucket}</td>
		<td>{commandcar}</td>
		<td>{trans_car}</td>
		<td>{armoredcar}</td>
		<td>{othercar}</td>
		<td>{shortwave}</td>
		<td>{uswave}</td>
		<td>{windequip}</td>
		<td>{watergun}</td>
		<td>{n2n3tool}</td>
		<td>{otherequip}</td>
		<td>{fightcost}</td>
		<td>{otherloss}</td>
		<td>{wrecker}</td>
		<td>{wreckerage}</td>
		<td>{wreckerpro}</td>
		<td>{wreckerunit}</td>
		<td>{wapunish}</td>
		<td>{wcpunish}</td>
		<td>{hapunish}</td>
		<td>{hcpunish}</td>
		<td>{tempera}</td>
		<td>{humidity}</td>
		<td>{winddir}</td>
		<td>{windpower}</td>
		<td>{windspeed}</td>
		<td>{rainfall}</td>
		<td>{description}</td>
		<td>{province}</td>
		<td>{town_name}</td>
		<td>{village_name}</td>
		<td>{elevation}</td>
		<td>{aspect}</td>
		<td>{slope_position}</td>
		<td>{slope}</td>
		<td>{land_type}</td>
		<td>{front_command_name}</td>
		<td>{front_command_post}</td>
		<td>{rear_command_name}</td>
		<td>{rear_command_post}</td>
		<td>{cadre}</td>
		<td>{for_cop}</td>
		<td>{brigade_people}</td>
		<td>{hbrigade_people}</td>
		<td>{hbrigade}</td>
		<td>{communication_car}</td>
		<td>{support_car}</td>
		<td>{hpw_tanker}</td>
		<td>{bulldozer}</td>
		<td>{interphone}</td>
		<td>{satellitephone}</td>
		<td>{other_communication}</td>
		<td>{nav_handheld}</td>
		<td>{nav_vehicle}</td>
		<td>{high_pressure_fex}</td>
		<td>{pump}</td>
		<td>{artificial_rain}</td>
		<td>{fire_bombs}</td>
		<td>{b_cutter}</td>
		<td>{chainsaw}</td>
		<td>{camera1}</td>
		<td>{camera2}</td>
		<td>{caseInfo}</td>
		<td>{wayofaccident}</td>
		<td>{weather}</td>
		<td>{high_tempera}</td>
		<td>{low_tempera}</td>
		<td>{low_humidity}</td>
		<td>{code}</td>
		<td>{forestarea}</td>
		<td>{newforestarea}</td>
		<td>{artificialfireout}</td>
		<td>{totavehiclenumber}</td>
		<td>{carnumber}</td>
		<td>{planeoutnumber}</td>
		<td>{WingAirPlaneNumber}</td>
		<td>{WingAirPlaneTime}</td>
		<td>{HelicopterNumber}</td>
		<td>{HelicopterTime}</td>
		<td>{WuRenJiNumber}</td>
		<td>{WuRenjiTime}</td>
		<td>{IsDealWith}</td>
		<td>{ProcessedNumber}</td>
		<td>{CriminalNumber}</td>
		<td>{APenaltyNumber}</td>
		<td>{ASanctionNumber}</td>
		<td>{RecordDispositionNumber}</td>
		<td>{reporter}</td>
		<td>{reportTime}</td>
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


		//判断用户pac等级
		this_data.pac_level = formatPacLevel(this_data.pac);

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


		var index = layer.load(2, {	shade: 0.6});

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 4210001,
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
				var data_length = data.length;

				this_data.last_rows = data_length;

				var $tbody = $('<tbody>');
				for (var i = 0; i < data_length; i++) {
					if (data.hasOwnProperty(i)) {
						var sub = temp.temp_hot_list.format({
							row: this_data.serialNumber + 1,

							pac: data[i].pac,
							shape: data[i].shape,
							city: data[i].city,
							county: data[i].county,
							no: data[i].no,
							name: data[i].name,
							hotid: data[i].hotid,
							address: data[i].address,
							longitude: data[i].longitude,
							latitude: data[i].latitude,
							s_time: data[i].s_time,
							f_time: data[i].f_time,
							e_time: data[i].e_time,
							duration: data[i].duration,
							firetype: data[i].firetype,
							cause: data[i].cause,
							firearea: data[i].firearea,
							nf_area: data[i].nf_area,
							af_proport: data[i].af_proport,
							t_categ: data[i].t_categ,
							ta_categ: data[i].ta_categ,
							d_categ: data[i].d_categ,
							treeform: data[i].treeform,
							woodloss: data[i].woodloss,
							iwoodloss: data[i].iwoodloss,
							saplloss: data[i].saplloss,
							inj_minor: data[i].inj_minor,
							inj_severe: data[i].inj_severe,
							inj_death: data[i].inj_death,
							conductor: data[i].conductor,
							con_post: data[i].con_post,
							brigade: data[i].brigade,
							for_police: data[i].for_police,
							army: data[i].army,
							arm_police: data[i].arm_police,
							masses: data[i].masses,
							plane: data[i].plane,
							opt: function () {
								var btn = '';
								btn += ' <a onclick="edit(this)" class="linkceond" title="修改"><i class="iconfont icon-xiugai"></i></a> ';
								btn += ' <a onclick="delbrigade(this)" class="linkceond" title="删除"><i class="iconfont icon-ttpodicon"> </i></a> ';
								return btn;
							},
							planetype: data[i].planetype,
							flighttime: data[i].flighttime,
							flightcost: data[i].flightcost,
							planefall: data[i].planefall,
							fallpeople: data[i].fallpeople,
							p_chemical: data[i].p_chemical,
							p_bucket: data[i].p_bucket,
							commandcar: data[i].commandcar,
							trans_car: data[i].trans_car,
							armoredcar: data[i].armoredcar,
							othercar: data[i].othercar,
							shortwave: data[i].shortwave,
							uswave: data[i].uswave,
							windequip: data[i].windequip,
							watergun: data[i].watergun,
							n2n3tool: data[i].n2n3tool,
							otherequip: data[i].otherequip,
							fightcost: data[i].fightcost,
							otherloss: data[i].otherloss,
							wrecker: data[i].wrecker,
							wreckerage: data[i].wreckerage,
							wreckerpro: data[i].wreckerpro,
							wreckerunit: data[i].wreckerunit,
							wapunish: data[i].wapunish,
							wcpunish: data[i].wcpunish,
							hapunish: data[i].hapunish,
							hcpunish: data[i].hcpunish,
							tempera: data[i].tempera,
							humidity: data[i].humidity,
							winddir: data[i].winddir,
							windpower: data[i].windpower,
							windspeed: data[i].windspeed,
							rainfall: data[i].rainfall,
							description: data[i].description,
							province: data[i].province,
							town_name: data[i].town_name,
							village_name: data[i].village_name,
							elevation: data[i].elevation,
							aspect: data[i].aspect,
							slope_position: data[i].slope_position,
							slope: data[i].slope,
							land_type: data[i].land_type,
							front_command_name: data[i].front_command_name,
							front_command_post: data[i].front_command_post,
							rear_command_name: data[i].rear_command_name,
							rear_command_post: data[i].rear_command_post,
							cadre: data[i].cadre,
							for_cop: data[i].for_cop,
							brigade_people: data[i].brigade_people,
							hbrigade_people: data[i].hbrigade_people,
							hbrigade: data[i].hbrigade,
							communication_car: data[i].communication_car,
							support_car: data[i].support_car,
							hpw_tanker: data[i].hpw_tanker,
							bulldozer: data[i].bulldozer,
							interphone: data[i].interphone,
							satellitephone: data[i].satellitephone,
							other_communication: data[i].other_communication,
							nav_handheld: data[i].nav_handheld,
							nav_vehicle: data[i].nav_vehicle,
							high_pressure_fex: data[i].high_pressure_fex,
							pump: data[i].pump,
							artificial_rain: data[i].artificial_rain,
							fire_bombs: data[i].fire_bombs,
							b_cutter: data[i].b_cutter,
							chainsaw: data[i].chainsaw,
							camera1: data[i].camera1,
							camera2: data[i].camera2,
							caseInfo: data[i].caseInfo,
							wayofaccident: data[i].wayofaccident,
							weather: data[i].weather,
							high_tempera: data[i].high_tempera,
							low_tempera: data[i].low_tempera,
							low_humidity: data[i].low_humidity,
							code: data[i].code,
							forestarea: data[i].forestarea,
							newforestarea: data[i].newforestarea,
							artificialfireout: data[i].artificialfireout,
							totavehiclenumber: data[i].totavehiclenumber,
							carnumber: data[i].carnumber,
							planeoutnumber: data[i].planeoutnumber,
							WingAirPlaneNumber: data[i].WingAirPlaneNumber,
							WingAirPlaneTime: data[i].WingAirPlaneTime,
							HelicopterNumber: data[i].HelicopterNumber,
							HelicopterTime: data[i].HelicopterTime,
							WuRenJiNumber: data[i].WuRenJiNumber,
							WuRenjiTime: data[i].WuRenjiTime,

							ProcessedNumber: data[i].ProcessedNumber,
							CriminalNumber: data[i].CriminalNumber,
							APenaltyNumber: data[i].APenaltyNumber,
							ASanctionNumber: data[i].ASanctionNumber,
							RecordDispositionNumber: data[i].RecordDispositionNumber,
							reporter: data[i].reporter,
							reportTime: data[i].reportTime,
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
							IsDealWith: function () {
								switch (data[i].IsDealWith) {
									case 1: return "是";

									default: return "否";
								}
							},

						});

						$tbody.append($(sub).data("data_tr", data[i]));
						this_data.serialNumber++;
					}
				}

				$dom.hot_list.append($tbody)

				$(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})

				forChecks()

				bindCols()

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

	//-------------------------------- 添加 防火林带 Start ------------------------------------//
	function add() {
		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			content: cg_vte.path + 'webpage/base_data/firedocument/firedocument_add.jsp',
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {layer.close( index );}
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
			content: cg_vte.path + 'webpage/base_data/firedocument/firedocument_edit.jsp',
			btn: ['提交', '关闭'],
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
					f: 4223001,
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

</script>

</body>
</html>


