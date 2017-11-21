<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/ffire-tags" prefix="p" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户管理</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.500">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/datapicker/datepicker3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/iCheck/custom.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-datetimepicker/3.1.4/css/bootstrap-datetimepicker.min.css">
	<link href="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/skin/default/layer.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?' + new Date().getTime() + '">');</script>
	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?' + new Date().getTime() + '">');</script>

	<style>
		.ibox.ibox-datagrid .datagrid-talbe {
			height: calc(100% - 100px);
			border: 1px solid #dcdcdc;
			overflow: auto;
		}
		#hot_list img{
			height: 25px;
			width: 25px;
		}
		.iconfont{
			position: relative;
			left: 3px;
		}

		#countries, #province, #city, #county, #fetchType{
			width: 156px;
		}


		.ibox.ibox-datagrid td {
			overflow: hidden;
			white-space: inherit;
			text-overflow: inherit;
			word-wrap: break-word;
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
			pac: '${user.pac}',
			sort: 'createTime',
			order: 'desc',
			date_sdf: 0,
			fetchType: 2,

			type: null,
			accountType: '',
			name: '',
			account: '',
			phone: '',
		};

		var ids = [];
	</script>

</head>

<body class="gray-bg">

	<div class="ibox float-e-margins ibox-datagrid animated fadeInRight">

		<div class="ibox-title">
			<div class=" ">
				<select id="countries" class="margin-10 form-control fl none"></select>
				<select id="province" class="margin-10 form-control fl" onchange="pac_change(this)"></select>
				<select id="city" class="margin-10 form-control fl" onchange="pac_change(this);"></select>
				<select id="county" class="margin-10 form-control fl" onchange="pac_change()"></select>
			</div>

			<div class="ibox-tools ibox-tools-one">
				<p:permissions code="user_add" name="添加"><a onclick="add()" class="margin-10">添加用户 </a></p:permissions>
				<p:permissions code="user_batch_delete" name="添加"><a onclick="deletes(this)" class="fa fa-map-o"> 批量删除</a></p:permissions>
				<a id="dLabel" class="dropdown-toggle fl" data-toggle="dropdown" aria-expanded="true" style="display: none"> <i class="glyphicon glyphicon-list"></i></a>

				<ul class="dropdown-menu bg-warning" aria-labelledby="dLabel" id="cols">
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="row"><i></i>行号</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="headPortrait"><i></i>头像</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="account"><i></i>帐号</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="name"><i></i>用户名</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="phone"><i></i>用户电话</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="roles"><i></i>角色</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="lastTime"><i></i>最后一次登陆时间</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="lastIp"><i></i>最后一次登陆IP</label></div></li>
					<li><div class="checkbox i-checks"><label><input type="checkbox" checked property="pac"><i></i>pac</label></div></li>
				</ul>

			</div>
		</div>

		<div class="ibox-content  " style="height: calc(100% - 80px);">


			<div class="row">
				<div class="col-md-12">
					<div class="form-inline  ">

                        <div class="form-group margin-10">

							<label for="fetchType" class="margin-10" style="line-height: 34px;">筛选范围</label>
							<select class="form-control margin-10" id="fetchType" onchange="this_data.fetchType = this.value;initDataGrid()"  >
								<option value="2" selected>全部</option>
								<option value="1">下一级</option>
								<option value="0">当前</option>
								<%--<option value="3">最大到县</option>--%>
							</select>
						</div>


						<div class="form-group margin-10">

							<label class="margin-10" style="line-height: 34px;">启用状态</label>
							<select class="form-control margin-10" onchange="this_data.state = this.value;initDataGrid()"  style="width:160px;" >
								<option value="">全部</option>
								<option value="0">启用</option>
								<option value="1">禁用</option>
							</select>
						</div>


						<div class="input-group pull-right">
							<input type="text" id="key" placeholder="帐号/用户名/电话" class="input form-control text-left">
							<span class="input-group-btn">
								<button type="button" class="btn btn btn-primary" onclick="searchDataGrid()"> <i class="fa fa-search"></i> 搜索</button>
				            </span>
						</div>



					</div>
				</div>
			</div>
			<hr>


			<div class="datagrid-talbe">


				<table class="table table-bordered table-hover" id="hot_list">
					<thead>
						<tr>
							<th data-property="checkbox" style="width:45px;"><div class="checkbox i-checks checkbox_all"><label><input type="checkbox" class="checkbox_all_input"><i></i></label></div></th>
							<th data-property="row">序号</th>
							<th data-property="headPortrait" style="width:50px">头像</th>
							<th class="sort" data-property="account">帐号<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="name">用户名<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="pac">所属行政区<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="accountType">用户类型<i class="iconfont icon-paixu"></i></th>
							<th data-property="roles">角色</th>
							<th class="sort"  data-property="state">启用状态<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="departs">所属部门<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="phone">电话<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="lastTime">最后一次登录时间<i class="iconfont icon-paixu"></i></th>
							<th class="sort" data-property="lastIp">最后一次登录IP<i class="iconfont icon-paixu"></i></th>
							<th class="text-center" style="width:130px;">操作</th>
						</tr>
					</thead>
				</table>




			</div>
			<div class="paging-footer paging">

				<div class="pull-left" style="min-width: 400px">
					<spaan>
						<select class="pagsele" onchange="selectChangeRows(this)">
							<option value="30">30</option>
							<option value="50">50</option>
							<option value="100">100</option>
							<option value="300">300</option>
						</select>
					</spaan>
					<span><i onclick="page_first()" class="fa fa-step-backward"></i></span>
					<span><i onclick="page_add()" class="fa fa-caret-left" ></i></span>
					<span>当前页 <input type="text" value="1" id="page" onchange="pageChange(this)" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="5" class="pageinput"> </span>
					<span><i onclick="page_next()" class="fa fa-caret-right"></i></span>
					<span><i onclick="page_last()" class="fa fa-step-forward"></i></span>
					<span><i onclick="initDataGrid()" class="fa fa-rotate-left"></i></span>
				</div>

				<span class="pull-right">总页数：<span id="total_pages"></span>， 总记录数：<span id="total"></span></span>


			</div>



		</div>


	</div>


