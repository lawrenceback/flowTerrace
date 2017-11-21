<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/22
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.4.0/css/font-awesome.css?v=4.4.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0&312">


	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?' + new Date().getTime() + '">');</script>
	<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js"></script>

	<link href='https://api.mapbox.com/mapbox-gl-js/v0.38.0/mapbox-gl.css' rel='stylesheet' />
</head>
<body class="gray-bg">

	<div class="wrapper wrapper-content animated fadeInRight">
		<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
		<div id='main' style='width: 100%; height: 90%;'></div>
	</div>


<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/echarts.min.js"></script>
<script src="http://echarts.baidu.com/resource/echarts-gl-latest/dist/echarts-gl.min.js"></script>
<script src="http://echarts.baidu.com/resource/echarts-gl-latest/mapboxgl-token.js"></script>
<script src='https://api.mapbox.com/mapbox-gl-js/v0.38.0/mapbox-gl.js'></script>
<script>
	$(function(){


		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		var uploadedDataURL = "${pageContext.request.contextPath}/webpage/char/hot/json/data-1495284690309-Bk9Ro3Te-.json";
		mapboxgl.accessToken = 'pk.eyJ1IjoieXc5MjExIiwiYSI6ImNqNm5jemswdDA3aXgyd292cTZ3dHc0NWQifQ.ZCMwvyiB29KXtH71_eDV1A';

		myChart.showLoading();

		$.getJSON(uploadedDataURL, function(linedata) {

			myChart.hideLoading();

			myChart.setOption({

				visualMap: {
					show: false,
					calculable: true,
					realtime: false,
					inRange: {
						color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
					},
					outOfRange: {
						colorAlpha: 0
					},
					max: linedata[1]
				},
				mapbox: {
					center: [108.177972,33.006454],
					zoom: 4,
					pitch: 25,
					bearing: -10,
					style: 'mapbox://styles/mapbox/light-v9',
					boxHeight: 50,
					altitudeScale: 3e2,
					postEffect: {
						enable: true,
						SSAO: {
							enable: true,
							radius: 2,
							intensity: 1.5
						}
					},
					light: {
						main: {
							intensity: 1,
							shadow: true,
							shadowQuality: 'high'
						},
						ambient: {
							intensity: 0.
						},
						ambientCubemap: {
							texture: '${pageContext.request.contextPath}/webpage/char/hot/json/data-1491896094618-H1DmP-5px.hdr',
							exposure: 1,
							diffuseIntensity: 0.5
						}
					}
				},
				series: [{
					type: 'bar3D',
					shading: 'realistic',
					coordinateSystem: 'mapbox',
					barSize: 0.3,
					silent: true,
					data: linedata[0]
				}]
			});
		});





	})

	function dds(){
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		// 指定图表的配置项和数据
		var option = {
			title: {
				text: 'ECharts 入门示例'
			},
			tooltip: {},
			legend: {
				data:['销量']
			},
			xAxis: {
				data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
			},
			yAxis: {},
			series: [{
				name: '销量',
				type: 'bar',
				data: [5, 20, 36, 10, 10, 20]
			}]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	}



</script>
</body>
</html>
