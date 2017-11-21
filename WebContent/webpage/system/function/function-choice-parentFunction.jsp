<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/6/28
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>选择父菜单</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dz">


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/default/easyui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/bootstrap/tree.css">


</head>
<body class="gray-bg">
	<div class="ibox float-e-margins animated fadeInUp">
		<div class="ibox-title">
			<h5>
				选择父菜单
			</h5>
			<div class="ibox-tools">
				<a class="collapse-link" onclick="location.reload()">
					<i class="fa fa-refresh"></i>
				</a>
				<a class="collapse-link" onclick="window.parent.choiceParentFunctionClose()">
					<i class="fa fa-times"></i>
				</a>
			</div>
		</div>
		<div class="ibox-content">


			<table id="test" class="easyui-treegrid" style="width:100%;"></table>

		</div>
	</div>
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/hplus/contabs.js"></script>
	<script src="${pageContext.request.contextPath}/config/cg.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>

	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		$(document).ready(function () {

			loadFunction();

		});

		function loadFunction() {

			$('#test').treegrid({
				url: cg_vte.service + '?f=920001',
				idField:'id',
				treeField:'function_name',
				lines: true,
				columns:[[
					{title:'名称',field:'function_name',style:"width:70%"},
					{
						title:'style',
						field: 'icon_class',
						style:"width:30%",
						formatter: function(icon_class){
							if (icon_class) {
								return "<i class='{iconClass}'></i> ".format({iconClass: icon_class});
							}

						}
					}
				]],
				loadFilter: function(data){
					if (data.d){
						return data.d;
					} else {
						return data;
					}
				},
				onLoadSuccess: function (row, data) {
					expandAll();

					getChildren(data)
				},
				onClickRow: function (row) {

					window.parent.choiceParentFunctionCallback(row.id,row.function_name)

				}
			});

		}

		function expandAll() {
			var node = $('#test').treegrid('getSelected');

			if (node){
				$('#test').treegrid('expandAll', node.target);
			} else {
				$('#test').treegrid('expandAll');
			}
		}
		
		function getChildren(data) {

			console.log(data);

			$('#test').treegrid('select', "${function.id}");
		}
		

	</script>
</body>
</html>
