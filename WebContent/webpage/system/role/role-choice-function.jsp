<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/29
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>选择菜单</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dz">


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/default/easyui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/bootstrap/tree.css">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<style>
		label.control{
			font-weight: 200;
			margin-right: 10px;
			margin-bottom:0;
		}
		.ibox-inner{
			overflow-x: scroll;
			overflow-y: scroll;
			height:600px;
			padding:0 2%;
			box-sizing:border-box;
			margin:0 auto;
		}


		input[type=checkbox]{
			display: inline-block;
			　　vertical-align: middle!important;
			　　margin-bottom: 2px!important;
		}
	</style>
</head>
<body class="gray-bg">

<div class="ibox float-e-margins animated fadeInUp" style="margin-bottom: 0">
	<div class="ibox-title">
		<h5>
			选择功能菜单
		</h5>
		<div class="ibox-tools">

			<%--<a class="collapse-link" onclick="choiceFunction()">确定</a>--%>
			<%--<a class="collapse-link" onclick="location.reload()">--%>
				<%--<i class="fa fa-refresh"></i>--%>
			<%--</a>--%>
			<%--<a class="collapse-link" onclick="window.parent.choiceRoleFunctionCallback()">--%>
				<%--<i class="fa fa-times"></i>--%>
			<%--</a>--%>
		</div>
	</div>
	<div class="ibox-content"  >



		<div class="ibox-inner" style="height: calc(100% - 86px);overflow-x: hidden;    padding-bottom: 70px;">

			<table id="test" class="easyui-treegrid" style="width:100%;"></table>

		</div>


	</div>

	<div class="text-right" style="overflow-y: hidden;position: fixed;bottom:0px;z-index: 1000;width: 100%;background: #fff;padding:20px 0;">
		<a  onclick="choiceFunction()"  class="btn btn-warning" style="margin-right: 40px;">确定</a>

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


	<!--style="display: inline-block;vertical-align: middle!important; margin-right: 3px; margin-bottom: 5px!important;"-->
	var temp = {
		input_check : '<label class="control" data-id="{id}"><input type="checkbox"  value="{id}" {checked} onchange="changeRoleControl(this)" style="vertical-align: middle!important;margin-bottom: 5px!important;">{controlName}</label>'
	}

	$(document).ready(function () {

		loadFunction();

	});

	function loadFunction() {



		$('#test').treegrid({
			url: cg_vte.service + '?f=920002&roleId=${role.id}',
			idField:'id',
			valueField:'id',
			treeField:'function_name',
			checkbox: true,
			cascadeCheck: true,
			lines: true,
            loadMsg: '正在加载中,请稍等...',
			columns:[[
				{title:'名称',field:'function_name',width:'51%'},
				{
					title:'控件权限',
					field:'controls',
					width:'50.8%',
					formatter: function (controls) {
						if (controls && controls.length > 0){
							console.log(controls);
							var $controls = $('<div>');
							for (var i in controls){
								if (controls.hasOwnProperty(i)){
									var sub = temp.input_check.format({
										id: controls[i].id,
										controlName: controls[i].controlName,
										controlCode: controls[i].controlCode,
										checked: function(){
											if (controls[i].checked != 1){
												return "checked";
											}
											return "";
										}
									})
									$controls.append( $(sub).data('control_this', controls[i]) );
								}
							}
							return $controls.html();
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

				getChildren(row,data)
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

	//---------------------------- 设置角色控件权限 Start -----------------------------//
	function changeRoleControl(dom) {
		var $this = $(dom);

		if ($this.is(":checked")){
			removeRoleControl($this.val())
			return;
		}
		addRoleControl($this.val());
	}

	function addRoleControl(id) {
		$.ajax({
			url: cg_vte.path + 'controlController.do?addRoleControl',
			type: 'post',
			dataType: 'text',
			data: {
				'role.id': '${role.id}',
				'control.id': id
			},
			success: function (data) {
				data = JSON.parse(data);
				console.log(data);
			}
		})
	}
	function removeRoleControl(id) {
		$.ajax({
			url: cg_vte.path + 'controlController.do?removeRoleControl',
			type: 'post',
			dataType: 'text',
			data: {
				'role.id': '${role.id}',
				'control.id': id
			},
			success: function (data) {
				data = JSON.parse(data);
				console.log(data);
			}
		})
	}
	//---------------------------- 设置角色控件权限 End -----------------------------//



	function getChildren(row,data) {

		for (var i in ids){
			if (ids.hasOwnProperty(i)){

				try{
					//在这里需要进行异常的捕捉，因为在动态获得节点时将多次执行
					//有时将可能存在不存在行
					$('#test').treegrid('checkNode', ids[i]);
				} catch (e){

				}


			}

		}




	}

	var formData = {};
	var roleId= '${role.id}';
	function choiceFunction() {
		var nodes = $('#test').treegrid('getCheckedNodes');

		if (nodes.length > 0){

			formData = {};

			for(var i in nodes){
				if (nodes.hasOwnProperty(i)){
					formData["roleFunctions["+i+"].function.id"] = nodes[i].id;
					formData["roleFunctions["+i+"].role.id"] = roleId;
				}
			}

			var length_nodes = nodes.length;
			$('span.tree-checkbox.tree-checkbox2').each(function(index){
				var $this = $(this);
				formData["roleFunctions["+(length_nodes + Number(index) )+"].function.id"] = $this.parents('tr').attr("node-id");
				formData["roleFunctions["+(length_nodes + Number(index) )+"].role.id"] = roleId;
			})



		}

		formData.id= roleId;

		submitForm(formData)
	}

	function submitForm(formData) {



		$.ajax({
			url: cg_vte.path + "roleController.do?setRoleFunction",
			type: 'post',
			dataType: 'json',
			data: formData,
			success: function (data) {

				show_msg(data);
			},
			error: function (data) {

				console.log(data);
			}
		})
	}
	
	function show_msg(data) {

		switch (data.status){
			case 10000:
				swal({
					title: "提示",
					text: "成功",
					type: "success",
					showCancelButton: true,
					confirmButtonColor: "#1AB394",
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					closeOnConfirm: true
				}, function () {
					window.parent.choiceRoleFunctionCallback();
				});
				break;
			default:
				layer.msg(data.msg);
				break;
		}
	}

</script>
</body>
</html>