<div id="temp_hot_list">
	<!-- <tr>
		<td data-property="checkbox" style="width:45px;"><div class="checkbox i-checks"><label><input type="checkbox"   property="row"><i></i></label></div></td>
		<td data-property="row">{row}</td>
		<td style="width:50px;text-align: center;"><img src="${pageContext.request.contextPath}/{headPortrait}"></td>
		<td>{account}</td>
		<td>{name}</td>
		<td>{pac}</td>
		<td>{accountType}</td>
		<td>{roles}</td>
		<td>{state}</td>
		<td>{departs}</td>
		<td>{phone}</td>
		<td>{lastTime}</td>
		<td>{lastIp}</td>
		<td class="text-center" style="width:130px;">{opt}</td>
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
		$dom.countries = $('#countries');
		$dom.province = $('#province');
		$dom.city = $('#city');
		$dom.county = $('#county');
		$dom.page = $('#page');
		$dom.total = $('#total');
		$dom.total_pages = $('#total_pages');

		this_data.pac_level = formatPacLevel(this_data.pac);

        peggingPac(function () {
            //加载地区
            initPac(this_data.pac, function(){
            	initDataGrid();
            });
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
		            console.log(obj)
		            for(var i in obj){
		            	if (obj.hasOwnProperty(i)){

				            switch (formatPacLevel(obj[i].code)){
					            case 0:
						            $dom.countries.html('<option>'+obj[i].name+'</option>').attr('disabled', 'disabled');
						            break;
					            case 1:
						            $dom.province.html('<option>'+obj[i].name+'</option>').attr('disabled', 'disabled');
						            break;
					            case 2:
						            $dom.city.html('<option>'+obj[i].name+'</option>').attr('disabled', 'disabled')
						            break;
					            case 3:
						            $dom.county.html('<option>'+obj[i].name+'</option>').attr('disabled', 'disabled')
						            break;
				            }

			            }
		            }

	            }
	            try{
		            callback()
	            } catch (e){

	            }

            }
        })

    }



    function pac_change(dom) {
		this_data.pac = dom.value;

	    initPac(this_data.pac, function(){
		    //节点树加载完成后加载所有该地区的用户
		    initDataGrid();
	    });
	}

	//加载城市
	function initPac(id, callback) {

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
							switch (formatPacLevel(children[i].code)){
								case 0:
									$dom.countries.show()
									$dom.province.append('<option value="' + children[i].code + '">全部</option>');
									$dom.city.empty().append('<option value="">全部</option>');
									$dom.county.empty().append('<option value="">全部</option>');
									break;
								case 1:
									$dom.province.append('<option value="' + children[i].code + '">全部</option>');
									$dom.city.empty().append('<option value="">全部</option>');
									$dom.county.empty().append('<option value="">全部</option>');
									break;
								case 2:
									$dom.city.append('<option value="' + children[i].code + '">全部</option>');
									$dom.county.empty().append('<option value="">全部</option>');
									break;
								case 3:
									$dom.county.append('<option value="' + children[i].code + '">全部</option>');
									break;
							}
							continue;
						}

						switch ( formatPacLevel(children[i].code) ){
							case 1:
								$dom.province.append('<option value="' + children[i].code + '">'+children[i].name+'</option>');
								break;
							case 2:
								$dom.city.append('<option value="' + children[i].code + '">'+children[i].name+'</option>');
								break;
							case 3:
								$dom.county.append('<option value="' + children[i].code + '">'+children[i].name+'</option>');
								break;
						}

					}
				}

				try{
					callback();
				} catch (e){

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

		var index = layer.load(2, {	shade: 0.6	});

		var param = {
			f: 220001,
			page: this_data.page,
			rows: this_data.rows,
			sort: this_data.sort,
			order: this_data.order,
			date_sdf: this_data.date_sdf,
			pac: this_data.pac,
			fetchType: this_data.fetchType,
			name: this_data.name,
			phone: this_data.phone,
			account: this_data.account,
		}

		if ( this_data.type){
			param.type = this_data.type;
		}
		if (this_data.state && this_data.state.length > 0){
			param.state = this_data.state;
		}
		if (this_data.accountType && this_data.accountType.length > 0){
			param.accountType = this_data.accountType;
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
							state: function () {
								switch (data[i].state){
									case 1: return "禁用";
									default: return "启用";
								}
							},
							departs: function () {
								var departs = data[i].departs;
								var str = '';
								for(var j in departs){

									if (departs.hasOwnProperty(j)){
										if (j==0){
											str = departs[j].depart_name;
											continue;
										}
										str += " - " +  departs[j].depart_name;
									}
								}
								return str;
							},
							roles: function () {
								if (!data[i].roles){
									return ''
								}
								var str = '';
								var roles = data[i].roles;
								for(var j in roles){
									if (roles.hasOwnProperty(j)){
										if (j < 1){
											str = roles[j].roleName
											continue;
										}
										str += " / " + roles[j].roleName;
									}
								}
								return str;
							},
							latitude: data[i].latitude || '',
							longitude: data[i].longitude || '',

                            online: function () {
                                switch (data.online){
                                    case 1: return '是';

                                    default: return '否';
                                }
                            },
							imei: data[i].imei || '',
							deviceType: data[i].deviceType || '',
							deviceId: data[i].deviceId || '',
							deviceName: data[i].deviceName || '',
							lastTime: data[i].lastTime || '',
                            lastIp: data[i].lastIp || '',
							voipAccount: data[i].voipAccount || '',
							voipPassword: data[i].voipPassword || '',
							accountType: function () {
								if (!data[i].accountType){
									return '';
								}
								switch (data[i].accountType){
									case 1: return '系统用户';
									case -1: return '超级管理员';
									default: return '';
								}
							},
							pac: data[i].pac || '',
							jid: data[i].jid || '',
							token: data[i].token || '',
							subscriber: data[i].subscriber || '',
							opt: function () {
								var btn = '';
								btn += ' <a onclick="eyes(this)" class="linkceond" title="查看"><i class="iconfont icon-chakan"></i></a>  ';
								btn += ' <p:permissions code="user_edit" name="修改"><a onclick="edit(this)" class="linkceond" title="编辑"><i class="iconfont icon-xiugai"></i></a></p:permissions>  ';
								<%--btn += '<p:permissions code="user_edit" name="修改密码"><a onclick="updatePassword(this)" class="linkceond" title="修改密码"><i class="iconfont icon-xiugaimima1" ></i></a> </p:permissions>';--%>
								<%--btn += ' <p:permissions code="user_role_manage" name="角色管理"><a onclick="setRoleUser(this)" class="linkceond" title="角色管理"><i class="iconfont icon-user"></i></a> </p:permissions>';--%>
								<%--btn += ' <p:permissions code="user_depart_manage" name="部门管理"> <a onclick="setUserDepart(this)" class="linkceond" title="部门管理"><i class="iconfont icon-icon_jiaoseguanli"></i></a> </p:permissions>';--%>
								btn += '  <p:permissions code="user_delete" name="删除"><a onclick="del(this)" class="linkceond" title="删除"><i class="iconfont icon-ttpodicon"> </i></a></p:permissions>  ';
								return btn.format({id: data[i].id});
							}
						});

						$tbody.append($(sub).data("data_tr", data[i]));
						this_data.serialNumber++;
					}
				}

				$dom.hot_list.append($tbody)

                $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})



				bindCols()

				forChecks()


			}
		})
	}

	function searchDataGrid() {
		var _key = $('#key').val();
		this_data.name = _key,this_data.phone = _key, this_data.account = _key;
		initDataGrid();
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


    function forChecksBind() {

        $(".checkbox_all").on('ifChecked ifUnchecked', function(event) {
            var $this = $(this);
            if (event.type == 'ifChecked') {
                $this.iCheck('check');
                $dom.hot_list.find('.i-checks').iCheck('check');
            } else {
                $this.iCheck('uncheck');
                $dom.hot_list.find('.i-checks').iCheck('uncheck');
            }
        });

    }
    forChecksBind()

    var ddsd = '<div class="checkbox i-checks checkbox_all"><label><input type="checkbox" class="checkbox_all_input" property="row"><i></i></label></div>';
    function forChecks() {

        var checkboxes = $("table tbody .i-checks");

        checkboxes.on('ifChecked ifUnchecked', function(event) {
            var data_tr = $(this).parents('tr').data('data_tr');
            var checkbox_all_parent = $('.checkbox_all').parent();
            $('.checkbox_all').remove();
            checkbox_all_parent.append(ddsd);
            $(".checkbox_all").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})
            if ($('tbody input:checkbox').length === $("tbody input:checked").length){
                $(".checkbox_all").iCheck('check');
            }

            switch (event.type){
                case 'ifChecked':
                    ids.push(data_tr.id);
                    break;
                case 'ifUnchecked':
                    ids.remove(data_tr.id);
                    break;
            }

            forChecksBind()

        });

    }

	//-------------------------------- 添加 Start ------------------------------------//
	function add() {
		layer.open({
			type: 2,
			title: '添加',
			closeBtn: 1,
			maxmin: false, //开启最大化最小化按钮
			area: ['840px', '500px;'],
			content: cg_vte.path + 'webpage/system/user/user-add.jsp',
			fix: false,
			scrollbar: false,
		});
	}
	function edit(item) {
		var tr_data = $(item).parents('tr').data('data_tr');
		layer.open({
			type: 2,
			title: '修改',
			closeBtn: 1,
			maxmin: false, //开启最大化最小化按钮
			area: ['840px', '500px'],
			content: cg_vte.path + 'if/userController/editGo?id=' + tr_data.id,
			fix: false,
			scrollbar: false,
		});

	}

    function eyes(item) {
        var tr_data = $(item).parents('tr').data('data_tr');

	    window.sessionStorage.setItem('user_manage_eyes', JSON.stringify(tr_data));

        layer.open({
            type: 2,
            title: '查看',
            closeBtn: 1,
            maxmin: false, //开启最大化最小化按钮
            area: ['840px', '70%'],
            content: cg_vte.path + 'userController.do?goHref&href=system/user/user-eye&id=' + tr_data.id,

            fix: false,
            scrollbar: false,
            yes: function (index, layero) {layer.close( index );}
        });

    }




	//添加或者修改时成功后的回调
	function addOrUpdCallback() {

		layer.closeAll();
		initDataGrid();
	}
	//-------------------------------- 添加 End ------------------------------------//


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
	
	function deletes() {

		if (ids.length<1){
			layer.msg('请先选择数据');
			return;
		}

		var params = {};

		for (var i in ids){
			if (ids.hasOwnProperty(i)){
				params['users["'+i+'"].id'] = ids[i];
			}
		}



		swal({
			title: "提示",
			text: "你确定要删除这些吗，这一步将无法恢复！\n回车“确定”",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			closeOnConfirm: true
		}, function () {

			var index = layer.load(2, {	shade: 0.6	});

			$.ajax({
				url: cg_vte.path + 'if/userController/deletes',
				type: 'post',
				dataType: 'text',
				data: params,
				success: function (data) {
					data = JSON.parse(data);
					layer.close(index);
					if (data.status == 10000){
						initDataGrid();
						layer.msg("删除成功");
					} else{
						layer.msg(data.msg);
					}
				}
			})


		});

	}


	//------------------------------ 角色管理 Start ------------------------------//
	var roleUser = {};

	function setRoleUser(item) {

		var data = $(item).parents('tr').data('data_tr');

		roleUser.dom = item;
		roleUser.layer = layer.open({
			type: 2,
			title: '',
			closeBtn: 0,

			area: ['70%', '70%'],
			content: cg_vte.path + 'userController.do?choiceRole&id=' + data.id,
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

	function setUserDepart(dom) {

		var data = $(dom).parents('tr').data('data_tr');

		userDepart.dom = dom;
		userDepart.layer = layer.open({
			type: 2,
			title: '',
			closeBtn: 0,

			area: ['65%', '70%'],
			content: cg_vte.path + 'userController.do?choiceDepart&id=' + data.id,
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
			area: ['70%', '70%'],
			content: url,
			fix: false,
			scrollbar: false,
			shadeClose: true,
		});
	}
	function updatePasswordCallback(){
		layer.close(updatePasswordConfig.layer);
	}
	//---------------------------- 修改密码 End ----------------------------------//

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
	
	function searchUser() {
		var value = $('#serch').val();
        this_data.account = value,this_data.name
    }




</script>

</body>
</html>


