<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/ffire-tags" prefix="p" %>
<!DOCTYPE html>
<html>
<head>
	<title>无线电台站</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?4444">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
	<style>
		.iconfont{
			position: relative;
			left: 3px;
		}
		#province, #city, #county{
			width: auto;
		}
	</style>

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>

	<!-- 高德地图插件 Start -->
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	<script src="http://webapi.amap.com/maps?v=1.3&key=fa744da6de658cb8b43af45dc91f399f"></script>
	<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.10"></script>
	<!-- 高德地图插件 End -->


	<script>
		var $dom = {};
		var this_data = {
			serialNumber: 0,
			totalPages: 1,
			total: 0,
			page: 1,
			rows: 30,
			pac: '${user.pac }',
			accountType: '',
			competence: '',
			name: '',
			sort: '',
			order: 'asc',
			date_sdf: 9,
			type: 0,
			reporttime_start: '',
			reporttime_end: '',
			fetchType: 3,
            data_type: 1,
            map_zoom: 12,
		};
		var ids = [];
	</script>
</head>

<body class="gray-bg animated fadeInRight">





<div class="ibox ibox-datagrid">
	<div class="ibox-title ibox-title1">
		<h5></h5>

		<div class=" ibox-tools-filtrate">
			<select id="province" class="form-control margin-10"></select>
			<select id="city" onchange="_city(this);" class="form-control margin-10" ></select>
			<select id="county"  onchange="_county(this)" class="form-control margin-10"></select>
		</div>

		<div class="ibox-tools ibox-tools-one">
			<p:permissions code="user_add" name="添加">
				<a onclick="addUser()">
					<i class="fa fa-plus"></i>
					添加电台
				</a>
			</p:permissions>
			<a onclick="mapView(this)" class="fa fa-map-o"> 地图模式</a>
			<a onclick="deletes()">批量删除</a>
		</div>
	</div>



	<div class="ibox-content">

		<form class="form-inline">



		</form>
		<hr>

		<div id="tr_datagrid_list" class="map_data_table">

			<div class="datagrid-talbe form-datagrid">
				<table class="table table-bordered table-hover" id="dg">
					<thead>
					<tr>
						<td data-property="checkbox" style="width:45px;"><div class="checkbox i-checks checkbox_all"><label><input type="checkbox" class="checkbox_all_input"><i></i></label></div></td>
						<td data-property="row">#</td>
						<td class="sort" data-property="name">名称<i class="iconfont icon-paixu"></i></td>
						<td class="sort" data-property="pac">区号代码<i class="iconfont icon-paixu"></i></td>
						<td class="sort" data-property="num_radio">电台呼号<i class="iconfont icon-paixu"></i></td>
						<td class="sort" data-property="radioname">电台名称<i class="iconfont icon-paixu"></i></td>
						<td class="sort" data-property="type">电台类型<i class="iconfont icon-paixu"></i></td>
						<td class="sort" data-property="coding">电台编号<i class="iconfont icon-paixu"></i></td>
						<td class="sort" data-property="status">status<i class="iconfont icon-paixu"></i></td>
						<td style="text-align:center;">操作</td>
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
					<span><i onclick="page_add()" class="fa fa-caret-left"  aria-hidden="true"></i></span>
					<span>当前页 <input type="text" value="1" id="page" onchange="pageChange(this)" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="5" class="pageinput"> </span>
					<span><i onclick="page_next()" class="fa fa-caret-right" aria-hidden="true"></i></span>
					<span><i onclick="page_last()" class="fa fa-step-forward" aria-hidden="true"></i></span>
					<span><i onclick="initDataGrid()" class="fa fa-rotate-left" aria-hidden="true"></i></span>
				</div>

				<span class="fr" style="float: right;">总页数：<span id="total_pages"></span>， 总记录数：<span id="total"></span></span>
			</div>

		</div>


		<div id="tr_datamap" class="height_per_100" style="height: inherit;display: none;">

			<div id="allmap" class="mpm"></div>

		</div>
	</div>

