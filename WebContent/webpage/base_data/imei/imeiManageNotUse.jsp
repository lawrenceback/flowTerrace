<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>许可管理-未分配</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-datetimepicker/3.1.4/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/datapicker/datepicker3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/iCheck/custom.css">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?' + new Date().getTime() + '">');</script>
	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?' + new Date().getTime() + '">');</script>

	<style>

		.ibox.ibox-datagrid .ibox-content {
			height: calc(100% - 40px);
		}
		.iconfont{
			font-size:16px;
			position: relative;
			left:2px;
		}
		.icheckbox_square-green input{
			display: none;
		}

		.form-group-link a {
			padding: 7px 10px!important;
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
			pac: '${user.pac }',
			pac_level: 0,
			accountType: '',
			competence: '',
			fetchType: 2,
			name: '',
			sort: 'createTime',
			order: 'desc',
			createTime_start: '',
			createTime_end: '',
			imei: '',
			key: '',
			deviceType: '',
			device: '',
		};

	</script>

</head>

<body class="gray-bg animated fadeInRight">

<div class="ibox ibox-datagrid">

	<div class="ibox-content height_per_100" style="height: calc(100% - 10px);">

		<div class="form-inline">
			<div class="form-group margin-10 form-group-link fr por"   >
				<a onclick="addImei()" class=" margin-10">添加许可</a>

				<a id="dLabel" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"> <i class="glyphicon glyphicon-list"></i></a>

				<ul class="dropdown-menu bg-warning" aria-labelledby="dLabel" id="cols">
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="checkbox"><i></i>选择</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="row"><i></i>序号</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="key"><i></i>key</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="imei"><i></i>imei</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="pac"><i></i>区号代码</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="status"><i></i>状态</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="deviceType"><i></i>设备类型</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="device"><i></i>设备种类</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="createTime"><i></i>开始时间</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="expTime"><i></i>截止时间</label></div></li>
				</ul>

			</div>

			<div class=" form-group">
				<select id="province" class="margin-10 form-control"  ></select>
				<select id="city" class="margin-10 form-control" onchange="_city(this);"></select>
				<select id="county" class="margin-10 form-control" onchange="this_data.pac = this.value; initImeiList();" ></select>
			</div>

			<div class="form-group margin-10">
				<label for="fetchType">筛选范围：</label>
				<select class="form-control" id="fetchType" onchange="this_data.fetchType = this.value;initImeiList()">
					<option value="2">全部</option>
					<option value="">当前</option>
					<option value="1">下一级</option>
				</select>
			</div>


		</div>
		<hr>

		<div class="row">
			<div class="col-md-12">
				<div class="form-inline">

					<div class="form-group margin-10 ">
						<label for="status">许可状态</label>
						<select class="form-control" id="status" onchange="this_data.status = this.value;initImeiList()">
							<option value="">全部</option>
							<option value="1">永久</option>
							<option value="2">试用</option>
						</select>
					</div>

					<div class="form-group margin-10 ">
						<label for="deviceType">设备种类</label>
						<select class="form-control" id="deviceType" onchange="this_data.deviceType = this.value;initImeiList()">
							<option value="">全部</option>
							<option value="1">移动端</option>
							<option value="2">调度台</option>
						</select>
					</div>

					<div class="input-group pull-right">
						<input type="text" id="key" placeholder="key / imei" class="input form-control text-left">
						<span class="input-group-btn">
							<button type="button" class="btn btn btn-primary" onclick="searchLicense()"> <i class="fa fa-search"></i> 搜索</button>
			            </span>
					</div>

				</div>
			</div>
		</div>
		<hr>

		<div class="datagrid-talbe form-datagrid" style="height: calc(100% - 150px);">
			<table class="table table-bordered table-hover" id="imei_list">
				<thead>
				<tr>
					<th data-property="row">序号</th>
					<th data-property="key">key</th>
					<th class="sort" data-property="imei">imei<i class="iconfont icon-paixu"></i></th>
					<th class="sort" data-property="pac">区号代码	<i class="iconfont icon-paixu"></i></th>
					<th class="sort" data-property="status">状态<i class="iconfont icon-paixu"></i></th>
					<th class="sort" data-property="deviceType">设备类型<i class="iconfont icon-paixu"></i></th>
					<th class="sort" data-property="device">设备种类<i class="iconfont icon-paixu"></i></th>
					<th class="sort" data-property="createTime">开始时间<i class="iconfont icon-paixu"></i></th>
					<th class="sort" data-property="expTime">截止时间<i class="iconfont icon-paixu"></i></th>
					<th class="text-center">操作</th>
				</tr>
				</thead>

			</table>
		</div>


		<div class="paging-footer paging" style="overflow:hidden;">
			<div class="fl" style="float: left;width:80%">
				<span>
					<select class="pagsele" onchange="selectChangeRows(this)">
						<option value="10">10</option>
						<option value="20">20</option>
						<option value="30" selected>30</option>
						<option value="50">50</option>
						<option value="100">100</option>
						<option value="300">300</option>
					</select>
				</span>
				<span><i onclick="page_first()" class="fa fa-step-backward" aria-hidden="true"></i></span>
				<span><i onclick="page_add()" class="fa fa-caret-left" aria-hidden="true"></i></span>
				<span>当前页 <input type="text" value="1" id="page" onchange="pageChange(this)" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="5" class="pageinput"> </span>
				<span><i onclick="page_next()" class="fa fa-caret-right" aria-hidden="true"></i></span>
				<span><i onclick="page_last()" class="fa fa-step-forward" aria-hidden="true"></i></span>
				<span><i onclick="initImeiList()" class="fa fa-rotate-left" aria-hidden="true"></i></span>
			</div>

			<span class="fr" style="float: right;">总页数：<span id="total_pages"></span>， 总记录数：<span id="total"></span></span>

		</div>

	</div>
