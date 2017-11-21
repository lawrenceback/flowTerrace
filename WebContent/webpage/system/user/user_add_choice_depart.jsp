<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/3
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>用户选择部门</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">


	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dz">


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/default/easyui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/bootstrap/tree.css">


</head>
<style>
	.ovelauto{
		overflow: auto;
	}
</style>
<script>
	var this_data = {}
</script>
<body class="gray-bg">
<div class="ibox float-e-margins animated fadeInUp" style="margin-bottom: 0">

	<div class="ibox-title">
		<h5>
			选择部门
			<small id="choice"></small>
		</h5>
		<div class="ibox-tools">
			<a onclick="choiceUserDeparts()">确定</a>
			<a onclick="window.parent.layer.closeAll()">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
	<div class="ibox-content" style="    height: calc(100% - 50px);  overflow: scroll; padding-bottom: 0;">


		<table id="test" class="easyui-treegrid" style="width:100%;"></table>

	</div>

</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/config/cg.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>

<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script>

	var ids = [];
	<c:forEach items="${ids}" var="id">
		ids.push("${id}");
	</c:forEach>

	$(document).ready(function () {

		loadFunction();

	});

	function loadFunction() {

		$('#test').treegrid({
			url: cg_vte.service + '?f=940001',
			idField:'id',
			treeField:'depart_name',
//			checkbox: true,
			//cascadeCheck: false,
			lines: true,
			columns:[[
//				{ field: 'oid',
//					title: '选择',
//					width: 50,
//					style: "text-align:center",
//					formatter: function(value, rowData){
//						return '<input type="radio" name="selectRadio" id="selectRadio" value="' + rowData.id + '" />';
//					}
//				},
				{title:'名称',field:'depart_name', width: '60%'},
				{title:'部门编号',field:'org_code', width: '40%'},

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

			},
			onClickRow: function(rowIndex, rowData){
				$('#choice').html('您当前选择的部门是：<code>' + rowIndex.depart_name + "</code>");
				this_data.data = rowIndex;
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


	function choiceUserDeparts() {
		if (!this_data.data){
			layer.msg('请选择部门');
			return;
		}
		$('#depart_id', window.parent.document).val(this_data.data.id);
		$('#depart', window.parent.document).html(this_data.data.depart_name).parents('div.departbox').removeClass('none');
		$('#depart_choice', window.parent.document).addClass('none');
		window.parent.layer.closeAll()
	}



</script>
</body>
</html>
