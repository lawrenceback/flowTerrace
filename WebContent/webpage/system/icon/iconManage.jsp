<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/6/23
  Time: 8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/ffire-tags" prefix="my"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>图标 管理</title>


	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<script src="${pageContext.request.contextPath}/config/cg.js?"></script>
	<script>document.write('<script src="'+cg_vte.iconfont_js+'?' + new Date().getTime() + '"><\/script>');</script>;

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
		.ibox{
			margin-bottom: 0;
		}
		.layui-layer-btn{
			border-top: 1px solid #ddd;
		}
		.clients-list table tr td{text-align: center;}

		#page-wrapper .datagrid-btable tr{height: 40px;}
		.sort{
			cursor: pointer;
		}
		.input-group[class*=col-]{
			float:left;    margin-left: 5%;
		}
		.table-responsive table thead{
			background:#f2f2f2;
			text-align: center;
		}
		.clients-list{
			border:1px solid #dcdcdc;
		}

		.ibox-title1{
			padding: 20px ;
		}
		label{
			font-weight: 600;
		}
		.client-avatar{
			text-align: left;
		}
		.iconfont{
			font-size:16px;
		}
		.icon {
			width:30px;
			height: 20px;
			position: relative;
			top:2px;
			vertical-align: -0.15em;
			fill: currentColor;
			overflow: hidden;

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
			iconClass: '',
			iconName: ''
		};
	</script>

</head>

<body class="fixed-sidebar full-height-layout gray-bg  pace-done" style="overflow:hidden">

<div id="wrapper">


	<div id="page-wrapper" class="gray-bg dashbard-1">

		<div class="wrapper wrapper-content  animated fadeInRight">

		<div class="row border-bottom">
			<div class="col-sm-12">

			<div class="ibox float-e-margins">



				<div class="ibox-title ovel ibox-title1 ">


					<div class="input-group col-sm-8 fl" >
						<input type="text" placeholder="图标查找" class="input form-control" id="icon_seach">
						<span class="input-group-btn">
                                <button type="button" class="btn btn btn-primary" onclick="initIcon()"> <i class="fa fa-search"></i> 搜索</button>
                        </span>
					</div>
					<div class="ibox-tools ibox-tools-two   " style="margin-top: 8px;margin-right: 5%;">


						<my:permissions code="icon_add" name="添加">

							<a onclick="addIcon()"> 	<i class="fa fa-plus"></i>添加信息</a>
						</my:permissions>
					</div>

				</div>

				<div class="ibox-content" style="padding-top:0;">







					<table class="table table-bordered table-striped table-hover" id="fontawesome-icon-list">
						<thead>
							<tr>
								<td style="width: 50px;">style</td>
								<td class="sort" data-property="iconClass" style="width: 250px;">
									iconClass
									<i class="iconfont icon-paixu"></i>
								</td>
								<td class="sort" data-property="iconName">
									iconName
									<i class="iconfont icon-paixu"></i>
								</td>
								<td class="text-center">操作</td>
							</tr>
						</thead>
						<tbody>


						</tbody>
					</table>

				</div>


			</div>





		</div>
		</div></div>
	</div>

</div>



<div id="temp_icon_list">
	<!-- <tr id="{id}">
		<td class="text-center"> <i class="{iconClass}"></i> </td>
		<td class="client-avatar">{iconClass}</td>
		<td>{iconName}</td>
		<td class="text-center">{opt}</td>
	</tr>-->
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>


<script>

	$(document).ready(function(){
		$dom.fontawesomeIconList = $('#fontawesome-icon-list tbody');
		temp.temp_icon_list = toComment( $('#temp_icon_list').html() );


		initIcon();


		orderDataGrid();

		$('#icon_seach').bind('keypress',function(event){

			this_data.iconClass=$(this).val();
			this_data.iconName=$(this).val();

			if(event.keyCode == '13')
			{
				layer.load(1, {
					shade: [0.6,'#000'] //0.1透明度的白色背景
				});
				initIcon();
			}
		});

	});


	function initIcon() {
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 910001,
				rows: 1000,
				sort: this_data.sort,
				order: this_data.order,
				iconName: this_data.iconName,
				iconClass: this_data.iconClass
			},
			success: function (data) {
				data = JSON.parse(data);
				console.log(data);

				var rows = data.rows;

				layer.closeAll();

				var sub = '';
				for (var i in rows) {
					if (rows.hasOwnProperty(i)){
						sub += temp.temp_icon_list.format({
							iconClass: rows[i].iconClass,
							iconName: rows[i].iconName,
							id: rows[i].id,
							row: Number(i) + 1,
							opt: function () {
								var btn = '';
								var row = rows[i];
								btn += '<my:permissions code="icon_edit" name="修改"> <a onclick="updIcon(\'{id}\',this)" class="linka"><i class="iconfont icon-xiugai icon-xiugai1"> </i>修改</a> </my:permissions>'.format({id: row.id});
								btn += ' <my:permissions code="icon_delete" name="删除"> <a onclick="delIcon(\'{id}\',this)" class="linka linka2"> <i class="iconfont icon-ttpodicon icon-ttpodicon1"> </i>删除 </a> </my:permissions>'.format({id: row.id});
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
	function addIcon() {

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			content: cg_vte.service_href + 'system/icon/icon-add',
			btn: ['提交','关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {

				layer.getChildFrame('#submit', index).click()
			},
			btn2: function (layero, index) {

				initIcon()

			}
		});

	}

	//修改图标
	function updIcon(id) {

		layer.open({
			type: 2,
			title: '',
			closeBtn: 0,
			maxmin: false, //开启最大化最小化按钮
			area: ['100%', '100%'],
			content: cg_vte.path + 'iconController.do?goHref&href=system/icon/icon-upd&id=' + id,
			btn: ['提交','关闭'],
			fix: false,
			scrollbar: false,
			yes: function (index, layero) {

				layer.getChildFrame('#submit', index).click()
			},
			btn2: function (layero, index) {

				initIcon()

			}
		});

	}

	//删除图标
	function delIcon(id, item) {

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
					f: 913001,
					id: id
				},
				success: function(data){
					data = JSON.parse(data);
					callbackDel( data.status, data.msg, item);
				},
				error: function (res) {
					layer.msg('删除失败，该图标可能已经被使用了。');
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

	//添加ICON成功的回调
	function completeCallback() {
		layer.closeAll();
		layer.msg('添加成功');
		initIcon();
	}

</script>

</body>
</html>
