<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/28
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/ffire-tags" prefix="my" %>
<!DOCTYPE html>
<html>
<head>
	<title>角色管理</title>


	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.3.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?553">


	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?' + new Date().getTime() + '">');</script>

	<style>




		.navbar-top-links li a {
			padding: 15px 10px;
		}

		.ibox {
			margin-bottom: 0;
		}


		#page-wrapper .datagrid-btable tr {
			height: 40px;
		}

		.sort {
			cursor: pointer;
		}

		#fontawesome-icon-list td:first-child,#fontawesome-icon-list th:first-child {
			text-align: center;
			width: 60px;
		}

		.input-group[class*=col-] {
			float: left;
			margin-left: 5%;
		}


		.iconfont{
			font-size:16px;

		}


		.table-responsive {
			border: 1px solid #dcdcdc;
		}

		.table-responsive table thead {
			background: #f2f2f2;
		}
		.ibox-title1 {
			padding: 20px;
		}
		.ibox-tools a {
			cursor: pointer;
			margin-left: 5px;
			background: #1bb394;
			color:#fff;
			padding: 8px 10px;
		}

	</style>

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {};
		var temp = {};
		var this_data = {
			name: '',
			sort: 'sorting',
			order: 'desc',
			roleName: '',
			roleCode: ''
		};
	</script>

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">


	<div class="row">
		<div class="col-sm-12">
			<div class="gray-bg dashbard-1">

				<div class="ibox float-e-margins">


					<div class="ibox-title">
						<h5>角色管理</h5>
						<div class="ibox-tools ibox-tools-one">
							<my:permissions code="role_add" name="添加">
								<a onclick="addOrUpdateRole('')">
									<i class="fa fa-plus"></i>&nbsp添加角色
								</a>
							</my:permissions>
							<a onclick="roleSorting()">
								角色排序
							</a>
						</div>
					</div>


					<div class="ibox-content" style="">


						<div class="row">
							<div class="form-inline col-md-8 col-md-offset-2">

								<div class="input-group"  style="width: 90%">
									<input type="text" placeholder="角色编号" class="input form-control" id="seach_role_code">
									<span class="input-group-btn">
										<button type="button" class="btn btn btn-primary" onclick="seach_form()">
											<i class="fa fa-search"></i>
											搜索
										</button>
									</span>
								</div>

							</div>
						</div>
						<hr>


						<table class="table table-bordered table-striped table-hover" id="fontawesome-icon-list">
							<thead>
								<tr>
									<th>行号</th>
									<th class="sort" data-property="roleName">角色名称<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="roleCode">角色编号<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="sorting">序号<i class="iconfont icon-paixu"></i></th>
									<th class="text-center">操作</th>
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


<div id="temp_icon_list">
	<!-- <tr id="{id}">
		<td>{row}</td>
		<td>{roleName}</td>
		<td>{roleCode}</td>
		<td>{sorting}</td>
		<td class="text-center">{opt}</td>
	</tr>-->
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/jsTree/jstree.min.js"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script src="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>


