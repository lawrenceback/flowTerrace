<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/ffire-tags" prefix="p" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户管理</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-datetimepicker/3.1.4/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/datapicker/datepicker3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?dsds">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?' + new Date().getTime() + '">');</script>

	<style>
		.i-checks{
			margin: 0;
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
			pac: '${LOCAL_CLINET_USER.pac }',
			accountType: '',
			competence: '',
			name: '',
			sort: '',
			order: 'asc',
			date_sdf: 0,
			type: null,
			fetchType: 2
		};
	</script>

</head>

<body class="gray-bg">

<div class="  wrapper wrapper-content  animated fadeInRight">

	<div class="row ">

		<div class="col-sm-12">


			<div class="ibox">

				<div class="ibox-title">
					<h5></h5>
					<div class="ibox-tools">
						<a onclick="add()" class="text-navy"><i class="fa fa-plus"></i> 添加用户</a>

						<a id="dLabel" data-toggle="dropdown" aria-expanded="true"> <i class="glyphicon glyphicon-list"></i></a>

						<ul class="dropdown-menu bg-warning" aria-labelledby="dLabel" id="cols">
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="row"><i></i>行号</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="account"><i></i>帐号</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="password"><i></i>密码</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="headPortrait"><i></i>头像</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="name"><i></i>用户名</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="phone"><i></i>用户电话</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="longitude"><i></i>经度</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="latitude"><i></i>纬度</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="competence"><i></i>用户类型</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="online"><i></i>在线否</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="imei"><i></i>设备ID</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="deviceType"><i></i>类型</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="deviceId"><i></i>唯一码</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="deviceName"><i></i>设备名</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="lastTime"><i></i>最后一次登陆时间</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="voipAccount"><i></i>voipAccount</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="voipPassword"><i></i>voipPassword</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="accountType"><i></i>accountType</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="pac"><i></i>pac</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="jid"><i></i>jid</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="token"><i></i>token</label></div></li>
							<li><div class="checkbox i-checks"><label><input type="checkbox" property="subscriber"><i></i>subscriber</label></div></li>
						</ul>

					</div>
				</div>

				<div class="ibox-content">


					<table class="table table-bordered table-striped table-hover" id="hot_list">
						<thead>
						<tr>
							<th data-property="row">#</th>
							<th class="sort" data-property="account">帐号<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="password">密码<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="headPortrait">头像<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="name">用户名<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="phone">用户电话<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="longitude">经度<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="latitude">纬度<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="competence">用户类型<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="online">在线否<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="imei">设备ID<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="deviceType">类型<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="deviceId">唯一码<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="deviceName">设备名<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="lastTime">最后一次登陆时间<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="voipAccount">voipAccount<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="voipPassword">voipPassword<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="accountType">账号类型<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="pac">账户行政区码<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="jid">jid<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="token">token<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="subscriber">subscriber<i class="iconfont icon-paixu"></i></th>
							<th class="text-center">操作</th>
						</tr>
						</thead>
					</table>


					<div class="paging row">
						<div class="col-md-12">
							<div class="pull-left" style="min-width: 400px">
								<spaan>
									<select class="pagsele" onchange="selectChangeRows(this)">
										<option value="30">30</option>
										<option value="50">50</option>
										<option value="100">100</option>
										<option value="300">300</option>
									</select>
								</spaan>
								<span><i onclick="page_first()" class="fa fa-step-backward" aria-hidden="true"></i></span>
								<span><i onclick="page_add()" class="fa fa-caret-left"  aria-hidden="true"></i></span>
								<span>当前页 <input type="text" value="1" id="page" onchange="pageChange(this)" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="5" class="pageinput"> </span>
								<span><i onclick="page_next()" class="fa fa-caret-right" aria-hidden="true"></i></span>
								<span><i onclick="page_last()" class="fa fa-step-forward" aria-hidden="true"></i></span>
								<span><i onclick="initDataGrid()" class="fa fa-rotate-left" aria-hidden="true"></i></span>
							</div>

							<span class="pull-right">总页数：<span id="total_pages"></span>， 总记录数：<span id="total"></span></span>
						</div>
					</div>


				</div>


			</div>


		</div>
	</div>
