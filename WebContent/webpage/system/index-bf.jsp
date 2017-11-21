<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>首页</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-category-select/css/style.css"/>
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0"
	      rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-combo-select/css/combo.select.css">

	<link rel="stylesheet"
	      href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css">


	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<script src="${pageContext.request.contextPath}/config/cg.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12 animated fadeInRight">
			<div class="row">
				<div class="col-sm-12">


					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>森林防火系统</h5>
						</div>
						<div class="ibox-content">
							<ol>
								<li><a onclick="this.href = cg_vte.service_href + cg_vte.pagePath.userManage">用户管理</a></li>
								<li><a onclick="this.href = cg_vte.service_href + cg_vte.pagePath.imeiList">IMEI管理</a></li>
							</ol>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>




<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/hplus/contabs.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>

</body>
</html>