<script>

	$(document).ready(function () {
		$dom.fontawesomeIconList = $('#fontawesome-icon-list tbody');
		temp.temp_icon_list = toComment($('#temp_icon_list').html());


		initRoles();


		orderDataGrid();

		$('#seach_role_code').bind('keypress',function(event){

			this_data.roleCode=$(this).val();
			this_data.roleName=$(this).val();

			if(event.keyCode == '13')
			{
				initRoles();
			}
		});

	});

	function seach_form() {
		var $seach_role_code = $('#seach_role_code');
		this_data.roleCode=$seach_role_code.val();
		this_data.roleName=$seach_role_code.val();

		initRoles();
	}

	function initRoles() {
		layer.load(1, {	shade: 0.6});

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 930001,
				rows: 1000,
				sort: this_data.sort,
				order: this_data.order,
				roleName: this_data.roleName,
				roleCode: this_data.roleCode
			},
			success: function (data) {
				data = JSON.parse(data);

				var rows = data.rows;

				layer.closeAll();

				var sub = '';
				for (var i in rows) {
					if (rows.hasOwnProperty(i)) {
						sub += temp.temp_icon_list.format({
							roleName: rows[i].roleName,
							roleCode: rows[i].roleCode,
							sorting: rows[i].sorting,
							id: rows[i].id,
							row: Number(i) + 1,
							opt: function () {
								var btn = '';
								var row = rows[i];
								btn += '<my:permissions code="role_edit" name="修改"> <a onclick="addOrUpdateRole(\'{id}\',this)" class="linka"><i class="iconfont icon-xiugai"></i>修改</a> </my:permissions>'.format({id: row.id});
								btn += ' <my:permissions code="role_permissions" name="权限"> <a onclick="setRoleFunction(\'{id}\',this)" class="linka linka1"><i class="iconfont icon-quanxian3"></i>权限</a> </my:permissions>'.format({id: row.id});
								btn += ' <my:permissions code="role_delete" name="删除"> <a onclick="delRole(\'{id}\',this)" class="linka linka2"><i class="iconfont icon-ttpodicon" ></i>删除</a> </my:permissions>'.format({id: row.id});
								return btn;
							}
						})
					}
				}

				$dom.fontawesomeIconList.html(sub);


			}
		})
	}

	function orderDataGrid() {
		$('table thead th.sort').unbind('click').bind('click', function (event) {
			var $this = $(this);
			$this.siblings().find("i.iconfont").attr("class", "iconfont icon-paixu");
			this_data.sort = $this.attr('data-property');

			var $i = $this.find('i');


			if ($i.hasClass("icon-paixu") || $i.hasClass("icon-paixuxia")) {
				this_data.order = 'desc';
				$i.get(0).className = 'iconfont icon-paixushang';

				initRoles();
				return;
			}

			if ($i.hasClass("icon-paixushang")) {
				this_data.order = 'asc';
				$i.get(0).className = 'iconfont icon-paixuxia';
			}
			initRoles()


		})
	}

	//添加
	function addOrUpdateRole(id) {

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['80%', '80%'],
			content: cg_vte.path + 'roleController.do?goHref&href=system/role/role-addOrUpdate&id=' + id,
			btn: ['提交', '关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {

				layer.getChildFrame('#submit', index).click();

			},
			btn2: function (layero, index) {

				initRoles()

			}
		});

	}

	//删除图标
	function delRole(id, item) {

		swal({
			title: "提示",
			text: "你确定要删除它吗？",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#F8AC59",
			confirmButtonText: "确定",
			cancelButtonText: "取消",
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
				success: function (data) {
					data = JSON.parse(data);
					callbackDel(data.status, data.msg, item);
				},
				error: function (res) {
					layer.msg(res);
				}
			})


		});

	}

	function callbackDel(state, msg, item) {

		switch (state) {
			case 10000:
				setTimeout(function () {

					swal({
						title: "提示",
						text: '成功',
						type: "success",
						showCancelButton: false,
						confirmButtonText: "确定",
						closeOnConfirm: true
					}, function () {
						$(item).parents("tr").remove();
					});

				}, 300);
				break;
			case 10008:
				layer.msg('删除失败，该角色可能有用户、菜单正在使用中！')
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
		initRoles();
	}

	//---------------------------------- 选择菜单功能 Start ------------------------------------//
	var choiceRoleFunction = {};
	function setRoleFunction(id, dom) {

		choiceRoleFunction.layer = layer.open({
			type: 2,
			title: '',
			closeBtn: 1,

			maxmin: false, //开启最大化最小化按钮
			area: ['70%', '80%'],
			content: cg_vte.path + 'roleController.do?choiceFunction&id=' + id,
			fix: false,
			scrollbar: false,
		});

	}
	function choiceRoleFunctionCallback() {
		layer.close(choiceRoleFunction.layer);
	}
	//---------------------------------- 选择菜单功能 End --------------------------------------//

	function roleSorting() {
		layer.open({
			type: 2,
			title: '',
			closeBtn: 1,
			maxmin: false, //开启最大化最小化按钮
			area: ['70%', '80%'],
			content: cg_vte.path + 'webpage/system/role/role-sorting.jsp',
			fix: false,
			scrollbar: false,
		});
	}

</script>

</body>
</html>
