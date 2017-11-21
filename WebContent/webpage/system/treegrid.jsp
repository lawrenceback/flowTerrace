<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>菜单DEMO</title>


	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/jsTree/style.min.css" rel="stylesheet">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0"
	      rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">


	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/bootstrap/tree.css">

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

					<table id="test" class="easyui-treegrid" style="width:100%;"
					       data-options="
							url: '/plug-in/jquery-easyui-1.5.2/demo/treegrid/treegrid_data11.json',
							method: 'get',
							rownumbers: true,
							idField: 'id',
							lines: true,
							checkbox: true,
							treeField: 'name',
							loadFilter: myLoadFilter
						">
						<thead>
						<tr>
							<th field="name" style="width: 20%;">标题</th>
							<th field="adds" style="width: 30%;">地址</th>
							<th field="icon" style="width: 20%;">图标</th>
							<th field="sort" style="width: 10%;">排序</th>
							<th field="opt">操作</th>
						</tr>
						</thead>
					</table>


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


<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>

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


	function ddss(dom){
		alert(dom)
	}


	function myLoadFilter(data,parentId){



		function setData(){
			var todo = [];
			for(var i=0; i<data.length; i++){
				todo.push(data[i]);
			}
			while(todo.length){
				var node = todo.shift();
				if (node.children){
					node.state = 'closed';
					node.children1 = node.children;
					node.children = undefined;
					todo = todo.concat(node.children1);
				}
			}
		}


		setData(data);
		var tg = $(this);
		var opts = tg.treegrid('options');
		opts.onBeforeExpand = function(row){
			if (row.children1){
				tg.treegrid('append',{
					parent: row[opts.idField],
					data: row.children1
				});
				row.children1 = undefined;
				tg.treegrid('expand', row[opts.idField]);
			}
			return row.children1 == undefined;
		};
		return data;
	}


	$(document).ready(function () {

		$('#test').treegrid({
			'onClickRow': function(index, row){

			}
		})

	});
</script>
</body>
</html>