</div>

<div id="temp_dg">
	<!--<tr>
	<td data-property="checkbox" style="width:45px;"><div class="checkbox i-checks"><label><input type="checkbox"   property="row"><i></i></label></div></td>
		<td data-property="row">{row}</td>
		<td>{name}</td>
		<td>{pac}</td>
		<td>{num_radio}</td>
		<td>{radioname}</td>
		<td>{type}</td>
		<td>{coding}</td>
		<td>{status}</td>
		<td style="text-align:center;">{opt}</td>
	</tr>-->
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script src="${pageContext.request.contextPath}/plug-in/coordtransform/index.js"></script>
<script>

    Array.prototype.remove = function(val) {
        var index = this.indexOf(val);
        if (index > -1) {
            this.splice(index, 1);
        }
    };


	var temp = {}
	$(document).ready(function () {


		temp.dg = toComment($('#temp_dg').html());
		$dom.dg = $('#dg');
		$dom.province = $('#province');
        $dom.province = $('#province');
		$dom.city = $('#city');
		$dom.county = $('#county');
        $dom.page = $('#page');
        $dom.total = $('#total');
        $dom.total_pages = $('#total_pages');



        $dom.tr_datamap = $('#tr_datamap');
        $dom.tr_datagrid_list = $('#tr_datagrid_list');


        this_data.pac_level = formatPacLevel(this_data.pac);

		peggingPac(function(){
			//加载地区
			initPac(this_data.pac);
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

    function _county(dom){
	    this_data.pac = dom.value;
	    initDataGrid();
    }

    function _city(dom) {
        this_data.pac = dom.value;

        $dom.county.unbind("change");
        initPacCounty(dom.value, function () {
            initDataGrid();

            $dom.county.bind("change", function () {
                initDataGrid();
            })

        });

        initDataGrid()

    }

	//加载城市
	function initPac(id) {
		if (this_data.pac_level > 2){

			this_data.pac = id;

			//节点树加载完成后加载所有该地区的用户
			initDataGrid();

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
	            initDataGrid();

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

                $dom.county.empty().show();

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




	function initDataGrid(callback) {

		var index = layer.load(2, { shade: 0.6 });

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 4140001,
				page: this_data.page,
				rows: this_data.rows,
				sort: this_data.sort,
				order: this_data.order,
				date_sdf: this_data.date_sdf,
				pac: this_data.pac,
				fetchType: this_data.fetchType,
				type: this_data.type,
				reportTime_start: this_data.reporttime_start,
				reportTime_end: this_data.reporttime_end,
			},
			success: function (data) {
				data = JSON.parse(data);

				layer.close(index);

				this_data.totalPages = Math.ceil(data.total / this_data.rows);
				this_data.total = data.total;
				$dom.total.text(this_data.total)
				$dom.page.val(this_data.page)
				$dom.total_pages.html(this_data.totalPages)

				this_data.serialNumber = (this_data.page-1)*this_data.rows;

				$dom.dg.find('tbody').remove();
				data = data.rows;
				this_data.data = data;
				var data_length = data.length;

				this_data.last_rows = data_length;

				var $tbody = $('<tbody>');
				for (var i = data_length; i >= 0; i--) {
					if (data.hasOwnProperty(i)) {
						var sub = temp.dg.format({
							row: this_data.serialNumber + 1,
							name: data[i].name || '',
							num_radio: data[i].num_radio || '',
							radioname: data[i].radioname || '',
							type: data[i].type || '',
							coding: data[i].coding || '',
							pac: data[i].pac || '',
							status: data[i].status || '',
							opt: function () {
								var btn = '';
								btn += '  <a onclick="editHot(this)" title="修改" class="linkceond"><i class="iconfont icon-xiugai"></i></a>  ';
								btn += '   <a onclick="delRadio(this)" title="删除" class="linkceond"><i class="iconfont icon-ttpodicon"> </i></a>  ';
								return btn.format({id: data[i].id});
							}
						})

						$tbody.append($(sub).data("data_tr", data[i]));

						this_data.serialNumber++;
					}
				}

				$dom.dg.append($tbody)
                $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})

                forChecks();

				getPacBoundary(this_data.pac)

				try{
					callback()
				} catch (e){

				}


			}
		})

	}

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

	var editHotConfig = {};
	function editHot(item) {
		var tr_data = $(item).parents('tr').data('data_tr');
		//H5本地存储
		console.log(tr_data)
		window.sessionStorage.setItem('data_tr', JSON.stringify(tr_data));

		editHotConfig.layer = layer.open({
			type: 2,
			title: '修改瞭望台管理',
			closeBtn: 1,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			content: cg_vte.path + 'webpage/base_data/radio_station/radio_station_addOrUpd.jsp',
			btn: ['提交', '关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {

				layer.getChildFrame('#submit', index).click()
			}
		});

	}


	function editHotCallback() {

		layer.closeAll();
		initDataGrid();

	}



	//提交删除动作
	function delRadio(item) {

		var data = $(item).parents('tr').data('data_tr');
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
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 4143001,
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
				params['radioStations["'+i+'"].id'] = ids[i];
			}
		}

		$.ajax({
			url: cg_vte.path + 'if/radioStationController/deleteRadioStations',
			type: 'post',
			dataType: 'text',
			data: params,
			success: function (data) {
				data = JSON.parse(data);
				if (data.status == 10000){
					initDataGrid();
					layer.msg("删除成功");
				} else{
					layer.msg(data.msg);
				}
			}
		})
	}

	function callbackDel(state, msg, item) {

		switch (state) {
			case 10000:
				setTimeout(function () {

					swal({
						title: "提示",
						text: msg,
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








	//添加用户弹出框
	function addUser() {


		layer.open({
			type: 2,
			title: '添加无线电台站',
			closeBtn: 1,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			content: cg_vte.service_href + 'base_data/radio_station/radio_station_add',
			fix: false,
			scrollbar: false,
		});
	}


	//添加用户成功的回调
	function completeCallback() {
		layer.closeAll();
		initDataGrid();
	}


    function forChecksBind() {

        $(".checkbox_all").on('ifChecked ifUnchecked', function(event) {
            var $this = $(this);
            if (event.type == 'ifChecked') {
                $this.iCheck('check');
                $dom.dg.find('.i-checks').iCheck('check');
            } else {
                $this.iCheck('uncheck');
                $dom.dg.find('.i-checks').iCheck('uncheck');
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



    function mapView(item) {
        //地图模式
        if (this_data.data_type == 1){
            $dom.tr_datagrid_list.hide();
            $dom.tr_datamap.show();
            this_data.data_type = 2;
            $(item).html(' 列表模式');
	        getPacBoundary(this_data.pac)
            return ;
        }
        $dom.tr_datagrid_list.show();
        $dom.tr_datamap.hide();
        this_data.data_type = 1;
        //location.href = cg_vte.path + 'webpage/base_data/fire_hot/fire_hot_map_view.jsp';
        $(item).html(' 地图模式');
    }


    //获得行政区地图边界
    function getPacBoundary(pac) {

        $.ajax({
            url: cg_vte.service,
            type: 'post',
            dataType: 'text',
            data: {
                f: 210005,
                code: pac,
                format_boundary_type: 1
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.status == 10000){
                    var area_boundary = data.obj;

                    if (area_boundary.boundary){
                        var boundary = JSON.parse(area_boundary.boundary) ;
                        if (boundary.type == 'Polygon'){

                            drawBoundary(boundary.coordinates);

                        }
                    }
                }
            }
        })
    }
    //绘制地图边界
    function drawBoundary(coordinates) {

	    var tileLayer = new AMap.TileLayer();
	    var roadNet = new AMap.TileLayer.RoadNet();
	    var googleLayer = new AMap.TileLayer({
		    getTileUrl: 'http://mt{1,2,3,0}.google.cn/vt/lyrs=y&hl=zh-CN&gl=cn&x=[x]&y=[y]&z=[z]&s=Galile',
		    zIndex: 8,
	    });

	    var map = new AMap.Map('allmap', {
		    zIndex: 5,
		    resizeEnable: true,
		    zoom : this_data.map_zoom,
		    layers: [tileLayer,googleLayer,roadNet]
	    });

	    roadNet.hide();
	    googleLayer.setMap(map);
	    this_data.map = map;


	    AMap.event.addListener(map,'zoomend',function(){
		    this_data.map_zoom = map.getZoom();
		    if (map.getZoom() >= 7){
			    $('u').css({visibility: 'inherit'})
		    } else {
			    $('u').css({visibility: 'hidden'})
		    }
	    });


	    var polygonOptions = {
		    map: this_data.map,
		    strokeColor: '#97EC71',
		    strokeWeight: 2,
		    fillColor: '',
		    fillOpacity: 0.7
	    };

	    var polygon = new AMap.Polygon(polygonOptions);
	    polygon.setPath(coordinates);
	    this_data.map.setFitView();

	    initHots();
    }

    function initHots(){

        var map = this_data.map;
        var rows = this_data.data;

        if (this_data.markers){
            for(var i in this_data.markers){
                map.remove(this_data.markers[i]);
            }
        }

        var markers = [];

        for(var i in rows){
            if (rows.hasOwnProperty(i)){

                var marker = new AMap.Marker({
                    map: map,
                    zIndex: 9999999,
                    offset: new AMap.Pixel(-15, -15),
                    content: '<i index="'+i+'" class="iconfont icon-redian7"><span class="none">' + JSON.stringify(rows[i])+'</span><u>' + rows[i].name + '</u></i> ',
                    position: coordtransform.gcj02towgs84(rows[i].longitude, rows[i].latitude)
                });
                markers.push(marker);

                //marker 点击时打开
                AMap.event.addListener(marker, 'click', function(event) {

                    var data = JSON.parse( $(event.target.Qi.content).find('span').html() );
                    console.log(data)
                    this_data.this_map_data = data;
                    //构建信息窗体中显示的内容
                    var info = [];
                    info.push("<div >");
                    info.push("<h5 class=\"firetitle\">热点信息</h5>");
                    info.push("<div class=\"firebox\">");
                    info.push("电台呼号 : {num_radio}");
                    info.push("<br>");
                    info.push("电台名称 : {radioname}");
                    info.push("<br>");
                    info.push("电台编号 : {coding}");
                    info.push("<br>");
                    info.push("接收频率 : {r_frequenc}");
                    info.push("<br>");
                    info.push("发射频率： {l_frequenc}");
                    info.push("<br>");
                    info.push("发射功率： {power}");
                    info.push("<br>");
                    info.push("天线高度： {height}");
                    info.push("</div>");
                    info.push("<a onclick='mapDatail()' class=\"firefor\">详情</a>")
                    info.push("</div>");
                    var content = info.join("").format({
                        num_radio: data.num_radio,
                        radioname: data.radioname,
                        coding: data.coding,
                        r_frequenc: data.r_frequenc,
                        l_frequenc: data.l_frequenc,
                        power: data.power,
                        height: data.height,

                        status: function(){
                            switch (data.status){
                                case 0: return '未反馈';
                                case 1: return '已反馈';
                                default: return 0;
                            }
                        }
                    })
                    var infoWindow = new AMap.InfoWindow({
                        offset: new AMap.Pixel(10, -5),
                        content: content
                    });

                    infoWindow.open(map, coordtransform.gcj02towgs84( data.longitude, data.latitude ));
                });

            }
        }

        this_data.markers = markers;

    }

    function mapDatail() {

        console.log(this_data.this_map_data);
        var data = this_data.this_map_data;

        var content = $('#this_map_data').html().trim();

        content = content.format({
            pac: data.pac || '',
            name: data.name || '',
            manager: data.manager || '',
            phone: data.phone || '',
            longitude: data.longitude || '',
            latitude: data.latitude || '',
            elevation: data.elevation || '',
            units: data.units || '',
            num_radio: data.num_radio || '',
            radioname: data.radioname || '',
            type: data.type || '',
            coding: data.coding || '',
            r_frequenc: data.r_frequenc || '',
            l_frequenc: data.l_frequenc || '',
            power: data.power || '',
            height: data.height || '',
            shape: data.shape || '',
            d_type: data.d_type || '',
            note: data.note || '',
            build_year: data.build_year || '',


            status: function () {
                switch (data.status){
                    case 1: return '1_优秀';
                    case 2: return '2_良好';
                    case 3: return '3_一般';
                    case 4: return '4_差';
                    default: return '5_废弃';
                }
            },

        });
        var mediaFiles = data.mediaFiles;
        console.log(mediaFiles)
        if (mediaFiles && mediaFiles.length > 0){
            for( var i in mediaFiles){
                if (mediaFiles.hasOwnProperty(i)){

                }
            }
        } else {
            $('#this_map_data .media_file').html('无媒体文件');
        }
        layer.open({
            type: 1,
            title: ' 热点详情',
            closeBtn: 1,
            maxmin: false, //开启最大化最小化按钮
            area: ['80%', '500px'],
            //offset: 'r',
            content: content,
            btn: ['关闭'],
            fix: false,
            shadeClose: true,
            scrollbar: false,
            yes: function (index, layero) {layer.close( index );}
        });

    }




</script>

<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/iCheck/icheck.min.js"></script>
</body>
</html>

<div class="none">
	<style>

		.this_map_data td:nth-child(1),.this_map_data td:nth-child(3){
			background-color: #f7f7f7;
		}
	</style>
	<div id="this_map_data">
		<div class="ibox">
			<div class="ibox-content">
				<table class="table table-bordered this_map_data">
					<tr>
						<td style="width:140px;"> 行政代码</td>
						<td style="padding-left:20px;">{pac}</td>
						<td style="width:140px;">名称</td>
						<td style="padding-left:20px;">{name}</td>

					</tr>
					<tr>
						<td>管理员</td>
						<td>{manager}</td>
						<td>管理员电话</td>
						<td>{phone}</td>
					</tr>
					<tr>
						<td>径度</td>
						<td>{longitude}</td>
						<td>纬度</td>
						<td>{latitude}</td>

					</tr>
					<tr>
						<td>海拔</td>
						<td>{elevation}</td>
						<td>使用单位</td>
						<td>{units}</td>
					</tr>
					<tr>
						<td>电台呼号</td>
						<td>{num_radio}</td>
						<td>电台名称</td>
						<td>{radioname}</td>

					</tr>
					<tr>
						<td>电台类型</td>
						<td>{type}</td>
						<td>电台编号</td>
						<td>{coding}</td>
					</tr>
					<tr>
						<td>接收频率</td>
						<td>{r_frequenc}</td>

						<td>发射频率</td>
						<td>{l_frequenc}</td>

					</tr>
					<tr>
						<td>	发射功率</td>
						<td>{power}</td>
						<td>天线高度</td>
						<td>{height}</td>
					</tr>
					<tr>
						<td>形状</td>
						<td>{shape}</td>
						<td>类型</td>
						<td>{d_type}</td>


					</tr>
					<tr>
						<td>状态</td>
						<td  >{status}</td>
						<td>类型</td>
						<td  >{d_type}</td>
					</tr>
					<tr>
						<td>说明</td>
						<td >{note}</td>
						<td>建站时间</td>
						<td >{build_year}</td>
					</tr>

				</table>
				<hr>

				<h5>媒体文件</h5>
				<div class="media_file">

				</div>
			</div>
		</div>

	</div>
</div>