</div>

</div>


<div id="temp_imei_list">
	<!-- <tr>
		<%--<td data-property="checkbox" style="width:45px;"><div class="checkbox i-checks"><label><input type="checkbox"   property="row"><i></i></label></div></td>--%>
		<td data-property="row">{row}</td>
		<td>{key}</td>
		<td>{imei}</td>
		<td>{pac}</td>
		<td>{status}</td>
		<td>{deviceType}</td>
		<td>{device}</td>
		<td>{createTime}</td>
		<td class="text-center">{expTime}</td>
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


		temp.temp_imei_list = toComment($('#temp_imei_list').html());
		$dom.imei_list = $('#imei_list');
		$dom.province = $('#province');
		$dom.city = $('#city');
		$dom.county = $('#county');
		$dom.county_box = $('#county_box');
		$dom.page = $('#page');
		$dom.total = $('#total');
		$dom.total_pages = $('#total_pages');
		$dom.total_pages = $('#total_pages');

		initImeiList();


		$('#data_5 .input-daterange').datepicker({
			keyboardNavigation: false,
			forceParse: false,
			autoclose: true
		});


		this_data.pac_level = formatPacLevel('${user.pac }');

		peggingPac(function(){
			initPac( this_data.pac );
		})


		orderDataGrid();

		$('#key').bind('keypress', function(event){
			if(event.keyCode == 13){

				this_data.key=$(this).val();
				this_data.imei=this_data.key;

				initImeiList();
			}
		})


	});

	function peggingPac(callback) {

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 210006,
				code: this_data.pac
			},
			success: function (data) {
				data = JSON.parse(data);
				if (data.status == 10000){
					var obj = data.obj;
					switch (obj.length){
						case 1:
							$dom.province.html('<option>'+obj[0].name+'</option>').attr('disabled', 'disabled');
							break;
						case 2:
							$dom.province.html('<option>'+obj[0].name+'</option>').attr('disabled', 'disabled')
							$dom.city.html('<option>'+obj[1].name+'</option>').attr('disabled', 'disabled')
							break;
						case 3:
							$dom.province.html('<option>'+obj[0].name+'</option>').attr('disabled', 'disabled')
							$dom.city.html('<option>'+obj[1].name+'</option>').attr('disabled', 'disabled')
							$dom.county.html('<option>'+obj[2].name+'</option>').attr('disabled', 'disabled')
							break;
					}
				}
				try{
					callback()
				} catch (e){

				}

			}
		})

	}


	function _city(dom) {
		this_data.pac = dom.value;

		if (this_data.pac_level == 1){

			$dom.county.unbind("change");
			initPacCounty(dom.value, function () {

				initImeiList();

				$dom.county.bind("change", function () {

					this_data.pac = $(this).val();
					initImeiList();
				})

			});

			return;
		}

		initImeiList();


	}


	//加载城市
	function initPac(id) {

		if (this_data.pac_level > 2){

			this_data.pac = id;

			//节点树加载完成后加载所有该地区的用户
			initDataGrid(function () {
				initMap(function () {
					getPacBoundary(this_data.pac);
				});
			});

			orderDataGrid();

			return;
		}

		$.ajax({
			url: "/if/serviceController/action",
			data: {
				f: 210002,
				pac: id,
				fetchType: 1,
			},
			dataType: 'text',
			success: function (data) {

				data = JSON.parse(data);

				var children = data.rows;
				for (var i in children) {
					if (children.hasOwnProperty(i)) {
						if (i < 1){
							switch (this_data.pac_level){
								case 1:
									$dom.city.append('<option value="' + children[i].code + '">全部</option>');
									break;
								case 2:
									$dom.county.append('<option value="' + children[i].code + '">全部</option>');
									break;
							}
							this_data.pac_name = children[i].name;
							continue;
						}
						switch (this_data.pac_level){
							case 1:
								$dom.city.append('<option value="' + children[i].code + '">' + children[i].name + '</option>')
								break;
							case 2:
								$dom.county.append('<option value="' + children[i].code + '">' + children[i].name + '</option>')
								break
						}
					}
				}

				this_data.pac = id;

				if (this_data.pac_level == 1){
					$dom.county.html('<option>全部</option>')
				}

				//节点树加载完成后加载所有该地区的用户
				initImeiList();
			}
		})
	}

	//加载区县
	function initPacCounty(id, callback) {
		if ($dom.city.val() == 450000) {
			$dom.county.html('<option>全部</option>');
			try {
				callback();
			} catch (e) {

			}
			return;
		}
		$.ajax({
			url: "/if/serviceController/action",
			data: {
				f: 210002,
				pac: id,
				fetchType: 1,
			},
			dataType: 'text',
			success: function (data) {

				data = JSON.parse(data);

				$dom.county.empty();

				var children = data.rows;

				for (var i in children) {
					if (children.hasOwnProperty(i)) {
						if (i == 0) {
							$dom.county.append('<option value="' + children[i].code + '">全部</option>');
							this_data.pac = children[i].code;
							continue;
						}
						$dom.county.append('<option value="' + children[i].code + '">' + children[i].name + '</option>')
					}
				}

				try {
					callback();
				} catch (e) {

				}

			}
		})
	}

	function searchLicense() {
		var value = $('#key').val();
		this_data.key = value;
		this_data.imei = value;
		this_data.page = 1;
		$dom.page.val(1)
		initImeiList()
	}

	//上一页
	function page_add() {

		if (this_data.page <= 1) {
			return;
		}
		this_data.page--;
		initImeiList();
	}

	//下一页
	function page_next() {
		if (this_data.page >= this_data.totalPages) {
			return;
		}
		this_data.page++;
		initImeiList();
	}

	//最后一页
	function page_last() {

		this_data.page = this_data.totalPages;
		initImeiList();
	}

	//第一页
	function page_first() {
		this_data.serialNumber = 0;
		this_data.page = 1;
		initImeiList();
	}

	//手动更新页数
	function pageChange(item) {
		if (item.value > this_data.totalPages) {
			item.value = this_data.totalPages;
		}
		if (item.value < 1) {
			item.value = 1;
		}

		this_data.page = item.value;
		initImeiList()
	}

	//更新每页显示记录数
	function selectChangeRows(item) {
		this_data.rows = item.value;
		this_data.page = 1;
		initImeiList();
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

				initImeiList()
				return;
			}

			if ($i.hasClass("icon-paixushang")) {
				this_data.order = 'asc';
				$i.get(0).className = 'iconfont icon-paixuxia';
			}
			initImeiList()


		})
	}

	function initImeiList() {

		var index = layer.load(2, {shade: 0.6});

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 210003,
                fetchStatus: 1,
				page: this_data.page,
				rows: this_data.rows,
				sort: this_data.sort,
				order: this_data.order,
				pac: this_data.pac,
				fetchType: this_data.fetchType,
				createTime_start: this_data.createTime_start,
				createTime_end: this_data.createTime_end,
				key: this_data.key,
				imei: this_data.imei,
				status: this_data.status,
				deviceType: this_data.deviceType,
				device: this_data.device,
			},
			success: function (data) {
				data = JSON.parse(data);

				layer.close(index);

				this_data.totalPages = Math.ceil(data.total / this_data.rows);
				this_data.total = data.total;
				$dom.total.text(this_data.total)
				$dom.page.val(this_data.page)
				$dom.total_pages.html(this_data.totalPages)
				this_data.serialNumber = (this_data.page - 1) * this_data.rows;

				$dom.imei_list.find('tbody').remove();

				data = data.rows;
				var data_length = data.length;

				this_data.last_rows = data_length;

				var $tbody = $('<tbody>');
				for (var i = data_length; i >= 0; i--) {
					if (data.hasOwnProperty(i)) {
						var sub = temp.temp_imei_list.format({
							row: this_data.serialNumber + 1,
							id: data[i].id,
                            createTime: function () {
                                if (data[i].createTime){
                                    return new Date(data[i].createTime).Format('yyyy-MM-dd');
                                }
                                return '';
                            },
							expTime: function () {
								if (data[i].expTime && data[i].expTime.length > 0){
									return new Date(data[i].expTime).Format('yyyy-MM-dd');
								}
								return '-';
							},
							founder: data[i].founder,
							imei: data[i].imei || '',
							key: data[i].key,
							pac: data[i].pac,
							status: function () {

								switch (data[i].status) {
									case 1: return "永久";
									case 2: return "试许可";
									case 3:  return '作废许可';
									default: return '';
								}
							},
							deviceType: function () {

								switch (data[i].deviceType) {
									case 1: return "移动端";
									case 2: return "调度台";
									default: return '';
								}
							},
							device: function () {

								switch (data[i].device) {
									case 1: return "Android";
									case 2: return "IOS";
									case 3: return 'PC';
									default: return '';
								}
							},
							opt: function () {
								var btn = '<a onclick="delImei(\'{id}\',this)" title="删除" class="text-primary">删除</a>'.format({id: data[i].id});

								return btn;
							}
						})
						$tbody.append($(sub).data('tr_data', data[i]))

						this_data.serialNumber++;
					}
				}

				$dom.imei_list.append($tbody)

				$(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})

				bindCols()

				forChecks()



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


	function forChecksBind() {

        $(".checkbox_all").on('ifChecked ifUnchecked', function(event) {
            var $this = $(this);
            if (event.type == 'ifChecked') {
                $this.iCheck('check');
                $dom.imei_list.find('.i-checks').iCheck('check');
            } else {
                $this.iCheck('uncheck');
                $dom.imei_list.find('.i-checks').iCheck('uncheck');
            }
        });

    }
    forChecksBind()

	var ddsd = '<div class="checkbox i-checks checkbox_all"><label><input type="checkbox" class="checkbox_all_input" property="row"><i></i></label></div>';
	function forChecks() {

		var checkboxes = $("table tbody .i-checks");

		checkboxes.on('ifChecked ifUnchecked', function(event) {
			var checkbox_all_parent = $('.checkbox_all').parent();
		    $('.checkbox_all').remove();
            checkbox_all_parent.append(ddsd);
            $(".checkbox_all").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})
			if ($('tbody input:checkbox').length === $("tbody input:checked").length){
                $(".checkbox_all").iCheck('check');
			}

            forChecksBind()

		});

	}


	function addImei() {
		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			content: cg_vte.service_href + 'base_data/imei/imei-add&pac=' + this_data.pac,
			btn: ['关闭'],
			fix: false,
			scrollbar: false,
			btn2: function (layero, index) {
				initImeiList()
			}
		});
	}


	function delImei(id, item) {

		swal({
			title: "提示",
			text: "你确定要删除吗，这一步将无法恢复！\n回车“确定”",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			closeOnConfirm: true

		}, function () {

			$.ajax({
				url: cg_vte.imei_service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 110003,
					id: id
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
						text: msg,
						type: "成功",
						showCancelButton: false,
						confirmButtonText: "确定",
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


</script>
</body>
</html>

