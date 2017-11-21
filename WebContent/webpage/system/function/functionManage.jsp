<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/ffire-tags" prefix="my"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>菜单管理</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dz">



	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/default/easyui.css?00">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/bootstrap/tree.css">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">


	<link href="${pageContext.request.contextPath}/plug-in/demo.css" rel="stylesheet">


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?22">

	<link href="${pageContext.request.contextPath}/plug-in/iconfont.css?35553" rel="stylesheet">

	<script src="${pageContext.request.contextPath}/plug-in/iconfont.js"> </script>


	<style>
		.datagrid-btable tr{height: 35px;}
		.datagrid-btable tr.treegrid-tr-tree{height: auto;}
		.ibox-tools-one a{
			padding: 6px 10px;
			background: #19aa8d;
			color: #fff;
		}
		.ibox-title1{
			padding: 19px 15px 7px;
			min-height: 60px;
		}
		.datagrid-header-row{
			height: 40px;
			text-align: center;
		}
		.panel-header, .panel-body{
			border-color: #19aa8d;
		}
		.datagrid-row-selected{
			background: #f4f4f4;
		}
		.datagrid-row-over, .datagrid-header td.datagrid-header-over {
			background: #fbfbfb;

		}
		.datagrid-cell-c2-id{
			border-right:0!important;
		}
		.iconfont{
			font-size:16px;
			position: relative;
			left:2px;
		}
	</style>
</head>
<body class="gray-bg">


<div class="wrapper wrapper-content  animated fadeInRight">


	<div class="row">

		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title ibox-title1">
					<h5>菜单管理</h5>
					<div class="ibox-tools ibox-tools-one">
						<my:permissions code="function_add" name="添加">
							<a class="close-link" onclick="addOrUpdateFunction('')">
								<i class="fa fa-plus"></i> 添加菜单
							</a>
						</my:permissions>

					</div>
				</div>
				<div class="ibox-content">

					<table id="test" class="easyui-treegrid" style="width:100%;"></table>


				</div>
			</div>
		</div>
	</div>
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/config/cg.js"></script>

<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script>


	//入口函数
	$(document).ready(function () {

		loadFunction();

	});

	function loadFunction() {

		$('#test').treegrid({
			url: cg_vte.service + '?f=920003',
			idField:'id',
			treeField:'function_name',
			cascadeCheck: false,
			lines: true,
			fitColumns:true,//宽度自适应
			nowrap:false,
			columns:[[
				{title:'名称',field:'function_name', width: 150},
				{title:'URL',field:'function_url', width: 300},
				{
					title:'控件',
					field:'controls',
					width: 100,
					formatter: function (controls) {
						if (controls && controls.length > 0){

							var sub = '';
							for (var i in controls){
								if (controls.hasOwnProperty(i)){
									sub += controls[i].controlName + " ";
								}
							}

							return sub;
						}
					}
				},
				{
					title:'style',
					field: 'icon_class',
					width: 200,
					formatter: function(icon_class){
						if (icon_class) {
							return "<i class='{iconClass}'></i> ".format({iconClass: icon_class}) + icon_class;
						}

					}
				},
				{
					title: '操作',
					field: 'id',
					width: 100,
					styler: function(){
						return "text-align: center;"
					},
					formatter: function (id) {

						var btn = '';
						btn += '<my:permissions code="function_edit" name="修改" ><a onclick="addOrUpdateFunction(\'{id}\')" class="linkceond" title="修改"><i class="iconfont icon-xiugai"></i></a></my:permissions>';
						btn += ' <my:permissions code="function_delete" name="删除"><a onclick="delFunctionConfirm(\'{id}\')"  class="linkceond  " title="删除"><i class="iconfont icon-ttpodicon"></i></a></my:permissions>';
						btn += ' <my:permissions code="control_manage" name="控件管理"><a onclick="controlManage(\'{id}\')" class="linkceond  " style="width:26px;" title="控件管理"><i class="iconfont icon-kongjian"  ></i></a></my:permissions>';
						return btn.format({
							id: id
						});
					}

				}
			]],
			onLoadSuccess: function (row, data) {
				expandAll();

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

	//--------------------- 控件管理 Start -------------------------//
	var controlManageConfig = {}
	function controlManage(id) {
		controlManageConfig.layer = layer.open({
			type: 2,
			title: '',
			closeBtn: 0,

			maxmin: false, //开启最大化最小化按钮
			area: ['60%', '80%'],
			content: cg_vte.path + 'functionController.do?goHref&href=system/function/function-control&id=' + id,
			scrollbar: false
		});
	}
	//--------------------- 控件管理 End -------------------------//

	//添加菜单
	function addOrUpdateFunction(id) {

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			content: cg_vte.path + 'functionController.do?goHref&href=system/function/function-addOrUpdate&id=' + id,
			btn: ['提交','关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {

				layer.getChildFrame('#submit', index).click()
			}
		});

	}

	//删除菜单确认
	function delFunctionConfirm(id) {
		swal({
			title: "提示",
			text: "确定要删除吗？",
			type: "warning",
			showCancelButton: true,
			closeOnConfirm: false,
			confirmButtonText: "是的",
			cancelButtonText: "稍后再说",
			confirmButtonColor: "#F8AC59"
		}, function() {
			delFunction(id);
		});
	}

	//删除菜单
	function delFunction(id, type) {

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 923002,
				id: id,
				type: type || 0
			},
			success: function (data) {
				data = JSON.parse(data);

				switch (data.status){
					case 10000:
						setTimeout(function () {
							swal({
								title: "提示",
								text: "成功.",
								type: "success",
								showCancelButton: false,
								closeOnConfirm: false,
								confirmButtonColor: "#1AB394"
							}, function() {
								window.location.reload();
							});
						},300);
						break;
					case 10004:
						//当有级联数据时
						setTimeout(function(){
							swal({
									title: "警告",
									text: "该菜单正在使用中，是否真实删除。",
									type: "warning",
									showCancelButton: true,
									confirmButtonColor: "#DD6B55",
									confirmButtonText: "立即删除",
									cancelButtonText: "稍后再说",
									closeOnConfirm: false,
									closeOnCancel: false
								},
								function(isConfirm){
									if (isConfirm) {
										delFunction(id, 1)
									}
								});
						},300)
						break;
					default:
						layer.msg(data.msg);
						break;
				}

			}
		})
	}
	
	function completeCallback() {
		layer.closeAll();
		loadFunction();
	}
</script>
</body>
</html>

