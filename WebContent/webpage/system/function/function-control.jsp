<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>菜单控件管理</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">


	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>

		.navbar-top-links li a {
			padding: 15px 10px;
		}
		.ibox{
			margin-bottom: 0;
		}

		#page-wrapper .datagrid-btable tr{height: 40px;}
	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {};
		var this_data = {
			function: {
				id: '${function.id}'
			},
			control: {

			}
		}
	</script>
</head>

<body>

<div class="gray-bg">


	<div class="gray-bg dashbard-1">

		<div class="ibox float-e-margins">


			<div class="ibox-title">
				<h5>控件列表</h5>
				<div class="ibox-tools">

					<a onclick="location.reload()">
						<i class="fa fa-refresh"></i>
					</a>
					<a onclick="window.parent.loadFunction();window.parent.layer.closeAll()">
						<i class="fa fa-times"></i>
					</a>
				</div>
			</div>


			<div class="ibox-content">

				<table class="table table-bordered table-hover" id="function_control_list">
					<thead>
					<tr>
						<td class="sort" data-property="controlName">
							控件名称
							<i class="iconfont icon-paixu"></i>
						</td>
						<td class="sort" data-property="controlCode">
							控件代码
							<i class="iconfont icon-paixu"></i>
						</td>
						<td>
							操作
						</td>
					</tr>
					</thead>

				</table>
			</div>

			<div>
				<input type="text" name="controlName" id="controlName" placeholder="controlName" onchange="this_data.controlName = this.value"><br>
				<input type="text" name="controlName" id="controlCode" placeholder="controlCode" onchange="this_data.controlCode = this.value"><br>
				<input type="button" value="submit" onclick="addControl()">
				<input type="button" value="修改提交" onclick="editControlSubmit()">
			</div>


		</div>


	</div>

</div>

<div id="temp_control_list">
	<!-- <tr>
		<td>{controlName}</td>
		<td>{controlCode}</td>
		<td>{opt}</td>
	</tr>-->
</div>

<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script>


	var temp = {}

	$(document).ready(function ()
	{

		temp.temp_control_list = toComment( $('#temp_control_list').html() );
		$dom.function_control_list = $('#function_control_list');

		initControl()

	});

	function initControl() {
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 950001,
				id: this_data.function.id
			},
			success: function (data) {
				data = JSON.parse(data);
				console.log(data);

				$dom.function_control_list.find('tbody').remove()

				var $tbody = $('<tbody>');
				for (var i in data){
					if (data.hasOwnProperty(i)){
						var sub = temp.temp_control_list.format({
							controlName: data[i].controlName,
							controlCode: data[i].controlCode,
							opt: function () {
								var btn = '';
								btn += ' <a onclick="editControl(this)">修改</a>';
								btn += ' <a onclick="deleteControl(this)">删除</a>';
								return btn;
							}
						});

						$tbody.append( $(sub).data('tr_data', data[i]) );
					}
				}

				$dom.function_control_list.append( $tbody );
			}
		})
	}

	//------------------ 添加菜单控件 Start -------------------/
	function addControl() {
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 951001,
				functionId: this_data.function.id,
				controlName: this_data.controlName,
				controlCode: this_data.controlCode,
			},
			success:function (data) {
				data = JSON.parse(data);
				console.log(data);

				if (data.status == 10000){
					swal({
						title: "提示",
						text: "success.",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonColor: "#1AB394"
					}, function() {

						location.reload();


					});

				}

			}
		})
	}
	//------------------ 添加菜单控件 End -------------------/
	function editControl(dom) {
		var tr_data = $(dom).parents('tr').data('tr_data');

		$('#controlName').val( tr_data.controlName );
		$('#controlCode').val( tr_data.controlCode );

		this_data.control.id = tr_data.id

	}
	
	function editControlSubmit() {

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 952001,
				id: this_data.control.id,
				controlName: this_data.controlName,
				controlCode: this_data.controlCode,
			},
			success:function (data) {
				data = JSON.parse(data);
				console.log(data);

				if (data.status == 10000){
					swal({
						title: "提示",
						text: "success.",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonColor: "#1AB394"
					}, function() {

						location.reload();


					});

				}

			}
		})

	}
	function deleteControl(dom) {
		var $this = $(dom).parents('tr');
		var tr_data = $this.data('tr_data');
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 953001,
				id: tr_data.id,
			},
			success:function (data) {
				data = JSON.parse(data);
				console.log(data);
				if (data.status == 10000){
					swal({
						title: "提示",
						text: "success.",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonColor: "#1AB394"
					}, function() {

						$this.remove()
						swal.close();

					});

				}
			}
		})
	}



</script>
</body>
</html>

