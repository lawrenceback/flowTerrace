<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>行政区-管理</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/jsTree/style.min.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/iCheck/custom.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?d222d">
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
			width: 100%;
			margin-left: 0;
			overflow: auto;
		}

		.navbar-static-side {
			background: #f9f9f9;
		}


		.jstree-default .jstree-node{
			position: relative;
		}
		#right_menu_pac{
			position: absolute;
			z-index: 999999;
			background: #fff;
			display: none;
			width: 200px;
			box-shadow: 0 0 5px #ccc;
		}
		#right_menu_pac .list-group{
			margin: 0;
		}
		#right_menu_pac .list-group-item{
			text-align: left;
		}
		body.canvas-menu .navbar-static-side, body.fixed-sidebar .navbar-static-side{
			width: auto;
		}
		.checkbox, .radio{
			margin-top: 0;
			margin-bottom: 0;
		}
		.mr5{
			margin-right:5px;
		}
		.glyphicon{
			display: inline;
		}

		.jstree-default .jstree-clicked {
			background: #999999;
			border-radius: 2px;
			padding: 0 5px;
			color: white;
			box-shadow: inset 0 0 1px #999;
		}
	</style>

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {};
		var temp = {};
		var this_data = {
			serialNumber: 0,
			totalPages: 1,
			total: 0,
			page: 1,
			rows: 30,
			sort: '',
			order: 'asc',
			date_sdf: 9,
			pac: cg_vte.initPac + '0000',
			fetchType: 0,
			pac_text: ''
		};
	</script>

</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">

<div id="wrapper" class="height_per_100">
	<nav id="navbar-static-side" class="navbar-default navbar-static-side" role="navigation">
		<div class="nav-close"><i class="fa fa-times-circle"></i></div>
		<div class="slimScrollDiv" style="position: relative; width: auto; height: 100%;">
			<div class="sidebar-collapse" style="padding: 20px;width: auto; height: calc(100% - 10px);overflow: auto;border-right: 1px solid #ccc;">
				<div id="pac_json"></div>
			</div>
		</div>
	</nav>

	<div id="page-wrapper">

		<div class="row height_per_100">


			<div class="ibox ibox-datagrid">

				<div class="ibox-title ibox-tools-one">
					<h5>
						<span id="pac_text">广西壮族自治区</span>
						<small>(<span id="pac_">${user.pac }</span>)</small>
					</h5>

				<div class="ibox-tools ibox-tools-one fl">
					<a onclick="addPac() " class="mr5">添加行政区</a>
					<%--<a onclick="sortPac()" class="mr5 green">排序</a>--%>
					<a onclick="editPac()" class="mr5 blue">修改</a>
					<a onclick="deletePac()" class="mr5 orange">删除</a>
				</div>

					<%--<select name="competence" onchange="this_data.fetchType = this.value;initDataGrid()" style="height: 27px;">
						<option value="0" selected>只查询当前</option>
						<option value="1">查询当前及下一级</option>
						<option value="2">查询所有</option>
					</select>--%>

					<div class="ibox-tools ibox-tools-one fr">

						<a onclick="add()" class=""> 添加部门</a>
						<a onclick="depart_sorting()">部门排序</a>
						<a id="dLabel" data-toggle="dropdown" aria-expanded="true"><i class="glyphicon glyphicon-list"></i></a>

						<ul class="dropdown-menu bg-warning" aria-labelledby="dLabel" id="cols">
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="row"><i></i>行号</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="code"><i></i>行政区代码</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="name"><i></i>行政区名称</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="depart_name"><i></i>单位名称</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="numberUsers"><i></i>用户</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="description"><i></i>描述</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="sorting"><i></i>排序</label></div></li>
						</ul>
					</div>
				</div>

				<div class="ibox-content">

					<div class="datagrid-talbe">
						<table class="table table-bordered table-hover" id="imei_list">
							<thead>
								<tr>
									<th data-property="row">序号</th>
									<th class="sort" data-property="code">行政区代码<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="name">行政区名称<i class="iconfont icon-paixu"></i></th>
									<th class="sort" data-property="depart_name">单位名称<i class="iconfont icon-paixu"></i></th>
									<th data-property="numberUsers">用户</th>
									<th data-property="description">描述</th>
									<th data-property="sorting">排序</th>
									<th class="text-center">操作</th>
								</tr>
							</thead>
						</table>
					</div>

					<div class="paging paging-footer">
						<div class="pull-left" style="min-width: 400px">

							<span><i onclick="page_first()" class="fa fa-step-backward" aria-hidden="true"></i></span>
							<span><i onclick="page_add()" class="fa fa-caret-left"  aria-hidden="true"></i></span>
							<span><input type="text" value="1" id="page" onchange="pageChange(this)" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="5" class="pageinput"> </span>
							<span><i onclick="page_next()" class="fa fa-caret-right" aria-hidden="true"></i></span>
							<span><i onclick="page_last()" class="fa fa-step-forward" aria-hidden="true"></i></span>
							<span><i onclick="initDataGrid()" class="fa fa-rotate-left" aria-hidden="true"></i></span>
							<spaan>
								<select class="pagsele" onchange="selectChangeRows(this)">
									<option value="30">30</option>
									<option value="50">50</option>
									<option value="100">100</option>
									<option value="300">300</option>
								</select>
							</spaan>
						</div>

						<span class="pull-right">总页数：<span id="total_pages"></span>， 总记录数：<span id="total"></span></span>
					</div>

				</div>


			</div>





		</div>

	</div>

