<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/6/28
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>部门用户</title>


	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">


	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?' + new Date().getTime() + '">');</script>

	<style>
		#wrapper {
			display: flex;
		}

		body.fixed-sidebar .navbar-static-side, body.canvas-menu .navbar-static-side {
			position: relative;
		}

		body.full-height-layout #wrapper, body.full-height-layout #page-wrapper {
			height: 100%;
			margin-left: 0;
			overflow: auto;
		}

		.navbar-static-side {
			background: #f9f9f9;
		}

		.navbar-top-links li a {
			padding: 15px 10px;
		}
		.ibox{
			margin-bottom: 0;
		}
		.layui-layer-btn{
			border-top: 1px solid #ddd;
		}

		#page-wrapper .datagrid-btable tr{height: 40px;}
		.sort{
			cursor: pointer;
		}
		.icon-xiugai,.fa-hand-rock-o,.icon-ttpodicon{
			padding:9px 7px;
			background:#1bb696;
			color:#fff;    font-size: 14px;
		}
		.icon-xiugai:hover{
			background: #0da283;
		}
		.fa-hand-rock-o:hover{
			background:#f8bb2c;
		}
		.fa-hand-rock-o{
			background:#fec43c;
		}
		.icon-ttpodicon{
			background:#fa3336;
		}
		.icon-ttpodicon:hover{
			background:#ef2526;
		}
		.table-responsive table thead{
			background:#f2f2f2;

		}.table-responsive table{
			 text-align: center;
		 }


		.clients-list {

			border: 1px solid #e0e0e0;
		}
	</style>

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {};
		var temp = {};
		var this_data = {
			name: '',
			sort: '',
			order: 'asc',
		};
	</script>

</head>

<body class="gray-bg">

<div class="gray-bg dashbard-1">

	<div class="wrapper wrapper-content  animated fadeInUp">


		<div class="row">
	<div class="col-sm-12">
	<div class="ibox float-e-margins">


		<div class="ibox-title">
			<h5></h5>
			<div class="ibox-tools ibox-tools-one">

				<a onclick="addOrUpdateRole('')">
					<i class="fa fa-plus"></i>添加信息
				</a>
			</div>
		</div>


		<div class="ibox-content">

			<div class="clients-list">

				<div class="slimScrollDiv" style="position: relative; width: auto; height: 100%;">
					<div class="full-height-scroll" style="width: auto; height: 100%;">
						<div class="table-responsive">
							<table class="table table-striped table-hover" id="fontawesome-icon-list">
								<thead>
									<tr>
										<td style="width: 20%;">#</td>
										<td class="sort" data-property="name" style="width: 20%;">
											名称
											<i class="iconfont icon-paixu"></i>
										</td>
										<td class="sort" data-property="deviceType" style="width: 20%;">
											设备类型
											<i class="iconfont icon-paixu"></i>
										</td>
										<td style="width: 40%;">操作</td>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>

				</div>


			</div>
		</div>


	</div>
</div>
		</div>
	</div>
</div>



<div id="temp_icon_list">
	<!-- <tr id="{id}">
		<td class="row-c">{row}</td>
		<td>{name}</td>
		<td>{deviceType}</td>
		<td>{opt}</td>
	</tr>-->
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/jsTree/jstree.min.js"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/hplus/contabs.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script src="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>


<script>

	$(document).ready(function(){
		$dom.fontawesomeIconList = $('#fontawesome-icon-list tbody');
		temp.temp_icon_list = toComment( $('#temp_icon_list').html() );


		initIcon();


		orderDataGrid();

	});


	function initIcon() {
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 220001,
				rows: 1000,
				sort: this_data.sort,
				order: this_data.order,
			},
			success: function (data) {
				data = JSON.parse(data);

				var rows = data.rows;

				var sub = '';
				for (var i in rows) {
					if (rows.hasOwnProperty(i)){
						sub += temp.temp_icon_list.format({
							name: rows[i].name,
							deviceType: rows[i].deviceType,
							id: rows[i].id,
							row: Number(i) + 1,
							opt: function () {
								var btn = '';
								var row = rows[i];
								btn += '  <a onclick="addOrUpdateRole(\'{id}\',this)"><i class="iconfont icon-xiugai">&nbsp修改</i></a>  '.format({id: row.id});
								btn += '  <a onclick="setRoleFunction(\'{id}\',this)"><i class="fa fa-hand-rock-o">&nbsp权限</i></a>  '.format({id: row.id});
								btn += '  <a onclick="delRole(\'{id}\',this)"><i class="iconfont icon-ttpodicon">&nbsp删除</i></a>  '.format({id: row.id});
								return btn;
							}
						})
					}
				}

				$dom.fontawesomeIconList.html( sub );


			}
		})
	}

	function orderDataGrid() {
		$('table thead td.sort').unbind('click').bind('click', function (event) {
			var $this = $(this);
			$this.siblings().find("i.iconfont").attr("class", "iconfont icon-paixu");
			this_data.sort = $this.attr('data-property');

			var $i = $this.find('i');


			if( $i.hasClass("icon-paixu") || $i.hasClass("icon-paixuxia") ) {
				this_data.order = 'desc';
				$i.get(0).className = 'iconfont icon-paixushang';

				initIcon();
				return;
			}

			if( $i.hasClass("icon-paixushang")) {
				this_data.order = 'asc';
				$i.get(0).className = 'iconfont icon-paixuxia';
			}
			initIcon()



		})
	}
	
	//添加
	function addOrUpdateRole(id) {

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			content: cg_vte.path + 'roleController.do?goHref&href=system/role/role-addOrUpdate&id=' + id,
			btn: ['提交','关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {

				layer.getChildFrame('#submit', index).click();

			},
			btn2: function (layero, index) {

				initIcon()

			}
		});

	}

	//删除图标
	function delRole(id, item) {

		swal({
			title: "提示",
			text: "Are you sure you want to delete it?",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#F8AC59",
			confirmButtonText: "Yes",
			cancelButtonText: "Cancel",
			closeOnConfirm: true

		}, function () {

			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 933001,
					id: id
				},
				success: function(data){
					data = JSON.parse(data);
					callbackDel( data.status, data.msg, item);
				},
				error: function (res) {
					layer.msg(res);
				}
			})


		});

	}

	function callbackDel(state,msg, item){

		switch (state){
			case 10000:
				setTimeout(function(){

					swal({
						title: "提示",
						text: msg,
						type: "success",
						showCancelButton: false,
						confirmButtonText: "ok",
						closeOnConfirm: true
					}, function () {
						$(item).parents("tr").remove();
					});

				}, 300);
				break;
			default:
				layer.msg(msg);
				break;
		}
	}

	//添加角色成功的回调
	function completeCallback() {
		layer.closeAll();
		layer.msg('操作成功');
		initIcon();
	}

	//---------------------------------- 选择菜单功能 Start ------------------------------------//
	var choiceRoleFunction = {};
	function setRoleFunction(id, dom) {

		choiceRoleFunction.layer = layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			offset: 'r',
			maxmin: false, //开启最大化最小化按钮
			area: ['50%', '100%'],
			content: cg_vte.path + 'roleController.do?choiceFunction&id=' + id,
			fix: false,
			scrollbar: false,
		});

	}
	function choiceRoleFunctionCallback() {
		layer.close( choiceRoleFunction.layer );
	}
	//---------------------------------- 选择菜单功能 End --------------------------------------//

</script>

</body>
</html>
