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
	<script src="${pageContext.request.contextPath}/webpage/char/map/china.js"></script>
<script>
	$(function(){


		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		function randomData() {
			return Math.round(Math.random()*1000);
		}

		var option = {
			grid: {
				left: '70%',
				right: '10%',
				bottom: '3%',
				containLabel: true
			},

			title: {
				text: 'iphone销量',
				subtext: '纯属虚构',
				left: 'center'
			},
			tooltip: {
				trigger: 'item'
			},
			legend: {
				orient: 'vertical',
				left: 'left',
				data: ['iphone3', 'iphone4', 'iphone5']
			},
			visualMap: {
				min: 0,
				max: 2500,
				left: 'left',
				top: 'bottom',
				text: ['高', '低'], // 文本，默认为数值文本
				calculable: true
			},
			series: [ {
					name: 'iphone3',
					type: 'map',
					mapType: 'china',
					roam: false,
					label: {
						normal: {
							show: true
						},
						emphasis: {
							show: true
						}
					},
					data: [{
						name: '北京',
						value: randomData()
					}, {
						name: '天津',
						value: randomData()
					}, {
						name: '上海',
						value: randomData()
					}, {
						name: '重庆',
						value: randomData()
					}, {
						name: '河北',
						value: randomData()
					}, {
						name: '河南',
						value: randomData()
					}, {
						name: '云南',
						value: randomData()
					}, {
						name: '辽宁',
						value: randomData()
					}, {
						name: '黑龙江',
						value: randomData()
					}, {
						name: '湖南',
						value: randomData()
					}, {
						name: '安徽',
						value: randomData()
					}, {
						name: '山东',
						value: randomData()
					}, {
						name: '新疆',
						value: randomData()
					}, {
						name: '江苏',
						value: randomData()
					}, {
						name: '浙江',
						value: randomData()
					}, {
						name: '江西',
						value: randomData()
					}, {
						name: '湖北',
						value: randomData()
					}, {
						name: '广西',
						value: randomData()
					}, {
						name: '甘肃',
						value: randomData()
					}, {
						name: '山西',
						value: randomData()
					}, {
						name: '内蒙古',
						value: randomData()
					}, {
						name: '陕西',
						value: randomData()
					}, {
						name: '吉林',
						value: randomData()
					}, {
						name: '福建',
						value: randomData()
					}, {
						name: '贵州',
						value: randomData()
					}, {
						name: '广东',
						value: randomData()
					}, {
						name: '青海',
						value: randomData()
					}, {
						name: '西藏',
						value: randomData()
					}, {
						name: '四川',
						value: randomData()
					}, {
						name: '宁夏',
						value: randomData()
					}, {
						name: '海南',
						value: randomData()
					}, {
						name: '台湾',
						value: randomData()
					}, {
						name: '香港',
						value: randomData()
					}, {
						name: '澳门',
						value: randomData()
					}]
				}, {
					name: 'iphone4',
					type: 'map',
					mapType: 'china',
					label: {
						normal: {
							show: true
						},
						emphasis: {
							show: true
						}
					},
					data: [{
						name: '北京',
						value: randomData()
					}, {
						name: '天津',
						value: randomData()
					}, {
						name: '上海',
						value: randomData()
					}, {
						name: '重庆',
						value: randomData()
					}, {
						name: '河北',
						value: randomData()
					}, {
						name: '安徽',
						value: randomData()
					}, {
						name: '新疆',
						value: randomData()
					}, {
						name: '浙江',
						value: randomData()
					}, {
						name: '江西',
						value: randomData()
					}, {
						name: '山西',
						value: randomData()
					}, {
						name: '内蒙古',
						value: randomData()
					}, {
						name: '吉林',
						value: randomData()
					}, {
						name: '福建',
						value: randomData()
					}, {
						name: '广东',
						value: randomData()
					}, {
						name: '西藏',
						value: randomData()
					}, {
						name: '四川',
						value: randomData()
					}, {
						name: '宁夏',
						value: randomData()
					}, {
						name: '香港',
						value: randomData()
					}, {
						name: '澳门',
						value: randomData()
					}]
				}, {
					name: 'iphone5',
					type: 'map',
					mapType: 'china',
					label: {
						normal: {
							show: true
						},
						emphasis: {
							show: true
						}
					},
					data: [{
						name: '北京',
						value: randomData()
					}, {
						name: '天津',
						value: randomData()
					}, {
						name: '上海',
						value: randomData()
					}, {
						name: '广东',
						value: randomData()
					}, {
						name: '台湾',
						value: randomData()
					}, {
						name: '香港',
						value: randomData()
					}, {
						name: '澳门',
						value: randomData()
					}]
				}
			]
		};



		myChart.setOption(option)


	})



</script>
</body>
</html>