</div>




<div id="temp_imei_list">
	<!-- <tr>
		<td data-property="row">{row}</td>
		<td>{code}</td>
		<td>{name}</td>
		<td>{departName}</td>
		<td>{numberUsers}</td>
		<td>{description}</td>
		<td>{sorting}</td>
		<td class="text-center">{opt}</td>
	</tr>-->
</div>

<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/jsTree/jstree.min.js"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/iCheck/icheck.min.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script src="https://cdn.bootcss.com/jquery.pin/1.0.1/jquery.pin.min.js"></script>

<script>



	$(document).ready(function(){


		temp.temp_imei_list = toComment( $('#temp_imei_list').html() );
		$dom.imei_list = $('#imei_list');
		$dom.city = $('#city');
		$dom.county = $('#county');
		$dom.page = $('#page');
		$dom.total = $('#total');
		$dom.total_pages = $('#total_pages');
		$dom.pac_text = $('#pac_text');
		$dom.pac_ = $('#pac_');

		initDataGrid();

		$(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})



		$("#pac_json").jstree({
			"core": {
				animation: 0,
				"multiple": false,
				//"plugins": ["dnd"],
				themes: {
					dots: true,
					icons: false,
					stripes: false,
					dblclick_toggle: false
				},
				data: {
					"dataType": 'json',
					"attr": {"code": "标示"},
					"url": cg_vte.service,
					"data": function (node) {

						if (node.id === "#") {
							return {'id': formatPacUnderline( '${user.pac }' ), f: 210004}
						} else {
							return {"id": node.id,f: 210004};
						}

					},
					error: function (prevented) {
						console.log(prevented)
					}
				}
			}
		}).on('loaded.jstree', function(e, data){

			//默认选中根目录
			var inst = data.instance;
			var obj = inst.get_node(e.target.firstChild.firstChild.lastChild);

			inst.select_node(obj);

		}).bind('click.jstree', function(event) {
			var eventNodeName = event.target.nodeName;

			if (eventNodeName == 'INS') {

				return;
			} else if (eventNodeName == 'A') {
				var $subject = $(event.target).parent();

				if ($subject.find('ul').length > 0) {
					$dom.pac_.html( $(event.target).parents('li').attr('id') );
					$dom.pac_text.html( $(event.target).text() );
				} else {
					//选择的id值
					$dom.pac_.html( $(event.target).parents('li').attr('id') );
					$dom.pac_text.html( $($(event.target).parents('li')[0]).text() );
				}
			} else if (eventNodeName == 'I') {
				if ($(event.target).attr('role') != 'presentation'){
					$dom.pac_.html( $(event.target).parents('li').attr('id') );
					$dom.pac_text.html( $($(event.target).parents('a')[0]).text() );
				}

			}
		});

		$('#pac_json').on("changed.jstree", function (e, data) {

			//防止插件报错，逐步判断
			if (data && data.node && data.node.id) {


				this_data.pac = data.node.id;
				this_data.pac_text = data.node.text;

			}

			initDataGrid();

		});

		orderDataGrid();


		//加载用户行政区
		findArea('${user.pac }');
	});
	
	
	function findArea(code) {
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 210005,
				code: code
			},
			success: function (data) {
				data = JSON.parse(data);
				console.log(data);
				var obj = data.obj;
				switch(data.status){
					case 10000:
						$dom.pac_text.html( obj.name );
						break;
				}
			}
		})
	}


	//上一页
	function page_add(){

		if (this_data.page <= 1){
			return;
		}
		this_data.page--;
		initDataGrid();
	}
	//下一页
	function page_next() {
		if (this_data.page >= this_data.totalPages){
			return;
		}
		this_data.page++;
		initDataGrid();
	}
	//最后一页
	function page_last() {
		this_data.page = this_data.totalPages;
		initDataGrid();
	}
	//第一页
	function page_first() {
		this_data.serialNumber = 0;
		this_data.page = 1;
		initDataGrid();
	}
	//手动更新页数
	function pageChange(item) {
		if ( item.value > this_data.totalPages ){
			item.value = this_data.totalPages;
		}
		if ( item.value < 1 ){
			item.value = 1;
		}

		this_data.page = item.value;
		initDataGrid()
	}
	//更新每页显示记录数
	function selectChangeRows(item) {
		this_data.rows = item.value;
		this_data.page = 1;
		initDataGrid();
	}

	function orderDataGrid() {
		$('table thead th.sort').unbind('click').bind('click', function (event) {
			var $this = $(this);
			$this.siblings().find("i.iconfont").attr("class", "iconfont icon-paixu");
			this_data.sort = $this.attr('data-property');

			var $i = $this.find('i');


			if( $i.hasClass("icon-paixu") || $i.hasClass("icon-paixuxia") ) {
				this_data.order = 'desc';
				$i.get(0).className = 'iconfont icon-paixushang';

				initDataGrid()
				return;
			}

			if( $i.hasClass("icon-paixushang")) {
				this_data.order = 'asc';
				$i.get(0).className = 'iconfont icon-paixuxia';
			}
			initDataGrid()



		})
	}

	function initDataGrid() {

		var index = layer.load(2, {shade: 0.6});

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 230001,
				page: this_data.page,
				rows: this_data.rows,
				sort: this_data.sort,
				order: this_data.order,
				pac: this_data.pac,
				fetchType: this_data.fetchType,
			},
			success: function (data) {
				data = JSON.parse(data);

				console.log(data)

				layer.close(index);

				$dom.imei_list.find('tbody').remove()


				this_data.totalPages = Math.ceil(data.total / this_data.rows);
				this_data.total = data.total;
				$dom.total.text(this_data.total)
				$dom.page.val(this_data.page)
				$dom.total_pages.html(this_data.totalPages)
				this_data.serialNumber = (this_data.page-1)*this_data.rows;

				var rows = data.rows;
				var data_length = rows.length;
				var $tbody = $('<tbody>');
				for(var i=0; i < data_length; i++){
					if(rows.hasOwnProperty(i)){
						var sub = temp.temp_imei_list.format({
							row: this_data.serialNumber+1,
							departName: rows[i].depart_name,
							description: rows[i].description || '',
							numberUsers: rows[i].numberUsers || '',
							code: rows[i].code || '',
							name: rows[i].name || '',
							sorting: rows[i].sorting || '',
							opt: function (){
								var btns = '';
//								btns += ' <a onclick="edit(this)" title="编辑"><i class="iconfont icon-xiugai"></i></a> ';
//								btns += ' <a onclick="userManage(this)"><i class="iconfont icon-yonghuguanli1"></i></a> ';
//								btns += ' <a onclick="delImei(this)"><i class="iconfont icon-ttpodicon"></i></a> ';

								btns += ' <a onclick="edit(this)" title="编辑">编辑</a> ';
//								btns += ' <a onclick="userManage(this)" title="用户管理">用户管理</a> ';
								btns += ' <a onclick="delImei(this)" title="删除">删除</a> ';

								return btns;
							}
						})
						$tbody.append( $(sub).data('tr_data', rows[i]))

						this_data.serialNumber++;
					}
				}

				$dom.imei_list.append($tbody)

				bindCols()


			}
		})
	}

	//绑定显示列
	function bindCols() {

		$('#cols li input').on('ifChanged', function(event){

			var $this = $(this);
			var property_name = $this.attr('property');
			var index = $dom.imei_list.find('thead th[data-property="'+property_name+'"]').index();
			//ifCreated 事件应该在插件初始化之前绑定
			if ($this.is(':checked')){
				//选中
				$dom.imei_list.find('tbody tr').find( 'td:eq('+index+')').show()
				$dom.imei_list.find('thead th[data-property="'+property_name+'"]').show();
				return;
			}
			$dom.imei_list.find('tbody tr').find('td:eq('+index + ')').hide()
			$dom.imei_list.find('thead th[data-property="'+property_name+'"]').hide();
		});

		$('#cols li input').each(function(){
			var $this = $(this);

			var property_name = $this.attr('property');
			var index = $dom.imei_list.find('thead th[data-property="'+property_name+'"]').index();
			if ($this.is(':checked')){
				//选中
				$dom.imei_list.find('tbody tr').find( 'td:eq('+index+')').show()
				$dom.imei_list.find('thead th[data-property="'+property_name+'"]').show();
				return;
			}
			$dom.imei_list.find('tbody tr').find('td:eq('+index + ')').hide()
			$dom.imei_list.find('thead th[data-property="'+property_name+'"]').hide();
		})
	}


	//------------------------------------- 添加 Start ----------------------------------------//
	function add(){

		window.sessionStorage.setItem('pac', this_data.pac );

		layer.open({
			type: 2,
			title: '添加',
			closeBtn: 0,
			maxmin: false,
			area: ['80%', '80%'],
			content: cg_vte.path + 'webpage/system/districts/districts_add.jsp',
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
		});

	}
	function edit(item) {
		var tr_data = $(item).parents('tr').data('tr_data');
		//H5本地存储
		console.log(tr_data)
		window.sessionStorage.setItem('data_tr', JSON.stringify(tr_data));

		layer.open({
			type: 2,
			title: '编辑：' + tr_data.depart_name,
			closeBtn: 0,
			maxmin: false,
            area: ['80%', '80%'],

			content: cg_vte.path + 'webpage/system/districts/districts_edit.jsp',
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
			shadeClose: true,
		});
	}
	function addOrUpdateCallback() {
		layer.closeAll();
		initDataGrid();
	}
	//------------------------------------- 添加 End  ----------------------------------------//


	function delImei(item){

		var tr_data = $(item).parents('tr').data('tr_data');

		swal({
			title: "提示",
			text: "你确定要删除吗，这一步将无法恢复！\n回车“确定”",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#dd6b55",
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			closeOnConfirm: true

		}, function () {

			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 233001,
					id: tr_data.area_id
				},
				success: function(data){
					data = JSON.parse(data);
					callbackDel( data.status, data.msg, item);
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
				layer.msg('删除失败，可能该单位还有用户。')
				break;
			default:
				layer.msg(msg);
				break;
		}
	}

	//---------------------------------- 用户管理 Start -----------------------------------------//
	function userManage(item) {
		var tr_data = $(item).parents('tr').data('tr_data');
		//H5本地存储
		console.log(tr_data)
		window.sessionStorage.setItem('data_tr', JSON.stringify(tr_data));
		window.sessionStorage.setItem('pac', this_data.pac);

		layer.open({
			type: 2,
			title: '行政单位 - 用户管理：'+ tr_data.depart_name,


			closeBtn: 0,
			maxmin: false,
			area: ['80%', '80%'],

			content: cg_vte.path + 'webpage/system/districts/districts_user_manage.jsp',
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
		});
	}
	//---------------------------------- 用户管理 End -----------------------------------------//

	//---------------------------------- 行政区 Start -------------------------------------//
	function addPac() {

		window.sessionStorage.setItem('pac', this_data.pac);

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false,
			area: ['80%', '80%'],
			content: cg_vte.path + 'webpage/system/districts/districts_pac_add.jsp',
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
			shadeClose: true,
		});
	}
	function editPac() {
		window.sessionStorage.setItem('pac', this_data.pac);
		window.sessionStorage.setItem('pac_text', this_data.pac_text);

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false,
			area: ['80%', '80%'],
			content: cg_vte.path + 'webpage/system/districts/districts_pac_edit.jsp',
			btn: ['关闭'],

			fix: false,
			scrollbar: false,
			shadeClose: true,
		});

	}


	function deletePac() {
		swal({
			title: "提示",
			text: "你确定要删除吗，这一步将无法恢复！\n回车“确定”",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#dd6b55",
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			closeOnConfirm: true

		}, function () {

			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 213001,
					code: this_data.pac
				},
				success: function(data){
					data = JSON.parse(data);

					switch (data.status){
						case 10000:
							setTimeout(function(){

								swal({
									title: "提示",
									text: "成功",
									type: "success",
									showCancelButton: false,
									confirmButtonText: "确定",
									closeOnConfirm: true
								}, function () {
									location.reload();
								});

							}, 300);
							break;
						default:
							layer.msg(data.msg);
							break;
					}
				}
			})


		});
	}
	function sortPac() {

		window.sessionStorage.setItem('pac', $('#' + this_data.pac).parents("li").attr('id'));
		window.sessionStorage.setItem('pac_text', this_data.pac_text);

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false,
			area: ['40%', '100%'],
			offset: 'r',
			content: cg_vte.path + 'webpage/system/districts/districts_pac_sorting.jsp',
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
			shadeClose: true,
		});

	}
	//---------------------------------- 行政区 End ---------------------------------------//

	//部门排序
	function depart_sorting(){

		window.sessionStorage.setItem('pac', this_data.pac);
		window.sessionStorage.setItem('pac_text', this_data.pac_text);

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false,
			area: ['40%', '100%'],
			offset: 'r',
			content: cg_vte.path + 'webpage/system/districts/districts_depart_sorting.jsp',
			btn: ['确定', '关闭'],
			yes: function(){
				initDataGrid();
				layer.closeAll();
			},
			fix: false,
			scrollbar: false,
			shadeClose: true,
		});
	}
</script>
</body>
</html>

