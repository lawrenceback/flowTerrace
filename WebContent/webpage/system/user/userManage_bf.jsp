<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/ffire-tags" prefix="p" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户管理</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/jsTree/style.min.css">


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/iCheck/custom.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-xenon/assets/js/icheck/skins/all.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/default/easyui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/bootstrap/tree.css">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<script src="https://cdn.staticfile.org/jquery/2.1.4/jquery.min.js"></script>
	<style>
		#wrapper {
			display: flex;
		}

		#page-wrapper {
			width: 100%;
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

		.ibox {
			margin-bottom: 0;
		}

		.layui-layer-btn {
			border-top: 1px solid #ddd;
		}
		.margin-10 {
			margin-right: 10px;
		}
		.btn-success,.btn-success:hover,.btn-success.active {
			background-color: #1ab394;

			color: #FFFFFF;
			border: 0;

			border-radius: 0;
		}
		.datagrid-btable tr{height: 35px;}
		.datagrid-btable tr.treegrid-tr-tree{height: auto;}
		.ibox-tools-one a{

			background: #19aa8d;
			color: #fff;
		}
		.ibox-title1{
			padding: 19px 15px 7px;
			min-height: 60px;
			min-height: 60px;
		}
		.datagrid-btable  {
			height: 35px;
			text-align: center;
		}
		.datagrid-header-row{
			height: 40px;
			text-align: center;
		}
		.panel-header, .panel-body{
			border-color: #19aa8d;
		}
		.datagrid-btable .iconfont {
			font-size:17px;
			color:#19aa8d;
		}
		.datagrid-row-selected{
			background: #f4f4f4;
		}
		.icon-jiaoseguanli1,.icon-bmgl{
			color:#19aa8d;
		}
		.datagrid-row-over, .datagrid-header td.datagrid-header-over {
			background: #fbfbfb;

		}.pagination-page-list, .pagination .pagination-num {
			    border-color: #c5c5c5;
			 padding: 6px 6px;    width: auto;
		 }
		.datagrid .datagrid-pager{
			padding:10px;
		}
		.pagination-page-list{
			padding: 4px 9px;
		}
		.pagination .pagination-num{
			padding: 5px 6px;text-align: center;
		}
		.ibox-content{
			padding: 20px;
		}
		.ibox-tools-one a {
			padding: 8px 10px;
		}
		.panel{
			border:1px solid #dcdcdc;
		}
		label{
			font-weight: normal!important;
		}
		.bff{
			color:#fff;
		}

	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {};
		var this_data = {
			pac: cg_vte.initPac,
			accountType: '',
			competence: '',
			name: '',
			fetchType: 3,   //0:只加载当前；1:加载当前及下一级;2:加载所有下一级;3:加载所有数据最大到级
		};
	</script>
</head>

<body>