</div>


<div id="temp_hot_list">
	<!-- <tr>
		<td data-property="row">{row}</td>
		<td>{account}</td>
		<td>{password}</td>
		<td>{headPortrait}</td>
		<td>{name}</td>
		<td>{phone}</td>
		<td>{longitude}</td>
		<td>{latitude}</td>
		<td>{competence}</td>
		<td>{online}</td>
		<td>{imei}</td>
		<td>{deviceType}</td>
		<td>{deviceId}</td>
		<td>{deviceName}</td>
		<td>{lastTime}</td>
		<td>{voipAccount}</td>
		<td>{voipPassword}</td>
		<td>{accountType}</td>
		<td>{pac}</td>
		<td>{jid}</td>
		<td>{token}</td>
		<td>{subscriber}</td>
		<td class="text-center">{opt}</td>
	</tr>-->
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/iCheck/icheck.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/datapicker/bootstrap-datepicker.js"></script>


<script>

	$(document).ready(function () {

		temp.temp_hot_list = toComment($('#temp_hot_list').html());
		$dom.hot_list = $('#hot_list');
		$dom.city = $('#city');
		$dom.county = $('#county');
		$dom.page = $('#page');
		$dom.total = $('#total');
		$dom.total_pages = $('#total_pages');

		var data_tr = window.sessionStorage.getItem('data_tr');
		data_tr = JSON.parse(data_tr);


		this_data.departId = data_tr.id;


		$(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})

		//节点树加载完成后加载所有该地区的用户
		initDataGrid();

		orderDataGrid();

	});



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


			if ($i.hasClass("icon-paixu") || $i.hasClass("icon-paixuxia")) {
				this_data.order = 'desc';
				$i.get(0).className = 'iconfont icon-paixushang';

				initDataGrid();
				return;
			}

			if ($i.hasClass("icon-paixushang")) {
				this_data.order = 'asc';
				$i.get(0).className = 'iconfont icon-paixuxia';
			}
			initDataGrid()


		})
	}

	//加载表格数据
	function initDataGrid() {


		var index = layer.load(2, {
			shade: [0.6,'#000'] //0.1透明度的白色背景
		});

		var param = {
			f: 940004,
			page: this_data.page,
			rows: this_data.rows,
			sort: this_data.sort,
			order: this_data.order,
			date_sdf: this_data.date_sdf,
			pac: this_data.pac,
			departId: this_data.departId,
			fetchType: this_data.fetchType,
		}

		if ( this_data.type){
			param.type = this_data.type;
		}
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: param,
			success: function (data) {
				data = JSON.parse(data);

				layer.close(index);

				console.log(data)

				this_data.totalPages = Math.ceil(data.total / this_data.rows);
				this_data.total = data.total;
				$dom.total.text(this_data.total)
				$dom.page.val(this_data.page)
				$dom.total_pages.html(this_data.totalPages)
				this_data.serialNumber = (this_data.page-1)*this_data.rows;

				$dom.hot_list.find('tbody').remove();
				data = data.rows;
				var data_length = data.length;

				this_data.last_rows = data_length;

				var $tbody = $('<tbody>');
				for (var i = 0; i < data_length; i++) {
					if (data.hasOwnProperty(i)) {
						var sub = temp.temp_hot_list.format({
							row: this_data.serialNumber + 1,
							account: data[i].account || '',
							password: data[i].password || '',
							headPortrait: data[i].headPortrait || '',
							name: data[i].name || '',
							phone: data[i].phone,
							latitude: data[i].latitude || '',
							longitude: data[i].longitude || '',
							competence: data[i].competence || '',
							online: data[i].online || '',
							imei: data[i].imei || '',
							deviceType: data[i].deviceType || '',
							deviceId: data[i].deviceId || '',
							deviceName: data[i].deviceName || '',
							lastTime: data[i].lastTime || '',
							voipAccount: data[i].voipAccount || '',
							voipPassword: data[i].voipPassword || '',
							accountType: data[i].accountType || '',
							pac: data[i].pac || '',
							jid: data[i].jid || '',
							token: data[i].token || '',
							subscriber: data[i].subscriber || '',
							opt: function () {
								var btn = '';
								btn += '  <a onclick="edit(this)" class="text-primary" title="修改"><i class="iconfont icon-xiugai"></i></a>  ';
								btn += ' <a onclick="updatePassword(this)" class="text-warning" title="修改密码"><i class="iconfont icon-xiugaimima" ></i></a> ';
								btn += ' <a onclick="del(this)" class="text-danger" title="删除"><i class="iconfont icon-ttpodicon"> </i></a>  ';
								return btn.format({id: data[i].id});
							}
						});

						$tbody.append($(sub).data("data_tr", data[i]));
						this_data.serialNumber++;
					}
				}

				$dom.hot_list.append($tbody)

				bindCols()

			}
		})
	}

	//绑定显示列
	function bindCols() {

		$('#cols li input').on('ifChanged', function(event){

			var $this = $(this);
			var property_name = $this.attr('property');
			var index = $dom.hot_list.find('thead th[data-property="'+property_name+'"]').index();
			//ifCreated 事件应该在插件初始化之前绑定
			if ($this.is(':checked')){
				//选中
				$dom.hot_list.find('tbody tr').find( 'td:eq('+index+')').show()
				$dom.hot_list.find('thead th[data-property="'+property_name+'"]').show();
				return;
			}
			$dom.hot_list.find('tbody tr').find('td:eq('+index + ')').hide()
			$dom.hot_list.find('thead th[data-property="'+property_name+'"]').hide();
		});

		$('#cols li input').each(function(){
			var $this = $(this);

			var property_name = $this.attr('property');
			var index = $dom.hot_list.find('thead th[data-property="'+property_name+'"]').index();
			if ($this.is(':checked')){
				//选中
				$dom.hot_list.find('tbody tr').find( 'td:eq('+index+')').show()
				$dom.hot_list.find('thead th[data-property="'+property_name+'"]').show();
				return;
			}
			$dom.hot_list.find('tbody tr').find('td:eq('+index + ')').hide()
			$dom.hot_list.find('thead th[data-property="'+property_name+'"]').hide();
		})
	}


	//------------------------------ 添加 Start ----------------------------------//
	function add() {
		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['80%', '80%'],

			shadeClose: true,
			content: cg_vte.path + 'webpage/system/districts/districts_user_add.jsp',
			fix: false,
			scrollbar: false,
		});
	}
	function edit(item) {

		var tr_data = $(item).parents('tr').data('data_tr');
		//H5本地存储
		window.sessionStorage.setItem('tr_data_districts_user', JSON.stringify(tr_data));

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['80%', '100%'],
			offset: 'r',
			shadeClose: true,
			content: cg_vte.path + 'webpage/system/districts/districts_user_edit.jsp',
			fix: false,
			scrollbar: false,
		});
	}
	function addOrUpdCallback(flog) {
		layer.closeAll();
		if (!flog){
			initDataGrid();
		}

	}
	//------------------------------ 添加 End ----------------------------------//


	//---------------------------- 修改密码 Start ----------------------------------//
	var updatePasswordConfig = {};
	function updatePassword(item){

		var data = $(item).parents('tr').data('data_tr');

		var url = '${pageContext.request.contextPath}/userController.do?goHref&id='+data.id+'&href=system/user/user-update-password';
		updatePasswordConfig.layer = layer.open({
			type: 2,
			title: '修改密码',
			closeBtn: 1,
			maxmin: false, //开启最大化最小化按钮
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

	function del(item) {

		var data = $(item).parents('tr').data('data_tr');

		swal({
			title: "提示",
			text: "你确定要删除吗，这一步将无法恢复！\n回车“确定”",
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
					f: 223001,
					id: data.id
				},
				success: function (data) {
					data = JSON.parse(data);
					callbackDel(data.status, data.msg, item);
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
						initDataGrid()
					});

				}, 300);
				break;
			default:
				layer.msg(msg);
				break;
		}
	}


</script>

</body>
</html>


