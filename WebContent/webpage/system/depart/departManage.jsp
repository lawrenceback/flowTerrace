<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/3
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/ffire-tags" prefix="my"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>菜单DEMO</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dz">


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/default/easyui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/bootstrap/tree.css">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css">
	<style>
		.datagrid-btable tr{height: 35px;}
		.datagrid-btable tr.treegrid-tr-tree{height: auto;}

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
		.datagrid-header td, .datagrid-body td, .datagrid-footer td{
			/*text-align: center;*/
		}
		.datagrid-body td:nth-child(1){
			text-align: left;
		}

		.iconfont{
			font-size:16px;
			position: relative;
			left:2px;
		}

	</style>
	<script>

		//在这里定义一些全局的变量
		var $dom = {};
		var this_data = {};

	</script>
</head>
<body class="gray-bg">


<div class="wrapper wrapper-content  animated fadeInUp">


	<div class="row">
		<div class="col-sm-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title ibox-title1">
				<h5>部门管理</h5>
				<div class="ibox-tools  ibox-tools-one">

					<my:permissions code="depart_add" name="添加">
						<a class="close-link" onclick="addOrUpdateDepart('')">
							<i class="fa fa-plus"></i> 添加
						</a>
					</my:permissions>
					<%--<a onclick="userManage()">	部门用户	</a>--%>
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

		loadDepart();

	});

	function loadDepart() {

		$('#test').treegrid({
			url: cg_vte.service + '?f=940001',
			idField:'id',
			treeField:'depart_name',
			cascadeCheck: false,
			lines: true,
			columns:[[
				{title:'名称',field:'depart_name',width:'30%'},
				{title:'部门编号',field:'org_code',width:'30%',
					styler: function(){
					return "text-align: center;"
				},
				},
				{
					title: '操作',
					field: 'id',
					width:'41.8%',
					styler: function(){
						return "text-align: center;"
					},
					formatter: function (id) {

						var btn = '';
						btn += '<my:permissions code="depart_edit" name="修改" ><a onclick="addOrUpdateDepart(\'{id}\')" class="linkceond"><i class="iconfont icon-xiugai" ></i></a></my:permissions>';
						btn += ' <my:permissions code="depart_delete" name="删除" ><a onclick="deleteDepartConfirm(\'{id}\')" class="linkceond"><i class="iconfont icon-ttpodicon"> </i></a></my:permissions>';
						return btn.format({
							id: id
						});
					}

				}
			]],
			onLoadSuccess: function (row, data) {

				//全部展开折叠
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

	//添加菜单
	function addOrUpdateDepart(id) {

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['80%', '80%'],
			content: cg_vte.path + 'departController.do?goHref&href=system/depart/depart-addOrUpdate&id=' + id,
			btn: ['提交','关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {

				layer.getChildFrame('#submit', index).click()
			}
		});

	}

	//删除菜单确认
	function deleteDepartConfirm(id) {
		swal({
			title: "提示",
			text: "确定要删除吗？",
			type: "warning",
			showCancelButton: true,
            cancelButtonText: "取消",
			closeOnConfirm: false,
			confirmButtonText: "确定",
			confirmButtonColor: "#F8AC59"
		}, function() {
			delDepart(id);
		});
	}

	//删除菜单
	function delDepart(id) {

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 943001,
				id: id
			},
			success: function (data) {
				data = JSON.parse(data);

				if (data.status = 10000){
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
				}
			}
		})
	}

	function completeCallback() {
		layer.closeAll();
		loadDepart();
	}

	//--------------------------------- 用户管理 Start ---------------------------------//
	function userManage() {
		//获得用户选择的行
		var data = $('#test').treegrid("getSelected");

		console.log(data)
		if(data){
			layer.closeAll();

			layer.open({
				type: 2,
				title: '',
				closeBtn: 0,
				maxmin: false, //开启最大化最小化按钮
				area: ['100%', '100%'],
				content: cg_vte.path + 'departController.do?goHref&href=system/depart/depart-users&id=' + data.id,
				btn: ['提交','关闭'],
				fix: false,
				scrollbar: false,
				yes: function (index, layero) {

					layer.getChildFrame('#submit', index).click()
				}
			});

			return;
		}
		layer.msg('请选择数据.')
	}
	//--------------------------------- 用户管理 End -----------------------------------//
</script>
</body>
</html>

