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
	<title>添加半专业森林消防队</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>
		#province, #city, #county {
			width: auto;
			display: initial;
		}

		#medit_file_box img {
			width: inherit;
			max-width: 300px;
		}

		#medit_file_box audio {
			width: 100%;
		}

		#medit_file_box .medit_file_value_box {
			padding: 10px;
		}

		#medit_file_box .medit_file_value_box {
			border: 1px solid transparent;
		}

		#medit_file_box .medit_file_value_box:hover {
			border: 1px dashed #ccc;
		}


	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var this_data = {
			pac: '${LOCAL_CLINET_USER.pac }',
		};
	</script>
</head>

<body>

<div id="bar-progress"><label id="bar-progress-text"></label></div>

<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12 animated fadeInUp">
			<form method="post" class="form-horizontal" name="form1" id="form1">

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="name">名称</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="name" id="name" placeholder="名称">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="address">地址 </label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="address" id="address" placeholder="地址">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="phone">值班电话</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="phone" id="phone" placeholder="值班电话">
						<span class="help-block m-b-none"><i class="fa fa-info-circle"></i>多个电话用","隔开”</span>
					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="cz_radio">车载台</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="cz_radio" id="cz_radio" placeholder="车载台">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="b_cutter">割灌机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="b_cutter" id="b_cutter" placeholder="割灌机">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="machetes">砍刀</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="machetes" id="machetes" placeholder="砍刀">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="t_car">运兵车辆</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="t_car" id="t_car" placeholder="运兵车辆">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="manager">管理员</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="manager" id="manager" placeholder="管理员">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="latitude">经度</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="latitude" id="latitude" placeholder="经度">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="longitude">纬度</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="longitude" id="longitude" placeholder="纬度">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fireengine">消防车</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fireengine" id="fireengine" placeholder="消防车">

					</div>
				</div>


				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="n2n3tool">二、三号工具</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="n2n3tool" id="n2n3tool" placeholder="二、三号工具">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="w_equipm">风力灭火机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="w_equipm" id="w_equipm" placeholder="风力灭火机">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="pump">灭火水泵</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="pump" id="pump" placeholder="灭火水泵">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fire_bomb">灭火弹</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fire_bomb" id="fire_bomb" placeholder="灭火弹">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="wsinjector">水雾喷射器</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="wsinjector" id="wsinjector" placeholder="水雾喷射器">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="chainsaw">油锯</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="chainsaw" id="chainsaw" placeholder="油锯">

					</div>
				</div>


				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="w_cannons">灭火水枪</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="w_cannons" id="w_cannons" placeholder="灭火水枪">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="interphone">对讲机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="interphone" id="interphone" placeholder="对讲机">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="zj_radio">中继台</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="zj_radio" id="zj_radio" placeholder="中继台">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="sc_radio">手持台</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="sc_radio" id="sc_radio" placeholder="手持台">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="jd_radio">基地台</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="jd_radio" id="jd_radio" placeholder="基地台">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="gps">GPS定位仪</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="gps" id="gps" placeholder="GPS定位仪">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="r_clothing">防火服</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="r_clothing" id="r_clothing" placeholder="防火服">

					</div>
				</div>

				<div class="hr-line-dashed"></div>


				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="flashlight">手电筒</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="flashlight" id="flashlight" placeholder="手电筒">

					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="helmet">头盔</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="helmet" id="helmet" placeholder="头盔">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="gloves">手套</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="gloves" id="gloves" placeholder="手套">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fire_shoes">扑火鞋</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fire_shoes" id="fire_shoes" placeholder="扑火鞋">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="o_equip">其它灭火工具</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="o_equip" id="o_equip" placeholder="其它灭火工具">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="dynamotor">发电机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="dynamotor" id="dynamotor" placeholder="发电机">

					</div>
				</div>

				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="anemometer">风速仪器</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="anemometer" id="anemometer" placeholder="风速仪器">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="communication_v">通信车</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="communication_v" id="communication_v"
						       placeholder="通信车">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="ax">大斧</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="ax" id="ax" placeholder="大斧">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="sleeping_bag">睡袋</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="sleeping_bag" id="sleeping_bag" placeholder="睡袋">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="high_pressure_fex">高压细水雾灭火机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="high_pressure_fex" id="high_pressure_fex"
						       placeholder="高压细水雾灭火机">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fire_shovel">消防铲</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fire_shovel" id="fire_shovel" placeholder="消防铲">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="lighter">点火器</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="lighter" id="lighter" placeholder="点火器">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="motorcycle">摩托车</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="motorcycle" id="motorcycle" placeholder="摩托车">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fire_detectors">余火探测仪</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fire_detectors" id="fire_detectors"
						       placeholder="余火探测仪">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fsf_extinguishers">风水灭火机</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fsf_extinguishers" id="fsf_extinguishers"
						       placeholder="风水灭火机">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="base_value">基地产值</label>

					<div class="col-xs-8 col-md-4 col-md-4 por">
						<input type="text" class="form-control" name="base_value" id="base_value" placeholder="基地产值">
						<span class="prafont poa">万元</span>
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="fire_extinguisher">灭火水车</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="fire_extinguisher" id="fire_extinguisher"
						       placeholder="灭火水车">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label">状态</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<div class="radio fl mr20">
							<label>
								<input type="radio" name="optionsRadios" id="status1" value="status1" checked>
								优秀
							</label>
						</div>
						<div class="radio fl mr20">
							<label>
								<input type="radio" name="optionsRadios" id="status2" value="status2">
								良好
							</label>
						</div>
						<div class="radio fl mr20">
							<label>
								<input type="radio" name="optionsRadios" id="status3" value="status3">
								一般
							</label>
						</div>
						<div class="radio fl mr20">
							<label>
								<input type="radio" name="optionsRadios" id="status4" value="status4">
								差
							</label>
						</div>
						<div class="radio fl mr20">
							<label>
								<input type="radio" name="optionsRadios" id="status5" value="status5">
								废弃
							</label>
						</div>
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="tent">帐篷</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="tent" id="tent" placeholder="帐篷">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label">类型</label>
					<div class="col-xs-8 col-md-4 col-md-4">
						<div class="radio fl mr20">
							<label>
								<input type="radio" name="typee" id="structure1" value="structure1" checked>
								半专业扑火队伍
							</label>
						</div>

					</div>

				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="barracks_area">营房面积</label>

					<div class="col-xs-8 col-md-4 col-md-4 por">
						<input type="text" class="form-control" name="barracks_area" id="barracks_area"
						       placeholder="营房面积">
						<span class="prafont poa">㎡</span>
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label">基地类型</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<div class="radio fl mr20">
							<label>
								<input type="radio" name="base_type1" id="base_type1" value="base_type1" checked>
								种植
							</label>
						</div>
						<div class="radio fl mr20">
							<label>
								<input type="radio" name="base_type1" id="base_type2" value="base_type2">
								养殖
							</label>
						</div>
						<div class="radio fl mr20">
							<label>
								<input type="radio" name="base_type1" id="base_type3" value="base_type3">
								加工
							</label>
						</div>
						<div class="radio fl mr20">
							<label>
								<input type="radio" name="base_type1" id="base_type4" value="base_type4">
								第三方产业
							</label>
						</div>
						<div class="radio fl mr20">
							<label>
								<input type="radio" name="base_type1" id="base_type5" value="base_type5">
								其它
							</label>
						</div>
					</div>
				</div>
				<div class="hr-line-dashed"></div>


				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="nav_handheld">导航手持终端数量</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="nav_handheld" id="nav_handheld"
						       placeholder="导航手持终端数量">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="nav_vehicle">导航车载终端数量</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="nav_vehicle" id="nav_vehicle"
						       placeholder="导航车载终端数量">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="note">说明</label>

					<div class="col-xs-8 col-md-4 col-md-4">
						<input type="text" class="form-control" name="note" id="note" placeholder="说明">

					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label" for="shape">几何</label>

					<div class="col-xs-8 col-md-6 col-md-6">
						<textarea class="form-control" name="shape" id="shape" placeholder="非必填" rows="3"></textarea>
						<span class="help-block m-b-none">示例-点：<code>POINT(15 20)</code></span>
						<span class="help-block m-b-none">示例-线：<code>LINESTRING(0 0, 10 10, 20 25, 50 60)</code></span>
						<span class="help-block m-b-none">示例-面：<code>POLYGON((0 0,10 0,10 10,0 10,0 0),(5 5,7 5,7 7,5 7, 5 5))</code></span>
						<span class="help-block m-b-none">示例-多点：<code>MULTIPOINT(0 0, 20 20, 60 60)</code></span>
						<span class="help-block m-b-none">示例-多线：<code>MULTILINESTRING((10 10, 20 20), (15 15, 30 15))</code></span>
						<span class="help-block m-b-none">示例-多面：<code>MULTIPOLYGON(((0 0,10 0,10 10,0 10,0 0)),((5 5,7 5,7 7,5 7, 5 5)))</code></span>
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label for="province" class="col-xs-2 col-xs-offset-2 control-label">行政区</label>

					<div class="col-xs-8 col-md-6 col-md-6">
						<select class="form-control" id="province"></select>
						<select class="form-control" id="city" onchange="changeCity(this)"></select>
						<select class="form-control" id="county" onchange="changeCounty(this)"></select>
					</div>
				</div>
				<div class="hr-line-dashed"></div>

				<div class="form-group">
					<label class="col-xs-4 control-label">媒体文件</label>
					<div class="col-xs-8 col-md-6 col-md-6">
						<style>    #mediaFile {
							opacity: 0;
							position: absolute;
							cursor: pointer
						}

						#medit_file_input {
							overflow: hidden;
							white-space: nowrap;
							text-overflow: ellipsis;
						}</style>
						<input type="file" class="form-control" id="mediaFile" accept="audio/*,video/*,image/*,*.mkv"
						       onchange="readyFile(this)" multiple>
						<div class="input-append input-group" style="z-index: -1;">
								<span class="input-group-btn">
									<button class="btn btn-white" type="button">选择文件</button>
								</span>
							<input class="input-large form-control" type="text" id="medit_file_input">
						</div>
						<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 支持的类型有: <code>image/*,video/*,audio/*</code></span>

						<div id="medit_file_box">

						</div>
					</div>
				</div>
				<div class="hr-line-dashed"></div>


				<div class="col-sm-4 col-md-offset-4 col-lg-offset-4 col-xs-offset-4">
					<button class="btn btn-primary" type="submit">提交内容</button>
					<button class="btn btn-warning" onclick="window.parent.layer.closeAll();">取消</button>
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
	$(document).ready(function () {


		$dom.city = $('#city');
		$dom.county = $('#county');
		$dom.province = $('#province');

		$dom.bar_progress = $('#bar-progress', window.top.document);

		this_data.pac_level = formatPacLevel(this_data.pac);

		peggingPac(function () {
			initPac(this_data.pac, function () {
				validateForm()
			})
		});


	});

	function validateForm() {
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
			submitHandler: function (form) {
				_submit(form);

			}
		});
	}


	function _submit(form) {


		$('#submit').attr('disabled', true).text('提交中...')

		var formData = new FormData(form);

		formData.append("f", 4201001);
		formData.append("pac", this_data.pac);


		//媒体文件
		var files = document.getElementById('mediaFile');
		files = files.files;
		for (var i in files) {
			if (files.hasOwnProperty(i)) {
				formData.append("file" + i, files[i]);
			}
		}

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
				if (data.status == 10000) {
					swal({
						title: "提示",
						text: "success",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonText: "OK",
						confirmButtonColor: "#1AB394"
					}, function () {
						window.parent.addOrUpdCallback();
					});
				} else {
					layer.msg(data.msg);
					$('#submit').removeAttr('disabled').text("保存内容")
				}
			}
		})
	}

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
				if (data.status == 10000) {
					var obj = data.obj;
					switch (obj.length) {
						case 1:
							$dom.province.html('<option>' + obj[0].name + '</option>').attr('disabled', 'disabled');
							break;
						case 2:
							$dom.province.html('<option>' + obj[0].name + '</option>').attr('disabled', 'disabled')
							$dom.city.html('<option>' + obj[1].name + '</option>').attr('disabled', 'disabled')
							break;
						case 3:
							$dom.province.html('<option>' + obj[0].name + '</option>').attr('disabled', 'disabled')
							$dom.city.html('<option>' + obj[1].name + '</option>').attr('disabled', 'disabled')
							$dom.county.html('<option>' + obj[2].name + '</option>').attr('disabled', 'disabled')
							break;
					}
				}
				try {
					callback()
				} catch (e) {

				}

			}
		})
	}

	//工具-拉取pac数据
	function initPac(id, callback) {
		if (this_data.pac_level > 2) {

			this_data.pac = id;

			return;
		}
		$.ajax({
			url: cg_vte.service,
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
						if (i < 1) {
							switch (this_data.pac_level) {
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
						switch (this_data.pac_level) {
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

				if (this_data.pac_level == 1) {
					$dom.county.html('<option>全部</option>')
				}

				try {
					callback()
				} catch (e) {

				}

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
				f: 4221001,
				pac: id,
				fetchType: 1,
			},
			dataType: 'text',
			success: function (data) {

				data = JSON.parse(data);

				$dom.county.empty();

				var children = data.rows;

				for (var i in children) {
					if (children.hasOwnProperty(i)) {
						if (i == 0) {
							this_data.pac_name = children[i].name;
							this_data.pac = children[i].code;
							$dom.county.append('<option value="' + children[i].code + '">全部</option>');
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

	//当市高等框发生改变
	function changeCity(dom) {

		this_data.pac = dom.value;

		if (this_data.pac_level == 1) {

			$dom.county.unbind("change");
			initPacCounty(dom.value, function () {


				$dom.county.bind("change", function () {

					this_data.pac = $(this).val();

				})

			});

		}

	}

	function changeCounty(item) {
		this_data.pac = item.value;
	}

	function readyFile(files) {

		$('#medit_file_box').empty();
		$('#medit_file_input').val('');
		var fileNames = '';
		files = files.files;
		for (var i in files) {
			if (files.hasOwnProperty(i)) {
				if (files[i].type.indexOf('video') >= 0) {
					$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_video"><video src="' + getObjectURL(files[i]) + '" controls="controls">您的浏览器不支持 video 标签。</video><p>' + files[i].name + '</p></div>')
				} else if (files[i].type.indexOf('audio') >= 0) {
					$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_audio"><audio src="' + getObjectURL(files[i]) + '" controls="controls">您的浏览器不支持 audio 标签。</audio><p>' + files[i].name + '</p></div>')
				} else if (files[i].type.indexOf('image') >= 0) {
					$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_image"><img src="' + getObjectURL(files[i]) + '"><p>' + files[i].name + '</p></div>')
				}

				if (i == 0) {
					fileNames = files[i].name;
					continue;
				}
				fileNames += "," + files[i].name;
			}
		}
		$('#medit_file_input').val(fileNames)

	}


	//获取文件地址，显示预览用
	var getObjectURL = function (file) {
		var url = null;
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(file);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	};

	/**
	 *    侦查附件上传情况    ,这个方法大概0.05-0.1秒执行一次
	 */
	function onprogress(evt) {
		var loaded = evt.loaded;                  //已经上传大小情况
		var tot = evt.total;                      //附件总大小
		var per = Math.floor(100 * loaded / tot);     //已经上传的百分比

		$dom.bar_progress.css("width", per + "%");
		$('#bar-progress-text').text(per + "%")
		console.log(per)
		if (per == 100) {

			console.log("上传成功")
			$dom.bar_progress.css("width", '0');
		}

	}

</script>
</body>
</html>