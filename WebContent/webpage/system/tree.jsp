<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>用户列表</title>


	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/jsTree/style.min.css" rel="stylesheet">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0"
	      rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

</head>

<body class="gray-bg">


<div class="wrapper wrapper-content  animated fadeInRight">

	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">

					<strong>jsTree</strong>是一个基于jQuery的Tree控件。支持
					XML，JSON，Html三种数据源。提供创建，重命名，移动，删除，拖放节点操作。可以自己自定义创建，删除，嵌套，重命名，选择节点的规则。在这些操作上可以添加多种监听事件。
					<br>更多信息请访问： <a href="http://www.jstree.com/" target="_blank">http://www.jstree.com/</a>
				</div>
			</div>
		</div>
	</div>
	<div class="row">

		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>JSON示例</h5>
					<div class="ibox-tools">
						<a class="collapse-link">
							<i class="fa fa-chevron-up"></i>
						</a>
						<a class="close-link">
							<i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">

					<div id="using_json"></div>

				</div>
			</div>
		</div>
	</div>
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/jsTree/jstree.min.js"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/hplus/contabs.js"></script>
<script src="${pageContext.request.contextPath}/config/cg.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<style>
	.jstree-open > .jstree-anchor > .fa-folder:before {
		content: "\f07c"
	}

	.jstree-default .jstree-icon.none {
		width: 0
	}
</style>
<script>


	var json_data = {
		"core": {
			animation: 0,
			"data": ["Empty Folder", {
				"text": "Resources",
				"state": {"opened": true},
				"children": [{
					"text": "css",
					"children": [{"text": "animate.css", "icon": "none"}, {
						"text": "bootstrap.css",
						"icon": "none"
					}, {"text": "main.css", "icon": "none"}, {"text": "style.css", "icon": "none"}],
					"state": {"opened": true}
				}, {
					"text": "js",
					"children": [{"text": "bootstrap.js", "icon": "none"}, {
						"text": "hplus.min.js",
						"icon": "none"
					}, {"text": "jquery.min.js", "icon": "none"}, {
						"text": "jsTree.min.js",
						"icon": "none"
					}, {"text": "custom.min.js", "icon": "none"}],
					"state": {"opened": true}
				}, {
					"text": "html",
					"children": [{"text": "layout.html", "icon": "none"}, {
						"text": "navigation.html",
						"icon": "none"
					}, {"text": "navbar.html", "icon": "none"}, {
						"text": "footer.html",
						"icon": "none"
					}, {"text": "sidebar.html", "icon": "none"}],
					"state": {"opened": true}
				}]
			}, "Fonts", "Images", "Scripts", "Templates",]
		}
	};




	$(document).ready(function () {
		$("#jstree1").jstree({
			"core": {"check_callback": true,animation: 0},
			"plugins": ["types", "dnd"],
			"types": {
				"default": {"icon": "fa fa-folder"},
				"html": {"icon": "fa fa-file-code-o"},
				"svg": {"icon": "fa fa-file-picture-o"},
				"css": {"icon": "fa fa-file-code-o"},
				"img": {"icon": "fa fa-file-image-o"},
				"js": {"icon": "fa fa-file-text-o"}
			}
		});

		$("#using_json").jstree( {
			"core": {
				animation: 0,
				"data": ["Empty Folder", {
					"text": "Resources",
					"state": {"opened": true},
					"children": [{
						"text": "css",
						"children": [{"text": "animate.css", "icon": "none"}, {
							"text": "bootstrap.css",
							"icon": "none"
						}, {"text": "main.css", "icon": "none"}, {"text": "style.css", "icon": "none"}],
						"state": {"opened": true}
					}, {
						"text": "js",
						"children": [{"text": "bootstrap.js", "icon": "none"}, {
							"text": "hplus.min.js",
							"icon": "none"
						}, {"text": "jquery.min.js", "icon": "none"}, {
							"text": "jsTree.min.js",
							"icon": "none"
						}, {"text": "custom.min.js", "icon": "none"}],
						"state": {"opened": true}
					}, {
						"text": "html",
						"children": [{"text": "layout.html", "icon": "none"}, {
							"text": "navigation.html",
							"icon": "none"
						}, {"text": "navbar.html", "icon": "none"}, {
							"text": "footer.html",
							"icon": "none"
						}, {"text": "sidebar.html", "icon": "none"}],
						"state": {"opened": true}
					}]
				}, "Fonts", "Images", "Scripts", "Templates",]
			}
		} );
	});
</script>
</body>
</html>

