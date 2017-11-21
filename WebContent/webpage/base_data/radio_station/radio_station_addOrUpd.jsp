<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/7/10
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>添加无线电台</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
</head>

<body>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12 animated fadeInRight">
			<div class="row">
				<div class="col-sm-12">


					<form method="post" class="form-horizontal" name="form1" id="form1">

						<input type="hidden" name="f" id="f" value="4142001">


						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="pac">行政代码</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="pac" id="pac" value="" placeholder="行政代码">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="id">id</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="id" id="id" value="" placeholder="id">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="name">名称</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="name" id="name" value="" placeholder="名称">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="manager">管理员</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="manager" id="manager" value="" placeholder="管理员">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="phone">管理员电话</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="phone" id="phone" value="" placeholder="管理员电话">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="longitude">径度</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="longitude" id="longitude" value="" placeholder="径度">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="latitude">纬度</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="latitude" id="latitude" value="" placeholder="纬度">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="elevation">海拔</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="elevation" id="elevation" value="" placeholder="海拔">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="units">使用单位</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="units" id="units" value="" placeholder="使用单位">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="num_radio">电台呼号</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="num_radio" id="num_radio" value="" placeholder="电台呼号">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="radioname">电台名称</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="radioname" id="radioname" value="" placeholder="电台名称">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="type">电台类型</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="type" id="type" value="" placeholder="电台类型">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="coding">	电台编号</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="coding" id="coding" value="" placeholder="电台编号">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="r_frequenc">接收频率</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="r_frequenc" id="r_frequenc" value="" placeholder="接收频率">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="l_frequenc">发射频率</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="l_frequenc" id="l_frequenc" value="" placeholder="发射频率">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="power">发射功率</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="power" id="power" value="" placeholder="发射功率">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="power">天线高度</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="height" id="height" value="" placeholder="天线高度">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="shape">形状</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="shape" id="shape" value="" placeholder="形状">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="status">状态</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="status" id="status" value="" placeholder="状态">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="d_type">	类型</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="d_type" id="d_type" value="" placeholder=" 类型">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="note">说明</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="note" id="note" value="" placeholder="说明">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="build_year">建站时间</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="build_year" id="build_year" value="" placeholder="建站时间">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div id="imgs_box">

						</div>

						<button class="btn btn-primary" type="submit" id="submit" style="display: none;">保存内容</button>
					</form>

				</div>
			</div>
		</div>
	</div>
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script>

	var $dom = {};
	$(document).ready(function() {


		$dom.imgs_box = $('#imgs_box');

		setFormValue(validation_());

	});

	function validation_(){

		$("form#form1").validate({
			rules: {
				pac: {
					required: true
				},
				pac: {
					required: true
				},
				name: {
					required: true
				},
				num_radio:{
					required: true
				},
				radioname:{
					required: true
				},
			},

			messages: {
				id: {
					required: '代码不得为空.'
				},
				pac: {
					required: '行政区号代码不得为空.'
				},
				name: {
					required: 'name不得为空.'
				},
				num_radio:{
					required: 'num_radio不得为空.'
				},
				radioname:{
					required: 'radioname不得为空.'
				},

			},

			// Form Processing via AJAX
			submitHandler: function(form)
			{
				_submit(form);

			}
		});


	}
	function setFormValue(callback) {

		var remotemonitoring = window.sessionStorage.getItem('data_tr');
		remotemonitoring = JSON.parse(remotemonitoring);

		console.log(remotemonitoring)


		$('#id').val(remotemonitoring.id);
		$('#name').val(remotemonitoring.name);
		$('#pac').val(remotemonitoring.pac);
		$('#manager').val(remotemonitoring.manager);
		$('#phone').val(remotemonitoring.phone);
		$('#longitude').val(remotemonitoring.longitude);
		$('#latitude').val(remotemonitoring.latitude);
		$('#elevation').val(remotemonitoring.elevation);
		$('#units').val(remotemonitoring.units);
		$('#num_radio').val(remotemonitoring.num_radio);
		$('#radioname').val(remotemonitoring.radioname);
		$('#type').val(remotemonitoring.type);
		$('#coding').val(remotemonitoring.coding);
		$('#r_frequenc').val(remotemonitoring.r_frequenc);
		$('#l_frequenc').val(remotemonitoring.l_frequenc);
		$('#power').val(remotemonitoring.power);
		$('#height').val(remotemonitoring.height);
		$('#shape').val(remotemonitoring.shape);
		$('#status').val(remotemonitoring.status);
		$('#d_type').val(remotemonitoring.d_type);
		$('#note').val(remotemonitoring.note);
		$('#build_year').val(remotemonitoring.build_year);

		var mediaFiles = remotemonitoring.mediaFiles;
		for(var i in mediaFiles){
			if (mediaFiles.hasOwnProperty(i)){
				$dom.imgs_box.append('<img src="${pageContext.request.contextPath}/'+mediaFiles[i].fileUrl+'">')
			}
		}

		try{
			callback()
		} catch (e){

		}
	}
	function _submit(form){

		var number = $('#number').val();

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: $(form).serialize(),
			success: function (data) {
				data = JSON.parse(data);
				console.log(data);
				if (data.status == 10000){
					swal({
						title: "提示",
						text: "success",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonText: "OK",
						confirmButtonColor: "#1AB394"
					}, function() {
						window.parent.completeCallback();
					});
				} else {
					layer.msg(data.msg);
				}
			}
		})
	}



</script>
</body>
</html>