<div class="gray-bg">
	<div class="  wrapper wrapper-content  animated fadeInRight">


		<div class=" dashbard-1">
			<div class="row  ">
				<div class="col-sm-12">

						<div class="ibox-title ibox-title1">
							<h5></h5>
							<div class="form-group margin-10">
								<div class="ibox-tools ibox-tools-one">
									<p:permissions code="user_add" name="添加">
										<a onclick="addUser()">
											<i class="fa fa-plus"></i>
											添加用户
										</a>
									</p:permissions>

								</div>
							</div>
						</div>
						<div class="ibox-content">
							<div class="form-inline">

								<div class="form-group margin-10 ">
									<select name="competence" id="city" onchange="_city(this);" class="form-control "></select>
								</div>
								<div class="form-group margin-10 ">
									<label for="name">请选择县&nbsp&nbsp</label>
									<select name="competence" id="county" onchange="this_data.pac = this.value; initDatagrid();" class="form-control "></select>
								</div>

								<div class="form-group margin-10">
									<label for="fetchType">抓取类型&nbsp&nbsp</label>
									<select class="form-control " id="fetchType" onchange="this_data.fetchType = this.value;initDatagrid()" >
										<option value="0">当前</option>
										<option value="1">this or next</option>
										<option value="2">全部</option>
										<option value="3" selected>最大到县</option>
									</select>
								</div>

								<div class="form-group margin-10">
									<label for="name">用户类型&nbsp&nbsp</label>
									<select class="form-control " name="competence" id="accountType" onchange="this_data.accountType = this.value" >
										<option value="">全部</option>
										<option value="1">PC</option>
										<option value="0">其他</option>
									</select>
								</div>



								<div class="form-group margin-10">
									<label for="name">&nbsp;&nbsp;&nbsp;用户名&nbsp&nbsp</label>
									<input name="name" id="name" placeholder="用户名" onchange="this_data.name = this.value" class="form-control "  >
								</div>

								<button type="button" class="btn btn-success margins-bottomnone" onclick="initDatagrid()"><i class="fa fa-search"></i>&nbsp查询</button>

								<%--<div class="form-group margin-10">
									<div class="ibox-tools ibox-tools-one">
										<p:permissions code="user_add" name="添加">
											<a onclick="rabishUser()">
												<i class="iconfont icon-ttpodicon bff"></i>
												批量删除
											</a>
										</p:permissions>

									</div>
								</div>--%>
							</div>





					</div>


				<div class="ibox-content">
					<table id="dg"  ></table>
				</div>

				</div>
			</div>

		</div>

	</div>
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
	<script>
		if ($(".modal").appendTo("body"), $("[data-toggle=popover]").popover(), $(".collapse-link").click(function () {
				var o = $(this).closest("div.ibox"), e = $(this).find("i"), i = o.find("div.ibox-content");
				i.slideToggle(200), e.toggleClass("fa-chevron-up").toggleClass("fa-chevron-down"), o.toggleClass("").toggleClass("border-bottom"), setTimeout(function () {
					o.resize(), o.find("[id^=map-]").resize()
				}, 50)
			}), $(".close-link").click(function () {
				var o = $(this).closest("div.ibox");
				o.remove()
			}), top == this) {
		}
	</script>
	<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script src="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>

	<script>



		function _city(dom) {
			this_data.pac=dom.value;


			$dom.county.unbind("change");
			initPacCounty( formatPac( dom.value ), function(){
				initDatagrid();

				$dom.county.bind("change", function(){
					if ($dom.city.val() == 450000){
						this_data.pac= '45';
					} else {
						this_data.pac=$(this).val();
					}

					initDatagrid();
				})

			} );

		}
		$(document).ready(function () {


			$dom.dg = $('#dg');
			$dom.province = $('#province');
			$dom.city = $('#city');
			$dom.county = $('#county');


			//加载地区
			initPac();

		});

		//加载城市
		function initPac(id) {
			$.ajax({
				url: "/if/serviceController/action",
				data:{
					f:210001,
					id: id || 45
				},
				dataType:'text',
				success:function (data) {

					data = JSON.parse(data);
					console.log(data)

					if (data.children){
						var children = data.children;

						$dom.city.append('<option value="'+data.id+'">'+data.text+'</option>');
						for(var i in children){
							if(children.hasOwnProperty(i)){
								$dom.city.append('<option value="'+children[i].id+'">'+children[i].text+'</option>')
							}
						}
					}

					this_data.pac= data.id;

					//节点树加载完成后加载所有该地区的用户
					initDatagrid();
				}
			})
		}

		//加载区县
		function initPacCounty(id, callback) {
			if ($dom.city.val() == 450000){
				$dom.county.empty();
				try{
					callback();
				} catch (e){

				}
				return;
			}
			$.ajax({
				url: "/if/serviceController/action",
				data:{
					f:210001,
					id: id || 45
				},
				dataType:'text',
				success:function (data) {

					data = JSON.parse(data);
					console.log(data)

					$dom.county.empty();

					if (data.children){
						var children = data.children;

						$dom.county.append('<option value="'+data.id+'">'+data.text+'</option>');

						for(var i in children){
							if(children.hasOwnProperty(i)){
								if (i==0){
									this_data.pac = children[i].id;
								}
								$dom.county.append('<option value="'+children[i].id+'">'+children[i].text+'</option>')
							}
						}

						try{
							callback();
						} catch (e){

						}
					}

				}
			})
		}

		function initDatagrid() {
			$dom.dg.datagrid({
				border: 0,
				url: cg_vte.service,
				//singleSelect: true,
				queryParams: {
					f: 220001,
					pac: this_data.pac,
					accountType: this_data.accountType,
					name: this_data.name,
					fetchType: this_data.fetchType
				},
				showFooter:true,
				pagination:true,
				rownumbers: true,
				pageNumber1: 1,
				pageSize: 100,
				pagePosition: 'bottom',
//				autoRowHeight: false,
				singleSelect: true,
				pageList: [100,300,500],
				fitColumns:true,//宽度自适应
				columns: [[

//					{field: 'ck', checkbox:true,width:'10%',class:'i-checks'},
					{field: 'account', title: '帐号', sortable: true,width: 100},
					{field: 'name', title: '用户名',sortable: true,width: 100},
					{field: 'pac', title: '行政区划', sortable: true,width: 100},
					{field: 'phone', title: '电话', sortable: true,width: 100},
					{field: 'voipAccount', title: 'voipAccount', sortable: true,width: 100},
					{field: 'voipPassword', title: 'voipPassword', sortable: true,width: 100},
					{
						field: 'accountType',
						title: '用户类型',
						sortable: true,
						width: 100,
						formatter: function (value, row, index) {
							switch (value) {
								case 1:
									return 'PC';
								default:
									return '其他用户';
							}
						}
					},
					{
						field: 'deviceType',
						title: '终端类型',
						sortable: true,
						width: 100,
						formatter: function (value, row, index) {
							switch (value) {
								case 1:
									return 'Android';
								case 2:
									return 'IOS';
								case 3:
									return 'PC';
								default:
									return '其他';
							}
						}
					},
					{
						field: '_operate', title: '操作',width: 200, formatter: function (value, row, index) {
						var operate = '';
						operate += '<p:permissions code="user_edit" name="查看"><a onclick="userInfo(\'' + row.id + '\')" title="查看"><i class="iconfont icon-xinxi-xianxingyuankuang"></i>&nbsp&nbsp</a> </p:permissions>';
						operate += '<p:permissions code="user_edit" name="修改"><a onclick="editUser(\'' + row.id + '\')" title="编辑"><i class="iconfont icon-bianjishuru-xianxing" ></i></a> </p:permissions>';
						operate += '<p:permissions code="user_edit" name="修改密码"><a onclick="updatePassword(\'' + row.id + '\')" title="修改密码"><i class="iconfont icon-xiugaimima" ></i></a> </p:permissions>';
						operate += ' <p:permissions code="user_role_manage" name="角色管理"><a onclick="setRoleUser(\'' + row.id + '\',this)" title="角色管理"><i class="iconfont icon-jiaoseguanli1"></i></a> </p:permissions>';
						operate += ' <p:permissions code="user_depart_manage" name="部门管理"> <a onclick="setUserDepart(\'' + row.id + '\',this)" title="部门管理"><i class="iconfont icon-bmgl"></i></a> </p:permissions>';
						operate += ' <p:permissions code="user_delete" name="删除"> <a onclick="delUserOpt(\'' + row.id + '\')"><i class="iconfont icon-ttpodicon" title="删除"></i></a> </p:permissions>';
						return operate;
					}
					},
				]]
			});
		}
		//---------------------------- 修改密码 Start ----------------------------------//
		var updatePasswordConfig = {};
		function updatePassword(id){
			var url = '${pageContext.request.contextPath}/userController.do?goHref&id='+id+'&href=system/user/user-update-password';
			updatePasswordConfig.layer = layer.open({
				type: 2,
				title: '修改密码',
				closeBtn: 1,
				maxmin: true, //开启最大化最小化按钮
				area: ['50%', '100%'],
				offset: 'r',
				content: url,
				fix: false,
				scrollbar: false,
			});
		}
		function updatePasswordCallback(){
			layer.close(updatePasswordConfig.layer);
		}
		//---------------------------- 修改密码 End ----------------------------------//
		function userInfo(id) {

			layer.open({
				type: 2,
				title: '查看用户',
				closeBtn: 1,
				maxmin: true, //开启最大化最小化按钮
				area: ['100%', '100%'],
				content: cg_vte.path + 'userController.do?goHref&href=system/user/userinfor&id=' + id,
				fix: false,
				scrollbar: false,
			});

		}

		function editUser(id) {

			layer.open({
				type: 2,
				title: '修改用户',
				closeBtn: 1,
				maxmin: false, //开启最大化最小化按钮
				area: ['100%', '100%'],
				content: cg_vte.path + 'userController.do?goHref&href=system/user/user-upd&id=' + id,
				btn: ['提交', '关闭'],
				fix: false,
				scrollbar: false,
				yes: function (index, layero) {

					layer.getChildFrame('#submit', index).click()
				}
			});

		}

		//用户删除操作动作确认
		function delUserOpt(id) {
			swal({
				title: "提示",
				text: "数据删除后，将无法恢复\n确定要删除吗？",
				type: "warning",
				showCancelButton: true,
				closeOnConfirm: false,
				confirmButtonText: "现在删除",
				confirmButtonColor: "#F8AC59"
			}, function () {
				delUser(id);
			});
		}

		//提交删除动作
		function delUser(id) {
			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 223001,
					id: id
				},
				success: function (data) {
					data = JSON.parse(data);

					console.log('----------- 删除用户 -------------');
					console.log(data);
					console.log('\n\n');

					if (data.status == 10000) {

						setTimeout(function () {
							swal({
								title: "提示",
								text: "删除成功",
								type: "success",
								showCancelButton: false,
								closeOnConfirm: false,
								confirmButtonText: "OK",
								confirmButtonColor: "#1AB394"
							}, function () {

								initDatagrid();
								setTimeout(function () {
									swal.close();
								}, 200);

							});

						}, 200);

					} else {

						layer.msg(data.msg);
						swal.close();
					}


				}
			})
		}

		//添加用户弹出框
		function addUser() {


			layer.open({
				type: 2,
				title: '添加用户',
				closeBtn: 1,
				maxmin: false, //开启最大化最小化按钮
				area: ['100%', '100%'],
				content: cg_vte.service_href + 'system/user/user-add&pac=' + this_data.pac,
				btn: ['提交', '关闭'],
				fix: false,
				scrollbar: false,
				yes: function (index, layero) {

					layer.getChildFrame('#submit', index).click()
				}
			});
		}

		//添加用户成功的回调
		function completeCallback() {
			layer.closeAll();
			initDatagrid();
		}


		//------------------------------ 角色管理 Start ------------------------------//
		var roleUser = {};

		function setRoleUser(id, dom) {
			roleUser.dom = dom;
			roleUser.layer = layer.open({
				type: 2,
				title: '',
				closeBtn: 0,
				offset: 'r',
				area: ['50%', '100%'],
				content: cg_vte.path + 'userController.do?choiceRole&id=' + id,
				fix: false,
				scrollbar: false,
			});
		}

		function setRoleUserCallback() {
			layer.close(roleUser.layer);
		}

		//------------------------------ 角色管理 End --------------------------------//


		//------------------------------ 部门管理 Start ------------------------------//
		var userDepart = {};

		function setUserDepart(id, dom) {
			userDepart.dom = dom;
			userDepart.layer = layer.open({
				type: 2,
				title: '',
				closeBtn: 0,
				offset: 'r',
				area: ['50%', '100%'],
				content: cg_vte.path + 'userController.do?choiceDepart&id=' + id,
				fix: false,
				scrollbar: false,
			});
		}

		function setUserDepartCallback() {
			layer.close(userDepart.layer);
		}

		function choiceDepartClose() {
			layer.close(userDepart.layer);
		}

		//------------------------------ 部门管理 End --------------------------------//

//		$('#text_box').on('ifChecked', function(event){
//			$('input').iCheck('check');
//		});
//		$('#text_box').on('ifUnchecked', function(event){
//			$('input').iCheck('uncheck');
//		});

	</script>



</div>
</body>
</html>

